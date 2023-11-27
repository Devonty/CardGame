package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

public class BotPlayerTurns {
    public static TurnRecord askForAttack(Player bot, GameDesk gameDesk) {
        System.out.println(bot.getName() + " сейчас атакует!");
        for (int i = 0; i < bot.countCardsOnHand(); i++) {
            Card card = bot.peekCardAt(i);
            if(gameDesk.isPossibleToAddCard(card))
                return new TurnRecord(TurnRecord.Turn.ADDING, bot, i, -1);
        }
        return new TurnRecord(TurnRecord.Turn.PASS, bot, -1, -1);
    }

    public static TurnRecord askForDefend(Player bot, GameDesk gameDesk) {
        System.out.println(bot.getName() + " сейчас защищается!");
        for (int indexToBeat = 0; indexToBeat < gameDesk.size(); indexToBeat++) {
            if(gameDesk.isCardBeatenAt(indexToBeat)) continue;
            // try to beat by any card in hand
            for (int indexThatBeat = 0; indexThatBeat < bot.countCardsOnHand(); indexThatBeat++) {
                Card cardThatBeat = bot.peekCardAt(indexThatBeat);
                if(gameDesk.canCardBeatAnotherAt(indexToBeat, cardThatBeat)){
                    return new TurnRecord(TurnRecord.Turn.BEATS, bot, indexToBeat, indexThatBeat);
                }
            }
            // not possible to beat card
            return new TurnRecord(TurnRecord.Turn.TAKE_PASS, bot, -1, -1);
        }

        assert true : "Не должно сюда доходить";
        return TurnRecord.WRONG_TURN_RECORD;
    }



}
