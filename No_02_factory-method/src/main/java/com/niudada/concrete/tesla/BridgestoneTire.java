package com.niudada.concrete.tesla;

import com.niudada.product.Tire;

public class BridgestoneTire implements Tire {
    @Override
    public void build() {
        System.out.println("Mounting Bridgestone Tire");
    }
}
