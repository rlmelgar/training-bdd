package com.rlm.training.bdd.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class Transmission {

  private String id;

  private Spaceship spaceship;

  private List<TransmissionPetition> petitions;

  private String clearanceCode;

  private Boolean active;
}
