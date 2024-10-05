package com.rlm.training.bdd.aceptance;

import java.util.List;

import com.rlm.training.bdd.Application;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocumentBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import com.rlm.training.bdd.infrastructure.rest.server.controller.ControllerExceptionHandler;
import com.rlm.training.bdd.infrastructure.rest.server.controller.TransmissionPetitionController;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionPetitionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionPetitionResponseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles("test")
class TransmissionPetitionControllerIT {

  @Autowired
  TransmissionPetitionController transmissionPetitionController;

  WebTestClient webTestClient;

  @Autowired
  TransmissionRepository transmissionRepository;

  @BeforeEach
  public void setup() {
    webTestClient = MockMvcWebTestClient.bindToController(transmissionPetitionController)
        .controllerAdvice(ControllerExceptionHandler.class)
        .build();
  }

  @Test
  void whenGetTransmissionsPetitionsThenReturnsTransmissionList() {
    //GIVEN
    transmissionRepository.deleteAll();
    transmissionRepository.save(TransmissionDocumentBuilder.build());

    //WHEN
    ResponseSpec responseSpec = webTestClient.get()
        .uri("/v1/transmissions/".concat(TransmissionBuilder.ID).concat("/petitions"))
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    //THEN
    responseSpec.expectStatus().isOk()
        .expectBodyList(TransmissionPetitionResponse.class)
        .hasSize(3)
        .isEqualTo(List.of(TransmissionPetitionResponseBuilder.buildPending(), TransmissionPetitionResponseBuilder.buildAccepted(),
            TransmissionPetitionResponseBuilder.buildRefused()));

    transmissionRepository.deleteAll();
  }

  @Test
  void whenThereAreNotTransmissionsPetitionsThenReturnsEmptyList() {
    //GIVEN
    transmissionRepository.deleteAll();
    transmissionRepository.save(TransmissionDocumentBuilder.build().setPetitions(List.of()));

    //WHEN
    ResponseSpec responseSpec = webTestClient.get()
        .uri("/v1/transmissions/".concat(TransmissionBuilder.ID).concat("/petitions"))
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    //THEN
    responseSpec.expectStatus().isOk()
        .expectBodyList(TransmissionPetitionResponse.class)
        .hasSize(0);

    transmissionRepository.deleteAll();
  }

  @Test
  void whenThereAreNotTransmissionWithSpecifiedIdThenReturnsNotFound() {
    //GIVEN

    //WHEN
    ResponseSpec responseSpec = webTestClient.get()
        .uri("/v1/transmissions/".concat("6666609a993e3f24a04602f4").concat("/petitions"))
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    //THEN
    responseSpec.expectStatus().isNotFound();
  }
}
