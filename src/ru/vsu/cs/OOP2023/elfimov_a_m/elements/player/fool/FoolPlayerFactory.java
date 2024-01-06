package ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.PlayerFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.SimpleBotStrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.StrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

public class FoolPlayerFactory implements PlayerFactory {
    private final GameConfig gameConfig;

    public FoolPlayerFactory(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public Player getPlayer(Strategy strategy, String playerName){
        Player player = new FoolPlayer(gameConfig, strategy);
        player.setName(playerName);
        strategy.setPlayer(player);
        return player;
    }

}
