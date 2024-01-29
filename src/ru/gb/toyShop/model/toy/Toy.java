package ru.gb.toyShop.model.toy;

import java.io.Serializable;

public class Toy implements Serializable {

    private int id;
    private String name;
    private int numberOfToys;

    private int frequency;

    public Toy (String name, int numberOfToys) {
        this.name = name;
        this.numberOfToys = numberOfToys;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfToys() {
        return numberOfToys;
    }

    public void setNumberOfToys(int numberOfToys) {
        this.numberOfToys = numberOfToys;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Название игрушки: " + name + ", количество: " + numberOfToys + ", частота выпадения: " + frequency;
    }
}
