package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.*;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.BotPlayer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.CycleList;

public class Game {
    public static final int PLAYER_COUNT = 3;
    public static final boolean isSize36 = true;
    private final CycleList<Player> players;
    private final GameDesk gameDesk;
    private final CardDeck cardDeck;
    private final GameController gameController;



    public Game() {
        players = new CycleList<>(PLAYER_COUNT);
        // players.add(new Player("Придумай мне имя"));
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players.add(new BotPlayer("Player_"+i));
        }

        gameDesk = new GameDesk();
        cardDeck = new CardDeck(isSize36);

        gameController = new GameController(this, gameDesk, cardDeck);
    }

    public void printForPlayer(Player player){
        System.out.println("=".repeat(80));
        System.out.println("=".repeat(80));
        System.out.println("=".repeat(80));
        gameDesk.print();
        System.out.println("Ход игрока: "  + player.getName());
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
                System.out.println(player.getName() + " Проиграл");
                return player;
            }
        }
        return null; // draw
    }
    public Player getPlayer(int index){
        return players.get(index);
    }
}
