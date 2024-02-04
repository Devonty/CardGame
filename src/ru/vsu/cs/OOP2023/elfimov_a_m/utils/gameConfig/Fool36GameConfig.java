package ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.CardDeck;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.CardDeckFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.fool.FoolCardDeckFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk.FoolGameDeskFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk.GameDeskFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.PlayerFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.fool.FoolPlayerFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PlayerList;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TrumpProvider;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameRules.FoolGameRules;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameRules.GameRules;

import java.util.Comparator;
import java.util.Random;

public class Fool36GameConfig implements GameConfig {
    /* min max кол-во игроков, а не их создание*/

    private static final String[] suits = new String[]{"♣","♠", "♥", "♦"};
    private static final String[] values =  new String[]{" 6"," 7"," 8"," 9","10"," J"," Q"," K"," A"};
    private final int valueCount;
    private final int suitCount;
    private final int maxCardsOnDesk;
    private final int maxPlayerCount;
    private final int minPlayerCount;
    private final int maxCardsOnHand;
    private final GameRules gameRules;
    private final CardDeckFactory cardDeckFactory;
    private final GameDeskFactory gameDeskFactory;

    public Fool36GameConfig(TrumpProvider trumpProvider) {
        this.valueCount = 9;
        this.suitCount = 4;
        this.maxCardsOnDesk = 6;
        this.maxCardsOnHand = 6;

        this.minPlayerCount = 2;
        this.maxPlayerCount = 6;

        this.gameRules = new FoolGameRules(this, trumpProvider);

        this.cardDeckFactory = new FoolCardDeckFactory(this);
        this.gameDeskFactory = new FoolGameDeskFactory(this);
    }

    @Override
    public int minPlayerCount() {
        return minPlayerCount;
    }

    @Override
    public int maxPlayerCount() {
        return maxPlayerCount;
    }

    @Override
    public int cardValuesCount() {
        return valueCount;
    }

    @Override
    public int maxCardsOnDesk() {
        return maxCardsOnDesk;
    }

    @Override
    public int maxCardsOnHand() {
        return maxCardsOnHand;
    }

    @Override
    public int cardSuitsCount() {
        return suitCount;
    }

    @Override
    public String getSuitByIndex(int suitIndex) {
        return suits[suitIndex];
    }

    @Override
    public String getValueByIndex(int valueIndex) {
        return values[valueIndex];
    }

    @Override
    public int generateTrumpSuitIndex() {
        return new Random().nextInt(this.suitCount);
    }

    @Override
    public GameRules gameRules() {
        return gameRules;
    }

    @Override
    public CardDeck getCardDeck() {
        return cardDeckFactory.getCardDeck();
    }

    @Override
    public GameDesk getGameDesk() {
        return gameDeskFactory.getGameDesk();
    }

}
