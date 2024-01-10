package ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.CardDeck;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameRules.GameRules;

public interface GameConfig {
    int maxPlayerCount();
    int minPlayerCount();

    int cardValuesCount();

    int cardSuitsCount();
    int initialTrumpSuitIndex();

    String getSuitByIndex(int suitIndex);
    String getValueByIndex(int valueIndex);

    int maxCardsOnDesk();

    int maxCardsOnHand();

    int generateTrumpSuitIndex();

    GameRules gameRules();

    CardDeck getCardDeck();

    GameDesk getGameDesk();

}
