package com.rlm.training.bdd.application.usecase.transmissionpetition;

import java.util.stream.Stream;

import com.rlm.training.bdd.domain.model.TransmissionPetition;
import com.rlm.training.bdd.domain.service.TransmissionPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetTransmissionPetitionsUseCase {

  private final TransmissionPersistenceService transmissionPersistenceService;

  public Stream<TransmissionPetition> getByTransmissionId(String transmissionId) {
    log.debug("[START] transmissionId {}", transmissionId);
    return transmissionPersistenceService.getByIdOrException(transmissionId)
        .getPetitions().stream().map(petition -> {
          log.debug("transmissionId: {}, petition found: {}", transmissionId, petition);
          return petition;
        });
  }
}
