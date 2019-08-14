package com.betapi.services;

import com.betapi.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game,Long> {

    List<Game> findAllByOrderByDatetimeAsc();

}
