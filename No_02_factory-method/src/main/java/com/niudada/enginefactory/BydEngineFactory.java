package com.niudada.enginefactory;

import com.niudada.concrete.byd.BydEngine;
import com.niudada.factory.EngineFactory;
import com.niudada.product.Engine;

public class BydEngineFactory implements EngineFactory {
    @Override
    public Engine createEngine() {
        return new BydEngine();
    }
}
