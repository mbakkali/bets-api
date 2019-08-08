package com.betapi;

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

    @Column
    private Double amount;

    @Column
    @CreationTimestamp
    private LocalDateTime datetime;

    public Bet(Long id, Long gameId, Double amount, LocalDateTime datetime){

        this.id = id;
        this.gameId = gameId;
        this.amount = amount;
        this.datetime = datetime;
    }

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

}
