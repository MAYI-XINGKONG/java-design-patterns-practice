package com.niudada.concrete.tesla;

import com.niudada.product.Engine;
import com.niudada.factory.EngineFactory;
import com.niudada.factory.TireFactory;
import com.niudada.product.Car;
import com.niudada.product.Tire;

public class TeslaCar implements Car {
    private final Engine engine;
    private final Tire tire;
    public TeslaCar(EngineFactory engineFactory, TireFactory tireFactory){
        this.engine = engineFactory.createEngine();
        this.tire = tireFactory.createTire();
    }
    @Override
    public void assemble() {
        engine.build();
        tire.build();
        System.out.println("Assembling Tesla Car");
    }
}
