package ru.vsu.cs.OOP2023.elfimov_a_m.elements;

import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TurnRecord;

public class BotPlayer extends AbstractPlayer {
    public BotPlayer(String name) {
        super(name);
    }

    @Override
    protected void sortCardsOnHand() {
        cardsOnHand.sort(BOT_COMP);
    }

}
