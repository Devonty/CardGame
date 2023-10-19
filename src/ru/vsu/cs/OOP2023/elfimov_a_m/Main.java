package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.CardDeck;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.GameDesk;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameDesk gameDesk = new GameDesk();

        gameDesk.addCardToBeat(new Card(Card.cardSuits[0], " 6"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[1], " 6"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[2], " 6"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[3], " 6"));

        gameDesk.beatCardAtBy(2, new Card(Card.cardSuits[3], " 7")); // not correct suit
        gameDesk.beatCardAtBy(3, new Card(Card.cardSuits[3], " 7"));

        gameDesk.addCardToBeat(new Card(Card.cardSuits[0], " 7"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[1], " 7"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[2], " 7")); // will be ignored cause max is 6

        gameDesk.print();

        gameDesk.printCardDeck(5, 20);

    }
}
