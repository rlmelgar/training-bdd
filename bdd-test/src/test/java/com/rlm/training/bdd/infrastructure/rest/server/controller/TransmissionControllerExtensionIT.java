package com.rlm.training.bdd.infrastructure.rest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rlm.training.bdd.domain.model.SpaceshipBuilder;
import com.rlm.training.bdd.domain.model.TransmissionBuilder;
import com.rlm.training.bdd.infrastructure.rest.server.config.TestWebConfig;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Spring test, no used Spring Boot
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration //Load web application context
@ContextConfiguration(classes = {TestWebConfig.class})
@TestInstance(Lifecycle.PER_CLASS)
class TransmissionControllerExtensionIT {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void whenGetTransmissionsThenReturnsTransmissionList() throws Exception {
    ServletContext servletContext = webApplicationContext.getServletContext();

    assertThat(servletContext).isNotNull().isInstanceOf(MockServletContext.class);
    assertThat(webApplicationContext.getBean("transmissionController")).isNotNull();

    mockMvc.perform(MockMvcRequestBuilders
            .get("/v1/transmissions/")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(TransmissionBuilder.ID))
        .andExpect(MockMvcResultMatchers.content().string(containsString(SpaceshipBuilder.CARGO)));
  }
}
