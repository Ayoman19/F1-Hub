package com.example.sehja.config;

import com.example.sehja.model.Driver;
import com.example.sehja.model.News;
import com.example.sehja.model.Race;
import com.example.sehja.model.RaceResult;
import com.example.sehja.model.Video;
import com.example.sehja.repository.DriverRepository;
import com.example.sehja.repository.NewsRepository;
import com.example.sehja.repository.RaceRepository;
import com.example.sehja.repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Map<String, String[]> TEAM_COLORS = Map.ofEntries(
        Map.entry("Mercedes",     new String[]{"#00D2BE", "#C8C8C8"}),
        Map.entry("Ferrari",      new String[]{"#DC0000", "#FFF200"}),
        Map.entry("McLaren",      new String[]{"#FF8000", "#47C7FC"}),
        Map.entry("Red Bull",     new String[]{"#3671C6", "#DC0000"}),
        Map.entry("Williams",     new String[]{"#64C4FF", "#1A1A1A"}),
        Map.entry("Aston Martin", new String[]{"#229971", "#BFD12B"}),
        Map.entry("RB",           new String[]{"#6692FF", "#FF1801"}),
        Map.entry("Alpine",       new String[]{"#00A1E8", "#FF87BC"}),
        Map.entry("Haas",         new String[]{"#B6BABD", "#DC0000"}),
        Map.entry("Audi",         new String[]{"#00B4B8", "#000000"}),
        Map.entry("Cadillac",     new String[]{"#003554", "#C9A961"})
    );

    private final DriverRepository driverRepo;
    private final RaceRepository raceRepo;
    private final NewsRepository newsRepo;
    private final VideoRepository videoRepo;

    public DataInitializer(DriverRepository driverRepo,
                           RaceRepository raceRepo,
                           NewsRepository newsRepo,
                           VideoRepository videoRepo) {
        this.driverRepo = driverRepo;
        this.raceRepo = raceRepo;
        this.newsRepo = newsRepo;
        this.videoRepo = videoRepo;
    }

    @Override
    public void run(String... args) {
        if (driverRepo.count() == 0) seedDrivers();
        if (raceRepo.count() == 0) seedRaces();
        if (newsRepo.count() == 0) seedNews();
        if (videoRepo.count() == 0) seedVideos();
    }

    private Driver d(String name, String team, int points, int position,
                     String nationality, String countryCode, int raceNumber,
                     LocalDate dob, String placeOfBirth,
                     int careerWins, int careerPodiums, int championships, int gpEntered,
                     String previousTeams, String bio) {
        Driver dr = new Driver(name, team, points, position);
        dr.setNationality(nationality);
        dr.setCountryCode(countryCode);
        dr.setRaceNumber(raceNumber);
        dr.setDateOfBirth(dob);
        dr.setPlaceOfBirth(placeOfBirth);
        dr.setCareerWins(careerWins);
        dr.setCareerPodiums(careerPodiums);
        dr.setChampionships(championships);
        dr.setGpEntered(gpEntered);
        dr.setPreviousTeams(previousTeams);
        dr.setBio(bio);
        String[] colors = TEAM_COLORS.getOrDefault(team, new String[]{"#e10600", "#ffffff"});
        dr.setTeamColor(colors[0]);
        dr.setTeamColorAccent(colors[1]);
        dr.setPhotoUrl(DRIVER_PHOTOS.get(name));
        return dr;
    }

    private static final String F1_DRIVER_BASE = "https://media.formula1.com/image/upload/c_lfill,w_440/q_auto/v1740000001/common/f1/2026/";
    private static final String F1_TRACK_BASE = "https://media.formula1.com/image/upload/c_fit,h_704/q_auto/v1740000001/common/f1/2026/track/2026track";

    private static final Map<String, String> DRIVER_PHOTOS = Map.ofEntries(
        Map.entry("Andrea Kimi Antonelli", F1_DRIVER_BASE + "mercedes/andant01/2026mercedesandant01right.webp"),
        Map.entry("George Russell",        F1_DRIVER_BASE + "mercedes/georus01/2026mercedesgeorus01right.webp"),
        Map.entry("Charles Leclerc",       F1_DRIVER_BASE + "ferrari/chalec01/2026ferrarichalec01right.webp"),
        Map.entry("Lewis Hamilton",        F1_DRIVER_BASE + "ferrari/lewham01/2026ferrarilewham01right.webp"),
        Map.entry("Lando Norris",          F1_DRIVER_BASE + "mclaren/lannor01/2026mclarenlannor01right.webp"),
        Map.entry("Oscar Piastri",         F1_DRIVER_BASE + "mclaren/oscpia01/2026mclarenoscpia01right.webp"),
        Map.entry("Max Verstappen",        F1_DRIVER_BASE + "redbullracing/maxver01/2026redbullracingmaxver01right.webp"),
        Map.entry("Isack Hadjar",          F1_DRIVER_BASE + "redbullracing/isahad01/2026redbullracingisahad01right.webp"),
        Map.entry("Pierre Gasly",          F1_DRIVER_BASE + "alpine/piegas01/2026alpinepiegas01right.webp"),
        Map.entry("Franco Colapinto",      F1_DRIVER_BASE + "alpine/fracol01/2026alpinefracol01right.webp"),
        Map.entry("Esteban Ocon",          F1_DRIVER_BASE + "haasf1team/estoco01/2026haasf1teamestoco01right.webp"),
        Map.entry("Oliver Bearman",        F1_DRIVER_BASE + "haasf1team/olibea01/2026haasf1teamolibea01right.webp"),
        Map.entry("Liam Lawson",           F1_DRIVER_BASE + "racingbulls/lialaw01/2026racingbullslialaw01right.webp"),
        Map.entry("Arvid Lindblad",        F1_DRIVER_BASE + "racingbulls/arvlin01/2026racingbullsarvlin01right.webp"),
        Map.entry("Carlos Sainz",          F1_DRIVER_BASE + "williams/carsai01/2026williamscarsai01right.webp"),
        Map.entry("Alexander Albon",       F1_DRIVER_BASE + "williams/alealb01/2026williamsalealb01right.webp"),
        Map.entry("Nico Hülkenberg",       F1_DRIVER_BASE + "audi/nichul01/2026audinichul01right.webp"),
        Map.entry("Gabriel Bortoleto",     F1_DRIVER_BASE + "audi/gabbor01/2026audigabbor01right.webp"),
        Map.entry("Sergio Pérez",          F1_DRIVER_BASE + "cadillac/serper01/2026cadillacserper01right.webp"),
        Map.entry("Valtteri Bottas",       F1_DRIVER_BASE + "cadillac/valbot01/2026cadillacvalbot01right.webp"),
        Map.entry("Fernando Alonso",       F1_DRIVER_BASE + "astonmartin/feralo01/2026astonmartinferalo01right.webp"),
        Map.entry("Lance Stroll",          F1_DRIVER_BASE + "astonmartin/lanstr01/2026astonmartinlanstr01right.webp")
    );

    private static final Map<Integer, String> CIRCUIT_LAYOUTS = Map.ofEntries(
        Map.entry(1,  F1_TRACK_BASE + "melbournedetailed.webp"),
        Map.entry(2,  F1_TRACK_BASE + "shanghaidetailed.webp"),
        Map.entry(3,  F1_TRACK_BASE + "suzukadetailed.webp"),
        Map.entry(4,  F1_TRACK_BASE + "miamidetailed.webp"),
        Map.entry(5,  F1_TRACK_BASE + "montrealdetailed.webp"),
        Map.entry(6,  F1_TRACK_BASE + "montecarlodetailed.webp"),
        Map.entry(7,  F1_TRACK_BASE + "catalunyadetailed.webp"),
        Map.entry(8,  F1_TRACK_BASE + "spielbergdetailed.webp"),
        Map.entry(9,  F1_TRACK_BASE + "silverstonedetailed.webp"),
        Map.entry(10, F1_TRACK_BASE + "spafrancorchampsdetailed.webp"),
        Map.entry(11, F1_TRACK_BASE + "hungaroringdetailed.webp"),
        Map.entry(12, F1_TRACK_BASE + "zandvoortdetailed.webp"),
        Map.entry(13, F1_TRACK_BASE + "monzadetailed.webp"),
        Map.entry(14, F1_TRACK_BASE + "madringdetailed.webp"),
        Map.entry(15, F1_TRACK_BASE + "bakudetailed.webp"),
        Map.entry(16, F1_TRACK_BASE + "singaporedetailed.webp"),
        Map.entry(17, F1_TRACK_BASE + "austindetailed.webp"),
        Map.entry(18, F1_TRACK_BASE + "mexicocitydetailed.webp"),
        Map.entry(19, F1_TRACK_BASE + "interlagosdetailed.webp"),
        Map.entry(20, F1_TRACK_BASE + "lasvegasdetailed.webp"),
        Map.entry(21, F1_TRACK_BASE + "lusaildetailed.webp"),
        Map.entry(22, F1_TRACK_BASE + "yasmarinadetailed.webp")
    );

    private Race race(String name, String circuit, LocalDate date, String country,
                      int round, int laps, double lengthKm, String description) {
        Race r = new Race(name, circuit, date, country, round, laps, lengthKm, description);
        r.setLayoutUrl(CIRCUIT_LAYOUTS.get(round));
        return r;
    }

    private void seedDrivers() {
        driverRepo.save(d("Andrea Kimi Antonelli", "Mercedes", 100, 1,
            "Italian", "IT", 12, LocalDate.of(2006, 8, 25), "Bologna, Italy",
            1, 4, 0, 30,
            "Mercedes (2025–)",
            "The Bologna-born prodigy graduated straight from F2 into Mercedes' senior seat for 2025 as Lewis Hamilton's replacement. A breakthrough sophomore campaign has him leading the 2026 championship in his second F1 season."));

        driverRepo.save(d("George Russell", "Mercedes", 80, 2,
            "British", "GB", 63, LocalDate.of(1998, 2, 15), "King's Lynn, England",
            4, 22, 0, 145,
            "Williams (2019–2021), Mercedes (2022–)",
            "F2 and GP3 champion before reaching F1 with Williams. Promoted to Mercedes alongside Hamilton in 2022 and now the team's most experienced driver."));

        driverRepo.save(d("Charles Leclerc", "Ferrari", 59, 3,
            "Monégasque", "MC", 16, LocalDate.of(1997, 10, 16), "Monte Carlo, Monaco",
            9, 42, 0, 155,
            "Sauber (2018), Ferrari (2019–)",
            "F2 champion in 2017 and Ferrari's golden boy since 2019. A specialist in qualifying, with a long list of pole positions and a string of near-misses for the title."));

        driverRepo.save(d("Lando Norris", "McLaren", 51, 4,
            "British", "GB", 4, LocalDate.of(1999, 11, 13), "Bristol, England",
            10, 32, 0, 140,
            "McLaren (2019–)",
            "McLaren academy graduate who has stayed loyal to the team through their rebuild. Took his first F1 win in 2024 and has been a regular championship contender since."));

        driverRepo.save(d("Lewis Hamilton", "Ferrari", 51, 5,
            "British", "GB", 44, LocalDate.of(1985, 1, 7), "Stevenage, England",
            105, 202, 7, 365,
            "McLaren (2007–2012), Mercedes (2013–2024), Ferrari (2025–)",
            "A seven-time world champion, joint-record holder with Michael Schumacher. Made the headline switch from Mercedes to Ferrari for 2025 in pursuit of an eighth title."));

        driverRepo.save(d("Oscar Piastri", "McLaren", 43, 6,
            "Australian", "AU", 81, LocalDate.of(2001, 4, 6), "Melbourne, Australia",
            6, 16, 0, 75,
            "McLaren (2023–)",
            "Back-to-back F3 and F2 champion before debuting with McLaren in 2023. Quickly established himself as Norris' equal and a future title contender."));

        driverRepo.save(d("Max Verstappen", "Red Bull", 26, 7,
            "Dutch", "NL", 1, LocalDate.of(1997, 9, 30), "Hasselt, Belgium",
            65, 115, 4, 225,
            "Toro Rosso (2015–2016), Red Bull (2016–)",
            "Four-time consecutive world champion (2021–2024). Promoted to Red Bull mid-2016 and became the youngest race winner in F1 history at 18. Carries the #1 plate by default."));

        driverRepo.save(d("Oliver Bearman", "Haas", 17, 8,
            "British", "GB", 87, LocalDate.of(2005, 5, 8), "Chelmsford, England",
            0, 0, 0, 32,
            "Ferrari reserve / Haas stand-in (2024), Haas (2025–)",
            "Made his F1 debut as a Ferrari super-sub in Saudi Arabia 2024 with less than a day's notice and scored points. Promoted to a full-time Haas seat from 2025."));

        driverRepo.save(d("Pierre Gasly", "Alpine", 16, 9,
            "French", "FR", 10, LocalDate.of(1996, 2, 7), "Rouen, France",
            1, 4, 0, 170,
            "Toro Rosso (2017–2019), Red Bull (2019), AlphaTauri (2019–2022), Alpine (2023–)",
            "GP2 champion who memorably won the 2020 Italian Grand Prix from 10th on the grid for AlphaTauri. Now Alpine's team leader."));

        driverRepo.save(d("Liam Lawson", "RB", 10, 10,
            "New Zealander", "NZ", 30, LocalDate.of(2002, 2, 11), "Hastings, New Zealand",
            0, 0, 0, 25,
            "AlphaTauri stand-in (2023), RB (2024), Red Bull (start 2025), RB (mid-2025–)",
            "Stepped in at AlphaTauri after Ricciardo's 2023 injury. Briefly elevated to Red Bull at the start of 2025 before returning to the sister team mid-season."));

        driverRepo.save(d("Franco Colapinto", "Alpine", 7, 11,
            "Argentine", "AR", 43, LocalDate.of(2003, 5, 27), "Pilar, Argentina",
            0, 0, 0, 25,
            "Williams (2024), Alpine (2025–)",
            "The first Argentine F1 driver in over two decades. Scored points on debut for Williams in 2024 and was poached by Alpine for 2025."));

        driverRepo.save(d("Arvid Lindblad", "RB", 4, 12,
            "British", "GB", 15, LocalDate.of(2007, 8, 8), "London, England",
            0, 0, 0, 6,
            "RB (2026–)",
            "Red Bull junior who climbed through F4, FRECA and F2. Joins RB for his rookie F1 campaign in 2026."));

        driverRepo.save(d("Isack Hadjar", "Red Bull", 4, 13,
            "French", "FR", 6, LocalDate.of(2004, 9, 28), "Paris, France",
            0, 0, 0, 30,
            "RB (2025), Red Bull (2026–)",
            "Impressed in his rookie 2025 campaign with RB and was promoted to the senior Red Bull team alongside Verstappen for 2026."));

        driverRepo.save(d("Carlos Sainz", "Williams", 4, 14,
            "Spanish", "ES", 55, LocalDate.of(1994, 9, 1), "Madrid, Spain",
            4, 27, 0, 215,
            "Toro Rosso (2015–2017), Renault (2017–2018), McLaren (2019–2020), Ferrari (2021–2024), Williams (2025–)",
            "Son of two-time WRC champion Carlos Sainz Sr. Brought race wins back to Ferrari in 2022 and 2024 before moving to lead the Williams revival."));

        driverRepo.save(d("Gabriel Bortoleto", "Audi", 2, 15,
            "Brazilian", "BR", 5, LocalDate.of(2004, 10, 14), "São Paulo, Brazil",
            0, 0, 0, 30,
            "Sauber/Kick Sauber (2025), Audi (2026–)",
            "Reigning F2 champion (2024) and an F3 champion before that. Stepped straight onto F1's grid for 2025 with the team that became Audi in 2026."));

        driverRepo.save(d("Esteban Ocon", "Haas", 1, 16,
            "French", "FR", 31, LocalDate.of(1996, 9, 17), "Évreux, France",
            1, 4, 0, 165,
            "Manor (2016), Force India / Racing Point (2017–2018), Renault / Alpine (2020–2024), Haas (2025–)",
            "Took a stunning maiden win at the chaotic 2021 Hungarian GP for Alpine. Moved to Haas in 2025 to lead an upward-trending midfield team."));

        driverRepo.save(d("Alexander Albon", "Williams", 1, 17,
            "Thai", "TH", 23, LocalDate.of(1996, 3, 23), "London, England",
            0, 2, 0, 105,
            "Toro Rosso (2019), Red Bull (2019–2020), Williams (2022–)",
            "Returned to F1 with Williams in 2022 after a year out of the senior series and has consistently delivered above the car's expected level."));

        driverRepo.save(d("Nico Hülkenberg", "Audi", 0, 18,
            "German", "DE", 27, LocalDate.of(1987, 8, 19), "Emmerich am Rhein, Germany",
            0, 1, 0, 235,
            "Williams (2010), Force India (2011–2012, 2014–2016), Sauber (2013), Renault (2017–2019), Racing Point (2020), Aston Martin (2022), Haas (2023–2024), Audi (2025–)",
            "Long-time midfield specialist who finally collected his maiden podium at the 2024 British GP. Anchored Sauber through its transition to Audi for 2026."));

        driverRepo.save(d("Valtteri Bottas", "Cadillac", 0, 19,
            "Finnish", "FI", 77, LocalDate.of(1989, 8, 28), "Nastola, Finland",
            10, 67, 0, 247,
            "Williams (2013–2016), Mercedes (2017–2021), Alfa Romeo / Stake Sauber (2022–2024), Cadillac (2026–)",
            "Five-time runner-up to Hamilton at Mercedes. Returns to a race seat in 2026 to spearhead Cadillac's debut alongside Sergio Pérez."));

        driverRepo.save(d("Sergio Pérez", "Cadillac", 0, 20,
            "Mexican", "MX", 11, LocalDate.of(1990, 1, 26), "Guadalajara, Mexico",
            6, 39, 0, 290,
            "Sauber (2011–2012), McLaren (2013), Force India / Racing Point / Aston Martin (2014–2020), Red Bull (2021–2024), Cadillac (2026–)",
            "F1's most experienced active Mexican driver. Won six grands prix for Red Bull and returns from a year out alongside Bottas at Cadillac."));

        driverRepo.save(d("Fernando Alonso", "Aston Martin", 0, 21,
            "Spanish", "ES", 14, LocalDate.of(1981, 7, 29), "Oviedo, Spain",
            32, 107, 2, 410,
            "Minardi (2001), Renault (2003–2006, 2008–2009), McLaren (2007, 2015–2018), Ferrari (2010–2014), Alpine (2021–2022), Aston Martin (2023–)",
            "Two-time world champion (2005, 2006) and the most experienced driver on the grid. Still chasing a third title with Aston Martin's long-term project."));

        driverRepo.save(d("Lance Stroll", "Aston Martin", 0, 22,
            "Canadian", "CA", 18, LocalDate.of(1998, 10, 29), "Montreal, Canada",
            0, 3, 0, 185,
            "Williams (2017–2018), Racing Point / Aston Martin (2019–)",
            "F3 European champion who debuted with Williams aged 18. Now in his eighth season with the family-owned Aston Martin team."));
    }

    private void seedRaces() {
        Race r1 = race("Australian Grand Prix", "Albert Park Grand Prix Circuit",
            LocalDate.of(2026, 3, 8), "Australia", 1, 58, 5.278,
            "The traditional season opener winds through Melbourne's Albert Park. Mostly fast, with technical chicanes by the lake.");
        r1.addResult(new RaceResult(1, "George Russell", "Mercedes", "1:23:06.801", 25, 1, "Finished"));
        r1.addResult(new RaceResult(2, "Andrea Kimi Antonelli", "Mercedes", "+2.974", 18, 2, "Finished"));
        r1.addResult(new RaceResult(3, "Charles Leclerc", "Ferrari", "+15.519", 15, 4, "Finished"));
        r1.addResult(new RaceResult(4, "Lewis Hamilton", "Ferrari", "+16.144", 12, 7, "Finished"));
        r1.addResult(new RaceResult(5, "Lando Norris", "McLaren", "+51.741", 10, 6, "Finished"));
        r1.addResult(new RaceResult(6, "Max Verstappen", "Red Bull", "+54.617", 8, 20, "Finished"));
        r1.addResult(new RaceResult(7, "Oliver Bearman", "Haas", "+1 lap", 6, 12, "Lapped"));
        r1.addResult(new RaceResult(8, "Arvid Lindblad", "RB", "+1 lap", 4, 9, "Lapped"));
        r1.addResult(new RaceResult(9, "Gabriel Bortoleto", "Audi", "+1 lap", 2, 10, "Lapped"));
        r1.addResult(new RaceResult(10, "Pierre Gasly", "Alpine", "+1 lap", 1, 14, "Lapped"));
        r1.addResult(new RaceResult(11, "Esteban Ocon", "Haas", "+1 lap", 0, 13, "Lapped"));
        r1.addResult(new RaceResult(12, "Alexander Albon", "Williams", "+1 lap", 0, 15, "Lapped"));
        r1.addResult(new RaceResult(13, "Liam Lawson", "RB", "+1 lap", 0, 8, "Lapped"));
        r1.addResult(new RaceResult(14, "Franco Colapinto", "Alpine", "+1 lap", 0, 16, "Lapped"));
        r1.addResult(new RaceResult(15, "Carlos Sainz", "Williams", "+1 lap", 0, 21, "Lapped"));
        r1.addResult(new RaceResult(16, "Sergio Pérez", "Cadillac", "+1 lap", 0, 18, "Lapped"));
        r1.addResult(new RaceResult(17, "Lance Stroll", "Aston Martin", "+1 lap", 0, 22, "Lapped"));
        r1.addResult(new RaceResult(18, "Fernando Alonso", "Aston Martin", "DNF", 0, 17, "Retired"));
        r1.addResult(new RaceResult(19, "Valtteri Bottas", "Cadillac", "DNF", 0, 19, "Retired"));
        r1.addResult(new RaceResult(20, "Isack Hadjar", "Red Bull", "DNF", 0, 3, "Retired"));
        r1.addResult(new RaceResult(21, "Oscar Piastri", "McLaren", "DNS", 0, 5, "Did not start"));
        r1.addResult(new RaceResult(22, "Nico Hülkenberg", "Audi", "DNS", 0, 11, "Did not start"));
        raceRepo.save(r1);

        Race r2 = race("Chinese Grand Prix", "Shanghai International Circuit",
            LocalDate.of(2026, 3, 15), "China", 2, 56, 5.451,
            "The 'snail-shell' Turn 1-3 sequence is the signature, opening into one of F1's longest straights down the back.");
        r2.addResult(new RaceResult(1, "Andrea Kimi Antonelli", "Mercedes", "1:33:15.607", 25, 1, "Finished"));
        r2.addResult(new RaceResult(2, "George Russell", "Mercedes", "+5.515", 18, 2, "Finished"));
        r2.addResult(new RaceResult(3, "Lewis Hamilton", "Ferrari", "+25.267", 15, 3, "Finished"));
        r2.addResult(new RaceResult(4, "Charles Leclerc", "Ferrari", "+28.894", 12, 4, "Finished"));
        r2.addResult(new RaceResult(5, "Oliver Bearman", "Haas", "+57.268", 10, 10, "Finished"));
        r2.addResult(new RaceResult(6, "Pierre Gasly", "Alpine", "+59.647", 8, 7, "Finished"));
        r2.addResult(new RaceResult(7, "Liam Lawson", "RB", "+1:20.588", 6, 14, "Finished"));
        r2.addResult(new RaceResult(8, "Isack Hadjar", "Red Bull", "+1:27.247", 4, 9, "Finished"));
        r2.addResult(new RaceResult(9, "Carlos Sainz", "Williams", "+1 lap", 2, 17, "Lapped"));
        r2.addResult(new RaceResult(10, "Franco Colapinto", "Alpine", "+1 lap", 1, 12, "Lapped"));
        r2.addResult(new RaceResult(11, "Nico Hülkenberg", "Audi", "+1 lap", 0, 11, "Lapped"));
        r2.addResult(new RaceResult(12, "Arvid Lindblad", "RB", "+1 lap", 0, 15, "Lapped"));
        r2.addResult(new RaceResult(13, "Valtteri Bottas", "Cadillac", "+1 lap", 0, 19, "Lapped"));
        r2.addResult(new RaceResult(14, "Esteban Ocon", "Haas", "+1 lap", 0, 13, "Lapped"));
        r2.addResult(new RaceResult(15, "Sergio Pérez", "Cadillac", "+1 lap", 0, 21, "Lapped"));
        r2.addResult(new RaceResult(16, "Max Verstappen", "Red Bull", "DNF", 0, 8, "Retired"));
        r2.addResult(new RaceResult(17, "Fernando Alonso", "Aston Martin", "DNF", 0, 18, "Retired"));
        r2.addResult(new RaceResult(18, "Lance Stroll", "Aston Martin", "DNF", 0, 20, "Retired"));
        r2.addResult(new RaceResult(19, "Oscar Piastri", "McLaren", "DNS", 0, 5, "Did not start"));
        r2.addResult(new RaceResult(20, "Lando Norris", "McLaren", "DNS", 0, 6, "Did not start"));
        r2.addResult(new RaceResult(21, "Gabriel Bortoleto", "Audi", "DNS", 0, 16, "Did not start"));
        r2.addResult(new RaceResult(22, "Alexander Albon", "Williams", "DNS", 0, 22, "Did not start"));
        raceRepo.save(r2);

        Race r3 = race("Japanese Grand Prix", "Suzuka Circuit",
            LocalDate.of(2026, 3, 29), "Japan", 3, 53, 5.807,
            "Suzuka's figure-eight layout features the legendary 130R and the high-speed Esses — a driver favourite.");
        r3.addResult(new RaceResult(1, "Andrea Kimi Antonelli", "Mercedes", "1:28:03.403", 25, 1, "Finished"));
        r3.addResult(new RaceResult(2, "Oscar Piastri", "McLaren", "+13.722", 18, 3, "Finished"));
        r3.addResult(new RaceResult(3, "Charles Leclerc", "Ferrari", "+15.270", 15, 4, "Finished"));
        r3.addResult(new RaceResult(4, "George Russell", "Mercedes", "+15.754", 12, 2, "Finished"));
        r3.addResult(new RaceResult(5, "Lando Norris", "McLaren", "+23.479", 10, 5, "Finished"));
        r3.addResult(new RaceResult(6, "Lewis Hamilton", "Ferrari", "+25.037", 8, 6, "Finished"));
        r3.addResult(new RaceResult(7, "Pierre Gasly", "Alpine", "+32.340", 6, 7, "Finished"));
        r3.addResult(new RaceResult(8, "Max Verstappen", "Red Bull", "+32.677", 4, 11, "Finished"));
        r3.addResult(new RaceResult(9, "Liam Lawson", "RB", "+50.180", 2, 14, "Finished"));
        r3.addResult(new RaceResult(10, "Esteban Ocon", "Haas", "+51.216", 1, 12, "Finished"));
        r3.addResult(new RaceResult(11, "Nico Hülkenberg", "Audi", "+52.280", 0, 13, "Finished"));
        r3.addResult(new RaceResult(12, "Isack Hadjar", "Red Bull", "+56.154", 0, 8, "Finished"));
        r3.addResult(new RaceResult(13, "Gabriel Bortoleto", "Audi", "+59.078", 0, 9, "Finished"));
        r3.addResult(new RaceResult(14, "Arvid Lindblad", "RB", "+59.848", 0, 10, "Finished"));
        r3.addResult(new RaceResult(15, "Carlos Sainz", "Williams", "+1:05.008", 0, 16, "Finished"));
        r3.addResult(new RaceResult(16, "Franco Colapinto", "Alpine", "+1:05.773", 0, 15, "Finished"));
        r3.addResult(new RaceResult(17, "Sergio Pérez", "Cadillac", "+1:32.453", 0, 19, "Finished"));
        r3.addResult(new RaceResult(18, "Fernando Alonso", "Aston Martin", "+1 lap", 0, 21, "Lapped"));
        r3.addResult(new RaceResult(19, "Valtteri Bottas", "Cadillac", "+1 lap", 0, 20, "Lapped"));
        r3.addResult(new RaceResult(20, "Alexander Albon", "Williams", "+1 lap", 0, 17, "Lapped"));
        r3.addResult(new RaceResult(21, "Lance Stroll", "Aston Martin", "DNF", 0, 22, "Retired"));
        r3.addResult(new RaceResult(22, "Oliver Bearman", "Haas", "DNF", 0, 18, "Retired"));
        raceRepo.save(r3);

        Race r4 = race("Miami Grand Prix", "Miami International Autodrome",
            LocalDate.of(2026, 5, 3), "USA", 4, 57, 5.412,
            "A modern street-style circuit threading around the Hard Rock Stadium, mixing high-speed straights with technical infield sections.");
        r4.addResult(new RaceResult(1, "Andrea Kimi Antonelli", "Mercedes", "1:33:19.273", 25, 1, "Finished"));
        r4.addResult(new RaceResult(2, "Lando Norris", "McLaren", "+3.264", 18, 4, "Finished"));
        r4.addResult(new RaceResult(3, "Oscar Piastri", "McLaren", "+27.092", 15, 7, "Finished"));
        r4.addResult(new RaceResult(4, "George Russell", "Mercedes", "+43.051", 12, 5, "Finished"));
        r4.addResult(new RaceResult(5, "Max Verstappen", "Red Bull", "+48.949", 10, 2, "Finished"));
        r4.addResult(new RaceResult(6, "Lewis Hamilton", "Ferrari", "+53.753", 8, 6, "Finished"));
        r4.addResult(new RaceResult(7, "Franco Colapinto", "Alpine", "+1:01.871", 6, 8, "Finished"));
        r4.addResult(new RaceResult(8, "Charles Leclerc", "Ferrari", "+1:04.245", 4, 3, "Finished"));
        r4.addResult(new RaceResult(9, "Carlos Sainz", "Williams", "+1:22.072", 2, 13, "Finished"));
        r4.addResult(new RaceResult(10, "Alexander Albon", "Williams", "+1:30.972", 1, 15, "Finished"));
        r4.addResult(new RaceResult(11, "Oliver Bearman", "Haas", "+1 lap", 0, 12, "Lapped"));
        r4.addResult(new RaceResult(12, "Gabriel Bortoleto", "Audi", "+1 lap", 0, 21, "Lapped"));
        r4.addResult(new RaceResult(13, "Esteban Ocon", "Haas", "+1 lap", 0, 14, "Lapped"));
        r4.addResult(new RaceResult(14, "Arvid Lindblad", "RB", "+1 lap", 0, 16, "Lapped"));
        r4.addResult(new RaceResult(15, "Fernando Alonso", "Aston Martin", "+1 lap", 0, 17, "Lapped"));
        r4.addResult(new RaceResult(16, "Sergio Pérez", "Cadillac", "+1 lap", 0, 20, "Lapped"));
        r4.addResult(new RaceResult(17, "Lance Stroll", "Aston Martin", "+1 lap", 0, 18, "Lapped"));
        r4.addResult(new RaceResult(18, "Valtteri Bottas", "Cadillac", "+1 lap", 0, 19, "Lapped"));
        r4.addResult(new RaceResult(19, "Nico Hülkenberg", "Audi", "DNF", 0, 10, "Retired"));
        r4.addResult(new RaceResult(20, "Liam Lawson", "RB", "DNF", 0, 11, "Retired"));
        r4.addResult(new RaceResult(21, "Pierre Gasly", "Alpine", "DNF", 0, 9, "Retired"));
        r4.addResult(new RaceResult(22, "Isack Hadjar", "Red Bull", "DNF", 0, 22, "Retired"));
        raceRepo.save(r4);
        raceRepo.save(race("Canadian Grand Prix", "Circuit Gilles Villeneuve",
            LocalDate.of(2026, 5, 24), "Canada", 5, 70, 4.361,
            "Built on Île Notre-Dame, this stop-start layout is famous for the 'Wall of Champions' at the final chicane."));
        raceRepo.save(race("Monaco Grand Prix", "Circuit de Monaco",
            LocalDate.of(2026, 6, 7), "Monaco", 6, 78, 3.337,
            "The crown jewel — narrow streets, no run-off, and qualifying that effectively decides the race."));
        raceRepo.save(race("Spanish Grand Prix", "Circuit de Barcelona-Catalunya",
            LocalDate.of(2026, 6, 14), "Spain", 7, 66, 4.657,
            "The pre-season testing benchmark. Long sweepers in sector one punish any aerodynamic weakness."));
        raceRepo.save(race("Austrian Grand Prix", "Red Bull Ring",
            LocalDate.of(2026, 6, 28), "Austria", 8, 71, 4.318,
            "Short, sharp, and set in the Styrian mountains. Only ten corners but plenty of elevation."));
        raceRepo.save(race("British Grand Prix", "Silverstone Circuit",
            LocalDate.of(2026, 7, 5), "United Kingdom", 9, 52, 5.891,
            "F1's spiritual home. Maggotts–Becketts–Chapel is one of the most demanding corner sequences on the calendar."));
        raceRepo.save(race("Belgian Grand Prix", "Circuit de Spa-Francorchamps",
            LocalDate.of(2026, 7, 19), "Belgium", 10, 44, 7.004,
            "The longest lap of the year, snaking through the Ardennes forest. Eau Rouge into Raidillon is iconic."));
        raceRepo.save(race("Hungarian Grand Prix", "Hungaroring",
            LocalDate.of(2026, 7, 26), "Hungary", 11, 70, 4.381,
            "A twisty, low-speed circuit often called 'Monaco without the walls'. Track position is everything."));
        raceRepo.save(race("Dutch Grand Prix", "Circuit Park Zandvoort",
            LocalDate.of(2026, 8, 23), "Netherlands", 12, 72, 4.259,
            "A flowing seaside circuit with two banked corners — one of them, Turn 3, is taken almost flat."));
        raceRepo.save(race("Italian Grand Prix", "Autodromo Nazionale di Monza",
            LocalDate.of(2026, 9, 6), "Italy", 13, 53, 5.793,
            "The 'Temple of Speed'. Lowest downforce setup of the year and the highest average lap speed."));
        raceRepo.save(race("Madrid Grand Prix", "Madring",
            LocalDate.of(2026, 9, 13), "Spain", 14, 57, 5.474,
            "Brand-new for 2026, the Madring is a hybrid street/permanent circuit with a banked final corner near IFEMA."));
        raceRepo.save(race("Azerbaijan Grand Prix", "Baku City Circuit",
            LocalDate.of(2026, 9, 26), "Azerbaijan", 15, 51, 6.003,
            "Mixes a tight, castle-walled old-town section with a 2.2 km flat-out straight along the Caspian Sea."));
        raceRepo.save(race("Singapore Grand Prix", "Marina Bay Street Circuit",
            LocalDate.of(2026, 10, 11), "Singapore", 16, 62, 4.940,
            "A demanding night race under floodlights in tropical humidity. Bumpy, slow, and physically brutal."));
        raceRepo.save(race("United States Grand Prix", "Circuit of the Americas",
            LocalDate.of(2026, 10, 25), "USA", 17, 56, 5.513,
            "An 'all-time greatest hits' design — Turn 1 is a steep climb, with sections inspired by Silverstone and Hockenheim."));
        raceRepo.save(race("Mexico City Grand Prix", "Autódromo Hermanos Rodríguez",
            LocalDate.of(2026, 11, 1), "Mexico", 18, 71, 4.304,
            "Run at 2,250 m altitude — thinner air means less downforce and a unique tuning challenge."));
        raceRepo.save(race("São Paulo Grand Prix", "Autódromo José Carlos Pace",
            LocalDate.of(2026, 11, 8), "Brazil", 19, 71, 4.309,
            "Anti-clockwise, bumpy, and notoriously weather-volatile. Always delivers drama."));
        raceRepo.save(race("Las Vegas Grand Prix", "Las Vegas Strip Street Circuit",
            LocalDate.of(2026, 11, 22), "USA", 20, 50, 6.201,
            "Night race down the Vegas Strip. Long straights make it one of the highest top-speed tracks on the calendar."));
        raceRepo.save(race("Qatar Grand Prix", "Losail International Circuit",
            LocalDate.of(2026, 11, 29), "Qatar", 21, 57, 5.419,
            "Fast, flowing medium- and high-speed corners under floodlights. High tyre stress on the long sweepers."));
        raceRepo.save(race("Abu Dhabi Grand Prix", "Yas Marina Circuit",
            LocalDate.of(2026, 12, 6), "UAE", 22, 58, 5.281,
            "The season finale at Yas Island. Day-to-dusk twilight race ending under the marina's lights."));
    }

    private void seedNews() {
        LocalDateTime now = LocalDateTime.now();
        newsRepo.save(new News(
            "Norris confident Montreal is 'a track that suits' McLaren",
            "Lando Norris expresses optimism about McLaren's prospects at the upcoming Canadian Grand Prix.",
            now.minusHours(4),
            "https://www.formula1.com/en/latest/article/norris-confident-in-mclarens-montreal-chances-on-a-track-that-suits-us.zcLbTRRjCsm017jM3DL1z",
            "https://media.formula1.com/image/upload/t_16by9South/c_lfill,w_3392/q_auto/v1740000001/trackside-images/2026/F1_Grand_Prix_of_Miami___Practice__Sprint_Qualifying/2274074886.webp"
        ));
        newsRepo.save(new News(
            "How F1 drivers and strategists tackle the Canadian Grand Prix",
            "A look at how drivers, engineers, and strategists approach the risk-reward balance at Circuit Gilles Villeneuve.",
            now.minusHours(10),
            "https://www.formula1.com/en/latest/article/the-risk-perspective-how-f1-drivers-engineers-and-strategists-tackle-the-circuit-gilles-villeneuve.68J673ef2kpFE6P3Ydx2ct",
            "https://media.formula1.com/image/upload/t_16by9Centre/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Canada/Marsh%20Risk%20Perspective%20Montreal.webp"
        ));
        newsRepo.save(new News(
            "Why there should be more to come from Ferrari's upgrades",
            "Technical analysis suggesting Ferrari's Miami upgrade package has more performance to unlock as the European season ramps up.",
            now.minusDays(1),
            "https://www.formula1.com/en/latest/article/tech-weekly-why-there-should-be-more-to-come-from-ferraris-major-miami-upgrade-package.3bR9GGw2vicxyoVKbxhszt",
            "https://media.formula1.com/image/upload/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Miscellaneous/GettyImages-2274038645-1.webp"
        ));
        newsRepo.save(new News(
            "The Villeneuves' contrasting F1 style legacy",
            "A look at the different racing styles and legacies of Canadian drivers Gilles and Jacques Villeneuve ahead of their home race.",
            now.minusDays(1).minusHours(6),
            "https://www.formula1.com/en/latest/article/the-contrasting-style-legacy-of-canadas-gilles-and-jacques-villeneuve.4qWfvBmzgkx7XBRCaDcSw7",
            "https://media.formula1.com/image/upload/ar_16:9,c_fill,g_south/c_lfill,w_3392/q_auto/v1740000001/fom-website/2025/Lifestyle/ADHOC/villeneuvesv2.webp"
        ));
        newsRepo.save(new News(
            "David Coulthard on fighting for the best seat in F1",
            "Beyond The Grid podcast episode where the former F1 driver talks about navigating mid-career and securing competitive seats.",
            now.minusDays(2),
            "https://www.formula1.com/en/latest/article/beyond-the-grid-legends-david-coulthard-on-fighting-for-the-best-seat-in-f1.649dToK9YZhOITov3kVG3I",
            "https://media.formula1.com/image/upload/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Beyond%20The%20Grid/BTG%20DAVID%20COULTHARD%20ARTWORK%2016x9.webp"
        ));
        newsRepo.save(new News(
            "QUIZ: 10 questions about Canadian drivers in F1",
            "Test your knowledge of Canadian Formula 1 drivers through the sport's history in this 10-question quiz.",
            now.minusDays(2).minusHours(8),
            "https://www.formula1.com/en/latest/article/quiz-10-questions-about-canadian-drivers-in-f1.4upNKnJO52B77NjchpZsfJ",
            "https://media.formula1.com/image/upload/c_lfill,w_3392/q_auto/v1740000001/fom-website/2025/Miscellaneous/Gilles%20Villeneuve%201981.webp"
        ));
        newsRepo.save(new News(
            "How to stream the 2026 Canadian Grand Prix on F1 TV Premium",
            "A viewer's guide to accessing F1 TV Premium coverage of the 2026 Canadian Grand Prix race weekend.",
            now.minusDays(3),
            "https://www.formula1.com/en/latest/article/how-to-stream-the-formula-1-2026-canadian-grand-prix-on-f1-tv-premium.4ObuYiOnDbMJfob1dpbLnd",
            "https://media.formula1.com/image/upload/t_16by9Centre/c_lfill,w_3392/q_auto/v1740000001/fom-website/2025/F1%20TV/F1TVPREMIUM_CANADA_MULTISCREEN_PRODUCT1_ENGLISH_726947_1920x1080.webp"
        ));
        newsRepo.save(new News(
            "Win the Ultimate David Guetta Experience at Silverstone & Ibiza",
            "An F1 Unlocked competition offering fans the chance to win an exclusive experience with David Guetta at Silverstone and Ibiza.",
            now.minusDays(4),
            "https://www.formula1.com/en/latest/article/2026-silverstone-f1-unlocked-competition.tfYnfCoiQVgnRHzzodCP2",
            "https://media.formula1.com/image/upload/c_lfill,w_3392/q_auto/v1740000001/fom-website/Unlocked2026/David-Guetta-16x9.webp"
        ));
    }

    private void seedVideos() {
        String base = "https://www.formula1.com";
        int i = 0;
        videoRepo.save(new Video(
            "Insider Intel: Horses & F1? Sam Collins has the answers",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/c3bc0eac-1bfb-47ad-a671-cc4967bea45e/2aa1cb73-c6f9-4737-a81d-c2602ac71dd4/864x486/match/image.jpg",
            base + "/en/video/insider-intel-horses-f1-sam-collins-has-the-answers.1865615220934022820",
            "Insider Intel", ++i));
        videoRepo.save(new Video(
            "Carlos Sainz's first laps of the Madring | New Spanish GP track",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/336b7bdb-7c9c-4457-94c3-b16bcf66ee88/dba1bf8b-8912-48b8-adfa-1866f4710a92/864x486/match/image.jpg",
            base + "/en/video/carlos-sainzs-first-laps-of-the-madring-new-spanish-gp-track.1865273374603327554",
            "Track Preview", ++i));
        videoRepo.save(new Video(
            "10 iconic races from Max Verstappen's 10 years at Red Bull",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/6cd6a018-cb62-472f-8799-15023556310a/24fef9b0-7a06-428b-becd-1ba94ee8ee8b/864x486/match/image.jpg",
            base + "/en/video/10-iconic-races-from-max-verstappens-10-years-at-red-bull.1865246318500627855",
            "Feature", ++i));
        videoRepo.save(new Video(
            "GianPiero Lambiase & Max Verstappen's most iconic radio moments",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/dcbb78b5-42f2-4452-bf59-9969a5c53676/e9a3d2bf-1765-487a-ac33-9297e5ffcd4b/864x486/match/image.jpg",
            base + "/en/video/gianpiero-lambiase-max-verstappens-most-iconic-radio-moments.1865062761376363648",
            "Team Radio", ++i));
        videoRepo.save(new Video(
            "Ocon and Bearman react to iconic F1 moments and personal highlights",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/49c4899f-b668-41c7-95ca-d5703bf5ae48/fba58de4-8d08-4615-9805-1c4d4434cbfb/864x486/match/image.jpg",
            base + "/en/video/ocon-and-bearman-react-to-iconic-f1-moments-and-personal-highlights.1865094579309105566",
            "Driver Reactions", ++i));
        videoRepo.save(new Video(
            "Insider Intel: Pit lane secrets? Sam Collins answers your questions",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/e4d3c21c-46f0-4cd1-ae55-71d360736e9d/f75b21e3-d10d-42f2-89ea-aea490627533/864x486/match/image.jpg",
            base + "/en/video/insider-intel-pit-lane-secrets-sam-collins-answers-your-questions.1864998552865175085",
            "Insider Intel", ++i));
        videoRepo.save(new Video(
            "Verstappen's Miami race start 360 amazes The Cooldown Room",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/db3c4183-c9c3-4829-8433-83c48c6be830/e1a18cd0-d07c-44f2-be20-dc0d21b787b4/864x486/match/image.jpg",
            base + "/en/video/verstappens-miami-race-start-360-amazes-the-cooldown-room.1864518234180660159",
            "Cooldown Room", ++i));
        videoRepo.save(new Video(
            "Top 10 Onboard Moments: 2026 Miami Grand Prix",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/f9c39c8a-5d69-4515-8e06-bfae9c97c9c6/e65be0db-6a1f-4760-bbe8-3f08dbfdfa63/864x486/match/image.jpg",
            base + "/en/video/top-10-onboard-moments-2026-miami-grand-prix.1864359051526489359",
            "Onboards", ++i));
        videoRepo.save(new Video(
            "Jolyon Palmer's Analysis: Miami Grand Prix",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/49459117-19fb-4796-a698-b0d95bab0556/1e47cce3-ab3e-4d3f-b9bd-88a3b29f01af/864x486/match/image.jpg",
            base + "/en/video/jolyon-palmers-analysis-miami-grand-prix.1864355557152502481",
            "Race Analysis", ++i));
        videoRepo.save(new Video(
            "Antonelli credits 'keeping cool after a difficult start' for Miami GP victory",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/d3a7d66b-e1e5-4c30-8560-bb594dc820a7/f6b61c48-bc38-4721-b711-a0f084fe383e/864x486/match/image.jpg",
            base + "/en/video/antonelli-credits-keeping-cool-after-a-difficult-start-for-miami-gp-victory.1864199549747752664",
            "Post-Race", ++i));
        videoRepo.save(new Video(
            "Norris: 'It hurts a bit… I feel like we should have won the race today'",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/d0a0b4b2-61b1-4b08-b141-ceea85e1b8ba/1f8508ee-1568-46e9-9f48-647ee43efecf/864x486/match/image.jpg",
            base + "/en/video/norris-it-hurts-a-bit-i-feel-like-we-should-have-won-the-race-today.1864198865995189460",
            "Post-Race", ++i));
        videoRepo.save(new Video(
            "Leclerc: 'The last lap mistake is all on me… and it cost us P3 or P4'",
            "https://d2n9h2wits23hf.cloudfront.net/image/v1/static/6057949432001/38aceff2-ed03-41bb-9613-130d3200a59c/a428ab7f-3351-40e5-9dde-d50105090259/864x486/match/image.jpg",
            base + "/en/video/leclerc-the-last-lap-mistake-is-all-on-me-and-it-cost-us-p3-or-p4.1864195705638505236",
            "Post-Race", ++i));
    }
}
