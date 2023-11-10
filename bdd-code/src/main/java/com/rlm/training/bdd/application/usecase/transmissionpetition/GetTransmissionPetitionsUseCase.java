package com.rlm.training.bdd.application.usecase.transmissionpetition;

import java.util.stream.Stream;

import com.rlm.training.bdd.domain.model.TransmissionPetition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetTransmissionPetitionsUseCase {

  public Stream<TransmissionPetition> getByTransmissionId(String transmissionId) {
    return Stream.empty();
  }
}
