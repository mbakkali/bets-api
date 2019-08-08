package com.betapi;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String teamA;

    @Column
    private String teamB;

    @Column
    private Double oddA;

    @Column
    private Double oddB;

    @Column
    private LocalDateTime datetime;

    public Game(){

    }

    public Game(String teamA, String teamB, Double oddA, Double oddB, LocalDateTime datetime) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.oddA = oddA;
        this.oddB = oddB;
        this.datetime = datetime;
    }

    public Double getOddB() {
        return oddB;
    }

    public void setOddB(Double oddB) {
        this.oddB = oddB;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public Double getOddA() {
        return oddA;
    }

    public void setOddA(Double oddA) {
        this.oddA = oddA;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
