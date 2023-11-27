package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    public static final int MAX_CARD_ON_HAND = 6;
    protected final List<Card> cardsOnHand = new LinkedList<>();
    private final String name;

    public static final Comparator<Card> USER_COMP = new Comparator<>() {
        @Override
        public int compare(Card c1, Card c2) {
            int[] s1 = cardToStringForCompare(c1);
            int[] s2 = cardToStringForCompare(c2);

            return Arrays.compare(s1, s2);
        }

        private int[] cardToStringForCompare(Card card) {
            return new int[]{card.isTrump() ? 1 : 0, Card.getSuitIndex(card.getSuit()), Card.getValueIndex(card.getValue())};
        }
    };

    public static final Comparator<Card> BOT_COMP = new Comparator<>() {
        @Override
        public int compare(Card c1, Card c2) {
            int[] s1 = cardToStringForCompare(c1);
            int[] s2 = cardToStringForCompare(c2);

            return Arrays.compare(s1, s2);
        }

        private int[] cardToStringForCompare(Card card) {
            return new int[]{card.isTrump() ? 1 : 0, Card.getValueIndex(card.getValue()), Card.getSuitIndex(card.getSuit())};
        }
    };

    public AbstractPlayer(String name) {
        this.name = name;
    }

    @Override
    public void addCard(Card cardToAdd) {
        cardsOnHand.add(cardToAdd);
        sortCardsOnHand();
    }


    protected abstract void sortCardsOnHand();

    @Override
    public Card peekCardAt(int index) {
        // will return card at index
        assert 0 <= index && index < cardsOnHand.size() : "Индекс за границей: " + index + " of " + cardsOnHand.size();
        return cardsOnHand.get(index);
    }

    @Override
    public Card takeCardAt(int index) {
        // will return and delete card at index
        assert 0 <= index && index < cardsOnHand.size() : "Индекс за границей: " + index + " of " + cardsOnHand.size();
        return cardsOnHand.remove(index);
    }

    @Override
    public void printCardOnHand() {
        PrintUtils.printCardsInLine(cardsOnHand);
        PrintUtils.printNumeric(cardsOnHand.size());
    }


    @Override
    public int countCardsOnHand() {
        return cardsOnHand.size();
    }
    @Override
    public boolean needCard(){
        return cardsOnHand.size() < MAX_CARD_ON_HAND;
    }

    @Override
    public String getName() {
        return name;
    }
}
