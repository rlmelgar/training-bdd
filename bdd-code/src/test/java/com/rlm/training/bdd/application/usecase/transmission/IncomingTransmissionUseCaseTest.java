package com.rlm.training.bdd.application.usecase.transmission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.domain.service.TransmissionPersistenceService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IncomingTransmissionUseCaseTest {

  @Mock
  private TransmissionPersistenceService transmissionPersistenceService;

  @InjectMocks
  private IncomingTransmissionUseCase incomingTransmissionUseCase;

  @Test
  void whenReceiveTransmissionThenReturnsIdTransmission() {
    // GIVEN
    String idTransmissionExpected = ObjectId.get().toString();
    when(this.transmissionPersistenceService.create(any(Transmission.class)))
        .thenAnswer(invocationOnMock -> ((Transmission) invocationOnMock.getArgument(0)).setId(idTransmissionExpected));

    // WHEN
    Transmission idTransmission = incomingTransmissionUseCase.receive(TransmissionBuilder.buildActive());

    // THEN
    assertThat(idTransmission).isEqualTo(TransmissionBuilder.buildActive().setId(idTransmissionExpected));

    final InOrder inOrder = Mockito.inOrder(transmissionPersistenceService);
    inOrder.verify(this.transmissionPersistenceService, Mockito.times(1)).create((any(Transmission.class)));
  }
}