package com.rlm.training.bdd.infrastructure.mongodb.mapper;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.model.TransmissionPetition;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocument;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionPetitionDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransmissionPetitionDocumentMapper {

  TransmissionPetition toModel(TransmissionPetitionDocument transmissionPetitionDocument);

  TransmissionPetitionDocument toDocument(TransmissionPetition transmissionPetition);

}
