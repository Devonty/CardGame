package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.*;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.CycleList;

public class Game {
    public static final int PLAYER_COUNT = 2;
    private CycleList<Player> players;
    private GameDesk gameDesk;
    private GameController gameController;

    private int indexOfPlayerToAttack;
    private int indexOfPlayerToDefend;

    public Game() {
        players = new CycleList<>(PLAYER_COUNT);
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players.add(new Player());
        }

        gameDesk = new GameDesk();
        gameController = new GameController(this);
    }
}
