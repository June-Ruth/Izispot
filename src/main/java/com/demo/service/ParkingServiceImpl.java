package com.demo.service;

import com.demo.dao.TicketRepository;
import com.demo.model.Ticket;
import com.demo.model.Vehicle;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ParkingServiceImpl implements ParkingService {

    private TicketRepository ticketRepository;

    private RateService rateService;

    public ParkingServiceImpl(TicketRepository ticketRepository, RateService rateService) {
        this.ticketRepository = ticketRepository;
        this.rateService = rateService;
    }

    @Override
    public Ticket processIncomingVehicle(String vehicleNumber, Vehicle vehicleType, boolean isSubscriber) {

        LocalDateTime inTime = LocalDateTime.now().minusHours(1); // For dev only

        Ticket ticket = new Ticket();
        ticket.setVehicleNumber(vehicleNumber);
        ticket.setVehicle(vehicleType);
        ticket.setSubscriber(isSubscriber);
        ticket.setInTime(inTime);

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket processOutComingVehicle(int id) {
        Ticket ticketToUpdate = ticketRepository.findById(id);
        if(ticketToUpdate != null) {
            LocalDateTime outTime = LocalDateTime.now();
            long duration = Duration.between(ticketToUpdate.getInTime(), outTime).toMinutes();
            double rate = rateService.calculateRate(ticketToUpdate.getVehicle(), ticketToUpdate.isSubscriber(), duration);
            ticketToUpdate.setOutTime(outTime);
            ticketToUpdate.setPrice(rate);
            return ticketRepository.save(ticketToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public Ticket getTicketInProgress(String vehicleNumber) {
        return ticketRepository.findByVehicleNumberAndOutTimeIsNull(vehicleNumber);
    }
}
