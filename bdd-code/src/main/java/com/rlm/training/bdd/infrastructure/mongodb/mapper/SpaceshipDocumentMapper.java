package com.rlm.training.bdd.infrastructure.mongodb.mapper;

import com.rlm.training.bdd.domain.model.Spaceship;
import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.infrastructure.mongodb.document.SpaceshipDocument;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpaceshipDocumentMapper {

  Spaceship toModel(SpaceshipDocument spaceshipDocument);

  SpaceshipDocument toDocument(Spaceship spaceship);

}
