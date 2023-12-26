package ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FoolCardContainer implements CardContainer{
    private final Deque<Card> cards;

    public FoolCardContainer() {
        this.cards = new LinkedList<>();
    }

    @Override
    public Card get(int index) {
        return (Card) cards.toArray()[index];
    }

    @Override
    public Card getOrDefault(int index, Card card) {
        if(0 <= index && index < cards.size()) return (Card) cards.toArray()[index];
        return card;
    }

    @Override
    public Card getTop() {
        return cards.getFirst();
    }

    @Override
    public Card popTop() {
        return cards.removeFirst();
    }

    @Override
    public Card getBottom() {
        return cards.getLast();
    }

    @Override
    public Card popBottom() {
        return cards.removeLast();
    }

    @Override
    public void addTop(Card card) {
        cards.addFirst(card);
    }

    @Override
    public void addBottom(Card card) {
        cards.addLast(card);
    }

    @Override
    public void clear() {
        cards.clear();
    }

    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public List<Card> getAllCards() {
        return new LinkedList<>(cards);
    }
}
