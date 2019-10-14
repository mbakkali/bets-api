package com.betapi.controllers.dtos;

import java.util.List;

public class BetsDTO {


    private List<FullBetDTO> bets;
    private Double combinedBetAmount;

    public BetsDTO(){
    }

    public Double getCombinedBetAmount() {
        return combinedBetAmount;
    }

    public void setCombinedBetAmount(Double combinedBetAmount) {
        this.combinedBetAmount = combinedBetAmount;
    }

    public List<FullBetDTO> getBets() {
        return bets;
    }

    public void setBets(List<FullBetDTO> bets) {
        this.bets = bets;
    }
}
