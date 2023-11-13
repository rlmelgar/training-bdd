package com.rlm.training.bdd.infrastructure.rest.server.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class TransmissionRequest implements Serializable {

  private SpaceshipDto spaceship;

  private String petition;

  private String clearanceCode;
}
