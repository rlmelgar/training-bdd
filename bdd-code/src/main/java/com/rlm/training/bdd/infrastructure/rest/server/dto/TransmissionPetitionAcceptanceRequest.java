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
public class TransmissionPetitionAcceptanceRequest implements Serializable {

  private Boolean approved;

  private String refusedReason;
}
