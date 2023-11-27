package ru.vsu.cs.OOP2023.elfimov_a_m.elements.player;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.ConsoleToPlayer;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TurnRecord;

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
