package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

public interface StrategyFactory{
    Strategy getStrategy(String type);
}
