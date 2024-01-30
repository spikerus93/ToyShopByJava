package ru.gb.toyShop.view;

import ru.gb.toyShop.model.writer.Writable;
import ru.gb.toyShop.presenter.Presenter;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {
    private Presenter presenter;
    private Scanner scanner;
    private boolean work;
    private MainMenu menu;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
        menu = new MainMenu(this);
    }

    public void start() {
        hello();
        load();
        while (work) {
            printMenu();
            choice();
        }
    }

    private void choice() {
        String value = scanner.nextLine();
        if (checkMenu(value)) {
            int num = Integer.parseInt(value);
            menu.execute(num);
        }
    }
    private boolean checkMenu(String text) {
        try {
            if (Integer.parseInt(text) > 0 && Integer.parseInt(text) <= menu.getSize()) {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Вы ввели неверное значение!");
        return false;
    }

    public void finish() {
        System.out.println("Работа завершена.");
        scanner.close();
        work = false;
    }

    private void printMenu() {
        System.out.println("Выберите Операцию: ");
        System.out.println(menu.menu());
    }

    private void load() {
        try {
            presenter.load();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setWritable (Writable writable) {
        presenter.setWritable(writable);
    }

    private void hello() {
        System.out.println("Добро пожаловать!");
    }

    public void resultList() {
        if (presenter.resultList()) {
            success();
        } else {
            error();
        }
    }

    private void error() {
        System.out.println("Данные не сохранены!");
    }

    private void success() {
        System.out.println("Данные сохранены.");
    }

    public void getInfo() {
        if (!presenter.getInfo()) {
            System.out.println("Список пуст!");
        }
    }

    public void changeNumberofToys() {
        System.out.println("Введите ID игрушки для изменения ее количества: ");
        int id  = checkInt();
        System.out.println("Задайте количество игрушек: ");
        int numberOfToys = checkInt();
        presenter.changeNumberOfToys(id, numberOfToys);
        if (presenter.save()) {
            success();
        } else {
            error();
        }
    }

    public void getResultList() {
        if (!presenter.getResultList()) {
            System.out.println("Список пуст!");
        }
    }

    private int checkInt() {
        int value = 0;
        boolean i = true;
        while (i) {
            String text = scanner.nextLine();
            if (text.matches("[0-9]+")) {
                value = Integer.parseInt(text);
                i = false;
            } else {
                System.out.println("Неверное значение! Введите целое число: ");
            }
        }
        return value;
    }

    public void addToy() {
        System.out.println("Введите название игрушки: ");
        String name = scanner.nextLine();
        System.out.println("Введите количество: ");
        int numberOfToys = checkInt();
        presenter.addToy(name, numberOfToys);
        if (presenter.save()) {
            success();
        } else {
            error();
        }
    }

    public void printAnswer(String answer) {
        System.out.println(answer);
    }

    public void delToy() {
        System.out.println("Введите ID игрушки, которую необходимо удалить: ");
        int id = checkInt();
        if (presenter.delToy(id)) {
            success();
        } else {
            error();
        }
    }
}



