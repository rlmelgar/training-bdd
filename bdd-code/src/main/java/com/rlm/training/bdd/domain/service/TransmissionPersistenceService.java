package com.rlm.training.bdd.domain.service;

import java.util.stream.Stream;

import com.rlm.training.bdd.domain.exception.EntityNotFoundException;
import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.port.TransmissionPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransmissionPersistenceService {

  private final TransmissionPersistencePort transmissionPersistencePort;

  public Stream<Transmission> getActive() {
    log.debug("[START]");
    return transmissionPersistencePort.getAll()
        .filter(Transmission::getActive);
  }

  public Transmission getByIdOrException(String transmissionId) {
    log.debug("[START] transmissionId {}", transmissionId);
    Transmission transmission = transmissionPersistencePort.getById(transmissionId)
        .orElseThrow(() -> new EntityNotFoundException(transmissionId, "Transmission"));
    log.debug("[STOP] transmission {}", transmission);
    return transmission;
  }
}
