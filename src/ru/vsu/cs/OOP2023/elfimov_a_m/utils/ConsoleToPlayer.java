package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

import java.util.Scanner;

public class ConsoleToPlayer {
    public static TurnRecord askForAttack(Player player) {
        System.out.println("Меню (Атака)\n1. Добавить карту\n2. Пасс");

        switch (new Scanner(System.in).nextInt()) {
            case (1):
                player.printCardOnHand();
                System.out.print("Что добавить: ");
                int indexToBeat = getIndexInRange(player.countCardsOnHand());
                if (indexToBeat == -1) return TurnRecord.WRONG_TURN_RECORD;

                return new TurnRecord(TurnRecord.Turn.ADDING, player, indexToBeat, -1);

            case (2):
                return new TurnRecord(TurnRecord.Turn.PASS, player, -1, -1);

            default:
                return TurnRecord.WRONG_TURN_RECORD;
        }

    }

    public static TurnRecord askForDefend(Player player, GameDesk gameDesk) {

        System.out.println("Меню (Защита)\n1. Отбить карту\n2. Стянуть");

        switch (new Scanner(System.in).nextInt()) {
            case (1):
                player.printCardOnHand();

                System.out.print("Что побить: ");
                    int indexToBeat = getIndexInRange(gameDesk.size());
                if (indexToBeat == -1) return TurnRecord.WRONG_TURN_RECORD;

                System.out.print("Чем побить: ");
                int indexThatBeat = getIndexInRange(player.countCardsOnHand());
                if (indexThatBeat == -1) return TurnRecord.WRONG_TURN_RECORD;

                return new TurnRecord(TurnRecord.Turn.BEATS, player, indexToBeat, indexThatBeat);

            case (2):
                return new TurnRecord(TurnRecord.Turn.TAKE_PASS, player, -1, -1);

            default:
                return TurnRecord.WRONG_TURN_RECORD;
        }

    }

    public static int getIndexInRange(int range) {
        System.out.printf("<Индекс то 0 до %s> (-1 чтобы вернуться)\n", range - 1);
        int index = new Scanner(System.in).nextInt();
        if (index < 0 || index >= range) index = -1;
        return index;
    }

}
