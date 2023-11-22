package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.BotPlayer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.BotPlayerTurns;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.ConsoleToPlayer;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TurnRecord;

public class GameController {
    private ConsoleToPlayer consoleToPlayer;
    private final Game game;
    private int indexOfPlayerToDefend = 0;

    private boolean isAllPass = false;
    private boolean defenderDoTakePass = false;

    public GameController(Game game) {
        this.game = game;
        fillCardsToPlayersFromCardDeck();
    }

    private TurnRecord askForDefend(Player defender) {
        if (defender instanceof BotPlayer) {
            return BotPlayerTurns.askForDefend((BotPlayer) defender, game, game.gameDesk.size());
        }
        return ConsoleToPlayer.askForDefend(defender, game, game.gameDesk.size());
    }

    private TurnRecord askForAttack(Player attacker) {
        if (attacker instanceof BotPlayer) {
            return BotPlayerTurns.askForAttack((BotPlayer) attacker, game);
        }
        return ConsoleToPlayer.askForAttack(attacker, game);
    }

    private void makeDefendTurn() {
        defenderDoTakePass = false;
        Player defender = game.players.get(indexOfPlayerToDefend);
        TurnRecord turnRecord = TurnRecord.WRONG_TURN_RECORD;

        // Make turn while not allBeaten
        while (!game.gameDesk.allBeaten()) {
            // for err turns
            while (turnRecord == TurnRecord.WRONG_TURN_RECORD) {
                turnRecord = askForDefend(defender);
            }
            // console turnRecord
            System.out.println(turnRecord);
            // Beats
            if (turnRecord.turn() == TurnRecord.Turn.BEATS) {
                int indexCardToBeat = turnRecord.cardToBeatIndex();
                Card cardThatBeat = defender.takeCardAt(turnRecord.cardThatBeatIndex());

                if (!game.gameDesk.beatCardAtBy(indexCardToBeat, cardThatBeat)) {
                    defender.addCard(cardThatBeat); // put back
                }
            }

            // Take Pass
            if (turnRecord.turn() == TurnRecord.Turn.TAKE_PASS) {
                defenderDoTakePass = true;
                System.out.println("Игрок тянет карты! Можете добавить карты на стол!" + "<- ".repeat(10));
                makeAttackTurn();
                game.gameDesk.giveAllDeskCardToPlayer(defender);
                return;
            }
            turnRecord = TurnRecord.WRONG_TURN_RECORD;
        }
    }

    private void makeAttackTurn() {
        Player defender = game.players.get(indexOfPlayerToDefend);
        isAllPass = true;
        if (!canAddAnyCardToBeat(defender)) return;

        Player attacker = game.players.get(indexOfPlayerToDefend - 1);

        for (int i = 2; attacker != defender; i++) {
            // Is winner already
            if (attacker.countCardsOnHand() == 0) {
                attacker = game.players.get(indexOfPlayerToDefend - i);
                continue;
            }

            TurnRecord turnRecord = TurnRecord.WRONG_TURN_RECORD;
            // Make turn while not PASS
            while (turnRecord.turn() != TurnRecord.Turn.PASS) {
                if (!canAddAnyCardToBeat(defender)) return;
                // for err turns
                while (turnRecord == TurnRecord.WRONG_TURN_RECORD) {
                    turnRecord = askForAttack(attacker);
                }

                // console turnRecord
                System.out.println(turnRecord);

                // All Pass check
                isAllPass = isAllPass && (turnRecord.turn() == TurnRecord.Turn.PASS);

                // Adding
                if (turnRecord.turn() == TurnRecord.Turn.ADDING) {
                    Card cardToBeat = attacker.takeCardAt(turnRecord.cardToBeatIndex());

                    if (!game.gameDesk.addCardToBeat(cardToBeat)) {
                        attacker.addCard(cardToBeat); // put back
                    }
                }


                if (turnRecord.turn() != TurnRecord.Turn.PASS) turnRecord = TurnRecord.WRONG_TURN_RECORD;
            }
            attacker = game.players.get(indexOfPlayerToDefend - i);
        }
    }


    public void fillCardsToPlayersFromCardDeck() {
        if (game.gameDesk.cardDeck.isEmpty()) return;

        Player player;
        for (int i = 1; i <= game.players.size(); i++) {
            player = game.players.get(indexOfPlayerToDefend - i);
            while (!game.gameDesk.cardDeck.isEmpty() && player.needCard()) {
                player.addCard(game.gameDesk.cardDeck.takeTopCard());
            }
        }

    }

    public void playRound() {
        defenderDoTakePass = false;
        isAllPass = false;

        Player defender = game.players.get(indexOfPlayerToDefend);
        while (defender.countCardsOnHand() == 0) {
            game.players.remove(indexOfPlayerToDefend);
            defender = game.players.get(indexOfPlayerToDefend);
        }

        while (!isEndOfRound()) {
            makeAttackTurn();
            makeDefendTurn();
        }
        // clear desk
        game.gameDesk.clearDesk();
        // next player
        if (defenderDoTakePass) indexOfPlayerToDefend++; // skip defender
        indexOfPlayerToDefend = (indexOfPlayerToDefend + 1) % game.players.size();
        fillCardsToPlayersFromCardDeck();
        System.out.println("В колоде осталось: " + game.gameDesk.cardDeck.size());


    }

    public boolean isEndOfGame() {
        int realPlayerCount = 0;
        for (Player player : game.players) {
            if (player.countCardsOnHand() != 0) realPlayerCount++;
        }
        return realPlayerCount <= 1;
    }

    public boolean isEndOfRound() {
        return game.gameDesk.allBeaten() && isAllPass || defenderDoTakePass;
    }

    public boolean canAddAnyCardToBeat(Player defender) {
        return game.gameDesk.size() < Math.min(GameDesk.MAX_CARDS_ON_DESK, defender.countCardsOnHand());
    }
}
