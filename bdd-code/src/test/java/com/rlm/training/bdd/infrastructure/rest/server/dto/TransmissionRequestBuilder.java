package com.rlm.training.bdd.infrastructure.rest.server.dto;

import java.time.Instant;
import java.util.List;

import com.rlm.training.bdd.domain.model.TransmissionPetitionBuilder;

public class TransmissionRequestBuilder {

  public static final String CARGO = "Cargo wearing";

  public static final String REQUEST = "Requesting";

  public static final Integer CREW = 5;

  public static final String NAME = "Ship name";

  public static final String NAV_POINT = "Navigation info";

  public static final String TYPE = "Type";

  public static final String CLEARANCE_CODE = "The code";

  public static final Instant CLEARANCE_CREATED = Instant.parse("2023-09-17T17:49:00.676055200Z");

  TransmissionRequestBuilder() {
  }

  public static TransmissionRequest build() {
    return TransmissionRequest.builder()
        .spaceship(SpaceshipDtoBuilder.build())
        .petitions(List.of(TransmissionPetitionBuilder.PETITION))
        .clearanceCode(CLEARANCE_CODE)
        .build();
  }
}
