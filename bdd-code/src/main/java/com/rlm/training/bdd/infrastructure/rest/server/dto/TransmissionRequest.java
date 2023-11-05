package com.rlm.training.bdd.infrastructure.rest.server.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class TransmissionRequest {

  private SpaceshipDto spaceship;
  
  private String petition;

  private String clearanceCode;
}
