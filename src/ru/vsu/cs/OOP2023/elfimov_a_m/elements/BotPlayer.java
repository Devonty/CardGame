package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import java.util.Arrays;
import java.util.Comparator;

public class BotPlayer extends Player {
    public int index = 0;

    public BotPlayer(String name) {
        super(name);
    }

    private void sortCardsOnHand() {
        cardsOnHand.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                int[] s1 = cardToStringForCompare(c1);
                int[] s2 = cardToStringForCompare(c2);

                return Arrays.compare(s1, s2);
            }

            private int[] cardToStringForCompare(Card card){
                return new int[]{
                        card.isTrump() ? 1 : 0,
                        Card.getValueIndex(card.getValue()),
                        Card.getSuitIndex(card.getSuit()),

                };
            }
        });
    }
    public void addCard(Card cardToAdd){
        cardsOnHand.add(cardToAdd);
        sortCardsOnHand();
    }
}
