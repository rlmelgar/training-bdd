package com.rlm.training.bdd.infrastructure.rest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Named.named;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import com.rlm.training.bdd.application.usecase.transmissionpetition.GetTransmissionPetitionsUseCase;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.domain.model.TransmissionPetition;
import com.rlm.training.bdd.domain.model.TransmissionPetitionBuilder;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionPetitionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionPetitionResponseBuilder;
import com.rlm.training.bdd.infrastructure.rest.server.mapper.TransmissionPetitionResponseMapper;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class TransmissionPetitionControllerTest {

  @Mock
  GetTransmissionPetitionsUseCase getTransmissionPetitionsUseCase;

  TransmissionPetitionResponseMapper transmissionPetitionResponseMapper = spy(Mappers.getMapper(TransmissionPetitionResponseMapper.class));

  @InjectMocks
  TransmissionPetitionController transmissionPetitionController;

  private static Stream<Arguments> stringIdValidCases() {
    return Stream.of(
        Arguments.of(named("When receive valid transmission Id but there is not any petition.", TransmissionBuilder.ID), List.of(),
            List.of()),
        Arguments.of(named("When receive valid transmission Id and there is a petition.", TransmissionBuilder.ID),
            List.of(TransmissionPetitionResponseBuilder.buildAccepted()), List.of(TransmissionPetitionBuilder.buildAccepted())),
        Arguments.of(named("When receive valid transmission Id and there are many petitions.", TransmissionBuilder.ID),
            List.of(TransmissionPetitionResponseBuilder.buildAccepted(), TransmissionPetitionResponseBuilder.buildRefused(),
                TransmissionPetitionResponseBuilder.buildPending()),
            List.of(TransmissionPetitionBuilder.buildAccepted(), TransmissionPetitionBuilder.buildRefused(),
                TransmissionPetitionBuilder.buildPending()))
    );
  }

  private static Stream<Arguments> stringIdInvalidCases() {
    return Stream.of(
        Arguments.of(named("When receive invalid transmission Id is null.", null)),
        Arguments.of(named("When receive invalid transmission Id is empty.", "")),
        Arguments.of(named("When receive invalid transmission Id is blank.", "    "))
    );
  }

  @ParameterizedTest(name = "Case: {0}")
  @MethodSource("stringIdValidCases")
  @MockitoSettings(strictness = Strictness.LENIENT)
  void whenReceiveAnTransmissionIdThenReturnsStreamOfPetitions(String transmissionId,
      List<TransmissionPetitionResponse> transmissionPetitionResponseExpected,
      List<TransmissionPetition> transmissionPetitionReturnedFromUseCase) {
    //GIVEN
    when(getTransmissionPetitionsUseCase.getByTransmissionId(anyString())).thenReturn(transmissionPetitionReturnedFromUseCase.stream());

    //WHEN
    ResponseEntity<Stream<TransmissionPetitionResponse>> responseEntity = transmissionPetitionController.getPetitions(transmissionId);

    //THEN
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .hasSize(transmissionPetitionResponseExpected.size())
        .containsExactlyElementsOf(transmissionPetitionResponseExpected);
  }

  @ParameterizedTest(name = "Case: {0}")
  @MethodSource("stringIdInvalidCases")
  @MockitoSettings(strictness = Strictness.LENIENT)
  void whenReceiveAnInvalidTransmissionIdThenThrowsIllegalArgumentException(String transmissionId) {
    //GIVEN

    //WHEN
    ThrowingCallable throwingCallableSupplier = () -> transmissionPetitionController.getPetitions(transmissionId);

    //THEN
    assertThatThrownBy(throwingCallableSupplier)
        .isInstanceOf(IllegalArgumentException.class)
        .message().contains(String.valueOf(transmissionId));
  }

}