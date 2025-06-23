package com.niudada.concrete.byd;

import com.niudada.product.Engine;
import com.niudada.factory.EngineFactory;
import com.niudada.factory.TireFactory;
import com.niudada.product.Car;
import com.niudada.product.Tire;

public class BydCar implements Car {
    private final Engine engine;
    private final Tire tire;

    public BydCar(EngineFactory engineFactory, TireFactory tireFactory) {
        this.engine = engineFactory.createEngine();
        this.tire = tireFactory.createTire();
    }
    @Override
    public void assemble() {
        engine.build();
        tire.build();
        System.out.println("Assembling Byd Car");
    }
}
