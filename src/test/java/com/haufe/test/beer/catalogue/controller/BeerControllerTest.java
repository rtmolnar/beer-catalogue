package com.haufe.test.beer.catalogue.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void should_return_200_on_get_all_beers() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
      .get("/beers")
      .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));
  }
}
