package com.example.sehja.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fullName;
    private Integer position;
    private Integer points;
    private Integer wins;
    private String nationality;

    @Column(length = 500)
    private String logoUrl;

    @Column(length = 500)
    private String carImageUrl;

    private String primaryColor;
    private String accentColor;

    private Integer founded;
    private String base;
    private String engine;
    private String principal;
    private Integer constructorTitles;
    private Integer driverTitles;
    private Integer totalWins;

    @Column(length = 2000)
    private String history;

    private String constructorSlug;

    @Transient
    private String wikipediaUrl;

    public Team() {}

    public Team(String name, String fullName, int position, int points, int wins,
                String nationality, String logoUrl, String carImageUrl,
                String primaryColor, String accentColor) {
        this.name = name;
        this.fullName = fullName;
        this.position = position;
        this.points = points;
        this.wins = wins;
        this.nationality = nationality;
        this.logoUrl = logoUrl;
        this.carImageUrl = carImageUrl;
        this.primaryColor = primaryColor;
        this.accentColor = accentColor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }
    public Integer getWins() { return wins; }
    public void setWins(Integer wins) { this.wins = wins; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getCarImageUrl() { return carImageUrl; }
    public void setCarImageUrl(String carImageUrl) { this.carImageUrl = carImageUrl; }
    public String getPrimaryColor() { return primaryColor; }
    public void setPrimaryColor(String primaryColor) { this.primaryColor = primaryColor; }
    public String getAccentColor() { return accentColor; }
    public void setAccentColor(String accentColor) { this.accentColor = accentColor; }
    public Integer getFounded() { return founded; }
    public void setFounded(Integer founded) { this.founded = founded; }
    public String getBase() { return base; }
    public void setBase(String base) { this.base = base; }
    public String getEngine() { return engine; }
    public void setEngine(String engine) { this.engine = engine; }
    public String getPrincipal() { return principal; }
    public void setPrincipal(String principal) { this.principal = principal; }
    public Integer getConstructorTitles() { return constructorTitles; }
    public void setConstructorTitles(Integer constructorTitles) { this.constructorTitles = constructorTitles; }
    public Integer getDriverTitles() { return driverTitles; }
    public void setDriverTitles(Integer driverTitles) { this.driverTitles = driverTitles; }
    public Integer getTotalWins() { return totalWins; }
    public void setTotalWins(Integer totalWins) { this.totalWins = totalWins; }
    public String getHistory() { return history; }
    public void setHistory(String history) { this.history = history; }
    public String getConstructorSlug() { return constructorSlug; }
    public void setConstructorSlug(String constructorSlug) { this.constructorSlug = constructorSlug; }
    public String getWikipediaUrl() { return wikipediaUrl; }
    public void setWikipediaUrl(String wikipediaUrl) { this.wikipediaUrl = wikipediaUrl; }
}
