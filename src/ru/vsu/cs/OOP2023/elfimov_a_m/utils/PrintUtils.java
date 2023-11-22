package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.CardDeck;

import java.util.List;

public class PrintUtils {
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

    public static void printNumeric(int n){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append('\t').append(" [").append(i).append("] ");
        }
        stringBuilder.append('\n');
        System.out.print(stringBuilder);
    }

    public static void printCardDeck(CardDeck cardDeck, int n, int m){
        List<Card> localCards = cardDeck.cards.stream().toList();
        for (int i = 0; i < n; i++) {
            int left = i*m;
            int right = Math.min((i+1)*m, cardDeck.cards.size());
            if(left >= right) return;
            printCardsInLine(localCards.subList(left, right));
        }
    }
}
