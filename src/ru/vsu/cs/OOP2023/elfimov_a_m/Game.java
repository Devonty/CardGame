package ru.vsu.cs.OOP2023.elfimov_a_m;

public class Game {
    public static final int PLAYER_COUNT = 2;
    private CardDeck cardDeck;
    private Player[] players;
    private GameDesk gameDesk;
    private GameController gameController;

    private int indexOfPlayerToAttack;
    private int indexOfPlayerToDefend;

    public Game() {
        cardDeck = new CardDeck();

        players = new Player[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players[i] = new Player();
        }

        gameDesk = new GameDesk();
        gameController = new GameController(this);
    }
}
