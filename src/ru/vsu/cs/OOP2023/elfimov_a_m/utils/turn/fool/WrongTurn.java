package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool;

import ru.vsu.cs.OOP2023.elfimov_a_m.Game;

public class WrongTurn extends AbstractTurn{

    public WrongTurn() {
        super(null, -1, -1);
    }

    @Override
    public boolean playInGame(Game game) {
        return false;
    }

    @Override
    public String describe() {
        return "Неверный ход!";
    }
}
