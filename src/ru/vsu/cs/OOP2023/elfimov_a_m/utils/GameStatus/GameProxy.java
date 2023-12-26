package ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus;

import ru.vsu.cs.OOP2023.elfimov_a_m.Game;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainerProxy;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.Set;

public class GameProxy implements GameStatus{
    private final Game game;

    public GameProxy(Game game) {
        this.game = game;
    }

    @Override
    public CardContainer getCardContainerAt(int index) {
        return new CardContainerProxy(game.getCardContainerAt(index));
    }

    @Override
    public int getTrumpSuitIndex() {
        return game.getTrumpSuitIndex();
    }

    @Override
    public int getContainerCount() {
        return game.getContainerCount();
    }

    @Override
    public int getNotBeatenCount() {
        return game.getNotBeatenCount();
    }

    @Override
    public int currentPlayerCount() {
        return game.currentPlayerCount();
    }

    @Override
    public int getDeckSize() {
        return game.getDeckSize();
    }

    @Override
    public int getDefenderIndex() {
        return game.getDefenderIndex();
    }

    @Override
    public int getPlayerCardCount(int playerIndex) {
        return game.getPlayerCardCount(playerIndex);
    }

    @Override
    public GameConfig getGameConfig() {
        return game.getGameConfig();
    }


    @Override
    public boolean allAttackerPass() {
        return false;
    }

    @Override
    public int getDeskSpotCount() {
        return 0;
    }

    @Override
    public Set<Integer> getValueIndexesOnDesk() {
        return game.getValueIndexesOnDesk();
    }

    @Override
    public Set<Integer> getSuitIndexesOnDesk() {
        return game.getSuitIndexesOnDesk();
    }
}
