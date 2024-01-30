package ru.gb.toyShop.model.toylist;

import ru.gb.toyShop.model.comparators.ToyComparator;
import ru.gb.toyShop.model.toy.Toy;

import java.io.Serializable;
import java.util.*;

public class ToyList<E extends ToyItem> implements Serializable, Iterable<E> {

    private  List<E> toyList;
    private  int toyId;
    private  int total;

    public ToyList() {
        toyList = new ArrayList<>();
    }

    public void addToy(E toy) {
        toy.setId(toyId++);
        toyList.add(toy);
    }

    public List<E> getToyList() {
        return toyList;
    }

    public List<Toy> resultList(ToyList toyList) {
        List<Toy> result = new ArrayList<>();
        Queue<Toy> priorityList = new PriorityQueue<>(new ToyComparator<>());
        priorityList.addAll(toyList.getToyList());
        while (toyList.getTotal() >= 1) {
            Toy choice = priorityList.peek();
            result.add(choice);
            assert choice != null;
            choice.setNumberOfToys(choice.getNumberOfToys()-1);
            toyList.setFrequences(toyList.getTotal()-1);
            priorityList.remove(choice);
            priorityList.add(choice);
        }
        return result;
    }

    public void setFrequences(int total) {
        setTotal(total);
        if (total != 0){
            for (E toy: toyList) {
                toy.setFrequency(toy.getNumberOfToys()*100/getTotal());
            }
        }
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int getTotal() {
        return  total;
    }

    public Toy findList(int id) {
        for (E toy: toyList) {
            if (toy.getId() == id) {
                return (Toy) toy;
            }
        }
        return null;
    }

    public void deleteToy(int id) {
        toyList.removeIf(toy -> toy.getId() == id);
        int newTotal = 0;
        for (E toy: toyList) {
            newTotal += toy.getNumberOfToys();
        }
        setFrequences(newTotal);
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        for (E toy: toyList) {
            sb.append(toy).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ToyIterator<>(toyList);
    }
}
