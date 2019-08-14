package com.betapi;

import com.betapi.model.Bet;
import com.betapi.model.FullBet;
import com.betapi.model.Game;
import com.betapi.model.Owner;
import com.betapi.services.BetRepository;
import com.betapi.services.GameRepository;
import com.betapi.services.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class DefaultController {

    Logger logger = LoggerFactory.getLogger(DefaultController.class);
    private final GameRepository gameRepository;
    private final BetRepository betRepository;
    private final UserRepository userRepository;


    @Autowired
    public DefaultController(GameRepository gameRepository, BetRepository betRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/games/{id}")
    public Game getOneGame(@PathVariable Long id) {
        logger.info("Get One Game: " + id);
        return gameRepository.findById(id).orElseGet(null);
    }

    @DeleteMapping("/games/delete/{id}")
    public void deleteOneGame(@PathVariable Long id) {
        List<Bet> allByGameId = betRepository.findAllByGameId(id);
        betRepository.deleteAll(allByGameId);
        gameRepository.deleteById(id);
        logger.info("Deleted game : " + id);
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
        return getFullBetsFromBets(Lists.newArrayList(betRepository.findAll()));
    }

    @GetMapping("/bets/info/{id}")
    public List<FullBet> getUserBets(@PathVariable Long id) {
        logger.info("Get Owner bets : " + id);
        Owner owner = userRepository.findById(id).orElseGet(null);
        if (owner == null) {
            logger.error("Can't save owner");
            return new ArrayList<>();
        }
        List<Bet> allById = Lists.newArrayList(betRepository.findAllById(owner.getBets()));
        return getFullBetsFromBets(allById);
    }

    @DeleteMapping("/bets/delete/{id}")
    public void deleteOneBet(@PathVariable Long id) {
        logger.info("Deleted bet : " + id);
        betRepository.deleteById(id);
    }


    @PostMapping(value = "/bets")
    public Long saveBets(@RequestBody List<Bet> newBets) throws JsonProcessingException {
        logger.info("Saving : " + new ObjectMapper().writeValueAsString(newBets));
        Iterable<Bet> bets = betRepository.saveAll(newBets);
        logger.info("Saved bets: " + new ObjectMapper().writeValueAsString(Lists.newArrayList(bets)));
        List<Long> ids = Lists.newArrayList(bets).stream().map(Bet::getId).collect(Collectors.toList());
        Owner owner = new Owner();
        owner.setBets(ids);
        Owner savedOwner = userRepository.save(owner);
        logger.info("Saved owner : " + new ObjectMapper().writeValueAsString(owner));
        return savedOwner.getId();
    }

    private List<FullBet> getFullBetsFromBets(List<Bet> bets) {
        List<FullBet> fullBets = new ArrayList<>();
        bets.forEach(bet -> {
            FullBet fullBet = new FullBet();
            fullBet.setAmount(bet.getAmount());
            fullBet.setId(bet.getId());
            fullBet.setSavedtime(bet.getDatetime());
            fullBet.setBetChoice(bet.getBetChoice());

            //Retrieve game
            Game game = gameRepository.findById(bet.getGameId()).orElseGet(null);
            if (game == null) {
                logger.error("Game not found");
            } else {
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