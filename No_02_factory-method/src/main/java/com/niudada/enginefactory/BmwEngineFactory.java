package com.niudada.enginefactory;

import com.niudada.concrete.bmw.BmwEngine;
import com.niudada.factory.EngineFactory;
import com.niudada.product.Engine;

public class BmwEngineFactory implements EngineFactory {
    @Override
    public Engine createEngine() {
        return new BmwEngine();
    }
}
