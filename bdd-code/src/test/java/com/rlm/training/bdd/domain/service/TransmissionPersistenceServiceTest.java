package com.rlm.training.bdd.domain.service;

import static org.junit.jupiter.api.Named.named;
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
import org.mockito.Captor;
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

  @Captor
  private ArgumentCaptor<String> transmissionIdCaptor;

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
    EntityNotFoundException entityNotFoundException = Assertions.assertThrows(EntityNotFoundException.class,
        () -> transmissionPersistenceService.getByIdOrException(TransmissionBuilder.ID));

    //THEN
    Assertions.assertEquals(TransmissionBuilder.ID, entityNotFoundException.getEntityId());
    Assertions.assertEquals("Transmission", entityNotFoundException.getEntityName());
  }

  @Test
  @DisplayName("when found transmission then returns it.")
  void whenFoundTransmissionThenReturnsIt() {
    //GIVEN
    Mockito.when(this.transmissionPersistencePort.getById(anyString()))
        .thenReturn(Optional.of(TransmissionBuilder.buildFull()));

    //WHEN
    Transmission result = this.transmissionPersistenceService.getByIdOrException(TransmissionBuilder.ID);

    //THEN
    verify(transmissionPersistencePort).getById(this.transmissionIdCaptor.capture());
    Assertions.assertEquals(TransmissionBuilder.ID, this.transmissionIdCaptor.getValue());
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
}