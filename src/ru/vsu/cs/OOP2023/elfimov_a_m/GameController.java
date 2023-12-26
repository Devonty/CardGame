package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.FoolPrintUtils;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.*;

public class GameController{
    private final Game game;
    private final GameStatus gameStatus;

    boolean triggerToEndRound = false;
    boolean allPass = false;

    public GameController(Game game) {
        this.game = game;
        this.gameStatus = game.getGameStatus();
    }

    public void playRound(){
        allPass = false;
        triggerToEndRound = false;
        while(!isEndOfRound()){
            askForAttackTurn();
            askForDefendTurn();
        }
    }

    public void askForAttackTurn(){
        int defenderIndex = game.getDefenderIndex();
        int i = defenderIndex - 1;
        Player defender = game.getPlayer(defenderIndex);
        Player attacker = game.getPlayer(i);

        allPass = true;

        // Для всех аттакующих
        while(attacker != defender){
            // Пропуская вышедших из игры
            while(attacker.getStatus() != Player.playerStatus.PLAYING) attacker = game.getPlayer(--i);
            if(attacker == defender) break;
            // Просим сделать ход
            Turn turn = new WrongTurn();
            // Пока не сделает PASS
            while(!turn.didPass()){
                FoolPrintUtils.printGameStatus(gameStatus);
                FoolPrintUtils.printCardsOnHand(gameStatus, attacker);

                turn = attacker.askForAttack(gameStatus);
                // Разыгрываем ход
                allPass = !turnInterpreter(turn) && allPass;
            }
            attacker = game.getPlayer(--i);
        }
    }

    public void askForDefendTurn(){
        Player defender = game.getPlayer(game.getDefenderIndex());
        Turn turn;
        // Просим сделать ход
        // Пока не отобъет все
        while(!game.isAllBeaten()){
            FoolPrintUtils.printGameStatus(gameStatus);
            FoolPrintUtils.printCardsOnHand(gameStatus, defender);

            turn = defender.askForDefend(gameStatus);
            // Разыгрываем ход
            turnInterpreter(turn);
        }

    }

    private boolean turnInterpreter(Turn turn){
        if(turn == null) return false;
        System.out.println(turn.describe());
        return turn.playInGame(game);

    }

    private boolean isEndOfRound() {
        if(allPass && gameStatus.isAllBeaten()) return true;
        if(triggerToEndRound) return true;
        if(game.getPlayer(gameStatus.getDefenderIndex()).countCardsOnHand() == 0) return true;

        return false;
    }


    public void endRound() {
        triggerToEndRound = true;
    }
}
