package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.*;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void testGameDesk(){
        GameDesk gameDesk = new GameDesk();

        gameDesk.addCardToBeat(new Card(Card.cardSuits[0], " 6"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[1], " 6"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[2], " 6"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[3], " 6"));

        gameDesk.beatCardAtBy(2, new Card(Card.cardSuits[3], " 7")); // not correct suit
        gameDesk.beatCardAtBy(3, new Card(Card.cardSuits[3], " 7"));

        gameDesk.addCardToBeat(new Card(Card.cardSuits[0], " 7"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[1], " 7"));
        gameDesk.addCardToBeat(new Card(Card.cardSuits[2], " 7")); // will be ignored cause max is 6

        gameDesk.print();

        PrintUtils.printCardDeck(gameDesk.cardDeck,5, 20);
    }

    public static void testPlayer(){
        Player player = new BotPlayer("Player_test");
        CardDeck cardDeck = new CardDeck();

        System.out.println("TrumpSuit is " + cardDeck.getTrumpSuit());

        while(!cardDeck.isEmpty() && player.needCard()){
            player.addCard(cardDeck.takeTopCard());
        }

        player.printCardOnHand();
    }

    public static void botSortTest(){
        CardDeck cardDeck = new CardDeck();
        cardDeck.cards.sort(new Comparator<Card>() {
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
        PrintUtils.printCardDeck(cardDeck, 9, 6);
    }
    public static void main(String[] args) {
        int game_to_play = 1000;
        Map<String, Integer> map = new HashMap<>();
        Player draw =  new Player("Draw");
        while (game_to_play-- != 0) {
            Game game = new Game();
            Player loser = game.start();
            if (loser == null) loser = draw;
            map.putIfAbsent(loser.name, 0);
            map.put(loser.name, map.get(loser.name) + 1);
        }

        System.out.println(map);

    }
}
