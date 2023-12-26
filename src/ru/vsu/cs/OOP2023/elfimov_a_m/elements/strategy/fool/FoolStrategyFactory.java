package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.StrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

public class FoolStrategyFactory implements StrategyFactory {

    private final GameConfig gameConfig;

    public FoolStrategyFactory(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public Strategy getStrategy(String type) {
        if (type.equalsIgnoreCase("User")) {
            return new FoolUserStrategy(gameConfig);
        } else if (type.equalsIgnoreCase("SimpleBot")) {
            return new FoolSimpleBotStrategy(gameConfig);
        } else {
            throw new RuntimeException(type + "is unknown type of strategy");
        }
    }
}
