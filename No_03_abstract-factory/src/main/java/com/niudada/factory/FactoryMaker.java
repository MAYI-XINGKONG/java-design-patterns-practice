package com.niudada.factory;

import com.niudada.api.KingdomFactory;
import com.niudada.enums.KingdomType;

public class FactoryMaker {
  public static KingdomFactory makeFactory(KingdomType type) {
    return switch (type) {
      case ELF -> new ElfKingdomFactory();
      case ORC -> new OrcKingdomFactory();
      default -> throw new IllegalArgumentException("没有找到对应王国制造工厂.");
    };
  }
}
