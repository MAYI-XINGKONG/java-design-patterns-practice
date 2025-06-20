package com.niudada.domain.elf;

import com.niudada.api.Army;

public class ElfArmy implements Army {
  private static final String DESCRIPTION = "这是精灵国的军队!";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
