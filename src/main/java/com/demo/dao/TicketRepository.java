package com.demo.dao;

import com.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket save(Ticket ticket);

    Ticket findByVehicleNumberAndOutTimeIsNull(String vehicleNumber);

    Ticket findById(int id);
}
