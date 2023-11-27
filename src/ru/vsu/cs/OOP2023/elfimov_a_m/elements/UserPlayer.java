package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.ConsoleToPlayer;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PrintUtils;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TurnRecord;

import java.util.*;

public class UserPlayer extends AbstractPlayer {
    public UserPlayer(String name) {
        super(name);
    }

    @Override
    protected void sortCardsOnHand() {
        cardsOnHand.sort(USER_COMP);
    }

    @Override
    public TurnRecord askForAttack(GameDesk gameDesk) {
        // not need gameDesk, but bot need
        return ConsoleToPlayer.askForAttack(this);
    }

    @Override
    public TurnRecord askForDefend(GameDesk gameDesk) {
        return ConsoleToPlayer.askForDefend(this, gameDesk);
    }
}
