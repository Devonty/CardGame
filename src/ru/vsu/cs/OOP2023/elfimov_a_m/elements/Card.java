package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

public class Card{
    private final int suitIndex;
    private final int valueIndex;

    public Card(int suitIndex, int valueIndex) {
        this.suitIndex = suitIndex;
        this.valueIndex = valueIndex;
    }

    public int getSuitIndex() {
        return suitIndex;
    }

    public int getValueIndex() {
        return valueIndex;
    }
}
