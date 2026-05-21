package com.example.sehja.controller;

import com.example.sehja.model.Driver;
import com.example.sehja.model.News;
import com.example.sehja.model.Race;
import com.example.sehja.repository.DriverRepository;
import com.example.sehja.repository.NewsRepository;
import com.example.sehja.repository.RaceRepository;
import com.example.sehja.repository.VideoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Controller
public class PageController {

    private final NewsRepository newsRepo;
    private final DriverRepository driverRepo;
    private final RaceRepository raceRepo;
    private final VideoRepository videoRepo;

    public PageController(NewsRepository newsRepo, DriverRepository driverRepo, RaceRepository raceRepo,
                          VideoRepository videoRepo) {
        this.newsRepo = newsRepo;
        this.driverRepo = driverRepo;
        this.raceRepo = raceRepo;
        this.videoRepo = videoRepo;
    }

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/standings")
    public String standings() { return "standings"; }

    @GetMapping("/calendar")
    public String calendar() { return "calendar"; }

    @GetMapping("/news")
    public String news() { return "news"; }

    @GetMapping("/videos")
    public String videos(Model model) {
        model.addAttribute("videos", videoRepo.findAllByOrderBySortOrderAsc());
        return "videos";
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
