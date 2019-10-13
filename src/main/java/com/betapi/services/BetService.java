package com.betapi.services;

import com.betapi.models.*;
import com.betapi.repositories.BetOwnerRepository;
import com.betapi.repositories.BetRepository;
import com.betapi.repositories.GameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BetService {
    private Logger logger = LoggerFactory.getLogger(BetService.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm").withLocale(Locale.forLanguageTag( "fr-FR" ));
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM").withLocale(Locale.forLanguageTag( "fr-FR" ));


    private final GameRepository gameRepository;
    private final BetRepository betRepository;
    private final BetOwnerRepository betOwnerRepository;

    @Autowired
    public BetService(GameRepository gameRepository, BetRepository betRepository, BetOwnerRepository betOwnerRepository) {
        this.gameRepository = gameRepository;
        this.betRepository = betRepository;
        this.betOwnerRepository = betOwnerRepository;
    }

    public List<FullBet> getAllBets() {
        List<Bet> bets = Lists.newArrayList(betRepository.findAll());
        Map<Long, Game> games = Lists.newArrayList(gameRepository.findAll()).stream().collect(Collectors.toMap(Game::getId, Function.identity()));
        List<FullBet> fullBets = new ArrayList<>();

        for (Bet bet : bets) {
            FullBet fullBet = new FullBet();
            fullBet.setAmount(bet.getAmount());
            fullBet.setId(bet.getId());
            fullBet.setSavedtime(bet.getDatetime());
            fullBet.setBetChoice(bet.getBetChoice());
            fullBet.setOwnerId(bet.getOwnerId());

            Game game = games.get(bet.getGameId());
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
        }
        return fullBets;
    }

    public BetOwner saveBet(List<Bet> newBets, Double combinedAmount){
        BetOwner betOwner = new BetOwner();
        if(combinedAmount != null){
            betOwner.setCombinedBetAmount(combinedAmount);
        }
        BetOwner savedBetOwner = betOwnerRepository.save(betOwner);
        for (Bet newBet : newBets) {
            newBet.setOwnerId(savedBetOwner.getId());
        }
        Iterable<Bet> bets = betRepository.saveAll(newBets);
        List<Long> ids = Lists.newArrayList(bets).stream().map(Bet::getId).collect(Collectors.toList());
        savedBetOwner.setBets(ids);
        betOwnerRepository.save(savedBetOwner);
        try {
            logger.info("Saved owner : " + new ObjectMapper().writeValueAsString(betOwner));
        } catch (JsonProcessingException e) {
            logger.error("Error serializing json", e);
        }
        return savedBetOwner;
    }


    public List<FullBet> getUserBets(Long id){
        BetOwner betOwner = betOwnerRepository.findById(id).orElseGet(null);
        if (betOwner == null) {
            logger.error("Can't save owner");
            return new ArrayList<>();
        }
        List<Bet> allById = Lists.newArrayList(betRepository.findAllById(betOwner.getBets()));
        return getFullBetsFromBets(allById);
    }

    public List<FullBet> getFullBetsFromBets(List<Bet> bets) {
        List<FullBet> fullBets = new ArrayList<>();
        bets.forEach(bet -> {
            FullBet fullBet = new FullBet();
            fullBet.setAmount(bet.getAmount());
            fullBet.setId(bet.getId());
            fullBet.setSavedtime(bet.getDatetime());
            fullBet.setBetChoice(bet.getBetChoice());
            fullBet.setOwnerId(bet.getOwnerId());

            //Retrieve game
            try {
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
            } catch (Exception e) {
                logger.error("Game not found");
            }
            fullBets.add(fullBet);
        });
        return fullBets;
    }

    public Statistics getStatistics() {
        Statistics statistics = new Statistics();
        List<FullBet> allBets = getAllBets();

        long allActiveGames = 0L;
        long allPassedGames = 0L;
        for (Game game : gameRepository.findAll()) {
            if (game.getDatetime().isAfter(LocalDateTime.now())) {
                allActiveGames += 1;
            }else {
                allPassedGames +=1;
            }
        }

        long allBetsCount = StreamSupport.stream(betOwnerRepository.findAll().spliterator(), false).count();

        double totalAmout = 0D;
        for (FullBet bet : allBets) {
            totalAmout += bet.getAmount();
        }

        SortedMap<LocalDate, Double> amountBydaysMap = new TreeMap<>();
        amountBydaysMap.put(LocalDate.now().minusDays(6), 0D);
        amountBydaysMap.put(LocalDate.now().minusDays(5), 0D);
        amountBydaysMap.put(LocalDate.now().minusDays(4), 0D);
        amountBydaysMap.put(LocalDate.now().minusDays(3), 0D);
        amountBydaysMap.put(LocalDate.now().minusDays(2), 0D);
        amountBydaysMap.put(LocalDate.now().minusDays(1), 0D);
        amountBydaysMap.put(LocalDate.now(), 0D);

        for (FullBet bet : allBets) {
            if (bet.getSavedtime().isAfter(LocalDateTime.now().minusWeeks(1).plusDays(1))) {
                LocalDate dayOfWeek = bet.getSavedtime().toLocalDate();
                Double currentAmount = amountBydaysMap.get(dayOfWeek);
                amountBydaysMap.put(dayOfWeek, currentAmount + bet.getAmount());
            }
        }
        List<ChartElement> amountByDays = new ArrayList<>();
        amountBydaysMap.forEach((dayOfWeek, aDouble) -> amountByDays.add(new ChartElement(dayOfWeek.format(dateFormatter),aDouble)));

        Map<String, Double> top10GamesMap = new HashMap<>();
        for (FullBet bet : allBets) {
            if (bet.getGameId() == null) {
                continue;
            }
            String gameKey = bet.getTeamA() + " - " + bet.getTeamB() + " le " + bet.getGametime().format(formatter);
            if (!top10GamesMap.containsKey(gameKey)) {
                top10GamesMap.put(gameKey, bet.getAmount());
            } else {
                Double currentAmount = top10GamesMap.get(gameKey);
                top10GamesMap.put(gameKey, currentAmount + bet.getAmount());
            }
        }
        List<ChartElement> top10Games = new ArrayList<>();
        for (Map.Entry<String, Double> entry : top10GamesMap.entrySet()) {
            top10Games.add(new ChartElement(entry.getKey(),entry.getValue()));
        }
        top10Games.sort(Comparator.comparing(ChartElement::getValue));
        if(top10Games.size() > 10){
            top10Games = new ArrayList<>(top10Games.subList(top10Games.size() - 10, top10Games.size()));
        }

        statistics.setGameNumber(allActiveGames);
        statistics.setGameEndedNumber(allPassedGames);
        statistics.setBetNumber(allBetsCount);
        statistics.setTotalAmount(totalAmout);
        statistics.setChartElements(amountByDays);
        statistics.setTop10Games(top10Games);
        return statistics;
    }



}
