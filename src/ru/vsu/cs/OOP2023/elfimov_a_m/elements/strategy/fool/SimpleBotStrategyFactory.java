package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.StrategyFactory;
public class SimpleBotStrategyFactory implements StrategyFactory {
    private static StrategyFactory strategyFactory = null;

    public static StrategyFactory getFactory(){
        if(strategyFactory == null) strategyFactory = new SimpleBotStrategyFactory();
        return strategyFactory;
    }

    private SimpleBotStrategyFactory(){}

    @Override
    public Strategy getStrategy() {
        return new SimpleBotStrategy();
    }

    @Override
    public String getStrategyName() {
        return SimpleBotStrategy.name;
    }
}
