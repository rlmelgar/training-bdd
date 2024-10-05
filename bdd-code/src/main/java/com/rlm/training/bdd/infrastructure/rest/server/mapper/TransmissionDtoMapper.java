package com.rlm.training.bdd.infrastructure.rest.server.mapper;

import java.util.List;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.model.TransmissionPetition;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionRequest;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransmissionDtoMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", ignore = true)
  @Mapping(target = "petitions", source = "petitions")
  Transmission toModel(TransmissionRequest transmissionRequest);

  TransmissionResponse toResponse(Transmission transmission);

  default List<TransmissionPetition> toTransmissionPetitions(List<String> petitions) {
    return petitions.stream().map(petition -> TransmissionPetition.builder().petition(petition).build()).toList();
  }

}
