package com.niudada.enginefactory;

import com.niudada.concrete.tesla.TeslaEngine;
import com.niudada.factory.EngineFactory;
import com.niudada.product.Engine;

public class TeslaEngineFactory implements EngineFactory {
    @Override
    public Engine createEngine() {
        return new TeslaEngine();
    }
}
