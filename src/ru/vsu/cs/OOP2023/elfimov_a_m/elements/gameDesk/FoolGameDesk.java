package ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.FoolCardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.*;
import java.util.stream.Collectors;

public class FoolGameDesk implements GameDesk {
    private final GameConfig gameConfig;
    private final List<CardContainer> spots;

    public FoolGameDesk(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        spots = new ArrayList<>(gameConfig.maxCardsOnDesk());
    }

    @Override
    public int size() {
        return spots.size();
    }

    @Override
    public Set<Integer> getValueIndexesOnDesk() {
        return spots.stream().map(CardContainer::getAllCards).
                flatMap(List::stream).
                map(Card::getValueIndex).collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> getSuitIndexesOnDesk() {
        return spots.stream().map(CardContainer::getAllCards).
                flatMap(List::stream).
                map(Card::getSuitIndex).collect(Collectors.toSet());
    }

    @Override
    public int getNotBeatenCount() {
        int count = 0;
        for (CardContainer spot : spots) {
            if (!gameConfig.gameRules().isCardContainerBeaten(spot))
                count++;
        }
        return count;
    }

    @Override
    public void clear() {
        spots.clear();
    }

    @Override
    public CardContainer get(int index) {
        if(!indexInRange(index)) return null;
        return spots.get(index);
    }

    @Override
    public boolean addCard(Card card, int index) {
        // Неправильный индекс
        if(!indexInRange(index)) return false;
        // Проверка, что можно добавить карту
        CardContainer cardContainer = spots.get(index);
        if(!gameConfig.gameRules().canAddCardInContainer(cardContainer, card)) return false;
        // Добавляем если можно
        spots.get(index).addBottom(card);
        return true;
    }

    @Override
    public boolean addCard(Card card) {
        if(spots.size() >= gameConfig.maxCardsOnDesk()) return false;
        spots.add(new FoolCardContainer(){{addTop(card);}});
        return true;
    }

    private boolean indexInRange(int index){
        return 0 <= index && index < size();
    }
}
