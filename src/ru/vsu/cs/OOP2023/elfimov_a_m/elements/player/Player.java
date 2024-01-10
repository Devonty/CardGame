package ru.vsu.cs.OOP2023.elfimov_a_m.elements.player;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;

import java.util.Comparator;

public interface Player{
    void setStatus(playerStatus status);

    GameConfig getGameConfig();

    enum playerStatus {WON, PLAYING, LOSE};

    playerStatus getStatus();
    void addCard(Card cardToAdd);
    boolean needCard();
    Card peekCardAt(int index);

    Card takeCardAt(int index);

    String getName();
    void setName(String newName);
    int countCardsOnHand();

    void sortCardsOnHand(Comparator<Card> comparator);

    Turn askForAttack(GameStatus gameStatus);
    Turn askForDefend(GameStatus gameStatus);
}
