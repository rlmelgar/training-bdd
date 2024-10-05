package com.rlm.training.bdd.infrastructure.rest.server.controller;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

import com.rlm.training.bdd.domain.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ProblemDetail handleEntityNotFoundException(HttpServletRequest req, EntityNotFoundException ex) {
    log.warn("[START]", ex);
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
        "Entity '%s' with id '%s' was not found".formatted(ex.getEntityName(), ex.getEntityId()));
    setPathParameters(req, pd);
    pd.setProperty(ex.getEntityName(), ex.getEntityId());
    return pd;
  }

  @SuppressWarnings(value = "unchecked")
  private void setPathParameters(HttpServletRequest req, ProblemDetail pd) {
    log.debug("[START] HttpServletRequest {}, ProblemDetail {} ", req, pd);
    pd.setProperty("id", UUID.randomUUID());
    pd.setProperty("timestamp", Instant.now());
    pd.setType(URI.create(""));
    try {
      pd.setProperty("parameters", req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
    } catch (Exception ex) {
      log.error("[ERROR] Error trying to add path parameters:", ex);
    }
    log.debug("[STOP] ProblemDetail {} ", pd);
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleException(HttpServletRequest req, Exception ex) {
    log.error("[ERROR] Unexpected error.", ex);
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    setPathParameters(req, pd);
    return pd;
  }
}
