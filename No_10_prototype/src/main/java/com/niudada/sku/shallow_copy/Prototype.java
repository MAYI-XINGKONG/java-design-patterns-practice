package com.niudada.sku.shallow_copy;

import java.io.Serializable;

public abstract class Prototype<T> implements Cloneable {
    public T copy() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("克隆失败", e);
        }
    }
}
