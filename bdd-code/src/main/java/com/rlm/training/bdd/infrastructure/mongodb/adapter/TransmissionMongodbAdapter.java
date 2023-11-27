package com.rlm.training.bdd.infrastructure.mongodb.adapter;

import java.util.Optional;
import java.util.stream.Stream;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.port.TransmissionPersistencePort;
import com.rlm.training.bdd.infrastructure.mongodb.mapper.TransmissionDocumentMapper;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransmissionMongodbAdapter implements TransmissionPersistencePort {

  private final TransmissionRepository transmissionRepository;

  private final TransmissionDocumentMapper personDocumentMapper;

  @Override
  public Stream<Transmission> getAll() {
    log.debug("[START]");
    return transmissionRepository.findAll().stream().map(personDocumentMapper::toModel);
  }

  @Override
  public Optional<Transmission> getById(String idTransmission) {
    log.debug("[START] idTransmission {}", idTransmission);
    Optional<Transmission> optionalTransmission = transmissionRepository.findById(idTransmission)
        .map(personDocumentMapper::toModel);
    log.debug("[STOP] idTransmission {}, found: {}", idTransmission, optionalTransmission.isPresent());
    return optionalTransmission;
  }

  @Override
  public Transmission insert(Transmission transmission) {
    log.debug("[START] transmission {}", transmission);
    Transmission transmissionCreated = personDocumentMapper.toModel(
        transmissionRepository.insert(
            personDocumentMapper.toDocument(transmission)));
    log.debug("[STOP] transmission Created {}", transmissionCreated);
    return transmissionCreated;
  }
}
