package ru.vsu.cs.OOP2023.elfimov_a_m.tests;

import org.junit.Test;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.Fool36GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.Comparator;

import static org.junit.Assert.*;

public class FoolGameRulesTest {
    public static final GameConfig gameConfig = new Fool36GameConfig();
    public static final Comparator<Card> CARD_COMP = gameConfig.gameRules().getComparator();

    @Test
    public void canCardBeatAnother1() {
        int trumpSuit = gameConfig.generateTrumpSuitIndex();
        int notTrumpSuit = trumpSuit + 1;
        Card c1 = new Card(notTrumpSuit, 0);
        Card c2 = new Card(notTrumpSuit, 0);
        assertFalse(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother2() {
        int trumpSuit = gameConfig.generateTrumpSuitIndex();
        int notTrumpSuit = trumpSuit + 1;
        Card c1 = new Card(notTrumpSuit, 10);
        Card c2 = new Card(notTrumpSuit, 0);
        assertTrue(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother3() {
        int trumpSuit = gameConfig.generateTrumpSuitIndex();
        int notTrumpSuit1 = trumpSuit + 1;
        int notTrumpSuit2 = trumpSuit + 2;
        Card c1 = new Card(notTrumpSuit1, 10);
        Card c2 = new Card(notTrumpSuit2, 0);
        assertFalse(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother4() {
        int trumpSuit = gameConfig.generateTrumpSuitIndex();
        int notTrumpSuit = trumpSuit + 1;
        Card c1 = new Card(notTrumpSuit, 10);
        Card c2 = new Card(trumpSuit, 0);
        assertFalse(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother5() {
        int trumpSuit = gameConfig.generateTrumpSuitIndex();
        int notTrumpSuit = trumpSuit + 1;
        Card c1 = new Card(trumpSuit, 10);
        Card c2 = new Card(trumpSuit, 0);
        assertTrue(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother6() {
        int trumpSuit = gameConfig.generateTrumpSuitIndex();
        int notTrumpSuit = trumpSuit + 1;
        Card c1 = new Card(trumpSuit, 0);
        Card c2 = new Card(trumpSuit, 10);
        assertFalse(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother7() {
        int trumpSuit = gameConfig.generateTrumpSuitIndex();
        int notTrumpSuit = trumpSuit + 1;
        Card c1 = new Card(trumpSuit, 0);
        Card c2 = new Card(notTrumpSuit, 10);
        assertTrue(CARD_COMP.compare(c1, c2) > 0);
    }
}
