package ru.vsu.cs.OOP2023.elfimov_a_m.utils.turn.fool;

public class WrongTurn extends AbstractTurn{

    public WrongTurn() {
        super(null, -1, -1);
    }

    @Override
    public String describe() {
        return "Неверный ход!";
    }
}
