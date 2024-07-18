package com.demo.service;

import com.demo.InputReader;
import com.demo.Vehicle;
import com.demo.dao.TicketDao;
import com.demo.model.Ticket;

import java.time.LocalDateTime;

public class ParkingService {

    private InputReader inputReader;

    private TicketDao ticketDao;

    public ParkingService(InputReader inputReader, TicketDao ticketDao) {
        this.inputReader = inputReader;
        this.ticketDao = ticketDao;
    }

    public void processIncomingVehicle() {
        String vehicleNumber = getVehicleNumber();
        Vehicle vehicleType = getVehicleType();
        boolean isSubscriber = isSubscriber();
        LocalDateTime inTime = LocalDateTime.now().minusHours(1);

        Ticket ticket = new Ticket();
        ticket.setVehicleNumber(vehicleNumber);
        ticket.setVehicle(vehicleType);
        ticket.setSubscriber(isSubscriber);
        ticket.setInTime(inTime);

        ticketDao.saveTicket(ticket);
    }

    private String getVehicleNumber() {
        String vehicleNumber = null;
        while(vehicleNumber == null || vehicleNumber.isEmpty()) {
            try {
                System.out.println("Merci de renseigner votre numéro de plaque");
                vehicleNumber = inputReader.readVehicleNumber();
                if(vehicleNumber == null || vehicleNumber.isEmpty()) {
                    throw new IllegalArgumentException("Plaque du vehicule incorrect");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Merci d'entrer une plaque valide");
            }
        }
        return vehicleNumber;
    }

    private Vehicle getVehicleType() {
        System.out.println("Votre véhicule est : \n 1. Une voiture \n 2. Une moto");
        int option;
        Vehicle vehicleType = null;
        do {
            option = inputReader.readVehicle();
            switch (option) {
                case 1:
                    vehicleType = Vehicle.CAR;
                    break;
                case 2:
                    vehicleType = Vehicle.MOTO;
                    break;
                default:
                    System.out.println("Veuillez rentrer un option valide");
                    break;
            }
        } while(option != 1 && option != 2);
        return vehicleType;
    }

    private boolean isSubscriber() {
        System.out.println("Etes-vous abonné ?  (true ou false)");
        return inputReader.readSubscription();
    }




}
