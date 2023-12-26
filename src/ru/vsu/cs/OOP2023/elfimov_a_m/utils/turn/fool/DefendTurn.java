package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

public class DefendTurn extends AbstractTurn{
    public DefendTurn(Player player, int cardIndexOnHand, int position) {
        super(player, cardIndexOnHand, position);
    }

    @Override
    public String describe() {
        return "Игрок " + player.getName() + " пытается отбить катру на позиции " + position + " картой с индексом " + cardIndexOnHand + ".";
    }
}
