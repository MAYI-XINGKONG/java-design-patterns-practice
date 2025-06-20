package com.niudada.domain.elf;

import com.niudada.api.King;

public class ElfKing implements King {

  private static final String DESCRIPTION = "这是精灵国🧚的国王!";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
