package com.betapi.controllers;

import com.betapi.controllers.dtos.BetsDTO;
import com.betapi.controllers.dtos.SaveBetsDTO;
import com.betapi.controllers.exceptions.BetOwnerNotFoundException;
import com.betapi.controllers.exceptions.GameNotFoundException;
import com.betapi.controllers.mappers.BetMapper;
import com.betapi.models.*;
import com.betapi.repositories.BetOwnerRepository;
import com.betapi.repositories.BetRepository;
import com.betapi.repositories.GameRepository;
import com.betapi.services.BetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class DefaultController {

    private Logger logger = LoggerFactory.getLogger(DefaultController.class);
    private final GameRepository gameRepository;
    private final BetRepository betRepository;
    private final BetOwnerRepository betOwnerRepository;
    private final BetService betService;


    @Autowired
    public DefaultController(GameRepository gameRepository, BetRepository betRepository, BetOwnerRepository betOwnerRepository, BetService betService) {
        this.gameRepository = gameRepository;
        this.betRepository = betRepository;
        this.betOwnerRepository = betOwnerRepository;
        this.betService = betService;
    }

    @GetMapping("/games/{id}")
    public Game getOneGame(@PathVariable Long id) throws GameNotFoundException {
        if (id != null) {
            // Replace pattern-breaking characters
            logger.info("Get One Game: " + id);
            return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
        }
        return null;

    }

    @DeleteMapping("/games/delete/{id}")
    public void deleteOneGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
        logger.info("Deleted game : " + id);
    }

    @GetMapping("/stats")
    public Statistics getStatistics() {
        logger.info("Get Statistics");
        return betService.getStatistics();
    }

    @GetMapping("/games")
    public List<Game> getAllGames() {
        logger.info("Get All games");
        return Lists.newArrayList(gameRepository.findAllByOrderByDatetimeAsc());
    }

    @PostMapping(value = "/games")
    public Game saveGame(@RequestBody Game newGame) throws JsonProcessingException {
        logger.info("Saving game: " + new ObjectMapper().writeValueAsString(newGame));
        return gameRepository.save(newGame);
    }

    @GetMapping("/bets")
    public List<FullBet> getAllBets() {
        logger.info("Get All bets");
        return betService.getAllBets();
    }


    @GetMapping("/bets/info/{id}")
    public BetsDTO getUserBets(@PathVariable Long id) throws BetOwnerNotFoundException {
        logger.info("Get Owner bets : " + id);
        BetsDTO betsDTO = new BetsDTO();
        betsDTO.setBets(betService.getUserBets(id).stream().map(BetMapper::toFullBetDTO).collect(Collectors.toList()));
        betsDTO.setCombinedBetAmount(betService.getBetOwner(id).getCombinedBetAmount());
        return betsDTO;
    }

    @DeleteMapping("/bets/delete/user/{id}")
    public void deleteOneUserBet(@PathVariable Long id) {
        logger.info("Deling one user bet : " + id);
        BetOwner betOwner = betOwnerRepository.findById(id).orElseGet(null);
        if (betOwner == null) {
            logger.error("Can't save owner");
        } else {
            for (Long betId : betOwner.getBets()) {
                betRepository.deleteById(betId);
                logger.info("Deleted bet number " + betId);
            }
            betOwnerRepository.deleteById(id);
        }
    }

    @DeleteMapping("/bets/delete/{id}")
    public void deleteOneBet(@PathVariable Long id) {
        logger.info("Deleted bet : " + id);
        betRepository.deleteById(id);
    }


    @PostMapping(value = "/bets")
    public Long saveBets(@RequestBody SaveBetsDTO betsToSave) throws JsonProcessingException {
        logger.info("Saving : " + new ObjectMapper().writeValueAsString(betsToSave));
        return betService.saveBet(betsToSave.getBets().stream().map(BetMapper::toBet).collect(Collectors.toList()), betsToSave.getCombinedBetAmount()).getId();
    }


}