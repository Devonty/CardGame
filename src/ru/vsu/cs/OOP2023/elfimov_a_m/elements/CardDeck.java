package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import java.util.*;

public class CardDeck {

    public final boolean isSize36;

    public final LinkedList<Card> cards;
    public final String[] values;

    private String trumpSuit;

    public CardDeck() {
        this(true);
    }


    public CardDeck(boolean isSize36) {
        this.isSize36 = isSize36;
        this.cards = new LinkedList<>();
        this.values = isSize36 ? Card.cardValues36 : Card.cardValues52;

        restart();
    }

    public void restart() {
        chooseTrump();
        generateCards();
        shuffleCards();
    }

    public Card takeTopCard() {
        assert !isEmpty(): "Колода кончилась";
        return cards.removeLast();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }
    public int size(){
        return cards.size();
    }
    private void chooseTrump() {
        Random rd = new Random();
        trumpSuit = Card.cardSuits[rd.nextInt(Card.cardSuits.length)];
    }

    private void generateCards() {
        // init cards
        for (String suit : Card.cardSuits) {
            for (String value : values) {
                Card card = new Card(suit, value, suit.equals(trumpSuit));
                cards.add(card);
            }
        }
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    public String getTrumpSuit() {
        return trumpSuit;
    }
}
