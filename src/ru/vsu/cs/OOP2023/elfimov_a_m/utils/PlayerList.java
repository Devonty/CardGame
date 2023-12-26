package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import ru.vsu.cs.OOP2023.elfimov_a_m.elements.player.Player;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerList<T extends Player> extends ArrayList<T> {
    @Override
    public T get(int index) {
        while (index < 0) index += size();
        return super.get(index % size());
    }

    public PlayerList(int initialCapacity) {
        super(initialCapacity);
    }

    public PlayerList(Collection<? extends T> c) {
        super(c);
    }

    public PlayerList() {
    }
}
