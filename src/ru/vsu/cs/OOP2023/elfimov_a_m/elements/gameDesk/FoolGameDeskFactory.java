package ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

public class FoolGameDeskFactory implements GameDeskFactory{
    private GameConfig gameConfig;

    public FoolGameDeskFactory(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public GameDesk getGameDesk() {
        return new FoolGameDesk(gameConfig);
    }
}
