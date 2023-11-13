package com.rlm.training.bdd.application.usecase.transmissionpetition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.domain.model.TransmissionPetition;
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
class GetTransmissionPetitionsUseCaseTest {

  @Mock
  private TransmissionPersistenceService transmissionPersistenceService;

  @InjectMocks
  private GetTransmissionPetitionsUseCase getTransmissionPetitionsUseCase;

  @Test
  void whenGetTransmissionPetitionsThenReturnsTransmissionPetitionsStream() {
    // GIVEN
    when(this.transmissionPersistenceService.getByIdOrException(anyString())).thenReturn(TransmissionBuilder.buildActive());

    // WHEN
    Stream<TransmissionPetition> transmissionPetitionStream =
        getTransmissionPetitionsUseCase.getByTransmissionId(ObjectId.get().toString());

    // THEN
    assertThat(transmissionPetitionStream).containsExactlyElementsOf(TransmissionBuilder.buildActive().getPetitions());

    final InOrder inOrder = Mockito.inOrder(transmissionPersistenceService);
    inOrder.verify(this.transmissionPersistenceService, Mockito.times(1)).getByIdOrException(anyString());

  }
}