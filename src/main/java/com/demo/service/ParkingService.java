package com.demo.service;

import com.demo.model.Ticket;
import com.demo.model.Vehicle;

public interface ParkingService {

    Ticket processIncomingVehicle(String vehicleNumber, Vehicle vehicleType, boolean isSubscriber);

    Ticket getTicketInProgress(String vehicleNumber);

    Ticket processOutComingVehicle(int id);
}
