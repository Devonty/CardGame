package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import java.util.*;

import static ru.vsu.cs.OOP2023.elfimov_a_m.utils.PrintUtils.printCardsInLine;
import static ru.vsu.cs.OOP2023.elfimov_a_m.utils.PrintUtils.printNumeric;

public class GameDesk {
    public static final Card cardNull = new Card("  ", "  ");
    public static final int MAX_CARDS_ON_DESK = 6;
    private final List<Card> cardsToBeat;
    private final List<Card> cardsThatBeat;

    public GameDesk(boolean isSize36) {
        cardsToBeat = new ArrayList<>(MAX_CARDS_ON_DESK);
        cardsThatBeat = new ArrayList<>(MAX_CARDS_ON_DESK);
    }

    public GameDesk() {
        this(true);
    }

    public int size() {
        return cardsToBeat.size();
    }

    public boolean allBeaten() {
        int countCardsThatBeat = 0;
        for (Card card : cardsThatBeat) {
            if (card != cardNull) countCardsThatBeat++;
        }
        return cardsToBeat.size() == countCardsThatBeat;
    }


    public boolean beatCardAtBy(int index, Card cardThatBeat) {
        if (!isIndexInRange(index)) return false;
        if (!canCardBeatAnother(cardsToBeat.get(index), cardThatBeat)) return false;
        if (cardsThatBeat.get(index) != cardNull) return false;
        cardsThatBeat.set(index, cardThatBeat);
        return true;
    }

    private boolean isIndexInRange(int index) {
        return 0 <= index && index < cardsToBeat.size();
    }

    public boolean canCardBeatAnotherAt(int indexCardToBeat, Card cardThatBeat) {
        return canCardBeatAnother(cardsToBeat.get(indexCardToBeat), cardThatBeat);
    }

    public boolean canCardBeatAnother(Card cardToBeat, Card cardThatBeat) {
        if (cardThatBeat.isTrump() && !cardToBeat.isTrump()) return true;
        if (!cardThatBeat.isTrump() && cardToBeat.isTrump()) return false;
        if (!cardThatBeat.getSuit().equals(cardToBeat.getSuit())) return false;
        // (both are trump || both aren't trump ) with same suit
        return Card.getValueIndex(cardThatBeat.getValue()) > Card.getValueIndex(cardToBeat.getValue());
    }

    public boolean isCardBeatenAt(int index) {
        return cardsThatBeat.get(index) != cardNull;
    }


    public boolean addCardToBeat(Card cardToAdd) {
        if (!isPossibleToAddCard(cardToAdd)) return false;
        cardsToBeat.add(cardToAdd);
        cardsThatBeat.add(cardNull);
        return true;
    }

    public boolean isPossibleToAddCard(Card cartToAdd) {
        if (cardsToBeat.size() >= MAX_CARDS_ON_DESK) return false; // desk is full
        if (cardsToBeat.size() == 0) return true; // first card
        return hasValueOnDesk(cartToAdd.getValue()); // search for value on desk
    }

    private boolean hasValueOnDesk(String value) {
        for (Card card : cardsToBeat) {
            if (card.getValue().equals(value)) return true;
        }
        for (Card card : cardsThatBeat) {
            if (card.getValue().equals(value)) return true;
        }
        return false;
    }

    public void giveAllDeskCardsToPlayer(Player defender) {
        // if defender take pass
        for (Card card : cardsToBeat) {
            defender.addCard(card);
        }
        for (Card card : cardsThatBeat) {
            if (card != cardNull) defender.addCard(card);
        }
        clearDesk();
    }

    public void clearDesk() {
        cardsThatBeat.clear();
        cardsToBeat.clear();
    }

    public void print() {
        printCardsInLine(cardsToBeat);
        printCardsInLine(cardsThatBeat);
        printNumeric(cardsToBeat.size());
    }

}
