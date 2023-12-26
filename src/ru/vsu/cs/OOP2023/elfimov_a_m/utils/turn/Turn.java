package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn;

import ru.vsu.cs.OOP2023.elfimov_a_m.Game;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

public interface Turn {
    Player getPlayer();
    int getCardIndexOnHand();
    int position();

    default boolean didPass() {
        return false;
    }

    // return true if new container added
    boolean playInGame(Game game);
    String describe();

}
