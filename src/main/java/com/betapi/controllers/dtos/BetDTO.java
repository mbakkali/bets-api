package com.betapi.controllers.dtos;

import com.betapi.models.BetChoice;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

public class BetDTO {

    private Long gameId;
    private Long ownerId;
    private Double amount;
    private Double combinedAmount;
    private BetChoice betChoice;
    private LocalDateTime datetime;


    public BetDTO(){

    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public BetChoice getBetChoice() {
        return betChoice;
    }

    public void setBetChoice(BetChoice betChoice) {
        this.betChoice = betChoice;
    }

    public Double getCombinedAmount() {
        return combinedAmount;
    }

    public void setCombinedAmount(Double combinedAmount) {
        this.combinedAmount = combinedAmount;
    }
}
