package com.rlm.training.bdd.aceptance;

import com.rlm.training.bdd.Application;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocumentBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import com.rlm.training.bdd.infrastructure.rest.server.controller.TransmissionController;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponseBuilder;
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
class TransmissionControllerIT {

  @Autowired
  TransmissionController transmissionController;

  WebTestClient webTestClient;

  @Autowired
  TransmissionRepository transmissionRepository;

  @BeforeEach
  public void setup() {
    webTestClient = MockMvcWebTestClient.bindToController(transmissionController).build();
  }

  @Test
  void whenGetTransmissionsThenReturnsTransmissionList() {
    //GIVEN
    transmissionRepository.deleteAll();
    transmissionRepository.save(TransmissionDocumentBuilder.build());

    //WHEN
    ResponseSpec responseSpec = webTestClient.get()
        .uri("/v1/transmissions")
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    //THEN
    responseSpec.expectBodyList(TransmissionResponse.class)
        .hasSize(1)
        .contains(TransmissionResponseBuilder.build());

    transmissionRepository.deleteAll();
  }

  @Test
  void whenThereAreNotTransmissionsThenReturnsEmptyList() {
    //GIVEN
    transmissionRepository.deleteAll();

    //WHEN
    ResponseSpec responseSpec = webTestClient.get()
        .uri("/v1/transmissions/")
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    //THEN
    responseSpec.expectBodyList(TransmissionResponse.class)
        .hasSize(0);
  }
}
