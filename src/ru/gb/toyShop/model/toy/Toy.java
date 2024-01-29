package ru.gb.toyShop.model.toy;

public class Toy {

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

    
}
