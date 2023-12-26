package ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;

import java.util.List;

public interface CardContainer{
    Card get(int index);
    Card getOrDefault(int index, Card card);
    Card getTop();
    Card getBottom();
    Card popTop();
    Card popBottom();
    void addTop(Card card);
    void addBottom(Card card);

    void clear();

    int size();

    default boolean isEmpty(){
        return size() == 0;
    }

    List<Card> getAllCards();


}
