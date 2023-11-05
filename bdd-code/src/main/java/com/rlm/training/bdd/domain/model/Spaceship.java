package com.rlm.training.bdd.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class Spaceship {

  private String name;

  private String type;

  private String navPoint;

  private String cargo;

  private Integer crew;
}
