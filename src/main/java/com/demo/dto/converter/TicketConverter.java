package com.demo.dto.converter;

import com.demo.dto.TicketInDto;
import com.demo.dto.TicketOutDto;
import com.demo.model.Ticket;

public class TicketConverter {

    public static TicketInDto convertToTicketInDto(Ticket ticket) {
        TicketInDto ticketInDto = new TicketInDto();
        ticketInDto.setId(ticket.getId());
        ticketInDto.setVehicleNumber(ticket.getVehicleNumber());
        ticketInDto.setVehicle(ticket.getVehicle());
        ticketInDto.setSubscriber(ticket.isSubscriber());
        return ticketInDto;
    }

    public static TicketOutDto convertToTicketOutDto(Ticket ticket) {
        TicketOutDto ticketOutDto = new TicketOutDto();
        ticketOutDto.setVehicleNumber(ticket.getVehicleNumber());
        ticketOutDto.setVehicle(ticket.getVehicle());
        ticketOutDto.setSubscriber(ticket.isSubscriber());
        ticketOutDto.setPrice(ticket.getPrice());
        return ticketOutDto;

    }
}
