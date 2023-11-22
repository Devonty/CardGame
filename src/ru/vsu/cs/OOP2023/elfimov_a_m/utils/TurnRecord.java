package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Player;

public record TurnRecord(Turn turn, Player player, int cardToBeatIndex, int cardThatBeatIndex) {
    public enum Turn {BEATS, PASS, ADDING, WRONG_TURN, TAKE_PASS}

    public static final TurnRecord WRONG_TURN_RECORD = new TurnRecord(Turn.WRONG_TURN, null, -1, -1);

    @Override
    public String toString() {
        return "TurnRecord{" +
                "turn=" + turn +
                ", player=" + player.name +
                ", cardToBeatIndex=" + cardToBeatIndex +
                ", cardThatBeatIndex=" + cardThatBeatIndex +
                '}';
    }
}
