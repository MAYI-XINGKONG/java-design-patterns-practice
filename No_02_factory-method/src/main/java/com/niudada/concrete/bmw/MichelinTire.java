package com.niudada.concrete.bmw;

import com.niudada.product.Tire;

public class MichelinTire implements Tire {
    @Override
    public void build() {
        System.out.println("Mounting Michelin Tire");
    }
}
