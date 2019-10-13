package com.betapi.controllers.mappers;

import com.betapi.controllers.dtos.BetDTO;
import com.betapi.controllers.dtos.UserDTO;
import com.betapi.models.Bet;
import com.betapi.models.User;
import org.springframework.beans.BeanUtils;

public class BetMapper {

    public static Bet toBet(BetDTO betDto){
        Bet bet = new Bet();
        BeanUtils.copyProperties(betDto, bet);
        return bet;
    }
}
