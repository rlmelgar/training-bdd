package com.rlm.training.bdd.infrastructure.rest.server.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class TransmissionPetitionAcceptanceRequest {

  private Boolean approved;

  private String refusedReason;
}
