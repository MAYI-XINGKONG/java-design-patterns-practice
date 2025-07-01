package com.niudada;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SwordDecorator implements Immortal {
    private final Immortal decoratedImmortal;

    public SwordDecorator(Immortal immortal) {
        this.decoratedImmortal = immortal;
    }

    @Override
    public void attack() {
        decoratedImmortal.attack();
        log.info("青龙宝剑出鞘，发出龙吟般的剑鸣！");
    }

    @Override
    public int getPower() {
        return decoratedImmortal.getPower() + 30;
    }

    @Override
    public void retreat() {
        decoratedImmortal.retreat();
    }
}
