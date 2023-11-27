package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PrintUtils;

import java.util.List;
import java.util.Objects;

public class Card implements GameObject {
    public static final int HEIGHT = 4;
    public static final String[] cardSuits = new String[]{"♣","♠", "♥", "♦" };

    public static final String[] cardValues36 = new String[]{" 6", " 7"," 8"," 9","10"," J"," Q"," K"," A"};
    public static final String[] cardValues52 = new String[]{" 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9","10"," J"," Q"," K"," A"};

    private final String suit;
    private final String value;

    private boolean isTrump;
    private final String[] stringImageLines;

    public Card(String suit, String value, boolean isTrump) {
        this.suit = suit;
        this.value = value;
        this.isTrump = isTrump;

        stringImageLines = new String[HEIGHT];
        buildStringImageLines();
    }

    public Card(String suit, String value) {
        this(suit,value,false);
    }


    private void buildStringImageLines(){
        stringImageLines[0] = isTrump ? "#+++#" : "+---+";
        stringImageLines[1] = String.format("|%s |", suit);
        stringImageLines[2] = String.format("| %s|", value);
        stringImageLines[3] = isTrump ? "#+++#" : "+---+";
        /*
        \t+---+
        \t|♦ |
        \t| 10|
        \t+---+
        */

    }

    public boolean isTrump() {
        return isTrump;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public static int getSuitIndex(String suit) {
        for (int i = 0; i < cardSuits.length; i++) {
            if(Objects.equals(cardSuits[i], suit)) return i;
        }
        return -1;
    }

    public static int getValueIndex(String value) {
        for (int i = 0; i < cardValues52.length; i++) {
            if(Objects.equals(cardValues52[i], value)) return i;
        }
        return -1;
    }

    public String[] getStringImageLines() {
        return stringImageLines;
    }

    @Override
    public void restart(){
        // Nothing to do
    }

    @Override
    public void print() {
        PrintUtils.printCardsInLine(List.of(this));
    }
}
