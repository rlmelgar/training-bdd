package com.rlm.training.bdd.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {

  private String entityId;

  private String entityName;
}
