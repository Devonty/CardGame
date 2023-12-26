package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.Game;
import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

public class TakePassTurn extends AbstractTurn{
    public TakePassTurn(Player player) {
        super(player, -1, -1);
    }

    @Override
    public boolean didPass() {
        return true;
    }

    @Override
    public boolean playInGame(Game game) {
        // Просим добавить карты
        game.getController().askForAttackTurn();
        // Ставим метку, что раунд должен закончится
        game.getController().endRound();
        // Отдаем все карты со стола игроку
        game.giveAllDeskCardToPlayer(player);
        // Игрок стянул - он не ходит
        game.nextPlayer();
        return true;
    }

    @Override
    public String describe() {
        return "Игрок " + player.getName() + " тянет карты.";
    }
}
