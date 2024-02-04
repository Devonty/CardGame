package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.TrumpProvider;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.AttackTurn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.DefendTurn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.PassTurn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.TakePassTurn;

import java.util.Arrays;
import java.util.Comparator;

public class SimpleBotStrategy implements Strategy {
    public static final String name = "BotSimple";
    private TrumpProvider trumpSuitIndex;

    private final Comparator<Card> CARD_ON_HAND_COMP = (c1, c2) -> {
        int[] cmpArr1 = new int[]{
                c1.getSuitIndex() == trumpSuitIndex.getTrumpSuitIndex() ? 1 : 0,
                c1.getValueIndex(),
                c1.getSuitIndex()
        };

        int[] cmpArr2 = new int[]{
                c2.getSuitIndex() == trumpSuitIndex.getTrumpSuitIndex() ? 1 : 0,
                c2.getValueIndex(),
                c2.getSuitIndex()
        };

        return Arrays.compare(cmpArr1, cmpArr2);
    };
    private Player player;
    @Override
    public Turn askForAttack(GameStatus gameStatus) {
        GameConfig gameConfig = gameStatus.getGameConfig();
        // save trump suit
        trumpSuitIndex = gameStatus;
        // moveTurn
        for (int i = 0; i < player.countCardsOnHand(); i++) {
            Card card = player.peekCardAt(i);
            if (gameConfig.gameRules().canAddCardOnDesk(gameStatus, card))
                return new AttackTurn(player, i);
        }
        return new PassTurn(player);
    }

    @Override
    public Turn askForDefend(GameStatus gameStatus) {
        trumpSuitIndex = gameStatus;

        GameConfig gameConfig = gameStatus.getGameConfig();
        // Первый неотбитый контейнер
        int position = 0;
        CardContainer cardContainer = gameStatus.getCardContainerAt(0);
        while (gameConfig.gameRules().isCardContainerBeaten(cardContainer))
            cardContainer = gameStatus.getCardContainerAt(++position);
        // Перебираем карты на руках, чтобы побить
        for (int i = 0; i < player.countCardsOnHand(); i++) {
            Card card = player.peekCardAt(i);
            if (gameConfig.gameRules().canCardBeatAnother(card, cardContainer.getBottom()))
                return new DefendTurn(player, i, position);
        }
        return new TakePassTurn(player);
    }


    @Override
    public void sortCardsOnHand() {
        player.sortCardsOnHand(CARD_ON_HAND_COMP);
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return name;
    }
}
