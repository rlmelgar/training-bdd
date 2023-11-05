package com.rlm.training.bdd.application.usecase.transmission;

import java.util.stream.Stream;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.service.TransmissionPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetActiveTransmissionsUseCase {

  private final TransmissionPersistenceService transmissionPersistenceService;

  public Stream<Transmission> getActive() {
    log.debug("[START]");
    return transmissionPersistenceService.getActive()
        .map(transmission -> {
          log.debug("transmission: {}", transmission);
          return transmission;
        });
  }

}
