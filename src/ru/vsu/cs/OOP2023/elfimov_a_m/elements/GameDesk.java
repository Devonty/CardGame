package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameDesk {
    public static final Card cardNull = new Card("  ", "  ");
    public static final int MAX_CARDS_ON_DESK = 6;
    private final List<Card> cardsToBeat;
    private final List<Card> cardsThatBeat;
    public CardDeck cardDeck;
    public GameDesk(boolean isSize36) {
        cardDeck = new CardDeck(isSize36);
        cardsToBeat = new ArrayList<>(MAX_CARDS_ON_DESK);
        cardsThatBeat = new ArrayList<>(MAX_CARDS_ON_DESK);
    }
    public GameDesk(){this(true);};

    public boolean beatCardAtBy(int index, Card cardThatBeat){
        if(!isIndexInRange(index)) return false;
        if(!canCardBeatAnother(cardsToBeat.get(index), cardThatBeat)) return false;
        cardsThatBeat.set(index, cardThatBeat);
        return true;
    }

    private boolean isIndexInRange(int index){
        return 0 <= index && index < cardsToBeat.size();
    }

    private boolean canCardBeatAnother(Card cardToBeat, Card cardThatBeat){
        if(cardThatBeat.isTrump() && !cardToBeat.isTrump()) return true;
        if(!cardThatBeat.isTrump() && cardToBeat.isTrump()) return false;
        if(!cardThatBeat.getSuit().equals(cardToBeat.getSuit())) return false;
        // (both are trump || both aren't trump ) with same suit
        return indexOfValue(cardThatBeat.getValue()) > indexOfValue(cardToBeat.getValue());
    }

    private int indexOfValue(String value){
        for (int i = 0; i < cardDeck.values.length; i++) {
            if(cardDeck.values[i].equals(value)) return i;
        }
        return -1;
    }
    public boolean addCardToBeat(Card cardToAdd){
        if(!isPossibleToAddCard(cardToAdd)) return false;
        cardsToBeat.add(cardToAdd);
        cardsThatBeat.add(cardNull);
        return true;
    }

    private boolean isPossibleToAddCard(Card cartToAdd){
        if(cardsToBeat.size() == MAX_CARDS_ON_DESK) return false; // desk is full
        if(cardsToBeat.size() == 0) return true; // first card
        return hasValueOnDesk(cartToAdd.getValue()); // search for value on desk
    }

    private boolean hasValueOnDesk(String value){
        for(Card card : cardsToBeat){
            if(card.getValue().equals(value)) return true;
        }
        for(Card card : cardsThatBeat){
            if(card.getValue().equals(value)) return true;
        }
        return false;
    }

    public static StringBuilder[] generateLines(List<Card> cardsToPrint) {
        StringBuilder[] lines = new StringBuilder[Card.HEIGHT];
        // init
        for (int i = 0; i < Card.HEIGHT; i++) {
            lines[i] = new StringBuilder();
        }
        // connect
        for (Card card : cardsToPrint) {
            String[] stringImageLines = card.getStringImageLines();
            for (int i = 0; i < Card.HEIGHT; i++) {
                lines[i].append('\t').append(stringImageLines[i]);
            }
        }
        // ends
        for (int i = 0; i < Card.HEIGHT; i++) {
            lines[i].append('\n');
        }
        return lines;
    }

    private static void printCardsInLine(List<Card> cardsToPrint){
        StringBuilder[] lines = generateLines(cardsToPrint);
        for (int i = 0; i < Card.HEIGHT; i++) {
            System.out.print(lines[i]);
        }
    }

    private void printNumeric(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cardsToBeat.size(); i++) {
            stringBuilder.append('\t').append(" [").append(i).append("] ");
        }
        stringBuilder.append('\n');
        System.out.print(stringBuilder);
    }

    public void print(){
        printCardsInLine(cardsToBeat);
        printCardsInLine(cardsThatBeat);
        printNumeric();
    }
    public void printCardDeck(int n, int m){
        for (int i = 0; i < n; i++) {
            int left = i*m;
            int right = Math.min((i+1)*m, cardDeck.cards.size());
            if(left >= right) return;
            printCardsInLine(cardDeck.cards.subList(left, right));
        }
    }
}
