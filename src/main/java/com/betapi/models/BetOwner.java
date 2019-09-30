package com.betapi.models;

import com.google.common.base.Strings;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class BetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @CreationTimestamp
    private LocalDateTime timestamp;

    @Column
    private String bets;


    public BetOwner(Long id, LocalDateTime timestamp, String bets){
        this.id = id;
        this.timestamp = timestamp;
        this.bets = bets;
    }

    public BetOwner(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Long> getBets() {
        if(Strings.isNullOrEmpty(bets)){
            return new ArrayList<>();
        }
        return Arrays.stream(bets.split(",")).map(Long::parseLong).collect(Collectors.toList());
    }

    public void setBets(List<Long> bets) {
        this.bets =  bets.stream().map(Object::toString)
                .collect(Collectors.joining(","));
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
