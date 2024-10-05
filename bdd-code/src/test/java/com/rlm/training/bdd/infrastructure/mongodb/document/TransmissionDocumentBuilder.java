package com.rlm.training.bdd.infrastructure.mongodb.document;

import java.util.List;

import com.rlm.training.bdd.domain.model.TransmissionBuilder;

public class TransmissionDocumentBuilder {

  TransmissionDocumentBuilder() {
  }

  public static TransmissionDocument build() {
    return TransmissionDocument.builder()
        .id(TransmissionBuilder.ID)
        .spaceship(SpaceshipDocumentBuilder.build())
        .petitions(List.of(TransmissionPetitionDocumentBuilder.buildPending(), TransmissionPetitionDocumentBuilder.buildAccepted(),
            TransmissionPetitionDocumentBuilder.buildRefused()))
        .active(Boolean.TRUE)
        .clearanceCode(TransmissionBuilder.CLEARANCE_CODE)
        .build();
  }
}
