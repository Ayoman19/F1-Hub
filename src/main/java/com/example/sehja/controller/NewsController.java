package com.example.sehja.controller;

import com.example.sehja.model.News;
import com.example.sehja.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping
    public List<News> getAllNews() {
        return newsRepository.findAllByOrderByPublishedDateDesc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNews(@PathVariable Long id) {
        return newsRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public News addNews(@RequestBody News news) {
        return newsRepository.save(news);
    }
}
