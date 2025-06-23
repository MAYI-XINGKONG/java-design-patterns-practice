package com.niudada.factory;

import com.niudada.product.Car;

public interface CarFactory {
    Car createCar(EngineFactory engineFactory, TireFactory tireFactory);
}
