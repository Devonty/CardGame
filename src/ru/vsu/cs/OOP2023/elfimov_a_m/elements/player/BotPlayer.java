package ru.vsu.cs.OOP2023.elfimov_a_m.elements.player;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.BotPlayerTurns;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TurnRecord;

public class BotPlayer extends AbstractPlayer {
    public BotPlayer(String name) {
        super(name);
    }

    @Override
    protected void sortCardsOnHand() {
        cardsOnHand.sort(BOT_COMP);
    }

    @Override
    public TurnRecord askForAttack(GameDesk gameDesk) {
        return BotPlayerTurns.askForAttack(this, gameDesk);
    }

    @Override
    public TurnRecord askForDefend(GameDesk gameDesk) {
        return BotPlayerTurns.askForDefend(this, gameDesk);
    }
}
