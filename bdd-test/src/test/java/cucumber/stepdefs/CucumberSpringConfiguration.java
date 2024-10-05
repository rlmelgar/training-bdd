package cucumber.stepdefs;

import com.rlm.training.bdd.Application;
import com.rlm.training.bdd.containers.MongoContainer;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {Application.class, MongoContainer.class})
@CucumberContextConfiguration
@ActiveProfiles("test")
public class CucumberSpringConfiguration {

}
