package com.betapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;


@RestController
public class DefaultController {

    Logger logger = LoggerFactory.getLogger(DefaultController.class);
    private final GameRepository gameRepository;
    private final BetRepository betRepository;

    @Autowired
    public DefaultController(GameRepository gameRepository, BetRepository betRepository) {
        this.gameRepository = gameRepository;
        this.betRepository = betRepository;
    }

    @GetMapping("/games/{id}")
    public Game getOneGame(@PathVariable Long id) {
        return gameRepository.findById(id).orElseGet(null);
    }

    @DeleteMapping("/games/delete/{id}")
    public void deleteOneGame(@PathVariable Long id) {
        List<Bet> allByGameId = betRepository.findAllByGameId(id);
        betRepository.deleteAll(allByGameId);
        gameRepository.deleteById(id);
    }

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return Lists.newArrayList(gameRepository.findAll());
    }

    @PostMapping(value="/games")
    public Game saveGame(@RequestBody Game newGame) {
        logger.info("Saving : " + newGame.toString());
        return gameRepository.save(newGame);
    }

    @GetMapping("/bets")
    public List<FullBet> getAllBets() {
        return getFullBetsFromBets(Lists.newArrayList(betRepository.findAll()));
    }

    @DeleteMapping("/bets/delete/{id}")
    public void deleteOneBet(@PathVariable Long id) {
        betRepository.deleteById(id);
    }


    @PostMapping(value="/bets")
    public List<FullBet> saveBets(@RequestBody List<Bet> newBets) throws JsonProcessingException {
        logger.info("Saving : " +  new ObjectMapper().writeValueAsString(newBets));
        ;
        return getFullBetsFromBets(Lists.newArrayList(betRepository.saveAll(newBets)));
    }

    private List<FullBet> getFullBetsFromBets(List<Bet> bets){
        List<FullBet> fullBets = new ArrayList<>();
        bets.forEach(bet -> {
            FullBet fullBet = new FullBet();
            fullBet.setAmount(bet.getAmount());
            fullBet.setId(bet.getId());
            fullBet.setSavedtime(bet.getDatetime());

            //Retrieve game
            Game game = gameRepository.findById(bet.getGameId()).orElseGet(null);
            if(game == null){
                logger.error("Game not found");
            }else {
                fullBet.setGameId(game.getId());
                fullBet.setTeamB(game.getTeamB());
                fullBet.setTeamA(game.getTeamA());
                fullBet.setGametime(game.getDatetime());
                fullBet.setOddA(game.getOddA());
                fullBet.setOddB(game.getOddB());
            }
            fullBets.add(fullBet);
        });
        return fullBets;
    }

}