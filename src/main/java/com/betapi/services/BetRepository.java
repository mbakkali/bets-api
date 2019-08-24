package com.betapi.services;

import com.betapi.model.Bet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetRepository extends CrudRepository<Bet,Long> {
    List<Bet> findAllByGameId(Long id);
    void deleteAllById(List<Long> ids);
}
