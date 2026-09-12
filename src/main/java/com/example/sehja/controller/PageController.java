package com.example.sehja.controller;

import com.example.sehja.model.Driver;
import com.example.sehja.model.News;
import com.example.sehja.model.Race;
import com.example.sehja.model.Team;
import com.example.sehja.repository.DriverRepository;
import com.example.sehja.repository.NewsRepository;
import com.example.sehja.repository.RaceRepository;
import com.example.sehja.repository.TeamRepository;
import com.example.sehja.repository.VideoRepository;
import com.example.sehja.service.HistoricalStandingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private static final int CURRENT_YEAR = 2026;

    private final NewsRepository newsRepo;
    private final DriverRepository driverRepo;
    private final RaceRepository raceRepo;
    private final VideoRepository videoRepo;
    private final TeamRepository teamRepo;
    private final HistoricalStandingsService historical;

    public PageController(NewsRepository newsRepo, DriverRepository driverRepo, RaceRepository raceRepo,
                          VideoRepository videoRepo, TeamRepository teamRepo,
                          HistoricalStandingsService historical) {
        this.newsRepo = newsRepo;
        this.driverRepo = driverRepo;
        this.raceRepo = raceRepo;
        this.videoRepo = videoRepo;
        this.teamRepo = teamRepo;
        this.historical = historical;
    }

    private static List<Integer> seasonYears() {
        return IntStream.rangeClosed(1950, CURRENT_YEAR)
            .boxed()
            .sorted((a, b) -> b - a)
            .toList();
    }

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/season")
    public String season() { return "forward:/season.html"; }

    @GetMapping("/predict")
    public String predict() { return "forward:/predict.html"; }


    private List<com.example.sehja.model.Driver> applyCurrentPhotos(List<com.example.sehja.model.Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) return drivers;
        Map<String, com.example.sehja.model.Driver> bySlug = new HashMap<>();
        for (com.example.sehja.model.Driver cd : driverRepo.findAll()) {
            if (cd.getDriverSlug() != null && cd.getPhotoUrl() != null) {
                bySlug.put(cd.getDriverSlug(), cd);
            }
        }
        for (com.example.sehja.model.Driver d : drivers) {
            if (d.getDriverSlug() != null) {
                com.example.sehja.model.Driver match = bySlug.get(d.getDriverSlug());
                if (match != null) d.setPhotoUrl(match.getPhotoUrl());
            }
        }
        return drivers;
    }

    @GetMapping("/standings")
    public String standings(@RequestParam(name = "year", required = false) Integer year, Model model) {
        int effectiveYear = (year == null) ? CURRENT_YEAR : year;
        List<com.example.sehja.model.Driver> drivers = (effectiveYear == CURRENT_YEAR)
            ? driverRepo.findAllByOrderByPositionAsc()
            : historical.driverStandings(effectiveYear);
        if (effectiveYear != CURRENT_YEAR) drivers = applyCurrentPhotos(drivers);
        Map<String, Long> currentDriverIdBySlug = new HashMap<>();
        for (com.example.sehja.model.Driver d : driverRepo.findAll()) {
            if (d.getDriverSlug() != null) currentDriverIdBySlug.put(d.getDriverSlug(), d.getId());
        }
        model.addAttribute("drivers", drivers);
        model.addAttribute("selectedYear", effectiveYear);
        model.addAttribute("isCurrent", effectiveYear == CURRENT_YEAR);
        model.addAttribute("years", seasonYears());
        model.addAttribute("currentDriverIdBySlug", currentDriverIdBySlug);
        return "standings";
    }

    @GetMapping("/calendar")
    public String calendar() { return "calendar"; }

    @GetMapping("/news")
    public String news() { return "news"; }

    @GetMapping("/videos")
    public String videos(Model model) {
        model.addAttribute("videos", videoRepo.findAllByOrderBySortOrderAsc());
        return "videos";
    }

    @GetMapping("/constructors")
    public String constructors(@RequestParam(name = "year", required = false) Integer year, Model model) {
        int effectiveYear = (year == null) ? CURRENT_YEAR : year;
        List<com.example.sehja.model.Team> teams = (effectiveYear == CURRENT_YEAR)
            ? teamRepo.findAllByOrderByPositionAsc()
            : historical.constructorStandings(effectiveYear);
        Map<String, Long> currentTeamIdBySlug = new HashMap<>();
        for (com.example.sehja.model.Team t : teamRepo.findAll()) {
            if (t.getConstructorSlug() != null) currentTeamIdBySlug.put(t.getConstructorSlug(), t.getId());
        }
        model.addAttribute("teams", teams);
        model.addAttribute("selectedYear", effectiveYear);
        model.addAttribute("isCurrent", effectiveYear == CURRENT_YEAR);
        model.addAttribute("years", seasonYears());
        model.addAttribute("currentTeamIdBySlug", currentTeamIdBySlug);
        return "constructors";
    }

    @GetMapping("/team/{id}")
    public String teamDetail(@PathVariable Long id, Model model) {
        Team team = teamRepo.findById(id).orElse(null);
        if (team == null) {
            return "team-detail";
        }
        List<Driver> lineup = driverRepo.findByTeamOrderByPositionAsc(team.getName());
        model.addAttribute("team", team);
        model.addAttribute("lineup", lineup);
        return "team-detail";
    }

    @GetMapping("/race/{id}")
    public String raceDetail(@PathVariable Long id, Model model) {
        Race race = raceRepo.findById(id).orElse(null);
        if (race == null) {
            return "race-detail";
        }
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        boolean isPast = race.getDate() != null && race.getDate().isBefore(LocalDate.now());
        model.addAttribute("race", race);
        model.addAttribute("results", race.getResults());
        model.addAttribute("dateStr", race.getDate() != null ? race.getDate().format(dateFmt) : "");
        model.addAttribute("isPast", isPast);
        model.addAttribute("totalDistance",
            race.getLaps() != null && race.getCircuitLengthKm() != null
                ? String.format("%.1f km", race.getLaps() * race.getCircuitLengthKm())
                : null);
        return "race-detail";
    }

    @GetMapping("/news/{id}")
    public String newsDetail(@PathVariable Long id, Model model) {
        News article = newsRepo.findById(id).orElse(null);
        if (article == null) {
            return "news-detail";
        }
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
        model.addAttribute("article", article);
        model.addAttribute("dateStr", article.getPublishedDate().format(dateFmt));
        model.addAttribute("timeStr", article.getPublishedDate().format(timeFmt));
        return "news-detail";
    }

    @GetMapping("/driver/historic/{year}/{slug}")
    public String historicDriverDetail(@PathVariable Integer year, @PathVariable String slug, Model model) {
        var driverOpt = historical.driverForYear(year, slug);
        if (driverOpt.isEmpty()) {
            model.addAttribute("historic", true);
            model.addAttribute("selectedYear", year);
            return "driver-detail";
        }
        com.example.sehja.model.Driver driver = driverOpt.get();
        var career = historical.driverCareer(slug);
        if (driver.getChampionships() == null) driver.setChampionships(career.championships());
        if (driver.getCareerWins() == null) driver.setCareerWins(career.careerWins());
        if (driver.getCareerPodiums() == null) driver.setCareerPodiums(career.careerPodiums());
        if (driver.getGpEntered() == null) driver.setGpEntered(career.gpEntered());
        DateTimeFormatter dobFmt = DateTimeFormatter.ofPattern("d MMMM yyyy");
        String dobStr = driver.getDateOfBirth() == null
            ? null : driver.getDateOfBirth().format(dobFmt);
        Integer age = driver.getDateOfBirth() == null
            ? null : Period.between(driver.getDateOfBirth(), LocalDate.now()).getYears();
        String[] nameParts = driver.getName().split(" ", 2);
        String firstName = nameParts.length > 0 ? nameParts[0] : driver.getName();
        String lastName = nameParts.length > 1 ? nameParts[1] : "";
        model.addAttribute("driver", driver);
        model.addAttribute("dobStr", dobStr);
        model.addAttribute("age", age);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("historic", true);
        model.addAttribute("selectedYear", year);
        return "driver-detail";
    }

    @GetMapping("/team/historic/{year}/{slug}")
    public String historicTeamDetail(@PathVariable Integer year, @PathVariable String slug, Model model) {
        var teamOpt = historical.teamForYear(year, slug);
        if (teamOpt.isEmpty()) {
            model.addAttribute("historic", true);
            model.addAttribute("selectedYear", year);
            return "team-detail";
        }
        com.example.sehja.model.Team team = teamOpt.get();
        // If this team is still on the current grid, enrich with the rich profile data
        // (logo, car image, full name, history, base, engine, principal, titles) — but keep
        // the year-specific position/points/wins from the historical fetch.
        com.example.sehja.model.Team current = teamRepo.findByConstructorSlug(slug);
        if (current != null) {
            if (team.getLogoUrl() == null) team.setLogoUrl(current.getLogoUrl());
            if (team.getCarImageUrl() == null) team.setCarImageUrl(current.getCarImageUrl());
            if (team.getFullName() == null || team.getFullName().equals(team.getName())) {
                team.setFullName(current.getFullName());
            }
            if (current.getBase() != null) team.setBase(current.getBase());
            if (current.getEngine() != null) team.setEngine(current.getEngine());
            if (current.getPrincipal() != null) team.setPrincipal(current.getPrincipal());
            if (current.getFounded() != null) team.setFounded(current.getFounded());
            if (current.getConstructorTitles() != null) team.setConstructorTitles(current.getConstructorTitles());
            if (current.getDriverTitles() != null) team.setDriverTitles(current.getDriverTitles());
            if (current.getTotalWins() != null) team.setTotalWins(current.getTotalWins());
            if (team.getHistory() == null) team.setHistory(current.getHistory());
        }
        List<com.example.sehja.model.Driver> lineup = applyCurrentPhotos(historical.driversForTeamInYear(year, slug));
        model.addAttribute("team", team);
        model.addAttribute("lineup", lineup);
        model.addAttribute("historic", true);
        model.addAttribute("selectedYear", year);
        return "team-detail";
    }

    @GetMapping("/driver/{id}")
    public String driverDetail(@PathVariable Long id, Model model) {
        Driver driver = driverRepo.findById(id).orElse(null);
        if (driver == null) {
            return "driver-detail";
        }
        DateTimeFormatter dobFmt = DateTimeFormatter.ofPattern("d MMMM yyyy");
        String dobStr = driver.getDateOfBirth() == null
            ? null : driver.getDateOfBirth().format(dobFmt);
        Integer age = driver.getDateOfBirth() == null
            ? null : Period.between(driver.getDateOfBirth(), LocalDate.now()).getYears();

        String[] nameParts = driver.getName().split(" ", 2);
        String firstName = nameParts.length > 0 ? nameParts[0] : driver.getName();
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        model.addAttribute("driver", driver);
        model.addAttribute("dobStr", dobStr);
        model.addAttribute("age", age);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        return "driver-detail";
    }
}
