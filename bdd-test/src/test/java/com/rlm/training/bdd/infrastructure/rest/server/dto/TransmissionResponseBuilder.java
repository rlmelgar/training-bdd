package com.rlm.training.bdd.infrastructure.rest.server.dto;

import com.rlm.training.bdd.domain.model.TransmissionBuilder;

public class TransmissionResponseBuilder {

  TransmissionResponseBuilder() {
  }

  public static TransmissionResponse build() {
    return TransmissionResponse.builder()
        .id(TransmissionBuilder.ID)
        .spaceship(SpaceshipDtoBuilder.build())
        .build();
  }
}
