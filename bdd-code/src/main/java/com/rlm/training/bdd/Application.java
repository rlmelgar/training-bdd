package com.rlm.training.bdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.rlm.training.bdd.infrastructure.mongodb.repository"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
  //^(?=[a-f\d]{24}$)(\d+[a-f]|[a-f]+\d)
}
