package karate.transmissionPetition;

import com.intuit.karate.junit5.Karate;
import com.rlm.training.bdd.Application;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocumentBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import cucumber.MongoContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {Application.class})
class GetTransmissionPetitionsRunner extends MongoContainer {

  @Autowired
  TransmissionRepository transmissionRepository;

  @Karate.Test
  Karate getTransmissionsFirstTestOnly() {
    transmissionRepository.deleteAll();
    transmissionRepository.save(TransmissionDocumentBuilder.build().setId("112233"));

    return Karate.run().relativeTo(getClass());
  }

}