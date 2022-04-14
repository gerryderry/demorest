package com.example.rest.demo.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RestApiDemoControllerTest {

    @Autowired
    MockMvc mockMvc;

    private String test = "testing";
    private int ZERO = 0;
    private boolean HAPPY = false;
    private Double TWO = 2.00;


    @Test
    public void getCoffeesTest() throws Exception {
        mockMvc.perform(get("/coffees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name")
                        .value(Matchers.containsInAnyOrder("Cafe Cereza",  "Cafe Ganador", "Cafe Lareno", "Cafe Tres Pontas")));
    }
}
