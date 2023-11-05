package com.rlm.training.bdd.infrastructure.rest.server.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class SpaceshipDto {

  private String name;

  private String type;

  private String navPoint;

  private String cargo;

  private Integer crew;
}
