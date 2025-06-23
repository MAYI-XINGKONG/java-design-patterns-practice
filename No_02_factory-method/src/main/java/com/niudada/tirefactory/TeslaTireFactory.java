package com.niudada.tirefactory;

import com.niudada.concrete.bmw.MichelinTire;
import com.niudada.factory.TireFactory;
import com.niudada.product.Tire;

public class TeslaTireFactory implements TireFactory {
    @Override
    public Tire createTire() {
        return new MichelinTire();
    }
}
