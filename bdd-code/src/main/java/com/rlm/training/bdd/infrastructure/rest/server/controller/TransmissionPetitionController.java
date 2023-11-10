package com.rlm.training.bdd.infrastructure.rest.server.controller;

import java.util.stream.Stream;

import com.rlm.training.bdd.application.usecase.transmission.GetTransmissionPetitionsUseCase;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionPetitionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.mapper.TransmissionPetitionResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/transmissions/")
@RequiredArgsConstructor
public class TransmissionPetitionController {

  private final GetTransmissionPetitionsUseCase getTransmissionPetitionsUseCase;

  private final TransmissionPetitionResponseMapper transmissionPetitionResponseMapper;

  private String validate(String transmissionId) {
    if (StringUtils.isBlank(transmissionId)) {
      throw new IllegalArgumentException("Transmission id is " + transmissionId);
    }

    return transmissionId;
  }

  public Stream<TransmissionPetitionResponse> getTransmissionPetitions(String transmissionId) {

    return getTransmissionPetitionsUseCase.getByTransmissionId(validate(transmissionId))
        .map(transmissionPetitionResponseMapper::toResponse);
  }
}
