package ru.gb.toyShop.model.toylist;

import java.util.Iterator;
import java.util.List;

public class ToyIterator<E> implements Iterator<E> {


    private int index;
    private List<E> toyList;

    public ToyIterator (List<E> toyList) {
        this.toyList = toyList;
    }
    @Override
    public boolean hasNext() {
        return index < toyList.size();
    }

    @Override
    public E next() {
        return toyList.get(index++);
    }
}
