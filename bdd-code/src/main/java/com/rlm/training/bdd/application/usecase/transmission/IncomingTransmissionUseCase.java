package com.rlm.training.bdd.application.usecase.transmission;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.service.TransmissionPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncomingTransmissionUseCase {

  private final TransmissionPersistenceService transmissionPersistenceService;

  public Transmission receive(Transmission transmission) {
    log.debug("[START] transmission: {}", transmission);
    Transmission transmissionSaved = transmissionPersistenceService.create(transmission);
    log.debug("[STOP] transmissionSaved: {}", transmissionSaved);
    return transmissionSaved;
  }

}
