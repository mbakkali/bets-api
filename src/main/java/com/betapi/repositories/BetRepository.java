package com.betapi.repositories;

import com.betapi.models.Bet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetRepository extends CrudRepository<Bet,Long> {
    List<Bet> findAllByGameId(Long id);
    void deleteAllById(List<Long> ids);
    void deleteBetsById(List<Long> ids);
}
