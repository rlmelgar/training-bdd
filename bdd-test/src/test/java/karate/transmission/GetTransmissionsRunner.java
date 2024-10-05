package karate.transmission;

import com.intuit.karate.junit5.Karate;
import com.rlm.training.bdd.Application;
import com.rlm.training.bdd.containers.MongoContainer;
import com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocumentBuilder;
import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {Application.class, MongoContainer.class})
@ActiveProfiles("test")
class GetTransmissionsRunner {

  @Autowired
  TransmissionRepository transmissionRepository;

  @Karate.Test
  Karate getTransmissions() {
    transmissionRepository.deleteAll();
    transmissionRepository.save(TransmissionDocumentBuilder.build());

    return Karate.run("classpath:karate/transmission/GetTransmissions.feature");
  }

  @Karate.Test
  Karate getTransmissionsFirstTestOnly() {
    transmissionRepository.deleteAll();
    transmissionRepository.save(TransmissionDocumentBuilder.build());

    return Karate.run("GetTransmissions.feature").relativeTo(getClass())
        .tags("@first");
  }

}