package cucumber.stepdefs;

import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CleanCollectionsStepDef extends CucumberSpringConfiguration {

  @Autowired
  TransmissionRepository transmissionRepository;

  @Given("no transmission stored")
  public void no_transmission_stored() {
    transmissionRepository.deleteAll();
  }

}
