package com.rlm.training.bdd.infrastructure.rest.server.dto;

import java.io.Serializable;
import java.util.List;

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
public class TransmissionRequest implements Serializable {

  private SpaceshipDto spaceship;

  private List<String> petitions;

  private String clearanceCode;
}
