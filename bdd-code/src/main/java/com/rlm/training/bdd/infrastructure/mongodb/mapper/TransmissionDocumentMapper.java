package com.rlm.training.bdd.infrastructure.mongodb.mapper;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses =  {SpaceshipDocumentMapper.class, TransmissionPetitionDocumentMapper.class})
public interface TransmissionDocumentMapper {

  Transmission toModel(TransmissionDocument transmissionDocument);

  TransmissionDocument toDocument(Transmission transmission);

}
