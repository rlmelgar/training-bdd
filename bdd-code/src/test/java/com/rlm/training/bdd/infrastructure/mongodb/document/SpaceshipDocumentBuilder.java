package com.rlm.training.bdd.infrastructure.mongodb.document;

import com.rlm.training.bdd.domain.model.SpaceshipBuilder;
import lombok.Data;

public class SpaceshipDocumentBuilder {

  SpaceshipDocumentBuilder() {
  }

  public static SpaceshipDocument build() {
    return SpaceshipDocument.builder()
        .cargo(SpaceshipBuilder.CARGO)
        .crew(SpaceshipBuilder.CREW)
        .name(SpaceshipBuilder.NAME)
        .navPoint(SpaceshipBuilder.NAV_POINT)
        .type(SpaceshipBuilder.TYPE)
        .build();
  }
}
