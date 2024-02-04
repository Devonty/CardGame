package ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TrumpProvider;

public class Fool36GameConfigFactory implements GameConfigFactory {
    @Override
    public GameConfig getGameConfig(TrumpProvider trumpProvider) {
        return new Fool36GameConfig(trumpProvider);
    }
}
