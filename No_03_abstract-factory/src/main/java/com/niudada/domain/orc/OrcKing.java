package com.niudada.domain.orc;

import com.niudada.api.King;

public class OrcKing implements King {

  private static final String DESCRIPTION = "这是兽人族的国王!";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
