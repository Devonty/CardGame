import org.junit.Test;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.Fool36GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.Comparator;

import static org.junit.Assert.*;

public class FoolGameRulesTest {
    public  static final int trumpSuit = 0;
    public  static final int notTrumpSuit = trumpSuit + 1;
    public static final GameConfig gameConfig = new Fool36GameConfig(()->trumpSuit);
    public static final Comparator<Card> CARD_COMP = gameConfig.gameRules().getComparator();

    @Test
    public void canCardBeatAnother1() {
        Card c1 = new Card(notTrumpSuit, 0);
        Card c2 = new Card(notTrumpSuit, 0);
        assertFalse(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother2() {
        Card c1 = new Card(notTrumpSuit, 10);
        Card c2 = new Card(notTrumpSuit, 0);
        assertTrue(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother3() {
        int notTrumpSuit1 = trumpSuit + 1;
        int notTrumpSuit2 = trumpSuit + 2;
        Card c1 = new Card(notTrumpSuit1, 10);
        Card c2 = new Card(notTrumpSuit2, 0);
        assertFalse(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother4() {
        Card c1 = new Card(notTrumpSuit, 10);
        Card c2 = new Card(trumpSuit, 0);
        assertFalse(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother5() {
        Card c1 = new Card(trumpSuit, 10);
        Card c2 = new Card(trumpSuit, 0);
        assertTrue(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother6() {
        Card c1 = new Card(trumpSuit, 0);
        Card c2 = new Card(trumpSuit, 10);
        assertFalse(CARD_COMP.compare(c1, c2) > 0);
    }

    @Test
    public void canCardBeatAnother7() {
        Card c1 = new Card(trumpSuit, 0);
        Card c2 = new Card(notTrumpSuit, 10);
        assertTrue(CARD_COMP.compare(c1, c2) > 0);
    }
}
