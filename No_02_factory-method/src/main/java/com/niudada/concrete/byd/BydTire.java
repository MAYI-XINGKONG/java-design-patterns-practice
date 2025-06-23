package com.niudada.concrete.byd;

import com.niudada.product.Tire;

public class BydTire implements Tire {
    @Override
    public void build() {
        System.out.println("Mounting Byd Tire");
    }
}
