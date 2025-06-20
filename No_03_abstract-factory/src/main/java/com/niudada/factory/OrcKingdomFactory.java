package com.niudada.factory;

import com.niudada.api.Army;
import com.niudada.api.Castle;
import com.niudada.api.King;
import com.niudada.api.KingdomFactory;
import com.niudada.domain.orc.OrcArmy;
import com.niudada.domain.orc.OrcCastle;
import com.niudada.domain.orc.OrcKing;

public class OrcKingdomFactory implements KingdomFactory {
  @Override
  public King createKing() {
    return new OrcKing();
  }

  @Override
  public Castle createCastle() {
    return new OrcCastle();
  }

  @Override
  public Army createArmy() {
    return new OrcArmy();
  }
}
