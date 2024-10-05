package com.rlm.training.bdd.domain.model;

import java.util.List;

import org.bson.types.ObjectId;

public class TransmissionBuilder {

  public static final String ID = new ObjectId().toString();

  public static final String ID2 = new ObjectId().toString();

  public static final String CLEARANCE_CODE = "The code";

  public static final String CLEARANCE_CODE2 = "The code2";

  TransmissionBuilder() {
  }

  public static Transmission buildInactive() {
    return Transmission.builder()
        .id(ID)
        .spaceship(SpaceshipBuilder.build())
        .petitions(List.of(TransmissionPetitionBuilder.buildAccepted()))
        .clearanceCode(CLEARANCE_CODE)
        .active(false)
        .build();
  }

  public static Transmission buildInactive2() {
    return Transmission.builder()
        .id(ID2)
        .spaceship(SpaceshipBuilder.build2())
        .petitions(List.of(TransmissionPetitionBuilder.buildRefused()))
        .clearanceCode(CLEARANCE_CODE2)
        .active(false)
        .build();
  }

  public static Transmission buildNew() {
    return Transmission.builder()
        .spaceship(SpaceshipBuilder.build())
        .petitions(List.of(TransmissionPetitionBuilder.buildPending()))
        .clearanceCode(CLEARANCE_CODE)
        .build();
  }

  public static Transmission buildActive() {
    return Transmission.builder()
        .id(ID)
        .spaceship(SpaceshipBuilder.build())
        .petitions(List.of(TransmissionPetitionBuilder.buildPending()))
        .clearanceCode(CLEARANCE_CODE)
        .active(Boolean.TRUE)
        .build();
  }

  public static Transmission buildActive2() {
    return Transmission.builder()
        .id(ID2)
        .spaceship(SpaceshipBuilder.build2())
        .petitions(List.of(TransmissionPetitionBuilder.buildPending2()))
        .clearanceCode(CLEARANCE_CODE2)
        .active(Boolean.TRUE)
        .build();
  }

  public static Transmission buildFull() {
    return Transmission.builder()
        .id(ID)
        .spaceship(SpaceshipBuilder.build())
        .petitions(List.of(TransmissionPetitionBuilder.buildPending(), TransmissionPetitionBuilder.buildAccepted(),
            TransmissionPetitionBuilder.buildRefused()))
        .clearanceCode(CLEARANCE_CODE)
        .active(Boolean.TRUE)
        .build();
  }
}
