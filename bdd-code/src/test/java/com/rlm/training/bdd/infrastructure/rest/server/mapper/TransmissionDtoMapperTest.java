package com.rlm.training.bdd.infrastructure.rest.server.mapper;

import static org.mockito.Mockito.spy;

import org.mapstruct.factory.Mappers;

class TransmissionDtoMapperTest {

  private TransmissionDtoMapper transmissionDtoMapper =
      spy(Mappers.getMapper(TransmissionDtoMapper.class));
}
