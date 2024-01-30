package ru.gb.toyShop.model.comparators;

import ru.gb.toyShop.model.toylist.ToyItem;

import java.util.Comparator;

public class ToyComparator<E extends ToyItem> implements Comparator<E> {
    @Override
    public int compare(E o1, E o2) {
        return Integer.compare(o2.getFrequency(), o1.getFrequency());
    }
}
