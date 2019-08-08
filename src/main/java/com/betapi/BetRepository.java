package com.betapi;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetRepository extends CrudRepository<Bet,Long> {
    List<Bet> findAllByGameId(Long id);
}
