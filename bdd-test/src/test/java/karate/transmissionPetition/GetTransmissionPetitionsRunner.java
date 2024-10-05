package karate.transmissionPetition;

import com.intuit.karate.junit5.Karate;
import com.rlm.training.bdd.Application;
import com.rlm.training.bdd.containers.MongoContainerTest;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocumentBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {Application.class})
@ActiveProfiles("test")
class GetTransmissionPetitionsRunner extends MongoContainerTest {

  @Autowired
  TransmissionRepository transmissionRepository;

  @Karate.Test
  Karate getTransmissionsFirstTestOnly() {
    transmissionRepository.deleteAll();
    transmissionRepository.save(TransmissionDocumentBuilder.build().setId("112233"));

    return Karate.run().relativeTo(getClass());
  }

}