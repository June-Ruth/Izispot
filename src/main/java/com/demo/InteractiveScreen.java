package com.demo;

import com.demo.dao.TicketDao;
import com.demo.service.ParkingService;

import java.util.Scanner;

public class InteractiveScreen {

    public InteractiveScreen() {}

    private final Scanner scanner = new Scanner(System.in);

    private final InputReader inputReader = new InputReader(scanner);

    private final ParkingService parkingService = new ParkingService(inputReader, new TicketDao());

    //private final RateCalculator rateCalculator = new RateCalculator();

    public void displayScreen() {

        System.out.println("Bienvenue!");

        boolean continueApp = true;
        while (continueApp) {
            System.out.println("Sélectionnez une option :");
            System.out.println("1 - Véhicule entrant");
            System.out.println("2 - Véhicule sortant");
            System.out.println("3 - Quitter le système");

            int option = inputReader.readOption();

            switch (option) {
                case 1:
                    parkingService.processIncomingVehicle();
                    continueApp = false;
                    break;
                case 2:
                    //TODO : process véhicule sortant
                    continueApp = false;
                    break;
                case 3:
                    //TODO : sortie du système
                    continueApp = false;
                    break;
                default:
                    System.out.println("Mauvaise option, veuillez entrer 1, 2 ou 3");
                    break;
            }
        }
    }

}
