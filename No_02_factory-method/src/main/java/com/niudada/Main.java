package com.niudada;

import com.niudada.enginefactory.BmwEngineFactory;
import com.niudada.carfactory.BmwCarFactory;
import com.niudada.product.Car;
import com.niudada.tirefactory.BmwTireFactory;

public class Main {
    public static void main(String[] args) {
        // 创建宝马整车及零部件工厂
        BmwCarFactory bmwCarFactory = new BmwCarFactory();
        BmwEngineFactory bmwEngineFactory = new BmwEngineFactory();
        BmwTireFactory bmwTireFactory = new BmwTireFactory();
        // 创建并装配汽车
        Car bmwCar = bmwCarFactory.createCar(bmwEngineFactory, bmwTireFactory);
        bmwCar.assemble();
    }
}