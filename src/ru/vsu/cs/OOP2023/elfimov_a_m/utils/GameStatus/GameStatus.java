package ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.Set;

public interface GameStatus {
    CardContainer getCardContainerAt(int index);
    int getTrumpSuitIndex();
    int getContainerCount();
    int getNotBeatenCount();
    int currentPlayerCount();
    int initialPlayerCount();
    int getDeckSize();
    int getDefenderIndex();

    String getDefenderName();
    String getPlayerNameByIndex(int index);

    int getPlayerCardCount(int playerIndex);

    GameConfig getGameConfig();
    boolean allAttackerPass();

    int getDeskSpotCount();

    default boolean isDeckEmpty() {
        return getDeckSize() == 0;
    }
    default boolean isAllBeaten() {
        return getNotBeatenCount() ==0;
    }

    Set<Integer> getValueIndexesOnDesk();
    Set<Integer> getSuitIndexesOnDesk();
}
