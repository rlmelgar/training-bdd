package com.rlm.training.bdd.infrastructure.rest.server.dto;

import com.rlm.training.bdd.domain.model.TransmissionPetitionBuilder;

public class TransmissionPetitionResponseBuilder {

  TransmissionPetitionResponseBuilder() {
  }

  public static TransmissionPetitionResponse buildAccepted() {
    return TransmissionPetitionResponse.builder()
        .id(TransmissionPetitionBuilder.ID)
        .petition(TransmissionPetitionBuilder.PETITION)
        .created(TransmissionPetitionBuilder.PETITION_CREATED)
        .approved(Boolean.TRUE)
        .refusedReason(null)
        .build();
  }

  public static TransmissionPetitionResponse buildRefused() {
    return TransmissionPetitionResponse.builder()
        .id(TransmissionPetitionBuilder.ID2)
        .petition(TransmissionPetitionBuilder.PETITION2)
        .created(TransmissionPetitionBuilder.PETITION_CREATED2)
        .approved(Boolean.FALSE)
        .refusedReason(TransmissionPetitionBuilder.REFUSED_REASON)
        .build();
  }

  public static TransmissionPetitionResponse buildPending() {
    return TransmissionPetitionResponse.builder()
        .id(TransmissionPetitionBuilder.ID)
        .petition(TransmissionPetitionBuilder.PETITION)
        .created(TransmissionPetitionBuilder.PETITION_CREATED)
        .approved(null)
        .refusedReason(null)
        .build();
  }
}
