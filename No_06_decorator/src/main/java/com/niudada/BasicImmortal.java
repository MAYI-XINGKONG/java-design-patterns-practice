package com.niudada;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicImmortal implements Immortal {
    @Override
    public void attack() {
        log.info("修仙者施展[基础功法]攻击!");
    }

    @Override
    public int getPower() {
        return 50;
    }

    @Override
    public void retreat() {
        log.info("说时迟那时快! 修仙者施展[遁术]转眼便没了踪影...");
    }
}
