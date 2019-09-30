package com.betapi.repositories;

import com.betapi.models.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game,Long> {

    List<Game> findAllByOrderByDatetimeAsc();

}
