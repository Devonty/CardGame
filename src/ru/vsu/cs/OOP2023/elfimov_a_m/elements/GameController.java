package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import ru.vsu.cs.OOP2023.elfimov_a_m.Game;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TurnRecord;

public class GameController {
    private final Game game;
    private final GameDesk gameDesk;
    private final CardDeck cardDeck;
    private int indexOfPlayerToDefend = 0;

    private boolean isAllPass = false;
    private boolean defenderDoTakePass = false;

    public GameController(Game game, GameDesk gameDesk, CardDeck cardDeck) {
        this.game = game;
        this.gameDesk = gameDesk;
        this.cardDeck = cardDeck;
        fillCardsToPlayersFromCardDeck();
    }

    private TurnRecord askForDefend(Player defender) {
        game.printForPlayer(defender);
        return defender.askForDefend(gameDesk);
    }

    private TurnRecord askForAttack(Player attacker) {
        game.printForPlayer(attacker);
        return attacker.askForAttack(gameDesk);
    }

    private void makeDefendTurn() {
        defenderDoTakePass = false;
        Player defender = game.getPlayer(indexOfPlayerToDefend);
        TurnRecord turnRecord = TurnRecord.WRONG_TURN_RECORD;

        // Make turn while not allBeaten
        while (!gameDesk.allBeaten()) {
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

                if (!gameDesk.beatCardAtBy(indexCardToBeat, cardThatBeat)) {
                    defender.addCard(cardThatBeat); // put back
                }
            }

            // Take Pass
            if (turnRecord.turn() == TurnRecord.Turn.TAKE_PASS) {
                defenderDoTakePass = true;
                System.out.println("Игрок тянет карты! Можете добавить карты на стол!" + "<- ".repeat(10));
                makeAttackTurn();
                gameDesk.giveAllDeskCardsToPlayer(defender);
                return;
            }
            turnRecord = TurnRecord.WRONG_TURN_RECORD;
        }
    }

    private void makeAttackTurn() {
        Player defender = game.getPlayer(indexOfPlayerToDefend);
        isAllPass = true;
        if (!canAddAnyCardToBeat(defender)) return;

        Player attacker = game.getPlayer
                (indexOfPlayerToDefend - 1);

        for (int i = 2; attacker != defender; i++) {
            // Is winner already
            if (attacker.countCardsOnHand() == 0) {
                attacker = game.getPlayer
                        (indexOfPlayerToDefend - i);
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

                    if (!gameDesk.addCardToBeat(cardToBeat)) {
                        attacker.addCard(cardToBeat); // put back
                    }
                }


                if (turnRecord.turn() != TurnRecord.Turn.PASS) turnRecord = TurnRecord.WRONG_TURN_RECORD;
            }
            attacker = game.getPlayer
                    (indexOfPlayerToDefend - i);
        }
    }


    public void fillCardsToPlayersFromCardDeck() {
        if (cardDeck.isEmpty()) return;

        Player player;
        for (int i = 1; i <= Game.PLAYER_COUNT; i++) {
            player = game.getPlayer
                    (indexOfPlayerToDefend - i);
            while (!cardDeck.isEmpty() && player.needCard()) {
                player.addCard(cardDeck.takeTopCard());
            }
        }

    }

    public void playRound() {
        defenderDoTakePass = false;
        isAllPass = false;

        Player defender = game.getPlayer
                (indexOfPlayerToDefend);
        while (defender.countCardsOnHand() == 0) {
            indexOfPlayerToDefend++;
            defender = game.getPlayer(indexOfPlayerToDefend);
        }

        while (!isEndOfRound()) {
            makeAttackTurn();
            makeDefendTurn();
        }
        // clear desk
        gameDesk.clearDesk();
        // next player
        if (defenderDoTakePass) indexOfPlayerToDefend++; // skip defender if he did TakePass
        indexOfPlayerToDefend = (indexOfPlayerToDefend + 1) % Game.PLAYER_COUNT; // normalize;
        fillCardsToPlayersFromCardDeck();

        System.out.println("В колоде осталось: " + cardDeck.size());


    }

    public boolean isEndOfGame() {
        int realPlayerCount = 0;
        Player player;
        for (int i = 0; i < Game.PLAYER_COUNT; i++) {
            player = game.getPlayer(i);
            if (player.countCardsOnHand() != 0) realPlayerCount++;
        }
        return realPlayerCount <= 1;
    }

    public boolean isEndOfRound() {
        return gameDesk.allBeaten() && isAllPass || defenderDoTakePass;
    }

    public boolean canAddAnyCardToBeat(Player defender) {
        return gameDesk.size() < Math.min(GameDesk.MAX_CARDS_ON_DESK, defender.countCardsOnHand());
    }
}
