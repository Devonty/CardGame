package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.*;

import java.util.Scanner;

public class ConsoleToUser {
    public static Turn askForAttack(GameStatus gameStatus, Player player) {
        System.out.println("Меню (Атака)\n1. Добавить карту\n2. Пасс");

        switch (new Scanner(System.in).nextInt()) {
            case (1):
                FoolPrintUtils.printCardsOnHand(gameStatus, player);
                System.out.print("Что добавить: ");
                int indexToAdd = getIndexInRange(player.countCardsOnHand());
                if (indexToAdd == -1) return new WrongTurn();

                return new AttackTurn(player, indexToAdd);
            case (2):
                return new PassTurn(player);
            default:
                return new WrongTurn();
        }

    }

    public static Turn askForDefend(GameStatus gameStatus, Player player) {

        System.out.println("Меню (Защита)\n1. Отбить карту\n2. Стянуть");

        switch (new Scanner(System.in).nextInt()) {
            case (1):
                FoolPrintUtils.printCardsOnHand(gameStatus, player);

                System.out.print("Что побить: ");
                int indexToBeat = getIndexInRange(gameStatus.getContainerCount());
                if (indexToBeat == -1) return new WrongTurn();

                System.out.print("Чем побить: ");
                int indexThatBeat = getIndexInRange(player.countCardsOnHand());
                if (indexThatBeat == -1) return new WrongTurn();

                return new DefendTurn(player, indexThatBeat, indexToBeat);

            case (2):
                return new TakePassTurn(player);

            default:
                return new WrongTurn();
        }

    }

    public static int getIndexInRange(int range) {
        System.out.printf("<Индекс то 0 до %s> (-1 чтобы вернуться)\n", range - 1);
        int index = new Scanner(System.in).nextInt();
        if (index < 0 || index >= range) index = -1;
        return index;
    }

}