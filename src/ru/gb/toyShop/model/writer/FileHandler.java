package ru.gb.toyShop.model.writer;

import ru.gb.toyShop.model.toylist.ToyList;

import java.io.*;

public class FileHandler implements Writable{

    private String filePath;
    @Override
    public boolean write(Serializable serializable, String filePath) {
        boolean flag = false;

        File file = new File(filePath);
        ObjectOutputStream oos = null;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            if (fos != null) {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(serializable);
                flag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Object read(String filePath) throws IOException {
        File file = new File(filePath);
        ObjectInputStream ois = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            if (fis != null) {
                ois = new ObjectInputStream(fis);
                ToyList readToys = (ToyList) ois.readObject();
                return readToys;
            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        throw new InvalidObjectException("Ошибка выполнения...");
    }
}
