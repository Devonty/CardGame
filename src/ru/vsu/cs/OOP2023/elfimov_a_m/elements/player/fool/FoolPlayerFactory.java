package ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.PlayerFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.FoolStrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.StrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

public class FoolPlayerFactory implements PlayerFactory {
    private final GameConfig gameConfig;
    private final StrategyFactory strategyFactory;

    public FoolPlayerFactory(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.strategyFactory = new FoolStrategyFactory(gameConfig);
    }

    public Player getPlayer(String type){
        Strategy strategy = strategyFactory.getStrategy(type);
        Player player = new FoolPlayer(gameConfig, strategy);
        strategy.setPlayer(player);
        return player;
    }

}
