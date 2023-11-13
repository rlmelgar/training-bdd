package com.rlm.training.bdd.infrastructure.rest.server.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;

import com.rlm.training.bdd.application.usecase.transmission.GetActiveTransmissionsUseCase;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.infrastructure.rest.server.config.TestMockSimpleConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(TransmissionController.class)
@Import(TestMockSimpleConfig.class)
class TransmissionControllerWebAppIT {

  @Autowired
  GetActiveTransmissionsUseCase getActiveTransmissionsUseCase;

  @Autowired
  WebApplicationContext webApplicationContext;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    when(this.getActiveTransmissionsUseCase.getActive()).thenReturn(Stream.of(TransmissionBuilder.buildActive2()));
  }

  @Test
  void whenGetTransmissionsThenReturnsTransmissionList() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .get("/v1/transmissions/")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(TransmissionBuilder.ID2));
  }

  @Test
  void whenThereAreNotTransmissionsThenReturnsEmptyList() throws Exception {
    when(this.getActiveTransmissionsUseCase.getActive()).thenReturn(Stream.of());

    mockMvc.perform(MockMvcRequestBuilders
            .get("/v1/transmissions/")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isEmpty());
  }
}
