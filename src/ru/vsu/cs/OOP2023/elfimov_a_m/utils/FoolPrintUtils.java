package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FoolPrintUtils {
    public static void printGameStatus(GameStatus gameStatus){
        System.out.println("#".repeat(40));
        // Вывод о козыре и кол-ве карт в колоде
        printDeckInfo(gameStatus);
        // Кто отбивается
        printDefenderName(gameStatus);
        // Кол-во карт
        printPlayersCardCount(gameStatus);
        // Вывод поля
        printGameDesk(gameStatus);
    }
    public static void printGameDesk(GameStatus gameStatus){
        System.out.println("Игровой стол:");
        List<Card> cards1 = new ArrayList<>(gameStatus.getContainerCount());
        List<Card> cards2 = new ArrayList<>(gameStatus.getContainerCount());
        for(int i = 0; i < gameStatus.getContainerCount(); i++){
            cards1.add(gameStatus.getCardContainerAt(i).getOrDefault(0, null));
            cards2.add(gameStatus.getCardContainerAt(i).getOrDefault(1, null));
        }
        printCardsInLine(gameStatus, cards1);
        printCardsInLine(gameStatus, cards2);
    }
    public static void printPlayersCardCount(GameStatus gameStatus){
        System.out.println("Карты игроков:");
        for(int i = 0; i < gameStatus.initialPlayerCount(); i++) {
            System.out.println((i + 1) + ") \"" + gameStatus.getPlayerNameByIndex(i) + "\" : " + gameStatus.getPlayerCardCount(i) + " карт(ы)");
        }
    }
    public static void printDeckInfo(GameStatus gameStatus){
        String trumpSuit = gameStatus.getGameConfig().getSuitByIndex(gameStatus.getTrumpSuitIndex());
        int deckSize = gameStatus.getDeckSize();
        System.out.println("Козырь: " + trumpSuit + "\tКарт в колоде: " + deckSize);
    }
    public static void printDefenderName(GameStatus gameStatus){
        String defenderName = gameStatus.getDefenderName();
        System.out.println("Защищается игрок: " + defenderName);
    }
    public static void printCardsInLine(GameStatus gameStatus, List<Card> cards){
        GameConfig gameConfig = gameStatus.getGameConfig();
        final int height = 4;
        StringBuilder[] lines = new StringBuilder[height - 1];
        // init
        for (int i = 0; i < height - 1; i++) {
            lines[i] = new StringBuilder();
        }
        // build
        for (Card card : cards) {
            if(card == null) lines[0].append("+---+\t");
            else lines[0].append(card.getSuitIndex() == gameStatus.getTrumpSuitIndex() ? "#+++#" : "+---+").append('\t');

            if(card == null) lines[1].append("|   |\t");
            else lines[1].append(String.format("|%s |", gameConfig.getSuitByIndex(card.getSuitIndex()))).append('\t');

            if(card == null) lines[2].append("|   |\t");
            else lines[2].append(String.format("| %s|", gameConfig.getValueByIndex(card.getValueIndex()))).append('\t');
        }

        System.out.println(lines[0]);
        System.out.println(lines[1]);
        System.out.println(lines[2]);
        System.out.println(lines[0]);



    }

    public static void printCardsOnHand(GameStatus gameStatus, Player player){
        List<Card> cards = new LinkedList<>();
        for (int i = 0; i < player.countCardsOnHand(); i++) {
            cards.add(player.peekCardAt(i));
        }
        System.out.println("Карты игрока: " + player.getName());
        printCardsInLine(gameStatus, cards);
    }
}
