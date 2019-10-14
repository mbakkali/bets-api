package com.betapi.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long gameId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Column
    private Long ownerId;

    @Column
    private Double amount;

    @Column
    private Double combinedAmount;

    @Column
    private BetChoice betChoice;

    @Column
    @CreationTimestamp
    private LocalDateTime datetime;


    public Bet(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
