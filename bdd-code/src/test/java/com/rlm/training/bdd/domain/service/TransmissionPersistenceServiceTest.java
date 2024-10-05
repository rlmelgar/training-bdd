package com.rlm.training.bdd.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Named.named;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.rlm.training.bdd.domain.exception.EntityNotFoundException;
import com.rlm.training.bdd.domain.model.Transmission;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.domain.port.TransmissionPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransmissionPersistenceServiceTest {

  @Mock
  TransmissionPersistencePort transmissionPersistencePort;

  @InjectMocks
  TransmissionPersistenceService transmissionPersistenceService;

  private static Stream<Arguments> getActiveCases() {
    return Stream.of(
        Arguments.of(named("When there are no transmissions.", Stream.empty()), List.of()),
        Arguments.of(named("When all transmissions are inactive.",
                Stream.of(TransmissionBuilder.buildInactive(), TransmissionBuilder.buildInactive2())),
            List.of()),
        Arguments.of(named("When some transmissions are inactive.",
                Stream.of(TransmissionBuilder.buildInactive(), TransmissionBuilder.buildActive(), TransmissionBuilder.buildInactive2())),
            List.of(TransmissionBuilder.buildActive())),
        Arguments.of(named("When all transmissions are active.",
                Stream.of(TransmissionBuilder.buildActive(), TransmissionBuilder.buildActive2())),
            List.of(TransmissionBuilder.buildActive(), TransmissionBuilder.buildActive2()))
    );
  }

  @Test
  @DisplayName("When not found transmission then throws EntityNotFoundException.")
  void whenNotFoundTransmissionThenThrowsEntityNotFoundException() {
    //GIVEN
    when(transmissionPersistencePort.getById(anyString())).thenReturn(Optional.empty());

    //WHEN
    assertThatThrownBy(() -> transmissionPersistenceService.getByIdOrException(TransmissionBuilder.ID))
        .isInstanceOf(EntityNotFoundException.class)
        .extracting("entityId", "entityName")
        .containsExactly(TransmissionBuilder.ID, "Transmission");
  }

  @Test
  @DisplayName("when found transmission then returns it.")
  void whenFoundTransmissionThenReturnsIt() {
    //GIVEN
    ArgumentCaptor<String> transmissionIdCaptor = ArgumentCaptor.forClass(String.class);

    Mockito.when(this.transmissionPersistencePort.getById(transmissionIdCaptor.capture()))
        .thenReturn(Optional.of(TransmissionBuilder.buildFull()));

    //WHEN
    Transmission result = this.transmissionPersistenceService.getByIdOrException(TransmissionBuilder.ID);

    //THEN
    verify(transmissionPersistencePort).getById(anyString());
    Assertions.assertEquals(TransmissionBuilder.ID, transmissionIdCaptor.getValue());
    Assertions.assertEquals(TransmissionBuilder.buildFull(), result);
  }

  @ParameterizedTest(name = "Case: {0}")
  @MethodSource("getActiveCases")
  @DisplayName("when get active then returns only active transmissions.")
  void whenGetActiveThenReturnsOnlyActiveTransmissions(Stream<Transmission> transmissionStreamDb,
      List<Transmission> transmissionsExpected) {
    //GIVEN
    Mockito.when(this.transmissionPersistencePort.getAll()).thenReturn(transmissionStreamDb);

    //WHEN
    Stream<Transmission> transmissionStreamActive = transmissionPersistenceService.getActive();

    // THEN
    Assertions.assertArrayEquals(transmissionsExpected.toArray(), transmissionStreamActive.toArray());
  }

  @Test
  void whenCreateTransmissionThenReturnsIt() {
    // GIVEN
    when(this.transmissionPersistencePort.insert(any(Transmission.class)))
        .thenAnswer(invocationOnMock -> ((Transmission) invocationOnMock.getArgument(0)).setId(TransmissionBuilder.ID));

    // WHEN
    Transmission transmission = transmissionPersistenceService.create(TransmissionBuilder.buildNew());

    // THEN
    assertThat(transmission).usingRecursiveComparison()
        .ignoringFields("petitions.id", "petitions.created")
        .isEqualTo(TransmissionBuilder.buildActive());

    final InOrder inOrder = Mockito.inOrder(transmissionPersistencePort);
    inOrder.verify(this.transmissionPersistencePort, Mockito.times(1)).insert(any(Transmission.class));
  }
}