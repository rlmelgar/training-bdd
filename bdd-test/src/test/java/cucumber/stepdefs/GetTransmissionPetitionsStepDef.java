package cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocument;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import com.rlm.training.bdd.infrastructure.rest.server.controller.TransmissionPetitionController;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionPetitionResponse;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

public class GetTransmissionPetitionsStepDef extends CucumberSpringConfiguration {

  @Autowired
  TransmissionPetitionController transmissionPetitionController;

  WebTestClient webTestClient;

  @Autowired
  TransmissionRepository transmissionRepository;

  ResponseSpec responseSpec;

  @Before("@transmissionPetitions")
  public void setup() {
    webTestClient = MockMvcWebTestClient.bindToController(transmissionPetitionController).build();
  }

  @When("recover all petitions of the transmission with identification id {word}")
  public void recoverAllPetitionsOfTheTransmissionWithIdentificationId(String transmissionId) {
    responseSpec = webTestClient.get()
        .uri("/v1/transmissions/".concat(transmissionId).concat("/petitions"))
        .accept(MediaType.APPLICATION_JSON)
        .exchange();
  }

  @Then("returns a list with all petitions of transmission with identification id {word}")
  public void returnsAListWithAllPetitionsOfTransmissionWithIdentificationId(String transmissionId) {
    Optional<TransmissionDocument> transmissionDocument = transmissionRepository.findById(transmissionId);

    assertThat(transmissionDocument).isPresent();

    responseSpec.expectStatus().isOk()
        .expectBodyList(TransmissionPetitionResponse.class)
        .hasSize(transmissionDocument.get().getPetitions().size());
  }
}
