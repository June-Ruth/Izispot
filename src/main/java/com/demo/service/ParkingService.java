package com.demo.service;

import com.demo.util.InputReader;
import com.demo.model.Vehicle;
import com.demo.dao.TicketDao;
import com.demo.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingService.class);

    private InputReader inputReader;

    private TicketDao ticketDao;

    private RateService rateService;

    public ParkingService(InputReader inputReader, TicketDao ticketDao, RateService rateService) {
        this.inputReader = inputReader;
        this.ticketDao = ticketDao;
        this.rateService = rateService;
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

    public void processOutComingVehicle() {
        String vehicleNumber = getVehicleNumber();
        Ticket ticketToUpdate = ticketDao.getTicket(vehicleNumber);
        if(ticketToUpdate != null) {
            LocalDateTime outTime = LocalDateTime.now();
            long duration = Duration.between(ticketToUpdate.getInTime(), outTime).toMinutes();
            double rate = rateService.calculateRate(ticketToUpdate.getVehicle(), ticketToUpdate.isSubscriber(), duration);
            ticketToUpdate.setOutTime(outTime);
            ticketToUpdate.setPrice(rate);
            ticketDao.updateTicket(ticketToUpdate);
            LOGGER.info("Le prix de votre ticket est de {}€. Bonne journée !", rate);
        } else {
            LOGGER.error("Ticket non trouvé");
        }
    }

    private String getVehicleNumber() {
        String vehicleNumber = null;
        while(vehicleNumber == null || vehicleNumber.isEmpty()) {
            try {
                LOGGER.info("Merci de renseigner votre numéro de plaque");
                vehicleNumber = inputReader.readVehicleNumber();
                if(vehicleNumber == null || vehicleNumber.isEmpty()) {
                    throw new IllegalArgumentException("Plaque du vehicule incorrect");
                }
            } catch (IllegalArgumentException e) {
                LOGGER.error("Merci d'entrer une plaque valide");
            }
        }
        return vehicleNumber;
    }

    private Vehicle getVehicleType() {
        LOGGER.info("Votre véhicule est : \n 1. Une voiture \n 2. Une moto");
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
                    LOGGER.error("Veuillez rentrer un option valide");
                    break;
            }
        } while(option != 1 && option != 2);
        return vehicleType;
    }

    private boolean isSubscriber() {
        LOGGER.info("Etes-vous abonné ?  (true ou false)");
        return inputReader.readSubscription();
    }




}
