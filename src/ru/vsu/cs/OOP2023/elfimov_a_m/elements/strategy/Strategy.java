package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;

import java.util.List;

public interface Strategy {

    Turn askForAttack(GameStatus gameStatus);
    Turn askForDefend(GameStatus gameStatus);

    void sortCardsOnHand(List<Card> cards);

    void setPlayer(Player player);
}
