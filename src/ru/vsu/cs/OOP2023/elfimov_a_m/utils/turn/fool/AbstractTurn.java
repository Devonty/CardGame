package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;

abstract public class AbstractTurn implements Turn {
    protected Player player;
    protected int cardIndexOnHand;
    protected int position;

    public AbstractTurn(Player player, int cardIndexOnHand, int position) {
        this.player = player;
        this.cardIndexOnHand = cardIndexOnHand;
        this.position = position;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int getCardIndexOnHand() {
        return cardIndexOnHand;
    }

    @Override
    public int position() {
        return position;

    }
}
