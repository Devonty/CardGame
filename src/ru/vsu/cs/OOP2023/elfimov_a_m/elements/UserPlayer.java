package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PrintUtils;

import java.util.*;

public class UserPlayer extends AbstractPlayer {
    public UserPlayer(String name) {
        super(name);
    }

    @Override
    protected void sortCardsOnHand() {
        cardsOnHand.sort(USER_COMP);
    }
}
