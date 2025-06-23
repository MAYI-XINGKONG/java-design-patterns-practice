package com.niudada.concrete.byd;

import com.niudada.product.Engine;

public class BydEngine implements Engine {
    @Override
    public void build() {
        System.out.println("Building Byd Engine");
    }
}
