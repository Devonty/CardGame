package ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.CardDeck;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PlayerList;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameRules.GameRules;

import java.util.Comparator;

public interface GameConfig {
    int playerCount();

    int cardValuesCount();

    int cardSuitsCount();

    String getSuitByIndex(int suitIndex);
    String getValueByIndex(int valueIndex);

    int maxCardsOnDesk();

    int maxCardsOnHand();

    int trumpSuitIndex();

    GameRules gameRules();

    CardDeck getCardDeck();

    GameDesk getGameDesk();

    PlayerList<Player> getPlayers();

}
