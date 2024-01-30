package ru.gb.toyShop.model;

import ru.gb.toyShop.model.toy.Toy;
import ru.gb.toyShop.model.toy.ToyBuilder;
import ru.gb.toyShop.model.toylist.ToyList;
import ru.gb.toyShop.model.writer.Writable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private ToyList toyList;
    private ToyBuilder builder;

    private Writable writable;

    public Service() {
        toyList = new ToyList<>();
        builder = new ToyBuilder();
    }

    public String addToy (String name, int numberOfToys) {
        Toy toy = builder.build(name, numberOfToys);
        toyList.addToy(toy);
        toyList.setTotal(toyList.getTotal() + numberOfToys);
        toyList.setFrequences(toyList.getTotal());
        return toy.toString();
    }

    public boolean delToy(int id) {
        toyList.deleteToy(id);
        return save();
    }

    private boolean save() {
        return writable.write(toyList, "toyList.data");
    }

    public void setWritable (Writable writable) {
        this.writable = writable;
    }

    public List<Toy> resultList() throws IOException {
        FileWriter fileWriter = new FileWriter("result.txt");
        List<Toy> result = toyList.resultList(toyList);

        for (Toy item: result) {
            fileWriter.write("ID: " + item.getId() + ", Игрушка: " + item.getName());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.flush();
        fileWriter.close();
        return result;
    }

    public String changeNumberOfToys(int id, int numberOfToys) {
        Toy toy = toyList.findList(id);
        toyList.setTotal(toyList.getTotal() - toy.getNumberOfToys());
        toy.setNumberOfToys(numberOfToys);
        toyList.setTotal(toyList.getTotal() + numberOfToys);
        toyList.setFrequences(toyList.getTotal());
        return toy.toString();
    }

    public  void  load() throws IOException {
        toyList = (ToyList) writable.read("toyList.data");
    }

    public String getResultList() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("result.txt"))) {
            sb.append(br.lines().collect(Collectors.joining(System.lineSeparator())));
            return sb.toString();
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }
}
