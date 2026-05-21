package com.example.sehja.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 2000)
    private String content;
    private LocalDateTime publishedDate;
    @Column(length = 500)
    private String url;

    @Column(length = 500)
    private String imageUrl;

    public News() {}

    public News(String title, String content, LocalDateTime publishedDate, String url) {
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
        this.url = url;
    }

    public News(String title, String content, LocalDateTime publishedDate, String url, String imageUrl) {
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDateTime publishedDate) { this.publishedDate = publishedDate; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
