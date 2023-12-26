package ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.CardDeck;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.*;

public class FoolCardDeck implements CardDeck {

    public final Deque<Card> cards;
    private final GameConfig gameConfig;

    public FoolCardDeck(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.cards = generateCards();
    }

    @Override
    public Card takeTopCard() {
        assert !isEmpty(): "Колода кончилась";
        return cards.removeFirst();
    }

    @Override
    public Card takeBottomCard() {
        assert !isEmpty(): "Колода кончилась";
        return cards.removeLast();
    }
    @Override
    public boolean isEmpty(){
        return cards.isEmpty();
    }
    @Override
    public int size(){
        return cards.size();
    }

    private LinkedList<Card> generateCards() {
        LinkedList<Card> cards = new LinkedList<>();
        for (int s = 0; s < gameConfig.cardSuitsCount(); s++) {
            for (int v = 0; v < gameConfig.cardValuesCount(); v++) {
                cards.add(new Card(s, v));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }


}
