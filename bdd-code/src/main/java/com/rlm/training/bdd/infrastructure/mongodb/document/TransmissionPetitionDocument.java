package com.rlm.training.bdd.infrastructure.mongodb.document;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class TransmissionPetitionDocument {

  private String id;

  private String petition;

  private Instant created;

  private Boolean approved;

  private String refusedReason;
}
