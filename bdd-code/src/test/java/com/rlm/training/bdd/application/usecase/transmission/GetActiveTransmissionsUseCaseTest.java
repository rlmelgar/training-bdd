package com.rlm.training.bdd.application.usecase.transmission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.domain.service.TransmissionPersistenceService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetActiveTransmissionsUseCaseTest {

  @Mock
  private TransmissionPersistenceService transmissionPersistenceService;

  @InjectMocks
  private GetActiveTransmissionsUseCase getActiveTransmissionsUseCase;

  private static Stream<Arguments> approveUseCases() {
    return Stream.of(
        Arguments.of(named("When transmission is approved.", true), null),
        Arguments.of(named("When transmission is rejected.", false), "Not valid code")
    );
  }

  @ParameterizedTest(name = "Case: {0}")
  @MethodSource("approveUseCases")
  void whenApproveTransmissionOkThenReturnsNothing(boolean approved, String refusedReason) {
    // GIVEN
    when(this.transmissionPersistenceService.getActive())
        .thenReturn(Stream.of(TransmissionBuilder.buildActive()));

    // WHEN
    Stream<Transmission> transmissionStream = getActiveTransmissionsUseCase.getActive();

    // THEN
    assertThat(transmissionStream.toList()).contains(TransmissionBuilder.buildActive());

    final InOrder inOrder = Mockito.inOrder(transmissionPersistenceService);
    inOrder.verify(this.transmissionPersistenceService, Mockito.times(1)).getActive();
  }

}