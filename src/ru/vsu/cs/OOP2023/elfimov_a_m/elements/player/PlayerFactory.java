package ru.vsu.cs.OOP2023.elfimov_a_m.elements.player;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;

public interface PlayerFactory {
    Player getPlayer(Strategy strategy, String playerName);
}
