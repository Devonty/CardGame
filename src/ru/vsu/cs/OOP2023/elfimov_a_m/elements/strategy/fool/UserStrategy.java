package ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.Card;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.strategy.Strategy;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.ConsoleToUser;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.GameStatus.GameStatus;
import ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.Turn;

import java.util.Arrays;
import java.util.Comparator;

public class UserStrategy implements Strategy {
    public static final String name = "User";
    private int trumpSuitIndex;

    public final Comparator<Card> CARD_ON_HAND_COMP = new Comparator<>() {
        @Override
        public int compare(Card c1, Card c2) {
            int[] cmpArr1 = new int[]{
                    c1.getSuitIndex() == trumpSuitIndex ? 1 : 0,
                    c1.getSuitIndex(),
                    c1.getValueIndex()
            };

            int[] cmpArr2 = new int[]{
                    c2.getSuitIndex() == trumpSuitIndex ? 1 : 0,
                    c2.getSuitIndex(),
                    c2.getValueIndex()
            };

            return Arrays.compare(cmpArr1, cmpArr2);
        }
    };
    private Player player;


    @Override
    public Turn askForAttack(GameStatus gameStatus) {
        trumpSuitIndex = gameStatus.getTrumpSuitIndex();
        return ConsoleToUser.askForAttack(gameStatus, player);
    }

    @Override
    public Turn askForDefend(GameStatus gameStatus) {
        trumpSuitIndex = gameStatus.getTrumpSuitIndex();
        return ConsoleToUser.askForDefend(gameStatus, player);
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
