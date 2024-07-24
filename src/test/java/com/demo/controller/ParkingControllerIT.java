package com.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ParkingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTicketInProgressThatExists() throws Exception {
        mockMvc.perform(get("/ticket/vehicleNumber/in_progress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleNumber").value("vehicleNumber"));
    }

}