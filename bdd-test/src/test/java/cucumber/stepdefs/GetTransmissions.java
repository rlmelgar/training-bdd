package cucumber.stepdefs;

import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocumentBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import com.rlm.training.bdd.infrastructure.rest.server.controller.TransmissionController;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponse;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponseBuilder;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

@Slf4j
public class GetTransmissions extends CucumberSpringConfiguration {

  @Autowired
  TransmissionController transmissionController;

  WebTestClient webTestClient;

  @Autowired
  TransmissionRepository transmissionRepository;

  ResponseSpec responseSpec;

  @Before("@transmission")
  public void setup() {
    webTestClient = MockMvcWebTestClient.bindToController(transmissionController).build();
  }

  @Given("an active transmission stored with petitions")
  public void an_active_transmission_stored_with_petitions() {
    transmissionRepository.deleteAll();
    transmissionRepository.save(TransmissionDocumentBuilder.build());
  }

  @When("recover all transmissions")
  public void recover_all_transmissions() {
    responseSpec = webTestClient.get()
        .uri("/v1/transmissions")
        .accept(MediaType.APPLICATION_JSON)
        .exchange();
  }

  @Then("returns a list with all transmissions")
  public void returns_a_list_with_all_transmissions() {
    responseSpec.expectBodyList(TransmissionResponse.class)
        .hasSize(1)
        .contains(TransmissionResponseBuilder.build());
  }
}
