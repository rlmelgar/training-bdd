package com.rlm.training.bdd.infrastructure.mongodb.document;

import com.rlm.training.bdd.domain.model.TransmissionPetitionBuilder;

public class TransmissionPetitionDocumentBuilder {

  TransmissionPetitionDocumentBuilder() {
  }

  public static TransmissionPetitionDocument buildAccepted() {
    return TransmissionPetitionDocument.builder()
        .id(TransmissionPetitionBuilder.ID)
        .petition(TransmissionPetitionBuilder.PETITION)
        .created(TransmissionPetitionBuilder.PETITION_CREATED)
        .approved(Boolean.TRUE)
        .refusedReason(null)
        .build();
  }

  public static TransmissionPetitionDocument buildRefused() {
    return TransmissionPetitionDocument.builder()
        .id(TransmissionPetitionBuilder.ID2)
        .petition(TransmissionPetitionBuilder.PETITION2)
        .created(TransmissionPetitionBuilder.PETITION_CREATED2)
        .approved(Boolean.FALSE)
        .refusedReason(TransmissionPetitionBuilder.REFUSED_REASON)
        .build();
  }

  public static TransmissionPetitionDocument buildPending() {
    return TransmissionPetitionDocument.builder()
        .id(TransmissionPetitionBuilder.ID3)
        .petition(TransmissionPetitionBuilder.PETITION)
        .created(TransmissionPetitionBuilder.PETITION_CREATED)
        .approved(null)
        .refusedReason(null)
        .build();
  }
}
