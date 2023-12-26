package ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;

public interface CardDeck {
    Card takeTopCard();

    Card takeBottomCard();

    boolean isEmpty();
    int size();
}
