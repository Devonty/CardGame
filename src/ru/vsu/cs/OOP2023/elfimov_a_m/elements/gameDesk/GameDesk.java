package ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;

import java.util.Set;

public interface GameDesk {
    CardContainer get(int index);
    boolean addCard(Card card, int index);
    boolean addCard(Card card);

    int size();

    Set<Integer> getValueIndexesOnDesk();

    Set<Integer> getSuitIndexesOnDesk();

    int getNotBeatenCount();

    default boolean isAllBeaten(){
        return getNotBeatenCount() == 0;
    }

    void clear();
}
