package com.rlm.training.bdd.infrastructure.rest.server.dto;

import com.rlm.training.bdd.domain.model.Spaceship;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class TransmissionResponse {

  private String id;

  private SpaceshipDto spaceship;
}
