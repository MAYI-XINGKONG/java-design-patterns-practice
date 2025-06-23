package com.niudada.tirefactory;

import com.niudada.concrete.tesla.BridgestoneTire;
import com.niudada.factory.TireFactory;
import com.niudada.product.Tire;

public class BmwTireFactory implements TireFactory {
    @Override
    public Tire createTire() {
        return new BridgestoneTire();
    }
}
