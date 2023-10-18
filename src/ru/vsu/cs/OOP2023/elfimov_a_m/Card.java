package ru.vsu.cs.OOP2023.elfimov_a_m;

import java.util.Arrays;

public class Card {
    public static final int HEIGHT = 4, WIDTH = 5;
    public static final String[] cardSuits = new String[]{"♣","♠", "♥", "♦" };

    public static final String[] cardValues36 = new String[]{" 6"," 7"," 8"," 9","10"," J"," Q"," K"," A"};
    public static final String[] cardValues56 = new String[]{" 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9","10"," J"," Q"," K"," A"};

    private final String suit;
    private final String value;

    private final String[] stringImageLines;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;

        stringImageLines = new String[HEIGHT];
        buildStringImageLines();
    }

    private void buildStringImageLines(){
        stringImageLines[0] = "+---+";
        stringImageLines[1] = String.format("|%s |", suit);
        stringImageLines[2] = String.format("| %s|", value);
        stringImageLines[3] = "+---+";
        /*
        \t+---+
        \t|♦ |
        \t| 10|
        \t+---+
        */

    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public String[] getStringImageLines() {
        return stringImageLines;
    }
}
