package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TurnRecord;

public interface Player {
    void addCard(Card cardToAdd);
    boolean needCard();


    Card peekCardAt(int index);

    Card takeCardAt(int index);

    String getName();

    void printCardOnHand();
    int countCardsOnHand();

}
