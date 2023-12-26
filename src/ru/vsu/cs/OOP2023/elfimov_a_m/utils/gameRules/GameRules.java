package ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameRules;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;

import java.util.Comparator;

public interface GameRules {
    boolean canCardBeatAnother(Card c1, Card c2);
    boolean canAddCardInContainer(CardContainer cardContainer, Card cardToAdd);

    boolean canAddCardOnDesk(GameStatus gameStatus, Card cardToAdd);

    boolean isCardContainerBeaten(CardContainer cardContainer);

    Comparator<Card> getComparator();

}
