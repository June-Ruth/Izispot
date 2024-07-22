package com.demo;

import com.demo.service.ParkingService;
import com.demo.util.InputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InteractiveScreen {

    private static final Logger LOGGER = LoggerFactory.getLogger(InteractiveScreen.class);

    private final InputReader inputReader ;

    private final ParkingService parkingService;

    public InteractiveScreen(InputReader inputReader, ParkingService parkingService) {
        this.inputReader = inputReader;
        this.parkingService = parkingService;

    }

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
