package com.example.sehja.service;

import com.example.sehja.config.TeamColors;
import com.example.sehja.model.Driver;
import com.example.sehja.model.Team;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class HistoricalStandingsService {

    private static final String ERGAST_BASE = "https://api.jolpi.ca/ergast/f1/";
    private static final String WIKI_SUMMARY = "https://en.wikipedia.org/api/rest_v1/page/summary/";
    private static final String UA = "F1Hub/1.0 (sehja@gmail.com)";

    public record WikiSummary(String title, String thumbnail, String extract, String pageUrl) {
        public static final WikiSummary EMPTY = new WikiSummary(null, null, null, null);
    }

    public record DriverCareer(int championships, int careerWins, int careerPodiums, int gpEntered) {
        public static final DriverCareer EMPTY = new DriverCareer(0, 0, 0, 0);
    }

    private final HttpClient http = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();
    private final ObjectMapper json = new ObjectMapper();

    private final ConcurrentMap<Integer, List<Driver>> driverCache = new ConcurrentHashMap<>();
    private final ConcurrentMap<Integer, List<Team>> teamCache = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, WikiSummary> wikiCache = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, DriverCareer> careerCache = new ConcurrentHashMap<>();
    private final ConcurrentMap<Integer, String> championByYear = new ConcurrentHashMap<>();

    public List<Driver> driverStandings(int year) {
        return driverCache.computeIfAbsent(year, this::fetchDriverStandings);
    }

    public List<Team> constructorStandings(int year) {
        return teamCache.computeIfAbsent(year, this::fetchConstructorStandings);
    }

    public DriverCareer driverCareer(String slug) {
        if (slug == null || slug.isBlank()) return DriverCareer.EMPTY;
        return careerCache.computeIfAbsent(slug, this::fetchDriverCareer);
    }

    private DriverCareer fetchDriverCareer(String slug) {
        try {
            int careerWins = 0;
            try {
                JsonNode winsRoot = ergast("drivers/" + slug + "/results/1.json?limit=100");
                careerWins = winsRoot.path("MRData").path("total").asInt(0);
            } catch (Exception ignored) {}

            int podiums = 0;
            int gpEntered = 0;
            int offset = 0;
            int limit = 100;
            int safety = 0;
            while (safety++ < 12) {
                JsonNode resultsRoot = ergast("drivers/" + slug + "/results.json?limit=" + limit + "&offset=" + offset);
                JsonNode races = resultsRoot.path("MRData").path("RaceTable").path("Races");
                if (!races.isArray() || races.size() == 0) break;
                for (JsonNode race : races) {
                    gpEntered++;
                    JsonNode results = race.path("Results");
                    if (results.size() > 0) {
                        int pos = results.get(0).path("position").asInt(99);
                        if (pos >= 1 && pos <= 3) podiums++;
                    }
                }
                int total = resultsRoot.path("MRData").path("total").asInt(0);
                offset += limit;
                if (offset >= total) break;
            }

            int championships = countChampionships(slug);

            return new DriverCareer(championships, careerWins, podiums, gpEntered);
        } catch (Exception e) {
            return DriverCareer.EMPTY;
        }
    }

    private int countChampionships(String slug) {
        try {
            JsonNode seasonsRoot = ergast("drivers/" + slug + "/seasons.json?limit=100");
            JsonNode seasons = seasonsRoot.path("MRData").path("SeasonTable").path("Seasons");
            if (!seasons.isArray() || seasons.size() == 0) return 0;

            List<Integer> yearsToFetch = new ArrayList<>();
            int known = 0;
            for (JsonNode s : seasons) {
                int year = s.path("season").asInt();
                String cached = championByYear.get(year);
                if (cached != null) {
                    if (slug.equals(cached)) known++;
                } else {
                    yearsToFetch.add(year);
                }
            }
            if (yearsToFetch.isEmpty()) return known;

            List<CompletableFuture<Boolean>> futures = yearsToFetch.stream()
                .map(year -> CompletableFuture.supplyAsync(() -> {
                    try {
                        JsonNode r = ergast(year + "/driverstandings/1.json");
                        JsonNode lists = r.path("MRData").path("StandingsTable").path("StandingsLists");
                        if (lists.size() == 0) return false;
                        JsonNode ds = lists.get(0).path("DriverStandings");
                        if (ds.size() == 0) return false;
                        String champSlug = ds.get(0).path("Driver").path("driverId").asText(null);
                        if (champSlug != null) championByYear.put(year, champSlug);
                        return slug.equals(champSlug);
                    } catch (Exception e) {
                        return false;
                    }
                }))
                .toList();

            int extra = 0;
            for (CompletableFuture<Boolean> f : futures) {
                try {
                    if (Boolean.TRUE.equals(f.get(15, java.util.concurrent.TimeUnit.SECONDS))) extra++;
                } catch (Exception ignored) {}
            }
            return known + extra;
        } catch (Exception e) {
            return 0;
        }
    }

    public Optional<Driver> driverForYear(int year, String slug) {
        return driverStandings(year).stream()
            .filter(d -> slug.equalsIgnoreCase(d.getDriverSlug()))
            .findFirst()
            .map(d -> {
                WikiSummary w = wikipediaSummary(wikiTitleFromUrl(d.getWikipediaUrl()));
                d.setWikipediaExtract(w.extract());
                if (d.getBio() == null) d.setBio(w.extract());
                if (d.getPhotoUrl() == null) d.setPhotoUrl(w.thumbnail());
                return d;
            });
    }

    public Optional<Team> teamForYear(int year, String slug) {
        return constructorStandings(year).stream()
            .filter(t -> slug.equalsIgnoreCase(t.getConstructorSlug()))
            .findFirst()
            .map(t -> {
                WikiSummary w = wikipediaSummary(wikiTitleFromUrl(t.getWikipediaUrl()));
                t.setHistory(w.extract());
                if (t.getLogoUrl() == null) t.setLogoUrl(w.thumbnail());
                return t;
            });
    }

    public List<Driver> driversForTeamInYear(int year, String constructorSlug) {
        return driverStandings(year).stream()
            .filter(d -> {
                Team team = constructorStandings(year).stream()
                    .filter(t -> constructorSlug.equalsIgnoreCase(t.getConstructorSlug()))
                    .findFirst()
                    .orElse(null);
                return team != null && team.getName().equals(d.getTeam());
            })
            .toList();
    }

    private List<Driver> fetchDriverStandings(int year) {
        try {
            JsonNode root = ergast(year + "/driverstandings.json?limit=60");
            JsonNode lists = root.path("MRData").path("StandingsTable").path("StandingsLists");
            if (!lists.isArray() || lists.size() == 0) return List.of();
            JsonNode standings = lists.get(0).path("DriverStandings");

            List<Driver> out = new ArrayList<>();
            for (JsonNode n : standings) {
                Driver d = new Driver();
                d.setPosition(n.path("position").asInt());
                d.setPoints((int) Math.round(n.path("points").asDouble()));
                String first = n.path("Driver").path("givenName").asText();
                String last = n.path("Driver").path("familyName").asText();
                d.setName((first + " " + last).trim());
                d.setNationality(n.path("Driver").path("nationality").asText());
                d.setDriverSlug(n.path("Driver").path("driverId").asText(null));
                d.setWikipediaUrl(n.path("Driver").path("url").asText(null));
                String dob = n.path("Driver").path("dateOfBirth").asText(null);
                if (dob != null && !dob.isBlank()) {
                    try { d.setDateOfBirth(java.time.LocalDate.parse(dob)); } catch (Exception ignored) {}
                }
                JsonNode constructors = n.path("Constructors");
                if (constructors.isArray() && constructors.size() > 0) {
                    d.setTeam(constructors.get(0).path("name").asText());
                }
                String[] colors = TeamColors.forTeam(d.getTeam());
                d.setTeamColor(colors[0]);
                d.setTeamColorAccent(colors[1]);
                out.add(d);
            }

            // Parallel Wikipedia thumbnail enrichment
            List<CompletableFuture<Void>> futures = out.stream()
                .map(d -> CompletableFuture.runAsync(() -> {
                    WikiSummary w = wikipediaSummary(wikiTitleFromUrl(d.getWikipediaUrl()));
                    if (w.thumbnail() != null) d.setPhotoUrl(w.thumbnail());
                }))
                .toList();
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .orTimeout(15, java.util.concurrent.TimeUnit.SECONDS)
                .exceptionally(e -> null)
                .join();

            return out;
        } catch (Exception e) {
            return List.of();
        }
    }

    private List<Team> fetchConstructorStandings(int year) {
        try {
            JsonNode root = ergast(year + "/constructorstandings.json?limit=30");
            JsonNode lists = root.path("MRData").path("StandingsTable").path("StandingsLists");
            if (!lists.isArray() || lists.size() == 0) return List.of();
            JsonNode standings = lists.get(0).path("ConstructorStandings");
            List<Team> out = new ArrayList<>();
            for (JsonNode n : standings) {
                String name = n.path("Constructor").path("name").asText();
                String nationality = n.path("Constructor").path("nationality").asText();
                String constructorId = n.path("Constructor").path("constructorId").asText(null);
                String wikiUrl = n.path("Constructor").path("url").asText(null);
                int position = n.path("position").asInt();
                int points = (int) Math.round(n.path("points").asDouble());
                int wins = n.path("wins").asInt();
                String[] colors = TeamColors.forTeam(name);
                Team t = new Team(name, name, position, points, wins, nationality,
                    null, null, colors[0], colors[1]);
                t.setConstructorSlug(constructorId);
                t.setWikipediaUrl(wikiUrl);
                out.add(t);
            }
            return out;
        } catch (Exception e) {
            return List.of();
        }
    }

    private JsonNode ergast(String path) throws Exception {
        HttpRequest req = HttpRequest.newBuilder(URI.create(ERGAST_BASE + path))
            .timeout(Duration.ofSeconds(15))
            .header("User-Agent", UA)
            .GET()
            .build();
        HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
        return json.readTree(resp.body());
    }

    private String wikiTitleFromUrl(String url) {
        if (url == null || url.isBlank()) return null;
        int slash = url.lastIndexOf('/');
        if (slash < 0) return null;
        String raw = url.substring(slash + 1);
        try { return URLDecoder.decode(raw, StandardCharsets.UTF_8); }
        catch (Exception e) { return raw; }
    }

    private WikiSummary wikipediaSummary(String title) {
        if (title == null || title.isBlank()) return WikiSummary.EMPTY;
        WikiSummary cached = wikiCache.get(title);
        if (cached != null) return cached;
        try {
            String encoded = URLEncoder.encode(title, StandardCharsets.UTF_8).replace("+", "%20");
            HttpRequest req = HttpRequest.newBuilder(URI.create(WIKI_SUMMARY + encoded))
                .timeout(Duration.ofSeconds(6))
                .header("User-Agent", UA)
                .GET()
                .build();
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() != 200) {
                wikiCache.put(title, WikiSummary.EMPTY);
                return WikiSummary.EMPTY;
            }
            JsonNode root = json.readTree(resp.body());
            String thumb = root.path("thumbnail").path("source").asText(null);
            String extract = root.path("extract").asText(null);
            String pageUrl = root.path("content_urls").path("desktop").path("page").asText(null);
            WikiSummary w = new WikiSummary(title, thumb, extract, pageUrl);
            wikiCache.put(title, w);
            return w;
        } catch (Exception e) {
            return WikiSummary.EMPTY;
        }
    }
}
