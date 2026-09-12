package com.example.sehja.config;

import com.example.sehja.model.Driver;
import com.example.sehja.model.News;
import com.example.sehja.model.Race;
import com.example.sehja.model.RaceResult;
import com.example.sehja.model.Team;
import com.example.sehja.model.Video;
import com.example.sehja.repository.DriverRepository;
import com.example.sehja.repository.NewsRepository;
import com.example.sehja.repository.RaceRepository;
import com.example.sehja.repository.TeamRepository;
import com.example.sehja.repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {


    private final DriverRepository driverRepo;
    private final RaceRepository raceRepo;
    private final NewsRepository newsRepo;
    private final VideoRepository videoRepo;
    private final TeamRepository teamRepo;

    public DataInitializer(DriverRepository driverRepo,
                           RaceRepository raceRepo,
                           NewsRepository newsRepo,
                           VideoRepository videoRepo,
                           TeamRepository teamRepo) {
        this.driverRepo = driverRepo;
        this.raceRepo = raceRepo;
        this.newsRepo = newsRepo;
        this.videoRepo = videoRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public void run(String... args) {
        if (driverRepo.count() == 0) seedDrivers();
        if (raceRepo.count() == 0) seedRaces();
        if (newsRepo.count() == 0) seedNews();
        if (videoRepo.count() == 0) seedVideos();
        if (teamRepo.count() == 0) seedTeams();
    }

    private void seedTeams() {
        String logoBase = "https://media.formula1.com/image/upload/c_lfill,w_240/q_auto/v1740000001/common/f1/2026/";
        String carBase = "https://media.formula1.com/image/upload/c_lfill,h_224/q_auto/d_common:f1:2026:fallback:car:2026fallbackcarright.webp/v1740000001/common/f1/2026/";

        Team mercedes = team("Mercedes", "Mercedes-AMG Petronas", 1, 468, 9, "German",
            logoBase + "mercedes/2026mercedeslogowhite.webp",
            carBase + "mercedes/2026mercedescarright.webp");
        mercedes.setFounded(2010);
        mercedes.setBase("Brackley, United Kingdom");
        mercedes.setEngine("Mercedes-AMG");
        mercedes.setPrincipal("Toto Wolff");
        mercedes.setConstructorTitles(8);
        mercedes.setDriverTitles(9);
        mercedes.setTotalWins(125);
        mercedes.setHistory("Mercedes returned to F1 as a constructor in 2010 after a 55-year absence. The team dominated the hybrid era with eight consecutive constructors' titles (2014–2021) and seven drivers' titles for Lewis Hamilton plus one for Nico Rosberg. With Hamilton's switch to Ferrari for 2025, Mercedes is now anchored by George Russell and rookie sensation Andrea Kimi Antonelli.");
        teamRepo.save(mercedes);

        Team ferrari = team("Ferrari", "Scuderia Ferrari", 2, 346, 2, "Italian",
            logoBase + "ferrari/2026ferrarilogowhite.webp",
            carBase + "ferrari/2026ferraricarright.webp");
        ferrari.setFounded(1929);
        ferrari.setBase("Maranello, Italy");
        ferrari.setEngine("Ferrari");
        ferrari.setPrincipal("Fred Vasseur");
        ferrari.setConstructorTitles(16);
        ferrari.setDriverTitles(15);
        ferrari.setTotalWins(248);
        ferrari.setHistory("The Scuderia is the oldest and most successful team in F1, having competed in every championship since the series began in 1950. Sixteen constructors' titles and fifteen drivers' titles — most recently Kimi Räikkönen in 2007. Ferrari signed Lewis Hamilton for 2025 to partner Charles Leclerc, chasing an eighth driver crown for the Prancing Horse.");
        teamRepo.save(ferrari);

        Team mclaren = team("McLaren", "McLaren Formula 1 Team", 3, 287, 2, "British",
            logoBase + "mclaren/2026mclarenlogowhite.webp",
            carBase + "mclaren/2026mclarencarright.webp");
        mclaren.setFounded(1963);
        mclaren.setBase("Woking, United Kingdom");
        mclaren.setEngine("Mercedes");
        mclaren.setPrincipal("Andrea Stella");
        mclaren.setConstructorTitles(10);
        mclaren.setDriverTitles(12);
        mclaren.setTotalWins(192);
        mclaren.setHistory("Founded by New Zealander Bruce McLaren, the team became one of F1's defining names through the Senna–Prost era and beyond. After a long mid-2010s slump McLaren rebuilt under Zak Brown and Andrea Stella, winning the Constructors' title in 2024 and again in 2025 with the Norris–Piastri pairing.");
        teamRepo.save(mclaren);

        Team redbull = team("Red Bull", "Oracle Red Bull Racing", 4, 204, 0, "Austrian",
            logoBase + "redbullracing/2026redbullracinglogowhite.webp",
            carBase + "redbullracing/2026redbullracingcarright.webp");
        redbull.setFounded(2005);
        redbull.setBase("Milton Keynes, United Kingdom");
        redbull.setEngine("Red Bull Ford Powertrains");
        redbull.setPrincipal("Laurent Mekies");
        redbull.setConstructorTitles(6);
        redbull.setDriverTitles(8);
        redbull.setTotalWins(124);
        redbull.setHistory("Red Bull took over the ailing Jaguar team for 2005 and quickly grew into a juggernaut. Sebastian Vettel won four straight titles (2010–13), and Max Verstappen added four more (2021–2024). Now building their own engines with Ford for the 2026 regulations, partnered with Verstappen and 2025 standout Isack Hadjar.");
        teamRepo.save(redbull);

        Team alpine = team("Alpine", "BWT Alpine F1 Team", 6, 62, 0, "French",
            logoBase + "alpine/2026alpinelogowhite.webp",
            carBase + "alpine/2026alpinecarright.webp");
        alpine.setFounded(2021);
        alpine.setBase("Enstone, United Kingdom");
        alpine.setEngine("Mercedes");
        alpine.setPrincipal("Steve Nielsen");
        alpine.setConstructorTitles(2);
        alpine.setDriverTitles(2);
        alpine.setTotalWins(21);
        alpine.setHistory("The Enstone team has competed under many names — Toleman, Benetton, Renault, Lotus, Renault again, and finally Alpine since 2021. The factory carries two constructor and two driver titles (Schumacher 1994–95 with Benetton, Alonso 2005–06 with Renault). Switched from Renault engines to Mercedes power for 2026.");
        teamRepo.save(alpine);

        Team haas = team("Haas", "MoneyGram Haas F1 Team", 7, 21, 0, "American",
            logoBase + "haasf1team/2026haasf1teamlogowhite.webp",
            carBase + "haasf1team/2026haasf1teamcarright.webp");
        haas.setFounded(2016);
        haas.setBase("Kannapolis, USA");
        haas.setEngine("Ferrari");
        haas.setPrincipal("Ayao Komatsu");
        haas.setConstructorTitles(0);
        haas.setDriverTitles(0);
        haas.setTotalWins(0);
        haas.setHistory("F1's only American-licensed constructor of the modern era, founded by NASCAR team owner Gene Haas. The team takes the maximum possible package of components from Ferrari and runs a lean operation. After Komatsu took over as Team Principal in 2024, Haas has steadily climbed the midfield order.");
        teamRepo.save(haas);

        Team rb = team("Racing Bulls", "Visa Cash App Racing Bulls", 5, 75, 0, "Italian",
            logoBase + "racingbulls/2026racingbullslogowhite.webp",
            carBase + "racingbulls/2026racingbullscarright.webp");
        rb.setFounded(2006);
        rb.setBase("Faenza, Italy");
        rb.setEngine("Red Bull Ford Powertrains");
        rb.setPrincipal("Alan Permane");
        rb.setConstructorTitles(0);
        rb.setDriverTitles(0);
        rb.setTotalWins(2);
        rb.setHistory("Red Bull's sister team, established in 2006 after Red Bull purchased Minardi. Known successively as Toro Rosso, AlphaTauri, RB, and now Racing Bulls. Famous for Vettel's stunning Monza win in 2008 and Gasly's 2020 victory at the same circuit. Functions as Red Bull's young-driver proving ground.");
        teamRepo.save(rb);

        Team williams = team("Williams", "Atlassian Williams Racing", 8, 11, 0, "British",
            logoBase + "williams/2026williamslogowhite.webp",
            carBase + "williams/2026williamscarright.webp");
        williams.setFounded(1977);
        williams.setBase("Grove, United Kingdom");
        williams.setEngine("Mercedes");
        williams.setPrincipal("James Vowles");
        williams.setConstructorTitles(9);
        williams.setDriverTitles(7);
        williams.setTotalWins(114);
        williams.setHistory("Founded by Sir Frank Williams and Patrick Head, Williams was a dominant force through the 1980s and 1990s with drivers like Mansell, Prost, Hill and Villeneuve. After more than a decade of decline, the team is being rebuilt under former Mercedes strategist James Vowles, with Carlos Sainz arriving in 2025 to lead the charge.");
        teamRepo.save(williams);

        Team audi = team("Audi", "Audi F1 Team", 8, 16, 0, "German",
            logoBase + "audi/2026audilogowhite.webp",
            carBase + "audi/2026audicarright.webp");
        audi.setFounded(1993);
        audi.setBase("Hinwil, Switzerland");
        audi.setEngine("Audi");
        audi.setPrincipal("Mattia Binotto");
        audi.setConstructorTitles(0);
        audi.setDriverTitles(0);
        audi.setTotalWins(1);
        audi.setHistory("Built on the foundation of the long-running Sauber operation (in F1 since 1993), the team became Audi's full works entry for 2026 with a brand-new Audi-designed power unit. Audi's Le Mans pedigree and works backing make this one of the most-watched long-term projects on the grid.");
        teamRepo.save(audi);

        Team cadillac = team("Cadillac", "Cadillac F1 Team", 11, 0, 0, "American",
            logoBase + "cadillac/2026cadillaclogowhite.webp",
            carBase + "cadillac/2026cadillaccarright.webp");
        cadillac.setFounded(2026);
        cadillac.setBase("Charlotte, USA");
        cadillac.setEngine("Ferrari (customer)");
        cadillac.setPrincipal("Graeme Lowdon");
        cadillac.setConstructorTitles(0);
        cadillac.setDriverTitles(0);
        cadillac.setTotalWins(0);
        cadillac.setHistory("F1's eleventh team, joining for the 2026 regulations. Cadillac is backed by General Motors and TWG Motorsports and operates from Charlotte with technical operations in the UK. Customer Ferrari power for the debut season, with General Motors' own Cadillac-branded engine planned for the end of the decade.");
        teamRepo.save(cadillac);

        Team astonMartin = team("Aston Martin", "Aston Martin Aramco F1 Team", 10, 3, 0, "British",
            logoBase + "astonmartin/2026astonmartinlogowhite.webp",
            carBase + "astonmartin/2026astonmartincarright.webp");
        astonMartin.setFounded(2021);
        astonMartin.setBase("Silverstone, United Kingdom");
        astonMartin.setEngine("Honda");
        astonMartin.setPrincipal("Andy Cowell");
        astonMartin.setConstructorTitles(0);
        astonMartin.setDriverTitles(0);
        astonMartin.setTotalWins(1);
        astonMartin.setHistory("The Silverstone-based team has raced as Jordan, Midland, Spyker, Force India, Racing Point and — since Lawrence Stroll's purchase and 2021 rebrand — Aston Martin. A new factory, Honda works engines for 2026, and the recent hire of Adrian Newey signal a serious long-term push, with Fernando Alonso and Lance Stroll as the drivers.");
        teamRepo.save(astonMartin);
    }

    private Team team(String name, String fullName, int position, int points, int wins,
                      String nationality, String logoUrl, String carImageUrl) {
        String[] colors = TeamColors.forTeam(name);
        Team t = new Team(name, fullName, position, points, wins, nationality, logoUrl, carImageUrl, colors[0], colors[1]);
        t.setConstructorSlug(TEAM_SLUGS.get(name));
        return t;
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
        String[] colors = TeamColors.forTeam(team);
        dr.setTeamColor(colors[0]);
        dr.setTeamColorAccent(colors[1]);
        dr.setPhotoUrl(DRIVER_PHOTOS.get(name));
        dr.setDriverSlug(DRIVER_SLUGS.get(name));
        return dr;
    }

    private static final Map<String, String> DRIVER_SLUGS = Map.ofEntries(
        Map.entry("Andrea Kimi Antonelli", "antonelli"),
        Map.entry("George Russell",        "russell"),
        Map.entry("Charles Leclerc",       "leclerc"),
        Map.entry("Lando Norris",          "norris"),
        Map.entry("Lewis Hamilton",        "hamilton"),
        Map.entry("Oscar Piastri",         "piastri"),
        Map.entry("Max Verstappen",        "max_verstappen"),
        Map.entry("Isack Hadjar",          "hadjar"),
        Map.entry("Pierre Gasly",          "gasly"),
        Map.entry("Franco Colapinto",      "colapinto"),
        Map.entry("Esteban Ocon",          "ocon"),
        Map.entry("Oliver Bearman",        "bearman"),
        Map.entry("Liam Lawson",           "lawson"),
        Map.entry("Arvid Lindblad",        "arvid_lindblad"),
        Map.entry("Carlos Sainz",          "sainz"),
        Map.entry("Alexander Albon",       "albon"),
        Map.entry("Nico Hülkenberg",       "hulkenberg"),
        Map.entry("Gabriel Bortoleto",     "bortoleto"),
        Map.entry("Sergio Pérez",          "perez"),
        Map.entry("Valtteri Bottas",       "bottas"),
        Map.entry("Fernando Alonso",       "alonso"),
        Map.entry("Lance Stroll",          "stroll"),
        Map.entry("Yuki Tsunoda",          "tsunoda")
    );

    private static final Map<String, String> TEAM_SLUGS = Map.ofEntries(
        Map.entry("Mercedes",     "mercedes"),
        Map.entry("Ferrari",      "ferrari"),
        Map.entry("McLaren",      "mclaren"),
        Map.entry("Red Bull",     "red_bull"),
        Map.entry("Alpine",       "alpine"),
        Map.entry("Haas",         "haas"),
        Map.entry("Racing Bulls",           "rb"),
        Map.entry("Williams",     "williams"),
        Map.entry("Audi",         "audi"),
        Map.entry("Cadillac",     "cadillac"),
        Map.entry("Aston Martin", "aston_martin")
    );

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
        Map.entry("Lance Stroll",          F1_DRIVER_BASE + "astonmartin/lanstr01/2026astonmartinlanstr01right.webp"),
        Map.entry("Yuki Tsunoda",          F1_DRIVER_BASE + "racingbulls/yuktsu01/2026racingbullsyuktsu01right.webp")
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
        driverRepo.save(d("Andrea Kimi Antonelli", "Mercedes", 267, 1,
            "Italian", "IT", 12, LocalDate.of(2006, 8, 25), "Bologna, Italy",
            1, 5, 0, 31,
            "Mercedes (2025–)",
            "The Bologna-born prodigy graduated straight from F2 into Mercedes' senior seat for 2025 as Lewis Hamilton's replacement. A breakthrough sophomore campaign has him leading the 2026 championship in his second F1 season."));

        driverRepo.save(d("George Russell", "Mercedes", 201, 2,
            "British", "GB", 63, LocalDate.of(1998, 2, 15), "King's Lynn, England",
            4, 22, 0, 146,
            "Williams (2019–2021), Mercedes (2022–)",
            "F2 and GP3 champion before reaching F1 with Williams. Promoted to Mercedes alongside Hamilton in 2022 and now the team's most experienced driver."));

        driverRepo.save(d("Charles Leclerc", "Ferrari", 155, 5,
            "Monégasque", "MC", 16, LocalDate.of(1997, 10, 16), "Monte Carlo, Monaco",
            9, 43, 0, 156,
            "Sauber (2018), Ferrari (2019–)",
            "F2 champion in 2017 and Ferrari's golden boy since 2019. A specialist in qualifying, with a long list of pole positions and a string of near-misses for the title."));

        driverRepo.save(d("Lewis Hamilton", "Ferrari", 191, 3,
            "British", "GB", 44, LocalDate.of(1985, 1, 7), "Stevenage, England",
            105, 203, 7, 366,
            "McLaren (2007–2012), Mercedes (2013–2024), Ferrari (2025–)",
            "A seven-time world champion, joint-record holder with Michael Schumacher. Made the headline switch from Mercedes to Ferrari for 2025 in pursuit of an eighth title."));

        driverRepo.save(d("Lando Norris", "McLaren", 171, 4,
            "British", "GB", 4, LocalDate.of(1999, 11, 13), "Bristol, England",
            10, 32, 0, 141,
            "McLaren (2019–)",
            "McLaren academy graduate who has stayed loyal to the team through their rebuild. Took his first F1 win in 2024 and has been a regular championship contender since."));

        driverRepo.save(d("Oscar Piastri", "McLaren", 116, 7,
            "Australian", "AU", 81, LocalDate.of(2001, 4, 6), "Melbourne, Australia",
            6, 16, 0, 76,
            "McLaren (2023–)",
            "Back-to-back F3 and F2 champion before debuting with McLaren in 2023. Quickly established himself as Norris' equal and a future title contender."));

        driverRepo.save(d("Max Verstappen", "Red Bull", 127, 6,
            "Dutch", "NL", 1, LocalDate.of(1997, 9, 30), "Hasselt, Belgium",
            65, 116, 4, 226,
            "Toro Rosso (2015–2016), Red Bull (2016–)",
            "Four-time consecutive world champion (2021–2024). Promoted to Red Bull mid-2016 and became the youngest race winner in F1 history at 18. Carries the #1 plate by default."));

        driverRepo.save(d("Pierre Gasly", "Alpine", 41, 10,
            "French", "FR", 10, LocalDate.of(1996, 2, 7), "Rouen, France",
            1, 4, 0, 171,
            "Toro Rosso (2017–2019), Red Bull (2019), AlphaTauri (2019–2022), Alpine (2023–)",
            "GP2 champion who memorably won the 2020 Italian Grand Prix from 10th on the grid for AlphaTauri. Now Alpine's team leader."));

        driverRepo.save(d("Oliver Bearman", "Haas", 18, 13,
            "British", "GB", 87, LocalDate.of(2005, 5, 8), "Chelmsford, England",
            0, 0, 0, 33,
            "Ferrari reserve / Haas stand-in (2024), Haas (2025–)",
            "Made his F1 debut as a Ferrari super-sub in Saudi Arabia 2024 with less than a day's notice and scored points. Promoted to a full-time Haas seat from 2025."));

        driverRepo.save(d("Liam Lawson", "Red Bull", 51, 9,
            "New Zealander", "NZ", 30, LocalDate.of(2002, 2, 11), "Hastings, New Zealand",
            0, 0, 0, 26,
            "AlphaTauri stand-in (2023), RB (2024), Red Bull (start 2025), RB (mid-2025–)",
            "Stepped in at AlphaTauri after Ricciardo's 2023 injury. Briefly elevated to Red Bull at the start of 2025 before returning to the sister team mid-season."));

        driverRepo.save(d("Franco Colapinto", "Alpine", 21, 12,
            "Argentine", "AR", 43, LocalDate.of(2003, 5, 27), "Pilar, Argentina",
            0, 0, 0, 26,
            "Williams (2024), Alpine (2025–)",
            "The first Argentine F1 driver in over two decades. Scored points on debut for Williams in 2024 and was poached by Alpine for 2025."));

        driverRepo.save(d("Isack Hadjar", "Red Bull", 71, 8,
            "French", "FR", 6, LocalDate.of(2004, 9, 28), "Paris, France",
            0, 0, 0, 31,
            "RB (2025), Red Bull (2026–)",
            "Impressed in his rookie 2025 campaign with RB and was promoted to the senior Red Bull team alongside Verstappen for 2026."));

        driverRepo.save(d("Carlos Sainz", "Williams", 6, 16,
            "Spanish", "ES", 55, LocalDate.of(1994, 9, 1), "Madrid, Spain",
            4, 27, 0, 216,
            "Toro Rosso (2015–2017), Renault (2017–2018), McLaren (2019–2020), Ferrari (2021–2024), Williams (2025–)",
            "Son of two-time WRC champion Carlos Sainz Sr. Brought race wins back to Ferrari in 2022 and 2024 before moving to lead the Williams revival."));

        driverRepo.save(d("Arvid Lindblad", "Racing Bulls", 29, 11,
            "British", "GB", 15, LocalDate.of(2007, 8, 8), "London, England",
            0, 0, 0, 7,
            "RB (2026–)",
            "Red Bull junior who climbed through F4, FRECA and F2. Joins RB for his rookie F1 campaign in 2026."));

        driverRepo.save(d("Gabriel Bortoleto", "Audi", 10, 14,
            "Brazilian", "BR", 5, LocalDate.of(2004, 10, 14), "São Paulo, Brazil",
            0, 0, 0, 31,
            "Sauber/Kick Sauber (2025), Audi (2026–)",
            "Reigning F2 champion (2024) and an F3 champion before that. Stepped straight onto F1's grid for 2025 with the team that became Audi in 2026."));

        driverRepo.save(d("Esteban Ocon", "Haas", 3, 18,
            "French", "FR", 31, LocalDate.of(1996, 9, 17), "Évreux, France",
            1, 4, 0, 166,
            "Manor (2016), Force India / Racing Point (2017–2018), Renault / Alpine (2020–2024), Haas (2025–)",
            "Took a stunning maiden win at the chaotic 2021 Hungarian GP for Alpine. Moved to Haas in 2025 to lead an upward-trending midfield team."));

        driverRepo.save(d("Alexander Albon", "Williams", 5, 17,
            "Thai", "TH", 23, LocalDate.of(1996, 3, 23), "London, England",
            0, 2, 0, 106,
            "Toro Rosso (2019), Red Bull (2019–2020), Williams (2022–)",
            "Returned to F1 with Williams in 2022 after a year out of the senior series and has consistently delivered above the car's expected level."));

        driverRepo.save(d("Nico Hülkenberg", "Audi", 6, 15,
            "German", "DE", 27, LocalDate.of(1987, 8, 19), "Emmerich am Rhein, Germany",
            0, 1, 0, 236,
            "Williams (2010), Force India (2011–2012, 2014–2016), Sauber (2013), Renault (2017–2019), Racing Point (2020), Aston Martin (2022), Haas (2023–2024), Audi (2025–)",
            "Long-time midfield specialist who finally collected his maiden podium at the 2024 British GP. Anchored Sauber through its transition to Audi for 2026."));

        driverRepo.save(d("Valtteri Bottas", "Cadillac", 0, 22,
            "Finnish", "FI", 77, LocalDate.of(1989, 8, 28), "Nastola, Finland",
            10, 67, 0, 248,
            "Williams (2013–2016), Mercedes (2017–2021), Alfa Romeo / Stake Sauber (2022–2024), Cadillac (2026–)",
            "Five-time runner-up to Hamilton at Mercedes. Returns to a race seat in 2026 to spearhead Cadillac's debut alongside Sergio Pérez."));

        driverRepo.save(d("Sergio Pérez", "Cadillac", 0, 23,
            "Mexican", "MX", 11, LocalDate.of(1990, 1, 26), "Guadalajara, Mexico",
            6, 39, 0, 291,
            "Sauber (2011–2012), McLaren (2013), Force India / Racing Point / Aston Martin (2014–2020), Red Bull (2021–2024), Cadillac (2026–)",
            "F1's most experienced active Mexican driver. Won six grands prix for Red Bull and returns from a year out alongside Bottas at Cadillac."));

        driverRepo.save(d("Lance Stroll", "Aston Martin", 0, 21,
            "Canadian", "CA", 18, LocalDate.of(1998, 10, 29), "Montreal, Canada",
            0, 3, 0, 186,
            "Williams (2017–2018), Racing Point / Aston Martin (2019–)",
            "F3 European champion who debuted with Williams aged 18. Now in his eighth season with the family-owned Aston Martin team."));

        driverRepo.save(d("Fernando Alonso", "Aston Martin", 3, 19,
            "Spanish", "ES", 14, LocalDate.of(1981, 7, 29), "Oviedo, Spain",
            32, 107, 2, 411,
            "Minardi (2001), Renault (2003–2006, 2008–2009), McLaren (2007, 2015–2018), Ferrari (2010–2014), Alpine (2021–2022), Aston Martin (2023–)",
            "Two-time world champion (2005, 2006) and the most experienced driver on the grid. Still chasing a third title with Aston Martin's long-term project."));

        driverRepo.save(d("Yuki Tsunoda", "Racing Bulls", 1, 20,
            "Japanese", "JP", 22, LocalDate.of(2000, 5, 11), "Sagamihara, Japan",
            0, 0, 0, 105,
            "AlphaTauri / RB (2021–2024), Red Bull (early 2025), Racing Bulls (2026–)",
            "Japan's first F1 race winner in a generation isn't here yet, but Tsunoda has become a mid-2020s regular. Called back into a Racing Bulls seat mid-2026 to sub for Lawson after his Red Bull promotion."));
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
        r1.addResult(new RaceResult(8, "Arvid Lindblad", "Racing Bulls", "+1 lap", 4, 9, "Lapped"));
        r1.addResult(new RaceResult(9, "Gabriel Bortoleto", "Audi", "+1 lap", 2, 10, "Lapped"));
        r1.addResult(new RaceResult(10, "Pierre Gasly", "Alpine", "+1 lap", 1, 14, "Lapped"));
        r1.addResult(new RaceResult(11, "Esteban Ocon", "Haas", "+1 lap", 0, 13, "Lapped"));
        r1.addResult(new RaceResult(12, "Alexander Albon", "Williams", "+1 lap", 0, 15, "Lapped"));
        r1.addResult(new RaceResult(13, "Liam Lawson", "Racing Bulls", "+1 lap", 0, 8, "Lapped"));
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
        r2.addResult(new RaceResult(7, "Liam Lawson", "Racing Bulls", "+1:20.588", 6, 14, "Finished"));
        r2.addResult(new RaceResult(8, "Isack Hadjar", "Red Bull", "+1:27.247", 4, 9, "Finished"));
        r2.addResult(new RaceResult(9, "Carlos Sainz", "Williams", "+1 lap", 2, 17, "Lapped"));
        r2.addResult(new RaceResult(10, "Franco Colapinto", "Alpine", "+1 lap", 1, 12, "Lapped"));
        r2.addResult(new RaceResult(11, "Nico Hülkenberg", "Audi", "+1 lap", 0, 11, "Lapped"));
        r2.addResult(new RaceResult(12, "Arvid Lindblad", "Racing Bulls", "+1 lap", 0, 15, "Lapped"));
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
        r3.addResult(new RaceResult(9, "Liam Lawson", "Racing Bulls", "+50.180", 2, 14, "Finished"));
        r3.addResult(new RaceResult(10, "Esteban Ocon", "Haas", "+51.216", 1, 12, "Finished"));
        r3.addResult(new RaceResult(11, "Nico Hülkenberg", "Audi", "+52.280", 0, 13, "Finished"));
        r3.addResult(new RaceResult(12, "Isack Hadjar", "Red Bull", "+56.154", 0, 8, "Finished"));
        r3.addResult(new RaceResult(13, "Gabriel Bortoleto", "Audi", "+59.078", 0, 9, "Finished"));
        r3.addResult(new RaceResult(14, "Arvid Lindblad", "Racing Bulls", "+59.848", 0, 10, "Finished"));
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
        r4.addResult(new RaceResult(14, "Arvid Lindblad", "Racing Bulls", "+1 lap", 0, 16, "Lapped"));
        r4.addResult(new RaceResult(15, "Fernando Alonso", "Aston Martin", "+1 lap", 0, 17, "Lapped"));
        r4.addResult(new RaceResult(16, "Sergio Pérez", "Cadillac", "+1 lap", 0, 20, "Lapped"));
        r4.addResult(new RaceResult(17, "Lance Stroll", "Aston Martin", "+1 lap", 0, 18, "Lapped"));
        r4.addResult(new RaceResult(18, "Valtteri Bottas", "Cadillac", "+1 lap", 0, 19, "Lapped"));
        r4.addResult(new RaceResult(19, "Nico Hülkenberg", "Audi", "DNF", 0, 10, "Retired"));
        r4.addResult(new RaceResult(20, "Liam Lawson", "Racing Bulls", "DNF", 0, 11, "Retired"));
        r4.addResult(new RaceResult(21, "Pierre Gasly", "Alpine", "DNF", 0, 9, "Retired"));
        r4.addResult(new RaceResult(22, "Isack Hadjar", "Red Bull", "DNF", 0, 22, "Retired"));
        raceRepo.save(r4);
        Race r5 = race("Canadian Grand Prix", "Circuit Gilles Villeneuve",
            LocalDate.of(2026, 5, 24), "Canada", 5, 70, 4.361,
            "Built on Île Notre-Dame, this stop-start layout is famous for the 'Wall of Champions' at the final chicane.");
        r5.addResult(new RaceResult(1, "Andrea Kimi Antonelli", "Mercedes", "1:28:15.758", 25, 2, "Finished"));
        r5.addResult(new RaceResult(2, "Lewis Hamilton", "Ferrari", "+10.768", 18, 5, "Finished"));
        r5.addResult(new RaceResult(3, "Max Verstappen", "Red Bull", "+11.276", 15, 6, "Finished"));
        r5.addResult(new RaceResult(4, "Charles Leclerc", "Ferrari", "+44.151", 12, 8, "Finished"));
        r5.addResult(new RaceResult(5, "Isack Hadjar", "Red Bull", "+45.214", 10, 7, "Finished"));
        r5.addResult(new RaceResult(6, "Franco Colapinto", "Alpine", "+58.512", 8, 10, "Finished"));
        r5.addResult(new RaceResult(7, "Liam Lawson", "Racing Bulls", "+1:12.747", 6, 12, "Finished"));
        r5.addResult(new RaceResult(8, "Pierre Gasly", "Alpine", "+1:13.084", 4, 14, "Finished"));
        r5.addResult(new RaceResult(9, "Carlos Sainz", "Williams", "+1:36.526", 2, 15, "Finished"));
        r5.addResult(new RaceResult(10, "Oliver Bearman", "Haas", "+1:37.561", 1, 16, "Finished"));
        r5.addResult(new RaceResult(11, "Oscar Piastri", "McLaren", "+1 lap", 0, 4, "Lapped"));
        r5.addResult(new RaceResult(12, "Nico Hülkenberg", "Audi", "+1 lap", 0, 11, "Lapped"));
        r5.addResult(new RaceResult(13, "Gabriel Bortoleto", "Audi", "+1 lap", 0, 13, "Lapped"));
        r5.addResult(new RaceResult(14, "Esteban Ocon", "Haas", "+1 lap", 0, 17, "Lapped"));
        r5.addResult(new RaceResult(15, "Lance Stroll", "Aston Martin", "+1 lap", 0, 22, "Lapped"));
        r5.addResult(new RaceResult(16, "Valtteri Bottas", "Cadillac", "+1 lap", 0, 21, "Lapped"));
        r5.addResult(new RaceResult(17, "Sergio Pérez", "Cadillac", "DNF", 0, 20, "Retired"));
        r5.addResult(new RaceResult(18, "Lando Norris", "McLaren", "DNF", 0, 3, "Retired"));
        r5.addResult(new RaceResult(19, "George Russell", "Mercedes", "DNF", 0, 1, "Retired"));
        r5.addResult(new RaceResult(20, "Fernando Alonso", "Aston Martin", "DNF", 0, 19, "Retired"));
        r5.addResult(new RaceResult(21, "Alexander Albon", "Williams", "DNF", 0, 18, "Retired"));
        r5.addResult(new RaceResult(22, "Arvid Lindblad", "Racing Bulls", "DNS", 0, 9, "Did not start"));
        raceRepo.save(r5);
        Race r6 = race("Monaco Grand Prix", "Circuit de Monaco",
            LocalDate.of(2026, 6, 7), "Monaco", 6, 78, 3.337,
            "The crown jewel — narrow streets, no run-off, and qualifying that effectively decides the race.");
        r6.addResult(new RaceResult(1, "Andrea Kimi Antonelli", "Mercedes", "2:23:31.243", 25, 1, "Finished"));
        r6.addResult(new RaceResult(2, "Lewis Hamilton", "Ferrari", "+6.271", 18, 3, "Finished"));
        r6.addResult(new RaceResult(3, "Pierre Gasly", "Alpine", "+20.369", 15, 9, "Finished"));
        r6.addResult(new RaceResult(4, "Isack Hadjar", "Red Bull", "+23.394", 12, 5, "Finished"));
        r6.addResult(new RaceResult(5, "Oscar Piastri", "McLaren", "+24.261", 10, 7, "Finished"));
        r6.addResult(new RaceResult(6, "Liam Lawson", "Racing Bulls", "+26.553", 8, 10, "Finished"));
        r6.addResult(new RaceResult(7, "Arvid Lindblad", "Racing Bulls", "+29.010", 6, 15, "Finished"));
        r6.addResult(new RaceResult(8, "Alexander Albon", "Williams", "+33.413", 4, 11, "Finished"));
        r6.addResult(new RaceResult(9, "Esteban Ocon", "Haas", "+37.140", 2, 17, "Finished"));
        r6.addResult(new RaceResult(10, "Fernando Alonso", "Aston Martin", "+41.899", 1, 21, "Finished"));
        r6.addResult(new RaceResult(11, "Gabriel Bortoleto", "Audi", "+42.748", 0, 16, "Finished"));
        r6.addResult(new RaceResult(12, "George Russell", "Mercedes", "+43.353", 0, 6, "Finished"));
        r6.addResult(new RaceResult(13, "Nico Hülkenberg", "Audi", "+44.102", 0, 13, "Finished"));
        r6.addResult(new RaceResult(14, "Franco Colapinto", "Alpine", "+48.964", 0, 14, "Finished"));
        r6.addResult(new RaceResult(15, "Sergio Pérez", "Cadillac", "+49.153", 0, 18, "Finished"));
        r6.addResult(new RaceResult(16, "Carlos Sainz", "Williams", "DNF", 0, 12, "Retired"));
        r6.addResult(new RaceResult(17, "Charles Leclerc", "Ferrari", "DNF", 0, 4, "Retired"));
        r6.addResult(new RaceResult(18, "Lance Stroll", "Aston Martin", "DNF", 0, 22, "Retired"));
        r6.addResult(new RaceResult(19, "Lando Norris", "McLaren", "DNF", 0, 8, "Retired"));
        r6.addResult(new RaceResult(20, "Oliver Bearman", "Haas", "DNF", 0, 19, "Retired"));
        r6.addResult(new RaceResult(21, "Valtteri Bottas", "Cadillac", "DNF", 0, 20, "Retired"));
        r6.addResult(new RaceResult(22, "Max Verstappen", "Red Bull", "DNF", 0, 2, "Retired"));
        raceRepo.save(r6);
        Race r7 = race("Spanish Grand Prix", "Circuit de Barcelona-Catalunya",
            LocalDate.of(2026, 6, 14), "Spain", 7, 66, 4.657,
            "The pre-season testing benchmark. Long sweepers in sector one punish any aerodynamic weakness.");
        r7.addResult(new RaceResult(1, "Lewis Hamilton", "Ferrari", "1:32:28.105", 25, 2, "Finished"));
        r7.addResult(new RaceResult(2, "George Russell", "Mercedes", "+19.561", 18, 1, "Finished"));
        r7.addResult(new RaceResult(3, "Lando Norris", "McLaren", "+23.719", 15, 4, "Finished"));
        r7.addResult(new RaceResult(4, "Max Verstappen", "Red Bull", "+40.497", 12, 5, "Finished"));
        r7.addResult(new RaceResult(5, "Oscar Piastri", "McLaren", "+58.661", 10, 7, "Finished"));
        r7.addResult(new RaceResult(6, "Isack Hadjar", "Red Bull", "+1 lap", 8, 6, "Lapped"));
        r7.addResult(new RaceResult(7, "Pierre Gasly", "Alpine", "+1 lap", 6, 14, "Lapped"));
        r7.addResult(new RaceResult(8, "Liam Lawson", "Racing Bulls", "+1 lap", 4, 8, "Lapped"));
        r7.addResult(new RaceResult(9, "Arvid Lindblad", "Racing Bulls", "+1 lap", 2, 11, "Lapped"));
        r7.addResult(new RaceResult(10, "Franco Colapinto", "Alpine", "+1 lap", 1, 13, "Lapped"));
        r7.addResult(new RaceResult(11, "Gabriel Bortoleto", "Audi", "+1 lap", 0, 12, "Lapped"));
        r7.addResult(new RaceResult(12, "Carlos Sainz", "Williams", "+1 lap", 0, 16, "Lapped"));
        r7.addResult(new RaceResult(13, "Esteban Ocon", "Haas", "+1 lap", 0, 17, "Lapped"));
        r7.addResult(new RaceResult(14, "Sergio Pérez", "Cadillac", "+1 lap", 0, 19, "Lapped"));
        r7.addResult(new RaceResult(15, "Charles Leclerc", "Ferrari", "DNF", 0, 10, "Retired"));
        r7.addResult(new RaceResult(16, "Andrea Kimi Antonelli", "Mercedes", "DNF", 0, 3, "Retired"));
        r7.addResult(new RaceResult(17, "Oliver Bearman", "Haas", "DNF", 0, 15, "Retired"));
        r7.addResult(new RaceResult(18, "Alexander Albon", "Williams", "+1 lap", 0, 18, "Lapped"));
        r7.addResult(new RaceResult(19, "Fernando Alonso", "Aston Martin", "DNF", 0, 22, "Retired"));
        r7.addResult(new RaceResult(20, "Nico Hülkenberg", "Audi", "DNF", 0, 9, "Retired"));
        r7.addResult(new RaceResult(21, "Valtteri Bottas", "Cadillac", "DNF", 0, 20, "Retired"));
        r7.addResult(new RaceResult(22, "Lance Stroll", "Aston Martin", "DNF", 0, 21, "Retired"));
        raceRepo.save(r7);
        Race r8 = race("Austrian Grand Prix", "Red Bull Ring",
            LocalDate.of(2026, 6, 28), "Austria", 8, 71, 4.318,
            "Short, sharp, and set in the Styrian mountains. Only ten corners but plenty of elevation.");
        r8.addResult(new RaceResult(1, "George Russell", "Mercedes", "1:26:37.979", 25, 1, "Finished"));
        r8.addResult(new RaceResult(2, "Max Verstappen", "Red Bull", "+1.611", 18, 5, "Finished"));
        r8.addResult(new RaceResult(3, "Andrea Kimi Antonelli", "Mercedes", "+1.986", 15, 4, "Finished"));
        r8.addResult(new RaceResult(4, "Oscar Piastri", "McLaren", "+21.809", 12, 7, "Finished"));
        r8.addResult(new RaceResult(5, "Lewis Hamilton", "Ferrari", "+26.393", 10, 3, "Finished"));
        r8.addResult(new RaceResult(6, "Isack Hadjar", "Red Bull", "+29.399", 8, 8, "Finished"));
        r8.addResult(new RaceResult(7, "Lando Norris", "McLaren", "+31.505", 6, 6, "Finished"));
        r8.addResult(new RaceResult(8, "Charles Leclerc", "Ferrari", "+45.659", 4, 2, "Finished"));
        r8.addResult(new RaceResult(9, "Liam Lawson", "Racing Bulls", "+1 lap", 2, 9, "Lapped"));
        r8.addResult(new RaceResult(10, "Arvid Lindblad", "Racing Bulls", "+1 lap", 1, 10, "Lapped"));
        r8.addResult(new RaceResult(11, "Gabriel Bortoleto", "Audi", "+1 lap", 0, 12, "Lapped"));
        r8.addResult(new RaceResult(12, "Nico Hülkenberg", "Audi", "+1 lap", 0, 14, "Lapped"));
        r8.addResult(new RaceResult(13, "Pierre Gasly", "Alpine", "+1 lap", 0, 11, "Lapped"));
        r8.addResult(new RaceResult(14, "Oliver Bearman", "Haas", "+1 lap", 0, 13, "Lapped"));
        r8.addResult(new RaceResult(15, "Franco Colapinto", "Alpine", "+1 lap", 0, 16, "Lapped"));
        r8.addResult(new RaceResult(16, "Esteban Ocon", "Haas", "+1 lap", 0, 15, "Lapped"));
        r8.addResult(new RaceResult(17, "Alexander Albon", "Williams", "+1 lap", 0, 18, "Lapped"));
        r8.addResult(new RaceResult(18, "Fernando Alonso", "Aston Martin", "+1 lap", 0, 21, "Lapped"));
        r8.addResult(new RaceResult(19, "Lance Stroll", "Aston Martin", "DNF", 0, 22, "Retired"));
        r8.addResult(new RaceResult(20, "Carlos Sainz", "Williams", "DNF", 0, 17, "Retired"));
        r8.addResult(new RaceResult(21, "Sergio Pérez", "Cadillac", "DNF", 0, 19, "Retired"));
        r8.addResult(new RaceResult(22, "Valtteri Bottas", "Cadillac", "DNF", 0, 20, "Retired"));
        raceRepo.save(r8);
        Race r9 = race("British Grand Prix", "Silverstone Circuit",
            LocalDate.of(2026, 7, 5), "United Kingdom", 9, 52, 5.891,
            "F1's spiritual home. Maggotts–Becketts–Chapel is one of the most demanding corner sequences on the calendar.");
        r9.addResult(new RaceResult(1, "Charles Leclerc", "Ferrari", "1:27:11.335", 25, 2, "Finished"));
        r9.addResult(new RaceResult(2, "George Russell", "Mercedes", "+0.427", 18, 4, "Finished"));
        r9.addResult(new RaceResult(3, "Lewis Hamilton", "Ferrari", "+0.772", 15, 3, "Finished"));
        r9.addResult(new RaceResult(4, "Lando Norris", "McLaren", "+1.149", 12, 6, "Finished"));
        r9.addResult(new RaceResult(5, "Isack Hadjar", "Red Bull", "+1.598", 10, 5, "Finished"));
        r9.addResult(new RaceResult(6, "Liam Lawson", "Racing Bulls", "+2.023", 8, 10, "Finished"));
        r9.addResult(new RaceResult(7, "Arvid Lindblad", "Racing Bulls", "+2.214", 6, 9, "Finished"));
        r9.addResult(new RaceResult(8, "Gabriel Bortoleto", "Audi", "+2.413", 4, 11, "Finished"));
        r9.addResult(new RaceResult(9, "Franco Colapinto", "Alpine", "+3.229", 2, 19, "Finished"));
        r9.addResult(new RaceResult(10, "Pierre Gasly", "Alpine", "+3.445", 1, 15, "Finished"));
        r9.addResult(new RaceResult(11, "Oscar Piastri", "McLaren", "+4.014", 0, 8, "Finished"));
        r9.addResult(new RaceResult(12, "Oliver Bearman", "Haas", "+5.245", 0, 13, "Finished"));
        r9.addResult(new RaceResult(13, "Esteban Ocon", "Haas", "+5.512", 0, 17, "Finished"));
        r9.addResult(new RaceResult(14, "Sergio Pérez", "Cadillac", "+7.403", 0, 20, "Finished"));
        r9.addResult(new RaceResult(15, "Andrea Kimi Antonelli", "Mercedes", "+8.005", 0, 1, "Finished"));
        r9.addResult(new RaceResult(16, "Valtteri Bottas", "Cadillac", "+8.162", 0, 18, "Finished"));
        r9.addResult(new RaceResult(17, "Carlos Sainz", "Williams", "+1 lap", 0, 14, "Lapped"));
        r9.addResult(new RaceResult(18, "Fernando Alonso", "Aston Martin", "+1 lap", 0, 21, "Lapped"));
        r9.addResult(new RaceResult(19, "Lance Stroll", "Aston Martin", "+1 lap", 0, 22, "Lapped"));
        r9.addResult(new RaceResult(20, "Max Verstappen", "Red Bull", "DNF", 0, 7, "Retired"));
        r9.addResult(new RaceResult(21, "Alexander Albon", "Williams", "DNF", 0, 16, "Retired"));
        r9.addResult(new RaceResult(22, "Nico Hülkenberg", "Audi", "DNF", 0, 12, "Retired"));
        raceRepo.save(r9);
        Race r10 = race("Belgian Grand Prix", "Circuit de Spa-Francorchamps",
            LocalDate.of(2026, 7, 19), "Belgium", 10, 44, 7.004,
            "The longest lap of the year, snaking through the Ardennes forest. Eau Rouge into Raidillon is iconic.");
        r10.addResult(new RaceResult(1, "Andrea Kimi Antonelli", "Mercedes", "1:24:42.479", 25, 1, "Finished"));
        r10.addResult(new RaceResult(2, "Charles Leclerc", "Ferrari", "+1.952", 18, 4, "Finished"));
        r10.addResult(new RaceResult(3, "Max Verstappen", "Red Bull", "+11.586", 15, 2, "Finished"));
        r10.addResult(new RaceResult(4, "Lewis Hamilton", "Ferrari", "+17.245", 12, 5, "Finished"));
        r10.addResult(new RaceResult(5, "Oscar Piastri", "McLaren", "+18.988", 10, 6, "Finished"));
        r10.addResult(new RaceResult(6, "Isack Hadjar", "Red Bull", "+23.307", 8, 21, "Finished"));
        r10.addResult(new RaceResult(7, "Lando Norris", "McLaren", "+24.014", 6, 13, "Finished"));
        r10.addResult(new RaceResult(8, "Gabriel Bortoleto", "Audi", "+49.140", 4, 8, "Finished"));
        r10.addResult(new RaceResult(9, "Arvid Lindblad", "Racing Bulls", "+50.406", 2, 7, "Finished"));
        r10.addResult(new RaceResult(10, "Franco Colapinto", "Alpine", "+1:16.037", 1, 11, "Finished"));
        r10.addResult(new RaceResult(11, "Pierre Gasly", "Alpine", "+1:16.991", 0, 10, "Finished"));
        r10.addResult(new RaceResult(12, "Liam Lawson", "Racing Bulls", "+1:17.523", 0, 9, "Finished"));
        r10.addResult(new RaceResult(13, "Nico Hülkenberg", "Audi", "+1:18.348", 0, 12, "Finished"));
        r10.addResult(new RaceResult(14, "Oliver Bearman", "Haas", "+1:34.465", 0, 14, "Finished"));
        r10.addResult(new RaceResult(15, "Alexander Albon", "Williams", "+1:44.684", 0, 15, "Finished"));
        r10.addResult(new RaceResult(16, "Carlos Sainz", "Williams", "+1:45.856", 0, 19, "Finished"));
        r10.addResult(new RaceResult(17, "Esteban Ocon", "Haas", "+1:50.925", 0, 16, "Finished"));
        r10.addResult(new RaceResult(18, "Valtteri Bottas", "Cadillac", "+1 lap", 0, 17, "Lapped"));
        r10.addResult(new RaceResult(19, "Fernando Alonso", "Aston Martin", "+1 lap", 0, 22, "Lapped"));
        r10.addResult(new RaceResult(20, "Lance Stroll", "Aston Martin", "DNF", 0, 20, "Retired"));
        r10.addResult(new RaceResult(21, "Sergio Pérez", "Cadillac", "DNF", 0, 18, "Retired"));
        r10.addResult(new RaceResult(22, "George Russell", "Mercedes", "DNF", 0, 3, "Retired"));
        raceRepo.save(r10);
        Race r11 = race("Hungarian Grand Prix", "Hungaroring",
            LocalDate.of(2026, 7, 26), "Hungary", 11, 70, 4.381,
            "A twisty, low-speed circuit often called 'Monaco without the walls'. Track position is everything.");
        r11.addResult(new RaceResult(1, "Lando Norris", "McLaren", "1:39:56.180", 25, 1, "Finished"));
        r11.addResult(new RaceResult(2, "Max Verstappen", "Red Bull", "+15.080", 18, 4, "Finished"));
        r11.addResult(new RaceResult(3, "Andrea Kimi Antonelli", "Mercedes", "+18.728", 15, 7, "Finished"));
        r11.addResult(new RaceResult(4, "Charles Leclerc", "Ferrari", "+23.840", 12, 2, "Finished"));
        r11.addResult(new RaceResult(5, "Lewis Hamilton", "Ferrari", "+24.540", 10, 5, "Finished"));
        r11.addResult(new RaceResult(6, "Isack Hadjar", "Red Bull", "+55.488", 8, 8, "Finished"));
        r11.addResult(new RaceResult(7, "George Russell", "Mercedes", "+57.503", 6, 6, "Finished"));
        r11.addResult(new RaceResult(8, "Liam Lawson", "Racing Bulls", "+1 lap", 4, 11, "Lapped"));
        r11.addResult(new RaceResult(9, "Nico Hülkenberg", "Audi", "+1 lap", 2, 10, "Lapped"));
        r11.addResult(new RaceResult(10, "Arvid Lindblad", "Racing Bulls", "+1 lap", 1, 9, "Lapped"));
        r11.addResult(new RaceResult(11, "Gabriel Bortoleto", "Audi", "+1 lap", 0, 14, "Lapped"));
        r11.addResult(new RaceResult(12, "Pierre Gasly", "Alpine", "+1 lap", 0, 12, "Lapped"));
        r11.addResult(new RaceResult(13, "Lance Stroll", "Aston Martin", "+1 lap", 0, 20, "Lapped"));
        r11.addResult(new RaceResult(14, "Fernando Alonso", "Aston Martin", "+1 lap", 0, 16, "Lapped"));
        r11.addResult(new RaceResult(15, "Franco Colapinto", "Alpine", "+1 lap", 0, 13, "Lapped"));
        r11.addResult(new RaceResult(16, "Esteban Ocon", "Haas", "+1 lap", 0, 15, "Lapped"));
        r11.addResult(new RaceResult(17, "Alexander Albon", "Williams", "+1 lap", 0, 19, "Lapped"));
        r11.addResult(new RaceResult(18, "Carlos Sainz", "Williams", "+1 lap", 0, 18, "Lapped"));
        r11.addResult(new RaceResult(19, "Oliver Bearman", "Haas", "+1 lap", 0, 17, "Lapped"));
        r11.addResult(new RaceResult(20, "Oscar Piastri", "McLaren", "DNF", 0, 3, "Retired"));
        r11.addResult(new RaceResult(21, "Sergio Pérez", "Cadillac", "DNF", 0, 22, "Retired"));
        r11.addResult(new RaceResult(22, "Valtteri Bottas", "Cadillac", "DNF", 0, 21, "Retired"));
        raceRepo.save(r11);
        Race r12 = race("Dutch Grand Prix", "Circuit Park Zandvoort",
            LocalDate.of(2026, 8, 23), "Netherlands", 12, 72, 4.259,
            "A flowing seaside circuit with two banked corners — one of them, Turn 3, is taken almost flat.");
        r12.addResult(new RaceResult(1, "Lando Norris", "McLaren", "2:04:44.859", 25, 1, "Finished"));
        r12.addResult(new RaceResult(2, "Andrea Kimi Antonelli", "Mercedes", "+11.536", 18, 3, "Finished"));
        r12.addResult(new RaceResult(3, "George Russell", "Mercedes", "+15.906", 15, 2, "Finished"));
        r12.addResult(new RaceResult(4, "Lewis Hamilton", "Ferrari", "+16.755", 12, 5, "Finished"));
        r12.addResult(new RaceResult(5, "Charles Leclerc", "Ferrari", "+17.258", 10, 6, "Finished"));
        r12.addResult(new RaceResult(6, "Oscar Piastri", "McLaren", "+32.332", 8, 4, "Finished"));
        r12.addResult(new RaceResult(7, "Liam Lawson", "Red Bull", "+1:19.915", 6, 8, "Finished"));
        r12.addResult(new RaceResult(8, "Nico Hülkenberg", "Audi", "+1 lap", 4, 13, "Lapped"));
        r12.addResult(new RaceResult(9, "Fernando Alonso", "Aston Martin", "+1 lap", 2, 18, "Lapped"));
        r12.addResult(new RaceResult(10, "Pierre Gasly", "Alpine", "+1 lap", 1, 11, "Lapped"));
        r12.addResult(new RaceResult(11, "Yuki Tsunoda", "Racing Bulls", "+1 lap", 0, 12, "Lapped"));
        r12.addResult(new RaceResult(12, "Arvid Lindblad", "Racing Bulls", "+1 lap", 0, 10, "Lapped"));
        r12.addResult(new RaceResult(13, "Gabriel Bortoleto", "Audi", "+1 lap", 0, 9, "Lapped"));
        r12.addResult(new RaceResult(14, "Franco Colapinto", "Alpine", "+1 lap", 0, 14, "Lapped"));
        r12.addResult(new RaceResult(15, "Sergio Pérez", "Cadillac", "+1 lap", 0, 22, "Lapped"));
        r12.addResult(new RaceResult(16, "Carlos Sainz", "Williams", "+1 lap", 0, 17, "Lapped"));
        r12.addResult(new RaceResult(17, "Alexander Albon", "Williams", "DNF", 0, 16, "Retired"));
        r12.addResult(new RaceResult(18, "Valtteri Bottas", "Cadillac", "DNF", 0, 21, "Retired"));
        r12.addResult(new RaceResult(19, "Esteban Ocon", "Haas", "DNF", 0, 15, "Retired"));
        r12.addResult(new RaceResult(20, "Lance Stroll", "Aston Martin", "DNF", 0, 19, "Retired"));
        r12.addResult(new RaceResult(21, "Oliver Bearman", "Haas", "DNF", 0, 20, "Retired"));
        r12.addResult(new RaceResult(22, "Max Verstappen", "Red Bull", "DNF", 0, 7, "Retired"));
        raceRepo.save(r12);
        Race r13 = race("Italian Grand Prix", "Autodromo Nazionale di Monza",
            LocalDate.of(2026, 9, 6), "Italy", 13, 53, 5.793,
            "The 'Temple of Speed'. Lowest downforce setup of the year and the highest average lap speed.");
        r13.addResult(new RaceResult(1, "Andrea Kimi Antonelli", "Mercedes", "1:51:15.281", 25, 19, "Finished"));
        r13.addResult(new RaceResult(2, "George Russell", "Mercedes", "+3.857", 18, 2, "Finished"));
        r13.addResult(new RaceResult(3, "Max Verstappen", "Red Bull", "+14.718", 15, 5, "Finished"));
        r13.addResult(new RaceResult(4, "Lando Norris", "McLaren", "+19.056", 12, 8, "Finished"));
        r13.addResult(new RaceResult(5, "Oscar Piastri", "McLaren", "+19.253", 10, 6, "Finished"));
        r13.addResult(new RaceResult(6, "Lewis Hamilton", "Ferrari", "+24.655", 8, 4, "Finished"));
        r13.addResult(new RaceResult(7, "Pierre Gasly", "Alpine", "+27.351", 6, 1, "Finished"));
        r13.addResult(new RaceResult(8, "Arvid Lindblad", "Racing Bulls", "+45.136", 4, 9, "Finished"));
        r13.addResult(new RaceResult(9, "Franco Colapinto", "Alpine", "+47.353", 2, 7, "Finished"));
        r13.addResult(new RaceResult(10, "Yuki Tsunoda", "Racing Bulls", "+58.187", 1, 15, "Finished"));
        r13.addResult(new RaceResult(11, "Gabriel Bortoleto", "Audi", "+1:05.187", 0, 10, "Finished"));
        r13.addResult(new RaceResult(12, "Nico Hülkenberg", "Audi", "+1:06.187", 0, 12, "Finished"));
        r13.addResult(new RaceResult(13, "Carlos Sainz", "Williams", "+1:14.117", 0, 13, "Finished"));
        r13.addResult(new RaceResult(14, "Liam Lawson", "Red Bull", "+1:15.609", 0, 22, "Finished"));
        r13.addResult(new RaceResult(15, "Oliver Bearman", "Haas", "+1:18.958", 0, 11, "Finished"));
        r13.addResult(new RaceResult(16, "Esteban Ocon", "Haas", "+1 lap", 0, 14, "Lapped"));
        r13.addResult(new RaceResult(17, "Alexander Albon", "Williams", "+1 lap", 0, 20, "Lapped"));
        r13.addResult(new RaceResult(18, "Sergio Pérez", "Cadillac", "+1 lap", 0, 17, "Lapped"));
        r13.addResult(new RaceResult(19, "Valtteri Bottas", "Cadillac", "+1 lap", 0, 16, "Lapped"));
        r13.addResult(new RaceResult(20, "Lance Stroll", "Aston Martin", "DNF", 0, 18, "Retired"));
        r13.addResult(new RaceResult(21, "Fernando Alonso", "Aston Martin", "DNF", 0, 21, "Retired"));
        r13.addResult(new RaceResult(22, "Charles Leclerc", "Ferrari", "DNF", 0, 3, "Retired"));
        raceRepo.save(r13);
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
            "How the new Madring circuit looks ahead of its inaugural GP",
            "Gallery showcasing the brand-new Madring in Madrid as it prepares for its first Formula 1 Grand Prix.",
            now.minusHours(3),
            "https://www.formula1.com/en/latest/article/gallery-how-the-new-madring-circuit-looks-ahead-of-its-inaugural-grand-prix.1lTvNgTWC4KjCLwjoVvRVy",
            "https://media.formula1.com/image/upload/t_16by9Centre/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Spain%20(Madrid)/16x9%20single%20image%20-%202026-09-09T161943.864.webp"
        ));
        newsRepo.save(new News(
            "5 storylines we're excited about ahead of the Spanish GP",
            "A preview of the inaugural Madring weekend — from Sainz on home soil to a title fight running hot.",
            now.minusHours(8),
            "https://www.formula1.com/en/latest/article/its-race-week-5-storylines-were-excited-about-ahead-of-the-2026-spanish-grand-prix.7bNRgNDfu8bVIRr80W4T9x",
            "https://media.formula1.com/image/upload/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Spain%20(Madrid)/It's%20Race%20Week%20Display%20template%20(6).webp"
        ));
        newsRepo.save(new News(
            "Exclusive: Sainz on bringing F1 back to his home city of Madrid",
            "The Williams driver reflects on what it means to have Formula 1 racing in his hometown for the first time.",
            now.minusHours(14),
            "https://www.formula1.com/en/latest/article/exclusive-sainz-on-bringing-f1-back-to-his-home-city-of-madrid.1nvETS54jP1KT8ro632NjN",
            "https://media.formula1.com/image/upload/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Spain%20(Madrid)/EXCLUSIVEINTERVIEWADHOC%20FEATURE%20V4%20DISPLAY%20(10).webp"
        ));
        newsRepo.save(new News(
            "De la Rosa on how Aston Martin's risky upgrades paid off",
            "The former F1 driver breaks down how Aston Martin's late-summer upgrade gamble turned into their best weekend of the season.",
            now.minusDays(1),
            "https://www.formula1.com/en/latest/article/one-of-the-best-moments-weve-had-de-la-rosa-on-how-aston-martins-risky-upgrade-strategy-paid-off.7upoB0qtRkJJyvu90aoeIE",
            "https://media.formula1.com/image/upload/t_16by9North/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Aston%20Martin/GettyImages-2287444787.webp"
        ));
        newsRepo.save(new News(
            "What is the weather forecast for the Spanish Grand Prix?",
            "Meteorological conditions expected during the Spanish Grand Prix at the brand-new Madring circuit.",
            now.minusDays(1).minusHours(6),
            "https://www.formula1.com/en/latest/article/what-is-the-weather-forecast-for-the-2026-spanish-grand-prix.5TA3WMRX4zHfd8MWH0KjtI",
            "https://media.formula1.com/image/upload/t_16by9Centre/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Spain%20(Madrid)/GetMyImages-2291863556.webp"
        ));
        newsRepo.save(new News(
            "What tyres will the teams have for the Spanish Grand Prix?",
            "Technical breakdown of Pirelli's compound choice and per-driver tyre allocations for the Madrid weekend.",
            now.minusDays(2),
            "https://www.formula1.com/en/latest/article/what-tyres-will-the-teams-and-drivers-have-for-the-2026-spanish-grand-prix.2vlcVOBnZUFRCooVcqWG7n",
            "https://media.formula1.com/image/upload/t_16by9Centre/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Italy/GettyImages-2293525925.webp"
        ));
        newsRepo.save(new News(
            "8 of the best debut Grands Prix in F1 history",
            "From title fights to crashes to stunning overtakes — a look back at F1's most memorable inaugural rounds.",
            now.minusDays(2).minusHours(8),
            "https://www.formula1.com/en/latest/article/from-title-fights-to-crashes-and-stunning-overtakes-8-of-the-best-debut-grands-prix.7ELl42hCinmG9cRxopzrGv",
            "https://media.formula1.com/image/upload/t_16by9Centre/c_lfill,w_3392/q_auto/v1740000001/fom-website/2026/Miscellaneous/Historic/GettyImages-1357344473.webp"
        ));
        newsRepo.save(new News(
            "Our early Bet Builder picks for the Spanish Grand Prix",
            "Betting recommendations and selections for the upcoming Spanish Grand Prix at the new Madrid circuit.",
            now.minusDays(3),
            "https://www.formula1.com/en/latest/article/our-early-bet-builder-selections-made-for-the-spanish-grand-prix.4h1GWpt3fyoQD0gMBHITPA",
            "https://media.formula1.com/image/upload/t_16by9Centre/c_lfill,w_3392/q_auto/v1740000001/trackside-images/2026/F1_Grand_Prix_of_Spain___Previews/2294328967.webp"
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
