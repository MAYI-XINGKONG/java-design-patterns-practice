package com.niudada;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShieldDecorator implements Immortal {

    private final Immortal decoratedImmortal;

    public ShieldDecorator(Immortal immortal) {
        this.decoratedImmortal = immortal;
    }

    @Override
    public void attack() {
        decoratedImmortal.attack();
    }

    @Override
    public int getPower() {
        return decoratedImmortal.getPower() + 20;
    }

    @Override
    public void retreat() {
        log.info("玄武盾牌展开，形成一道防御屏障！");
        decoratedImmortal.retreat();
    }
}
