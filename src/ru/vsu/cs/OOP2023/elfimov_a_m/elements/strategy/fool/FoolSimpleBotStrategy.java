package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.cardContainer.CardContainer;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.gameConfig.GameConfig;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.AttackTurn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.DefendTurn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.PassTurn;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool.TakePassTurn;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FoolSimpleBotStrategy implements Strategy {

    public final Comparator<Card> CARD_ON_HAND_COMP = new Comparator<>() {
        @Override
        public int compare(Card c1, Card c2) {
            int[] cmpArr1 = new int[]{
                    c1.getSuitIndex() == gameConfig.trumpSuitIndex() ? 1 : 0,
                    c1.getSuitIndex(),
                    c1.getValueIndex()
            };

            int[] cmpArr2 = new int[]{
                    c2.getSuitIndex() == gameConfig.trumpSuitIndex() ? 1 : 0,
                    c2.getSuitIndex(),
                    c2.getValueIndex()
            };

            return Arrays.compare(cmpArr1, cmpArr2);
        }
    };
    private final GameConfig gameConfig;
    private Player player;

    public FoolSimpleBotStrategy(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }


    @Override
    public Turn askForAttack(GameStatus gameStatus) {
        for (int i = 0; i < player.countCardsOnHand(); i++) {
            Card card = player.peekCardAt(i);
            if (gameConfig.gameRules().canAddCardOnDesk(gameStatus, card))
                return new AttackTurn(player, i);
        }
        return new PassTurn(player);
    }

    @Override
    public Turn askForDefend(GameStatus gameStatus) {
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
    public void sortCardsOnHand(List<Card> cards) {
        cards.sort(CARD_ON_HAND_COMP);
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
}
