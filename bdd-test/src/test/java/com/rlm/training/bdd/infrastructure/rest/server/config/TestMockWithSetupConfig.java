package com.rlm.training.bdd.infrastructure.rest.server.config;

import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import com.rlm.training.bdd.application.usecase.transmission.GetActiveTransmissionsUseCase;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestMockWithSetupConfig {

  @MockBean
  GetActiveTransmissionsUseCase getActiveTransmissionsUseCase;

  @PostConstruct
  void setup() {
    when(this.getActiveTransmissionsUseCase.getActive()).thenReturn(Stream.of(TransmissionBuilder.buildActive()));
  }
}
