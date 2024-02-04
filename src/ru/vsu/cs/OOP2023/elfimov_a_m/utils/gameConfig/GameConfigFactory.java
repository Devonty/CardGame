package ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TrumpProvider;

public interface GameConfigFactory {
    GameConfig getGameConfig(TrumpProvider trumpProvider);
}
