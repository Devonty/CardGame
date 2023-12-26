package ru.vsu.cs.OOP2023.elfimov_a_m;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardDeck.CardDeck;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.gameDesk.GameDesk;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.PlayerList;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameProxy;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;

import java.util.Set;

public class Game implements GameStatus {
    private final PlayerList<Player> players;
    private final GameController gameController;
    private final GameConfig gameConfig;
    private final GameDesk gameDesk;
    private final CardDeck cardDeck;

    private final GameStatus gameStatus;

    private int trumpSuitIndex;
    private int defenderIndex = 0;

    public Game(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.players = gameConfig.getPlayers();
        this.gameDesk = gameConfig.getGameDesk();
        this.cardDeck = gameConfig.getCardDeck();
        this.trumpSuitIndex = gameConfig.trumpSuitIndex();

        this.gameStatus = new GameProxy(this);
        this.gameController = new GameController(this);
    }

    public boolean addCardOnDesk(Card card) {
        if (!gameConfig.gameRules().canAddCardOnDesk(gameStatus, card)) return false;
        gameDesk.addCard(card);
        return true;
    }

    public boolean addCardOnDesk(Card card, int position) {
        if (!gameConfig.gameRules().canAddCardInContainer(gameStatus.getCardContainerAt(position), card)) return false;
        return gameDesk.addCard(card, position);
    }

    public void giveAllDeskCardToPlayer(Player defender) {
        for (int i = 0; i < gameDesk.size(); i++) {
            for (Card card : gameDesk.get(i).getAllCards())
                defender.addCard(card);
        }
        gameDesk.clear();
    }

    public void giveCardsToPlayersFromDeck() {
        for (Player player : players) {
            while (!cardDeck.isEmpty() && player.needCard()) {
                player.addCard(cardDeck.takeTopCard());
            }
        }
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String start() {
        // начальная раздача карт
        giveCardsToPlayersFromDeck();
        while (!isEndOfGame()) {
            // Отыграть раунд
            gameController.playRound();
            // Подготовиться к следующему раунду
            giveCardsToPlayersFromDeck();
            checkPlayerStatuses();
            gameDesk.clear();
            nextPlayer();
        }
        checkPlayerStatuses();

        Player loser = getLoser();
        if (loser == null) {
            System.out.println("Ничья!");
            return "Draw";
        }
        System.out.println("Игрок " + loser.getName() + " проиграл!");
        return loser.getName();

    }

    public void nextPlayer() {
        int i = 1;
        Player defender = getPlayer(defenderIndex + i);
        while (i < players.size() && defender.getStatus() != Player.playerStatus.PLAYING)
            defender = getPlayer((++i) + defenderIndex);
        defenderIndex = (i + defenderIndex) % players.size();
    }

    private boolean isEndOfGame() {
        return currentPlayerCount() <= 1 && getDeckSize() == 0;
    }

    public void checkPlayerStatuses() {
        if (!isDeckEmpty()) return;

        for (Player player : players) {
            if (player.countCardsOnHand() == 0) player.setStatus(Player.playerStatus.WON);
            else if (isEndOfGame()) player.setStatus(Player.playerStatus.LOSE);

        }
    }

    public Player getLoser() {
        for (Player player : players) {
            if (player.getStatus() == Player.playerStatus.LOSE)
                return player;
        }
        return null;
    }

    @Override
    public CardContainer getCardContainerAt(int index) {
        return gameDesk.get(index);
    }

    @Override
    public int getTrumpSuitIndex() {
        return trumpSuitIndex;
    }

    @Override
    public int getContainerCount() {
        return gameDesk.size();
    }

    @Override
    public int getNotBeatenCount() {
        return gameDesk.getNotBeatenCount();
    }

    @Override
    public int currentPlayerCount() {
        int k = 0;
        for (Player player : players) k += player.getStatus() == Player.playerStatus.PLAYING ? 1 : 0;
        return k;
    }

    @Override
    public int getDeckSize() {
        return cardDeck.size();
    }

    @Override
    public int getDefenderIndex() {
        return defenderIndex;
    }

    @Override
    public int getPlayerCardCount(int playerIndex) {
        return players.get(playerIndex).countCardsOnHand();
    }

    @Override
    public GameConfig getGameConfig() {
        return gameConfig;
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
        return gameDesk.getValueIndexesOnDesk();
    }

    @Override
    public Set<Integer> getSuitIndexesOnDesk() {
        return gameDesk.getValueIndexesOnDesk();
    }

    public GameController getController() {
        return gameController;
    }
}
