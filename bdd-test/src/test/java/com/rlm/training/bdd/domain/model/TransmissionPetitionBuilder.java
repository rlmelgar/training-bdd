package com.rlm.training.bdd.domain.model;

import java.time.Instant;
import java.util.UUID;

public class TransmissionPetitionBuilder {

  public static final String ID = UUID.randomUUID().toString();

  public static final String ID2 = UUID.randomUUID().toString();

  public static final String PETITION = "Docking request";

  public static final String PETITION2 = "Refuel";

  public static final Instant PETITION_CREATED = Instant.parse("2023-09-17T17:49:00.676055200Z");

  public static final Instant PETITION_CREATED2 = Instant.parse("2023-09-17T17:49:00.676055200Z");

  public static final String REFUSED_REASON = "Code invalid";

  TransmissionPetitionBuilder() {
  }

  public static TransmissionPetition buildPending() {
    return TransmissionPetition.builder()
        .id(ID)
        .petition(PETITION)
        .created(PETITION_CREATED)
        .approved(null)
        .refusedReason(null)
        .build();
  }

  public static TransmissionPetition buildPending2() {
    return TransmissionPetition.builder()
        .id(ID2)
        .petition(PETITION2)
        .created(PETITION_CREATED2)
        .approved(null)
        .refusedReason(null)
        .build();
  }

  public static TransmissionPetition buildAccepted() {
    return TransmissionPetition.builder()
        .id(ID)
        .petition(PETITION)
        .created(PETITION_CREATED)
        .approved(Boolean.TRUE)
        .refusedReason(null)
        .build();
  }

  public static TransmissionPetition buildRefused() {
    return TransmissionPetition.builder()
        .id(ID2)
        .petition(PETITION2)
        .created(PETITION_CREATED2)
        .approved(Boolean.FALSE)
        .refusedReason(REFUSED_REASON)
        .build();
  }
}
