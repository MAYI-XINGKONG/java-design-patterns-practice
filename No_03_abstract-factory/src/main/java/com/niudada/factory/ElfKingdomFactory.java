package com.niudada.factory;

import com.niudada.api.Army;
import com.niudada.api.Castle;
import com.niudada.api.King;
import com.niudada.api.KingdomFactory;
import com.niudada.domain.elf.ElfArmy;
import com.niudada.domain.elf.ElfCastle;
import com.niudada.domain.elf.ElfKing;

public class ElfKingdomFactory implements KingdomFactory {
  @Override
  public King createKing() {
    return new ElfKing();
  }

  @Override
  public Castle createCastle() {
    return new ElfCastle();
  }

  @Override
  public Army createArmy() {
    return new ElfArmy();
  }
}
