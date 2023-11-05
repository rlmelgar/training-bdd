package com.rlm.training.bdd.infrastructure.rest.server.controller;

import java.util.stream.Stream;

import com.rlm.training.bdd.application.usecase.transmission.GetActiveTransmissionsUseCase;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.mapper.TransmissionDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/transmissions/")
@RequiredArgsConstructor
public class TransmissionController {

  private final GetActiveTransmissionsUseCase getActiveTransmissionsUseCase;

  private final TransmissionDtoMapper transmissionDtoMapper;

  @GetMapping("/")
  public ResponseEntity<Stream<TransmissionResponse>> getActive() {
    log.debug("[START]");
    return ResponseEntity.status(HttpStatus.OK)
        .body(getActiveTransmissionsUseCase.getActive()
            .map(transmissionDtoMapper::toResponse));
  }

}
