package com.rlm.training.bdd.infrastructure.rest.server.mapper;

import com.rlm.training.bdd.domain.model.TransmissionPetition;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionPetitionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransmissionPetitionResponseMapper {

  TransmissionPetitionResponse toResponse(TransmissionPetition transmissionPetition);
}
