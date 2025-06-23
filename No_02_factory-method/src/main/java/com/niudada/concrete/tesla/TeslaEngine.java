package com.niudada.concrete.tesla;

import com.niudada.product.Engine;

public class TeslaEngine implements Engine {
    @Override
    public void build() {
        System.out.println("Building Tesla Engine");
    }
}
