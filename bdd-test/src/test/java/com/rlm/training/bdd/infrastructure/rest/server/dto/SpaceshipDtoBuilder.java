package com.rlm.training.bdd.infrastructure.rest.server.dto;

import com.rlm.training.bdd.domain.model.SpaceshipBuilder;

public class SpaceshipDtoBuilder {

  SpaceshipDtoBuilder() {
  }

  public static SpaceshipDto build() {
    return SpaceshipDto.builder()
        .cargo(SpaceshipBuilder.CARGO)
        .crew(SpaceshipBuilder.CREW)
        .name(SpaceshipBuilder.NAME)
        .navPoint(SpaceshipBuilder.NAV_POINT)
        .type(SpaceshipBuilder.TYPE)
        .build();
  }
}
