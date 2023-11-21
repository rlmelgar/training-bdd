package cucumber.stepdefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocument;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocumentBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import com.rlm.training.bdd.infrastructure.rest.server.controller.TransmissionController;
import com.rlm.training.bdd.infrastructure.rest.server.dto.TransmissionResponse;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
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

  @ParameterType("active|with|inactive|without")
  public Boolean booleanValue(String value) {
    if ("active".equals(value) || "with".equals(value)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  @ParameterType("a|an|one|two|tree")
  public int numeric(String value) {
    return switch (value) {
      case "a", "an", "one" -> 1;
      case "two" -> 2;
      case "tree" -> 3;
      default -> Integer.parseInt(value);
    };
  }

  @Given("no transmission stored")
  public void no_transmission_stored() {
    transmissionRepository.deleteAll();
  }

  @Given("{numeric} {booleanValue} transmission(s) stored {booleanValue} petitions")
  public void given_an_transmission_stored_petitions(Integer nDocuments, Boolean active, Boolean hasPetitions) {
    List<TransmissionDocument> transmissionDocumentList = new ArrayList<>();
    for (int i = 0; i < nDocuments; i++) {
      TransmissionDocument transmissionDocument = TransmissionDocumentBuilder.build().setId(null).setActive(active);
      transmissionDocument.getSpaceship().setName(transmissionDocument.getSpaceship().getName() + UUID.randomUUID());
      if (!hasPetitions) {
        transmissionDocument.setPetitions(List.of());
      }
      transmissionDocumentList.add(transmissionDocument);
    }
    transmissionRepository.saveAll(transmissionDocumentList);
  }

  @DataTableType
  public TransmissionDocument transmissionDocumentTransformer(Map<String, String> row) {
    TransmissionDocument transmissionDocument = TransmissionDocumentBuilder.build()
        .setId(row.get("id"))
        .setActive(Boolean.parseBoolean(row.get("active")));
    transmissionDocument.getSpaceship().setName(transmissionDocument.getSpaceship().getName() + transmissionDocument.getId());

    if (!Boolean.parseBoolean(row.get("hasPetitions"))) {
      transmissionDocument.setPetitions(List.of());
    }
    return transmissionDocument;
  }

  @Given("the following transmissions stored")
  public void given_an_transmission_stored_petitions(List<TransmissionDocument> transmissionDocumentList) {
    transmissionRepository.saveAll(transmissionDocumentList);
  }

  @When("recover all transmissions")
  public void recover_all_transmissions() {
    responseSpec = webTestClient.get()
        .uri("/v1/transmissions")
        .accept(MediaType.APPLICATION_JSON)
        .exchange();
  }

  @Then("returns a list with {numeric} transmission(s)")
  public void returns_a_list_with_all_transmissions(Integer nDocuments) {
    responseSpec.expectBodyList(TransmissionResponse.class)
        .hasSize(nDocuments);
  }

  @Then("the response was ok")
  public void the_response_was_ok() {
    responseSpec.expectStatus().isOk();
  }
}
