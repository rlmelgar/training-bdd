package com.rlm.training.bdd.domain.model;

public class SpaceshipBuilder {

  public static final String CARGO = "Cargo wearing";

  public static final String CARGO2 = "Cargo wearing2";

  public static final String REQUEST = "Requesting";

  public static final String REQUEST2 = "Requesting2";

  public static final Integer CREW = 5;

  public static final Integer CREW2 = 1;

  public static final String NAME = "Ship name";

  public static final String NAME2 = "Ship name2";

  public static final String NAV_POINT = "Navigation info";

  public static final String NAV_POINT2 = "Navigation info2";

  public static final String TYPE = "Type";

  public static final String TYPE2 = "Type2";

  SpaceshipBuilder() {
  }

  public static Spaceship build() {
    return Spaceship.builder()
        .cargo(CARGO)
        .crew(CREW)
        .name(NAME)
        .navPoint(NAV_POINT)
        .type(TYPE)
        .build();
  }

  public static Spaceship build2() {
    return Spaceship.builder()
        .cargo(CARGO2)
        .crew(CREW2)
        .name(NAME2)
        .navPoint(NAV_POINT2)
        .type(TYPE2)
        .build();
  }
}
