package com.rlm.training.bdd.containers;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration(proxyBeanMethods = false)
public class MongoContainer {

  @Bean
  @ServiceConnection
  public MongoDBContainer mongoDBContainer() {
    return new MongoDBContainer("mongo:latest").withExposedPorts(27017);
  }

}
