package com.betapi.models;

import java.util.List;

public class Statistics {
    Long betNumber;
    Long gameNumber;
    Long gameEndedNumber;
    Double totalAmount;
    List<ChartElement> chartElements;
    List<ChartElement> top10Games;

    public Long getGameEndedNumber() {
        return gameEndedNumber;
    }

    public void setGameEndedNumber(Long gameEndedNumber) {
        this.gameEndedNumber = gameEndedNumber;
    }

    public List<ChartElement> getChartElements() {
        return chartElements;
    }

    public void setChartElements(List<ChartElement> chartElements) {
        this.chartElements = chartElements;
    }

    public List<ChartElement> getTop10Games() {
        return top10Games;
    }

    public void setTop10Games(List<ChartElement> top10Games) {
        this.top10Games = top10Games;
    }

    public Statistics() {
    }

    public Long getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(Long betNumber) {
        this.betNumber = betNumber;
    }

    public Long getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(Long gameNumber) {
        this.gameNumber = gameNumber;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
