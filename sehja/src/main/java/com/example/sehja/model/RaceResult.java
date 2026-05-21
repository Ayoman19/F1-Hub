package com.example.sehja.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "race_results")
public class RaceResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    @JsonIgnore
    private Race race;

    private Integer position;
    private String driverName;
    private String team;
    private String time;
    private Integer points;
    private Integer gridPosition;
    private String status;

    public RaceResult() {}

    public RaceResult(Integer position, String driverName, String team,
                      String time, Integer points, Integer gridPosition, String status) {
        this.position = position;
        this.driverName = driverName;
        this.team = team;
        this.time = time;
        this.points = points;
        this.gridPosition = gridPosition;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Race getRace() { return race; }
    public void setRace(Race race) { this.race = race; }
    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }
    public Integer getGridPosition() { return gridPosition; }
    public void setGridPosition(Integer gridPosition) { this.gridPosition = gridPosition; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
