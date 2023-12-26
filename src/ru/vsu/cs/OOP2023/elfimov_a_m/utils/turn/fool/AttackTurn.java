package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.Game;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

public class AttackTurn extends AbstractTurn{
    public AttackTurn(Player player, int cardIndexOnHand) {
        super(player, cardIndexOnHand, -1);
    }

    @Override
    public boolean playInGame(Game game) {
        Card card = player.takeCardAt(cardIndexOnHand);
        if(!game.addCardOnDesk(card)) {
            // возвращаем, если не смогли положить
            player.addCard(card);
            return false;
        }
        return true;
    }

    @Override
    public String describe() {
        return "Игрок " + player.getName() + " пытается кинуть катру с индексом " + cardIndexOnHand + " на стол.";
    }
}
