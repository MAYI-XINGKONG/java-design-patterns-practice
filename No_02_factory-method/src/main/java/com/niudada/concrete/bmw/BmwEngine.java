package com.niudada.concrete.bmw;

import com.niudada.product.Engine;

public class BmwEngine implements Engine {
    @Override
    public void build() {
        System.out.println("Building Bmw Engine");
    }
}
