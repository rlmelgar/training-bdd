package com.rlm.training.bdd.infrastructure.rest.server.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransmissionResponse implements Serializable {

  private String id;

  private SpaceshipDto spaceship;
}
