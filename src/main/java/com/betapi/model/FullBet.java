package com.betapi.model;

import java.time.LocalDateTime;

public class FullBet {

    private Long id;
    private Long gameId;
    private String teamA;
    private String teamB;
    private Double oddA;
    private Double oddB;
    private Double oddN;
    private BetChoice betChoice;
    private LocalDateTime gametime;
    private Double amount;
    private LocalDateTime savedtime;
    private Long ownerId;

    public FullBet(){

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

    public Double getOddN() {
        return oddN;
    }

    public void setOddN(Double oddN) {
        this.oddN = oddN;
    }

    public BetChoice getBetChoice() {
        return betChoice;
    }

    public void setBetChoice(BetChoice betChoice) {
        this.betChoice = betChoice;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
