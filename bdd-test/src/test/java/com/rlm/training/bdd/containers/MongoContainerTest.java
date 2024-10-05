package com.rlm.training.bdd.containers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MongoContainerTest {

  @Container
  @ServiceConnection
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest").withExposedPorts(27017);

  @BeforeAll
  public static void beforeAllInitDb() {
    mongoDBContainer.start();
  }

  @AfterAll
  public static void afterAllStopDb() {
    mongoDBContainer.stop();
  }

}
