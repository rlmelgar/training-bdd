package com.rlm.training.bdd.infrastructure.rest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rlm.training.bdd.Application;
import org.apache.catalina.core.ApplicationContextFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TransmissionControllerBoot2IT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void whenGetTransmissionsThenReturnsTransmissionList() throws Exception {
    assertThat(mockMvc.getDispatcherServlet().getServletContext()).isNotNull().isInstanceOf(ApplicationContextFacade.class);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/v1/transmissions")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
