package com.demo.controller;

import com.demo.model.Ticket;
import com.demo.model.Vehicle;
import com.demo.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ParkingController.class)
public class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    private Ticket ticket;
    private Ticket ticket2;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        ticket.setVehicleNumber("vehicleNumber");
        ticket.setVehicle(Vehicle.CAR);
        ticket.setSubscriber(true);
        ticket.setInTime(LocalDateTime.now().minusMinutes(10));
        ticket2 = new Ticket();
        ticket2.setVehicleNumber("vehicleNumber");
        ticket2.setVehicle(Vehicle.CAR);
        ticket2.setSubscriber(true);
        ticket2.setInTime(LocalDateTime.now().minusMinutes(10));
        ticket2.setOutTime(LocalDateTime.now());
        ticket2.setPrice(10);
    }

    @Test
    void getTicketInProgressThatExists() throws Exception {
        when(parkingService.getTicketInProgress("vehicleNumber")).thenReturn(ticket);

        mockMvc.perform(get("/ticket/vehicleNumber/in_progress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleNumber").value(ticket.getVehicleNumber()));
    }

    @Test
    void getTicketInProgressThatDoesNOtExist() throws Exception {
        when(parkingService.getTicketInProgress("vehicleNumber")).thenReturn(null);

        mockMvc.perform(get("/ticket/vehicleNumber/in_progress"))
                .andExpect(status().isNotFound());
    }


}
