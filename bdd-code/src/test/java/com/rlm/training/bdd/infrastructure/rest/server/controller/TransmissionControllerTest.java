package com.rlm.training.bdd.infrastructure.rest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import com.rlm.training.bdd.application.usecase.transmission.GetActiveTransmissionsUseCase;
import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponseBuilder;
import com.rlm.training.bdd.infrastructure.rest.server.mapper.TransmissionDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class TransmissionControllerTest {

  private TransmissionDtoMapper transmissionDtoMapper = spy(Mappers.getMapper(TransmissionDtoMapper.class));

  @Mock
  private GetActiveTransmissionsUseCase getActiveTransmissionsUseCase;

  @InjectMocks
  private TransmissionController transmissionController;

  @Test
  void whenGetActiveTransmissionsThenReturnsAStreamWithActiveTransmissions() {
    // GIVEN
    when(this.getActiveTransmissionsUseCase.getActive())
        .thenReturn(Stream.of(TransmissionBuilder.buildActive()));

    // WHEN
    ResponseEntity<Stream<TransmissionResponse>> allTransmissions =
        transmissionController.getActive();

    // THEN
    assertThat(allTransmissions.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(allTransmissions.getBody())
        .hasSize(1)
        .contains(TransmissionResponseBuilder.build());

    final InOrder inOrder =
        Mockito.inOrder(getActiveTransmissionsUseCase, transmissionDtoMapper);
    inOrder.verify(this.getActiveTransmissionsUseCase, Mockito.times(1)).getActive();
    inOrder.verify(this.transmissionDtoMapper, Mockito.times(1)).toResponse(any(Transmission.class));
  }

}
