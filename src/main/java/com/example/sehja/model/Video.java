package com.example.sehja.model;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 400)
    private String title;

    @Column(length = 600)
    private String thumbnailUrl;

    @Column(length = 600)
    private String sourceUrl;

    @Column(length = 80)
    private String category;

    private Integer sortOrder;

    public Video() {}

    public Video(String title, String thumbnailUrl, String sourceUrl, String category, Integer sortOrder) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.sourceUrl = sourceUrl;
        this.category = category;
        this.sortOrder = sortOrder;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
