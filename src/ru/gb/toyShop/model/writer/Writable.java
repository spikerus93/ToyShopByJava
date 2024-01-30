package ru.gb.toyShop.model.writer;

import java.io.IOException;
import java.io.Serializable;

public interface Writable {
    boolean write (Serializable serializable, String filePath);
    Object read (String filePath) throws IOException;
}
