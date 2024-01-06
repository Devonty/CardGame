package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.StrategyFactory;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

public class UserStrategyFactory implements StrategyFactory {
    private static StrategyFactory strategyFactory = null;

    public static StrategyFactory getFactory(){
        if(strategyFactory == null) strategyFactory = new UserStrategyFactory();
        return strategyFactory;
    }

    private UserStrategyFactory(){}

    @Override
    public Strategy getStrategy() {
        return new UserStrategy();
    }

    @Override
    public String getStrategyName() {
        return UserStrategy.name;
    }
}
