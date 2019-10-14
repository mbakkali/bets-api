package com.betapi.controllers.mappers;

import com.betapi.controllers.dtos.BetDTO;
import com.betapi.controllers.dtos.FullBetDTO;
import com.betapi.models.Bet;
import com.betapi.models.FullBet;
import org.springframework.beans.BeanUtils;

public class BetMapper {

    public static Bet toBet(BetDTO betDto){
        Bet bet = new Bet();
        BeanUtils.copyProperties(betDto, bet);
        return bet;
    }

    public static FullBetDTO toFullBetDTO(FullBet fullBet){
        FullBetDTO bet = new FullBetDTO();
        BeanUtils.copyProperties(fullBet, bet);
        return bet;
    }
}
