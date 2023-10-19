package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CardDeck {

    public final boolean isSize36;

    public final List<Card> cards;
    public final String[] values;

    private String trumpSuit;

    public CardDeck() {
        this(true);
    }


    public CardDeck(boolean isSize36) {
        this.isSize36 = isSize36;
        this.cards = new LinkedList<>();
        this.values = isSize36 ? Card.cardValues36 : Card.cardValues56;

        restart();
    }

    public void restart(){
        generateCards();
        shuffleCards();
        chooseTrump();
    }

    private void chooseTrump() {
        Random rd = new Random();

        trumpSuit =  cards.get(rd.nextInt(0, values.length)).getSuit();
        for(Card card : cards){
            card.setTrump(card.getSuit().equals(trumpSuit));
        }
    }

    private void generateCards(){
        // init cards
        for (String suit : Card.cardSuits) {
            for(String value : values){
                cards.add(new Card(suit, value));
            }
        }
    }

    private void shuffleCards(){
        Collections.shuffle(cards);
    }

    public String getTrumpSuit() {
        return trumpSuit;
    }
}
