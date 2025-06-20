package com.niudada;

import com.niudada.enums.KingdomType;
import com.niudada.factory.FactoryMaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
  public static void main(String[] args) {
    var elf = FactoryMaker.makeFactory(KingdomType.ELF);
    log.info(elf.createCastle().getDescription());
    log.info(elf.createKing().getDescription());
    log.info(elf.createArmy().getDescription());
    var orc = FactoryMaker.makeFactory(KingdomType.ORC);
    log.info(orc.createCastle().getDescription());
    log.info(orc.createKing().getDescription());
    log.info(orc.createArmy().getDescription());
  }
}
