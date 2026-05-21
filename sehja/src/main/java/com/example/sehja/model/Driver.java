package com.example.sehja.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String team;
    private int points;
    private int position;

    private String nationality;
    private String countryCode;
    private Integer raceNumber;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private Integer careerWins;
    private Integer careerPodiums;
    private Integer championships;
    private Integer gpEntered;

    @Column(length = 600)
    private String previousTeams;

    @Column(length = 1200)
    private String bio;

    private String teamColor;
    private String teamColorAccent;

    @Column(length = 500)
    private String photoUrl;

    public Driver() {}

    public Driver(String name, String team, int points, int position) {
        this.name = name;
        this.team = team;
        this.points = points;
        this.position = position;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
    public Integer getRaceNumber() { return raceNumber; }
    public void setRaceNumber(Integer raceNumber) { this.raceNumber = raceNumber; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getPlaceOfBirth() { return placeOfBirth; }
    public void setPlaceOfBirth(String placeOfBirth) { this.placeOfBirth = placeOfBirth; }
    public Integer getCareerWins() { return careerWins; }
    public void setCareerWins(Integer careerWins) { this.careerWins = careerWins; }
    public Integer getCareerPodiums() { return careerPodiums; }
    public void setCareerPodiums(Integer careerPodiums) { this.careerPodiums = careerPodiums; }
    public Integer getChampionships() { return championships; }
    public void setChampionships(Integer championships) { this.championships = championships; }
    public Integer getGpEntered() { return gpEntered; }
    public void setGpEntered(Integer gpEntered) { this.gpEntered = gpEntered; }
    public String getPreviousTeams() { return previousTeams; }
    public void setPreviousTeams(String previousTeams) { this.previousTeams = previousTeams; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getTeamColor() { return teamColor; }
    public void setTeamColor(String teamColor) { this.teamColor = teamColor; }
    public String getTeamColorAccent() { return teamColorAccent; }
    public void setTeamColorAccent(String teamColorAccent) { this.teamColorAccent = teamColorAccent; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}
