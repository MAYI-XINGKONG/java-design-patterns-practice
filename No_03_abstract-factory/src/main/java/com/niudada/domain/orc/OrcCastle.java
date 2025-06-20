package com.niudada.domain.orc;

import com.niudada.api.Castle;

public class OrcCastle implements Castle {
  private static final String DESCRIPTION = "这是兽人族的城堡🏰!";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
