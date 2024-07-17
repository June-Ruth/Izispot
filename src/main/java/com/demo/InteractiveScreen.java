package com.demo;

import java.util.Scanner;

public class InteractiveScreen {

    private final Scanner scanner = new Scanner(System.in);

    public InteractiveScreen() {}

    private final InputReader inputReader = new InputReader(scanner);

    private final RateCalculator rateCalculator = new RateCalculator();

    static boolean isSubscriber;

    static int durationInMinutes;

    static double rate;

    static Vehicle vehicle;

    public void displayScreen() {

        System.out.println("Bonjour. Avez-vous un abonnement ?");

        isSubscriber = inputReader.readSubscription();

        System.out.println("Votre véhicule est : \n 1. Une voiture \n 2. Une moto");

        vehicle = inputReader.readVehicle();

        System.out.println("Combien de temps êtes vous resté dans le parking ?");

        durationInMinutes = inputReader.readDuration();

        rate = rateCalculator.calculateRate(vehicle, isSubscriber, durationInMinutes);

        System.out.println("Votre ticket coûte " + rate + "€");
    }

}
