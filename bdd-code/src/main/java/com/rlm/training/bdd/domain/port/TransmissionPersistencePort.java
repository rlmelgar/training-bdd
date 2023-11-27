package com.rlm.training.bdd.domain.port;

import java.util.Optional;
import java.util.stream.Stream;

import com.rlm.training.bdd.domain.model.Transmission;

public interface TransmissionPersistencePort {

  Stream<Transmission> getAll();

  Optional<Transmission> getById(String id);

  Transmission insert(Transmission transmission);
}
