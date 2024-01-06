package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.StrategyFactoryManager;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.SimpleBotStrategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.SimpleBotStrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.UserStrategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.UserStrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.Fool36GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {// Поменять на User, для ручной игры
        GameConfig gameConfig = new Fool36GameConfig();

        Map<String, Integer> statistic = new HashMap<>();

        /*
        PlayersStrategyFactoryManager X = new PlayersStrategyFactoryManager();
        X.add(new PlayerStrategy.Factory());
        Game game;

        game.setPlayersStrategyFactory(X);
        game.addPlayer("Type");
        game.start();
         */

        // Manager init
        StrategyFactoryManager strategyFactoryManager = new StrategyFactoryManager();
        // Adding possible strategies
        strategyFactoryManager.add(UserStrategyFactory.getFactory());
        strategyFactoryManager.add(SimpleBotStrategyFactory.getFactory());
        // statistic
        System.out.println("Введите число игр: ");
        int n = new Scanner(System.in).nextInt();
        while(n-- > 0){
            Game game = new Game(gameConfig, strategyFactoryManager);
            // Player add
            //game.addPlayer(UserStrategy.name);
            game.addPlayer(SimpleBotStrategy.name, "Bot_1");
            game.addPlayer(SimpleBotStrategy.name, "Bot_2");
            // Start
            String name = game.start();

            statistic.put(name, 1+statistic.getOrDefault(name,0));
        }

        System.out.println(statistic);
    }
}
