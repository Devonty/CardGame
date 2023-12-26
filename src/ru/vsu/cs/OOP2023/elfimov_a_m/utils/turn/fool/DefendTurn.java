package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.Game;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

public class DefendTurn extends AbstractTurn{
    public DefendTurn(Player player, int cardIndexOnHand, int position) {
        super(player, cardIndexOnHand, position);
    }

    @Override
    public boolean playInGame(Game game) {
        Card card = player.takeCardAt(cardIndexOnHand);

        if(!game.addCardOnDesk(card, position)) {
            // возвращаем, если не смогли положить
            player.addCard(card);
        }
        return false;
    }

    @Override
    public String describe() {
        return "Игрок " + player.getName() + " пытается отбить катру на позиции " + position + " картой с индексом " + cardIndexOnHand + ".";
    }
}
