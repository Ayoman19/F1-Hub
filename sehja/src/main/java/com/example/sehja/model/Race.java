package com.example.sehja.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String circuit;
    private LocalDate date;
    private String country;
    private Integer round;
    private Integer laps;
    private Double circuitLengthKm;
    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String layoutUrl;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    private List<RaceResult> results = new ArrayList<>();

    public Race() {}

    public Race(String name, String circuit, LocalDate date, String country) {
        this.name = name;
        this.circuit = circuit;
        this.date = date;
        this.country = country;
    }

    public Race(String name, String circuit, LocalDate date, String country,
                Integer round, Integer laps, Double circuitLengthKm, String description) {
        this.name = name;
        this.circuit = circuit;
        this.date = date;
        this.country = country;
        this.round = round;
        this.laps = laps;
        this.circuitLengthKm = circuitLengthKm;
        this.description = description;
    }

    public void addResult(RaceResult result) {
        result.setRace(this);
        this.results.add(result);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCircuit() { return circuit; }
    public void setCircuit(String circuit) { this.circuit = circuit; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public Integer getRound() { return round; }
    public void setRound(Integer round) { this.round = round; }
    public Integer getLaps() { return laps; }
    public void setLaps(Integer laps) { this.laps = laps; }
    public Double getCircuitLengthKm() { return circuitLengthKm; }
    public void setCircuitLengthKm(Double v) { this.circuitLengthKm = v; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLayoutUrl() { return layoutUrl; }
    public void setLayoutUrl(String layoutUrl) { this.layoutUrl = layoutUrl; }
    public List<RaceResult> getResults() { return results; }
    public void setResults(List<RaceResult> results) { this.results = results; }
}
