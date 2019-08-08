package com.betapi;

import java.time.LocalDateTime;

public class FullBet {

    private Long id;
    private Long gameId;
    private String teamA;
    private String teamB;
    private Double oddA;
    private Double oddB;
    private LocalDateTime gametime;
    private Double amount;
    private LocalDateTime savedtime;

    public FullBet(){

    }

    public FullBet(Long id, String teamA, String teamB, Double oddA, Double oddB, LocalDateTime gametime, Double amount, LocalDateTime savedtime) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
        this.oddA = oddA;
        this.oddB = oddB;
        this.gametime = gametime;
        this.amount = amount;
        this.savedtime = savedtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getOddB() {
        return oddB;
    }

    public void setOddB(Double oddB) {
        this.oddB = oddB;
    }

    public LocalDateTime getGametime() {
        return gametime;
    }

    public void setGametime(LocalDateTime gametime) {
        this.gametime = gametime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getSavedtime() {
        return savedtime;
    }

    public void setSavedtime(LocalDateTime savedtime) {
        this.savedtime = savedtime;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
