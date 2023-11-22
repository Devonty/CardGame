package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.*;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.CycleList;

public class Game {
    public static final int PLAYER_COUNT = 3;
    protected CycleList<Player> players;
    protected GameDesk gameDesk;
    protected GameController gameController;


    public Game() {
        players = new CycleList<>(PLAYER_COUNT);
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players.add(new BotPlayer("Player_"+i));
        }

        gameDesk = new GameDesk();
        gameController = new GameController(this);
    }

    public void printForPlayer(Player player){
        System.out.println("=".repeat(80));
        System.out.println("=".repeat(80));
        System.out.println("=".repeat(80));
        gameDesk.print();
        System.out.println("Ход игрока: "  + player.name);
        player.printCardOnHand();
    }
    public Player start(){
        // returns loser
        while(!gameController.isEndOfGame()) gameController.playRound();
        System.out.println("Игра окончена!");
        return getLoser();
    }

    private Player getLoser(){
        for (Player player : players) {
            if (player.countCardsOnHand() != 0) {
                System.out.println(player.name + " Проиграл");
                return player;
            }
        }
        return null; // draw
    }
    public final GameDesk getGameDesk() {
        return gameDesk;
    }
}
