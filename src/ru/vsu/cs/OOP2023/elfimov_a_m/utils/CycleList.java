package ru.vsu.cs.OOP2023.elfimov_a_m.utils;

import java.util.ArrayList;
import java.util.Collection;

public class CycleList<T> extends ArrayList<T> {
    @Override
    public T get(int index) {
        return super.get(index % size());
    }

    public CycleList(int initialCapacity) {
        super(initialCapacity);
    }

    public CycleList(Collection<? extends T> c) {
        super(c);
    }

    public CycleList() {
    }
}
