package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateMessage() throws Exception {
        mockMvc.perform(post("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Tjohej från Mölle!! <3"))
                .andExpect(jsonPath("$.number").value(12));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testGetAllMessagesWhenEmpty() throws Exception {
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testGetAllMessagesAfterCreation() throws Exception {
        mockMvc.perform(post("/messages"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].text").value("Tjohej från Mölle!! <3"))
                .andExpect(jsonPath("$[0].number").value(12));
    }
}
