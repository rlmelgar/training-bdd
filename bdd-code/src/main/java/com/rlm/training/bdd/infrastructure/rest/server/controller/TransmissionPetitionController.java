package com.rlm.training.bdd.infrastructure.rest.server.controller;

import java.util.stream.Stream;

import com.rlm.training.bdd.application.usecase.transmissionpetition.GetTransmissionPetitionsUseCase;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionPetitionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.mapper.TransmissionPetitionResponseMapper;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/transmissions/{transmissionId}/petitions")
@RequiredArgsConstructor
public class TransmissionPetitionController {

  private final GetTransmissionPetitionsUseCase getTransmissionPetitionsUseCase;

  private final TransmissionPetitionResponseMapper transmissionPetitionResponseMapper;

  private static String validate(String transmissionId) {
    if (StringUtils.isBlank(transmissionId)) {
      throw new IllegalArgumentException("Transmission id is " + transmissionId);
    }

    return transmissionId;
  }

  @GetMapping("")
  public ResponseEntity<Stream<TransmissionPetitionResponse>> getPetitions(
      @Pattern(regexp = "^[a-f\\d]{24}$")
      @Parameter(description = "Transmission Id assigned to spaceship", required = true)
      @PathVariable("transmissionId") String transmissionId) {
    log.debug("[START] transmissionId {}", transmissionId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(getTransmissionPetitionsUseCase.getByTransmissionId(validate(transmissionId).trim())
            .map(transmissionPetitionResponseMapper::toResponse));
  }
}
