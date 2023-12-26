package ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;

import java.util.LinkedList;
import java.util.List;

public class FoolPlayer implements Player {
    protected final List<Card> cardsOnHand = new LinkedList<>();
    private String name;
    private final GameConfig gameConfig;

    private playerStatus status = playerStatus.PLAYING;
    private final Strategy strategy;

    private Turn lastTurn = null; // Не пока делал ходов


    public FoolPlayer(GameConfig gameConfig, Strategy strategy, String name) {
        this.gameConfig = gameConfig;
        this.name = name;
        this.strategy = strategy;
    }

    public FoolPlayer(GameConfig gameConfig, Strategy strategy){
        this(gameConfig, strategy, "nameless");
    }


    @Override
    public void setStatus(playerStatus status) {
        this.status = status;
    }

    @Override
    public playerStatus getStatus() {
        return status;
    }

    @Override
    public Turn getLastTurn() {
        return lastTurn;
    }

    @Override
    public void addCard(Card cardToAdd) {
        cardsOnHand.add(cardToAdd);
        strategy.sortCardsOnHand(cardsOnHand);
    }

    @Override
    public boolean needCard() {
        if(status != playerStatus.PLAYING) return false;
        return countCardsOnHand() < gameConfig.maxCardsOnHand();
    }

    @Override
    public Card peekCardAt(int index) {
        return cardsOnHand.get(index);
    }

    @Override
    public Card takeCardAt(int index) {
        return cardsOnHand.remove(index);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public int countCardsOnHand() {
        return cardsOnHand.size();
    }

    @Override
    public Turn askForAttack(GameStatus gameStatus) {
        return strategy.askForAttack(gameStatus);
    }

    @Override
    public Turn askForDefend(GameStatus gameStatus) {
        return strategy.askForDefend(gameStatus);
    }
}
