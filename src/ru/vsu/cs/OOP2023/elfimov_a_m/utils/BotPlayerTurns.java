package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import ru.vsu.cs.OOP2023.elfimov_a_m.Game;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.BotPlayer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.GameDesk;

public class BotPlayerTurns {
    public static TurnRecord askForAttack(BotPlayer bot, Game game) {
        game.printForPlayer(bot);
        System.out.println(bot.name + " сейчас атакует!");
        for (int i = 0; i < bot.countCardsOnHand(); i++) {
            Card card = bot.peekCardAt(i);
            if(game.getGameDesk().isPossibleToAddCard(card))
                return new TurnRecord(TurnRecord.Turn.ADDING, bot, i, -1);
        }
        return new TurnRecord(TurnRecord.Turn.PASS, bot, -1, -1);
    }

    public static TurnRecord askForDefend(BotPlayer bot, Game game, int countCardsToBeat ) {
        game.printForPlayer(bot);
        System.out.println(bot.name + " сейчас защищается!");
        GameDesk  gameDesk = game.getGameDesk();
        for (int indexToBeat = 0; indexToBeat < countCardsToBeat; indexToBeat++) {
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
