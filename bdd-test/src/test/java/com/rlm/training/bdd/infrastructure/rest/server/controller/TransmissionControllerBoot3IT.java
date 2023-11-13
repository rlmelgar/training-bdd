package com.rlm.training.bdd.infrastructure.rest.server.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rlm.training.bdd.domain.model.SpaceshipBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TransmissionControllerBoot3IT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void whenGetTransmissionsThenReturnsTransmissionList() throws Exception {
    String json = new ObjectMapper().writeValueAsString(SpaceshipBuilder.build());
    mockMvc.perform(MockMvcRequestBuilders
            .get("/v1/transmissions/")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].spaceship.name").value("Ship name"));
  }
}
