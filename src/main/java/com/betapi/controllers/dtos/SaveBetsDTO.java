package com.betapi.controllers.dtos;

import com.betapi.models.BetChoice;

import java.time.LocalDateTime;
import java.util.List;

public class SaveBetsDTO {


    private List<BetDTO> bets;
    private Double combinedBetAmount;

    public SaveBetsDTO(){
    }

    public List<BetDTO> getBets() {
        return bets;
    }

    public void setBets(List<BetDTO> bets) {
        this.bets = bets;
    }

    public Double getCombinedBetAmount() {
        return combinedBetAmount;
    }

    public void setCombinedBetAmount(Double combinedBetAmount) {
        this.combinedBetAmount = combinedBetAmount;
    }
}
