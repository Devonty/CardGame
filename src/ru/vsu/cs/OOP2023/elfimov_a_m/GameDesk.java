package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.ArrayList;
import java.util.List;

public class GameDesk {
    public static final int MAX_CARDS_ON_DESK = 6;
    private final List<Card> cardsToBeat;
    private final List<Card> cardsThatBeat;
    public GameDesk() {
        cardsToBeat = new ArrayList<>(MAX_CARDS_ON_DESK);
        cardsThatBeat = new ArrayList<>(MAX_CARDS_ON_DESK);
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

    public static void printCardsInLine(List<Card> cardsToPrint){
        StringBuilder[] lines = generateLines(cardsToPrint);
        for (int i = 0; i < Card.HEIGHT; i++) {
            System.out.print(lines[i]);
        }
    }
}
