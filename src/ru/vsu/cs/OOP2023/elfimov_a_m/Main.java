package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.StrategyFactoryManager;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.SimpleBotStrategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.SimpleBotStrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.UserStrategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool.UserStrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.Fool36GameConfigFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfigFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Map<String, Integer> statistic = new HashMap<>();

        // GameConfigFactory
        GameConfigFactory gameConfigFactory = new Fool36GameConfigFactory();

        // Manager init
        StrategyFactoryManager strategyFactoryManager = new StrategyFactoryManager();

        // Adding possible strategies
        strategyFactoryManager.add(UserStrategyFactory.getFactory());
        strategyFactoryManager.add(SimpleBotStrategyFactory.getFactory());

        // statistic
        System.out.println("Введите число игр: ");
        int n = new Scanner(System.in).nextInt();
        while(n-- > 0){
            // Game init
            Game game = new Game(gameConfigFactory, strategyFactoryManager);

            // Player add
            game.addPlayer(UserStrategy.name, "Player_1");
            game.addPlayer(SimpleBotStrategy.name, "Bot_2");

            // Start
            String name = game.start();

            statistic.put(name, 1+statistic.getOrDefault(name,0));
        }

        System.out.println(statistic);
    }
}
