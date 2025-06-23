package com.niudada.carfactory;

import com.niudada.factory.EngineFactory;
import com.niudada.concrete.byd.BydCar;
import com.niudada.factory.CarFactory;
import com.niudada.product.Car;
import com.niudada.factory.TireFactory;

public class BydCarFactory implements CarFactory {
    @Override
    public Car createCar(EngineFactory engineFactory, TireFactory tireFactory) {
        return new BydCar(engineFactory, tireFactory);
    }
}
