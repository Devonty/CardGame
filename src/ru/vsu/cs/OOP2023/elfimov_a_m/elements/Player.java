package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PrintUtils;

import java.util.*;

public class Player {
    public static final int MAX_CARD_ON_HAND = 6;
    protected final List<Card> cardsOnHand;
    public final String name;

    public Player(String name) {
        this.name = name;
        this.cardsOnHand = new LinkedList<>();
    }

    public void addCard(Card cardToAdd){
        cardsOnHand.add(cardToAdd);
        sortCardsOnHand();
    }

    public boolean needCard(){
        return cardsOnHand.size() < MAX_CARD_ON_HAND;
    }

    private void sortCardsOnHand() {
        cardsOnHand.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                String s1 = cardToStringForCompare(c1);
                String s2 = cardToStringForCompare(c2);
                return s1.compareTo(s2);
            }

            private String cardToStringForCompare(Card card){
                return (card.isTrump() ? "1" : "0") + card.getSuit() + card.getValue();
            }
        });
    }

    public Card peekCardAt(int index){
        // will return card at index
        assert 0 <= index && index < cardsOnHand.size() : "Индекс за границей: " + index+ " of " + cardsOnHand.size();
        return cardsOnHand.get(index);
    }
    public Card takeCardAt(int index){
        // will return and delete card at index
        assert 0 <= index && index < cardsOnHand.size() : "Индекс за границей: " + index + " of " + cardsOnHand.size();
        return cardsOnHand.remove(index);
    }


    public void printCardOnHand(){
        PrintUtils.printCardsInLine(cardsOnHand);
        PrintUtils.printNumeric(cardsOnHand.size());
    }

    public int countCardsOnHand(){
        return cardsOnHand.size();
    }


}
