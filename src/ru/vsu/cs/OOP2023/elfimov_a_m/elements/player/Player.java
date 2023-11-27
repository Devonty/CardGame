package ru.vsu.cs.OOP2023.elfimov_a_m.elements.player;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TurnRecord;

public interface Player {
    void addCard(Card cardToAdd);
    boolean needCard();


    Card peekCardAt(int index);

    Card takeCardAt(int index);

    String getName();

    void printCardOnHand();
    int countCardsOnHand();

    TurnRecord askForAttack(GameDesk gameDesk);
    TurnRecord askForDefend(GameDesk gameDesk);
}
