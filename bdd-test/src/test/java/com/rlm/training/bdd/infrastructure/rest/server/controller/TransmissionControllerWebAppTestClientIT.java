package com.rlm.training.bdd.infrastructure.rest.server.controller;

import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import com.rlm.training.bdd.application.usecase.transmission.GetActiveTransmissionsUseCase;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.infrastructure.rest.server.config.TestMockSimpleConfig;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

@WebMvcTest(TransmissionController.class)
@Import(TestMockSimpleConfig.class)
class TransmissionControllerWebAppTestClientIT {

  @Autowired
  GetActiveTransmissionsUseCase getActiveTransmissionsUseCase;

  WebTestClient webTestClient;

  @Autowired
  TransmissionController transmissionController;

  @BeforeEach
  public void setup() {
    webTestClient = MockMvcWebTestClient.bindToController(transmissionController).build();

    when(this.getActiveTransmissionsUseCase.getActive()).thenReturn(Stream.of(TransmissionBuilder.buildActive()));
  }

  @Test
  void whenGetTransmissionsThenReturnsTransmissionList() {
    //GIVEN

    //WHEN
    ResponseSpec responseSpec = webTestClient.get()
        .uri("/v1/transmissions/")
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    //THEN
    responseSpec.expectStatus().isOk()
        .expectBody().jsonPath("$.[*]").exists()
        .jsonPath("$.[*].id").isNotEmpty();

    responseSpec.expectBodyList(TransmissionResponse.class)
        .hasSize(1)
        .contains(TransmissionResponseBuilder.build());
  }

  @Test
  void whenThereAreNotTransmissionsThenReturnsEmptyList() {
    //GIVEN
    when(this.getActiveTransmissionsUseCase.getActive()).thenReturn(Stream.of());

    //WHEN
    ResponseSpec responseSpec = webTestClient.get()
        .uri("/v1/transmissions/")
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    //THEN
    responseSpec.expectStatus().isOk()
        .expectBody().jsonPath("$.[*]").isEmpty();

    responseSpec.expectBodyList(TransmissionResponse.class)
        .hasSize(0);
  }
}
