package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CardDeck {

    public final boolean isSize36;

    private final List<Card> cards;

    public CardDeck() {
        this(true);
    }


    public CardDeck(boolean isSize36) {
        this.isSize36 = isSize36;
        this.cards = new LinkedList<>();
        generateCards();
        shuffleCards();
    }

    public void restart(){
        generateCards();
        shuffleCards();
    }
    private void generateCards(){
        String[] values = isSize36 ? Card.cardValues36 : Card.cardValues56;

        for (String suit : Card.cardSuits) {
            for(String value : values){
                cards.add(new Card(suit, value));
            }
        }
    }

    private void shuffleCards(){
        Collections.shuffle(cards);
    }

    public void print(int n, int m){
        for (int i = 0; i < n; i++) {
            GameDesk.printCardsInLine(cards.subList(i*n,(i+1) * n));
        }

    }
}
