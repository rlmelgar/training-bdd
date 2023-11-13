package com.rlm.training.bdd.infrastructure.rest.server.config;

import com.rlm.training.bdd.infrastructure.mongodb.repository.TransmissionRepository;
import com.rlm.training.bdd.infrastructure.rest.server.mapper.TransmissionDtoMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@TestConfiguration
public class TestMockSimpleConfig {

  @MockBean
  TransmissionRepository transmissionRepository;

  @SpyBean
  TransmissionDtoMapper transmissionDtoMapper;
}
