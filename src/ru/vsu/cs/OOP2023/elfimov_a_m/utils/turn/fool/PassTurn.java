package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

public class PassTurn extends AbstractTurn{
    public PassTurn(Player player) {
        super(player, -1, -1);
    }

    @Override
    public boolean didPass() {
        return true;
    }

    @Override
    public String describe() {
        return "Игрок " + player.getName() + " спасовал.";
    }
}
