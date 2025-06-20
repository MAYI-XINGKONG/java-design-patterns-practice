package com.niudada.domain.orc;

import com.niudada.api.Army;

public class OrcArmy implements Army {
  private static final String DESCRIPTION = "这是兽人族的军队!";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
