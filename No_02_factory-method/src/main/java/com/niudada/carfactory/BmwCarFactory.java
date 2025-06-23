package com.niudada.carfactory;

import com.niudada.factory.EngineFactory;
import com.niudada.concrete.bmw.BmwCar;
import com.niudada.factory.CarFactory;
import com.niudada.product.Car;
import com.niudada.factory.TireFactory;

public class BmwCarFactory implements CarFactory {
    @Override
    public Car createCar(EngineFactory engineFactory, TireFactory tireFactory) {
        return new BmwCar(engineFactory, tireFactory);
    }
}
