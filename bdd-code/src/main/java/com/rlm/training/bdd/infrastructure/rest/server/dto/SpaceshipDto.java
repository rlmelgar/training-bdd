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
public class SpaceshipDto implements Serializable {

  private String name;

  private String type;

  private String navPoint;

  private String cargo;

  private Integer crew;
}
