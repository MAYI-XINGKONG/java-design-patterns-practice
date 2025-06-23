package com.niudada.tirefactory;

import com.niudada.concrete.byd.BydTire;
import com.niudada.factory.TireFactory;
import com.niudada.product.Tire;

public class BydTireFactory implements TireFactory {
    @Override
    public Tire createTire() {
        return new BydTire();
    }
}
