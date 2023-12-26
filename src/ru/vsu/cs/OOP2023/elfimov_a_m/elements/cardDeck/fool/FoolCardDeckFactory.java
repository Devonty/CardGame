package ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.CardDeck;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.CardDeckFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

public class FoolCardDeckFactory implements CardDeckFactory {
    private GameConfig gameConfig;

    public FoolCardDeckFactory(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public CardDeck getCardDeck() {
        return new FoolCardDeck(gameConfig);
    }
}
