package com.demo;

import com.demo.dao.TicketDao;
import com.demo.service.ParkingService;
import com.demo.service.RateService;
import com.demo.util.InputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InteractiveScreen {

    private static final Logger LOGGER = LoggerFactory.getLogger(InteractiveScreen.class);

    public InteractiveScreen() {}

    private final Scanner scanner = new Scanner(System.in);

    private final InputReader inputReader = new InputReader(scanner);

    private final TicketDao ticketDao = new TicketDao();

    private final RateService rateService = new RateService();

    private final ParkingService parkingService = new ParkingService(inputReader, ticketDao, rateService);

    public void displayScreen() {

        LOGGER.info("Bienvenue!");

        boolean continueApp = true;
        while (continueApp) {
            LOGGER.info("Sélectionnez une option :");
            LOGGER.info("1 - Véhicule entrant");
            LOGGER.info("2 - Véhicule sortant");
            LOGGER.info("3 - Quitter le système");

            int option = inputReader.readOption();

            switch (option) {
                case 1:
                    parkingService.processIncomingVehicle();
                    continueApp = false;
                    break;
                case 2:
                    parkingService.processOutComingVehicle();
                    continueApp = false;
                    break;
                case 3:
                    LOGGER.info("Au revoir !");
                    continueApp = false;
                    break;
                default:
                    LOGGER.error("Mauvaise option, veuillez entrer 1, 2 ou 3");
                    break;
            }
        }
    }
}
