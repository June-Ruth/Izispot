package com.demo.service;

import com.demo.dao.TicketRepository;
import com.demo.model.Ticket;
import com.demo.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ParkingServiceTest {

    private ParkingService parkingService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private RateService rateService;

    private Ticket ticket;
    private Ticket ticket2;

    @BeforeEach
    void setUp() {
        parkingService = new ParkingServiceImpl(ticketRepository, rateService);
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
    void getTicketInProgressThatExists() {
        when(ticketRepository.findByVehicleNumberAndOutTimeIsNull(anyString())).thenReturn(ticket);
        assertNotNull(parkingService.getTicketInProgress(anyString()));
        verify(ticketRepository, times(1)).findByVehicleNumberAndOutTimeIsNull(anyString());
    }

    @Test
    void getTicketInProgressThatDoesNotExist() {
        when(ticketRepository.findByVehicleNumberAndOutTimeIsNull(anyString())).thenReturn(null);
        assertNull(parkingService.getTicketInProgress(anyString()));
        verify(ticketRepository, times(1)).findByVehicleNumberAndOutTimeIsNull(anyString());
    }

    @Test
    void processOutComingVehicleThatExists() {
        double rate = 10;
        when(ticketRepository.findById(anyInt())).thenReturn(ticket);
        when(rateService.calculateRate(ticket.getVehicle(), ticket.isSubscriber(), 10)).thenReturn(rate);
        when(ticketRepository.save(any())).thenReturn(ticket2);

        assertEquals(parkingService.processOutComingVehicle(anyInt()), ticket2);

        verify(ticketRepository, times(1)).findById(anyInt());
        verify(rateService, times(1)).calculateRate(ticket.getVehicle(), ticket.isSubscriber(), 10);
        verify(ticketRepository, times(1)).save(any());
    }

    @Test
    void processOutComingVehicleThatDoesNotExist() {
        double rate = 10;
        when(ticketRepository.findById(anyInt())).thenReturn(null);
        //when(rateService.calculateRate(ticket.getVehicle(), ticket.isSubscriber(), 10)).thenReturn(rate);
        //when(ticketRepository.save(any())).thenReturn(ticket2);
        assertNull(parkingService.processOutComingVehicle(anyInt()));

        verify(ticketRepository, times(1)).findById(anyInt());
        verify(rateService, times(0)).calculateRate(ticket.getVehicle(), ticket.isSubscriber(), 10);
        verify(ticketRepository, times(0)).save(any());
    }
}
