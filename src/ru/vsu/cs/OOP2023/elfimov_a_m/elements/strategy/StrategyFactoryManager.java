package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactoryManager {
    private final Map<String, StrategyFactory> factoryMap;

    public StrategyFactoryManager() {
        factoryMap = new HashMap<>();
    }

    public Strategy getStrategy(String type) {
        StrategyFactory factory =  factoryMap.getOrDefault(type, null);
        if(factory == null) return null;

        return factory.getStrategy();
    }

    public void add(StrategyFactory factory) {
        if(factory == null) return;
        factoryMap.put(factory.getStrategyName(), factory);
    }
}
