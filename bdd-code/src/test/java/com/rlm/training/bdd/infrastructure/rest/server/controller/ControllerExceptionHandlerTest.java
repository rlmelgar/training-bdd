package com.rlm.training.bdd.infrastructure.rest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Map;

import com.rlm.training.bdd.domain.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.servlet.HandlerMapping;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

  @Mock
  HttpServletRequest httpServletRequest;

  @InjectMocks
  ControllerExceptionHandler controllerExceptionHandler;

  @Test
  void whenHandleEntityNotFoundExceptionThenReturnsNotFoundError() {
    // GIVEN
    String entity = "Transmission";
    String entityId = ObjectId.get().toString();
    String messageError = "Entity '" + entity + "' with id '" + entityId + "' was not found";
    String transmissionId = ObjectId.get().toString();
    when(this.httpServletRequest.getAttribute(eq(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE))).thenReturn(
        Map.of("transmissionId", transmissionId));

    // WHEN
    ProblemDetail problemDetail =
        controllerExceptionHandler.handleEntityNotFoundException(httpServletRequest, new EntityNotFoundException(entityId, entity));

    // THEN
    assertThat(problemDetail.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    assertThat(problemDetail.getDetail()).isEqualTo(messageError);
    assertThat(problemDetail.getProperties()).containsOnlyKeys("id", "parameters", "timestamp", entity);
  }

  @Test
  void whenHandleUnexpectedExceptionThenReturnsInternalError() {
    // GIVEN
    String messageError = "Unexpected error";
    when(this.httpServletRequest.getAttribute(eq(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE))).thenReturn(null);

    // WHEN
    ProblemDetail problemDetail = controllerExceptionHandler.handleException(httpServletRequest, new RuntimeException(messageError));

    // THEN
    assertThat(problemDetail.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    assertThat(problemDetail.getDetail()).isEqualTo(messageError);
    assertThat(problemDetail.getProperties()).containsKeys("id", "parameters", "timestamp");
  }

  @Test
  void whenExceptionRecoveringParametersThenReturnsProblemDetailWithoutParameters() {
    // GIVEN
    String messageError = "Unexpected error";
    when(this.httpServletRequest.getAttribute(eq(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE))).thenThrow(new RuntimeException());

    // WHEN
    ProblemDetail problemDetail = controllerExceptionHandler.handleException(httpServletRequest, new RuntimeException(messageError));

    // THEN
    assertThat(problemDetail.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    assertThat(problemDetail.getDetail()).isEqualTo(messageError);
    assertThat(problemDetail.getProperties()).containsKeys("id", "timestamp");
  }

}