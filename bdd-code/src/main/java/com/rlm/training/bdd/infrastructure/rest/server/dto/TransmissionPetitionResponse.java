package com.rlm.training.bdd.infrastructure.rest.server.dto;

import java.io.Serializable;
import java.time.Instant;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class TransmissionPetitionResponse implements Serializable {

  private String id;

  private String petition;

  private Instant created;

  private Boolean approved;

  private String refusedReason;
}
