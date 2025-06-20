package com.niudada.domain.elf;

import com.niudada.api.Castle;

public class ElfCastle implements Castle {
  private static final String DESCRIPTION = "这是精灵国🧚的城堡🏰!";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
