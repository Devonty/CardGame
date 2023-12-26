package ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;

import java.util.List;

public class CardContainerProxy implements CardContainer{
    /*
    * Класс для котроля доступа, к изменению контейнера. К примеру, чтобы игрок не менять контейнер
    * */

    private CardContainer cardContainer;

    public CardContainerProxy(CardContainer cardContainer) {
        this.cardContainer = cardContainer;
    }

    @Override
    public Card get(int index) {
        return cardContainer.get(index);
    }

    @Override
    public Card getOrDefault(int index, Card card) {
        return cardContainer.getOrDefault(index, card);
    }

    @Override
    public Card getTop() {
        return cardContainer.getTop();
    }

    @Override
    public Card getBottom() {
        return cardContainer.getBottom();
    }

    @Override
    public Card popTop() {
        throw new UnsupportedOperationException("Нет доступа к удалению!");
    }

    @Override
    public Card popBottom() {
        throw new UnsupportedOperationException("Нет доступа к удалению!");
    }

    @Override
    public void addTop(Card card) {
        throw new UnsupportedOperationException("Нет доступа к добавлению!");
    }

    @Override
    public void addBottom(Card card) {
        throw new UnsupportedOperationException("Нет доступа к добавлению!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Нет доступа к очистке!");
    }

    @Override
    public int size() {
        return cardContainer.size();
    }

    @Override
    public List<Card> getAllCards() {
        // Возвращается новый массив с картами
        return cardContainer.getAllCards();
    }
}
