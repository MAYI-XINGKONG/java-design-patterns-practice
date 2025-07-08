package com.niudada.sku.deep_copy;

import java.io.*;

public abstract class Prototype<T> implements Serializable {
    public T deepCopy() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()))) {
                return (T) ois.readObject();
            }
        } catch (Exception e) {
            throw new RuntimeException("深拷贝失败", e);
        }
    }
}
