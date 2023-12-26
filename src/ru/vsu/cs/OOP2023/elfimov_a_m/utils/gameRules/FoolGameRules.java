package ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameRules;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;

import java.util.Comparator;

public class FoolGameRules implements GameRules {
    private final GameConfig gameConfig;

    public final Comparator<Card> CARD_COMP = new Comparator<Card>() {
        @Override
        public int compare(Card c1, Card c2) {
            if (isTrump(c1) && !isTrump(c2)) return 1;
            if (!isTrump(c1) && isTrump(c2)) return -1;
            if (c1.getSuitIndex() != c2.getSuitIndex()) return 0;
            return Integer.compare(c1.getValueIndex(), c2.getValueIndex());
        }

        public boolean isTrump(Card card) {
            return card.getSuitIndex() == gameConfig.trumpSuitIndex();
        }
    };

    public FoolGameRules(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public boolean canCardBeatAnother(Card c1, Card c2) {
        return CARD_COMP.compare(c1, c2) > 0;
    }

    @Override
    public boolean canAddCardInContainer(CardContainer cardContainer, Card cardToAdd) {
        if (cardContainer.size() >= 2) return false;
        if (cardContainer.size() == 0) return true;
        return canCardBeatAnother(cardToAdd, cardContainer.getBottom());
    }

    @Override
    public boolean canAddCardOnDesk(GameStatus gameStatus, Card cardToAdd) {
        if(gameStatus.getContainerCount() == 0) return true;
        if(gameStatus.getContainerCount() >= gameConfig.maxCardsOnDesk()) return false;
        if(gameStatus.getContainerCount() >= gameStatus.getPlayerCardCount(gameStatus.getDefenderIndex())) return false;
        return gameStatus.getValueIndexesOnDesk().contains(cardToAdd.getValueIndex());
    }

    @Override
    public boolean isCardContainerBeaten(CardContainer cardContainer) {
        return cardContainer.size() == 2;
    }

    @Override
    public Comparator<Card> getComparator() {
        return CARD_COMP;
    }

}
