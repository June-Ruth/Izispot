package com.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static String name;

    static boolean isSubscriber;

    static int durationInMinutes;

    static double rate;

    static Vehicle vehicle;

    static final double PRICE_PER_MINUTE_FOR_OCCASIONAL_CAR = 0.5;
    static final double PRICE_PER_MINUTE_FOR_OCCASIONAL_MOTO = 0.4;
    static final double PRICE_PER_MINUTE_FOR_SUBSCRIBER_CAR = 0.2;
    static final double PRICE_PER_MINUTE_FOR_SUBSCRIBER_MOTO = 0.1;

    public static void main(String[] args) {

        /*bonjourWithNumericFor();

        bonjourWithCollectionFor();

        bonjourWithWhile();

        bonjourWithDoWhile();*/

        System.out.println("Bonjour! Quel est votre nom ?");

        readClientName();

        System.out.println("Bonjour " + name + ".\n" + "Avez-vous un abonnement ?");

        readSubscription();

        System.out.println("Votre véhicule est : \n 1. Une voiture \n 2. Une moto");

        readVehicle();

        System.out.println("Combien de temps êtes vous resté dans le parking ?");

        readDuration();

        rate = calculateRate();

        System.out.println("Votre ticket coûte " + rate + "€");
    }

    /*private static void bonjourWithNumericFor() {
        for(int i = 0; i<=5; i++) {
            System.out.println("Bonjour " + i);
        }
    }

    private static void bonjourWithCollectionFor() {
        List<String> example = new ArrayList<>();
        example.add("Jean");
        example.add("John");
        example.add("Jane");
        example.add("Joe");
        example.add("Jack");

        for(String name : example) {
            System.out.println("Bonjour " + name);
        }
    }

    private static void bonjourWithWhile() {
        int i = 6;
        while(i <= 5) {
            System.out.println("Bonjour while " + i);
            i++;
        }
    }

    private static void bonjourWithDoWhile() {
        int i = 6;
        do {
            System.out.println("Bonjour do while " + i);
            i++;
        } while(i <= 5);
    }*/

    private static void readClientName() {
        name = scanner.nextLine();
    }

    private static void readSubscription() {
        isSubscriber = scanner.nextBoolean();
    }

    private static void readDuration() {
        durationInMinutes = scanner.nextInt();
    }

    private static void readVehicle() {
        vehicle = scanner.nextInt() == 1 ? Vehicle.CAR : Vehicle.MOTO;
    }

    private static double calculateRate() {
        double result = 0;
        switch(vehicle) {
            case CAR:
                if(isSubscriber) {
                    result = PRICE_PER_MINUTE_FOR_SUBSCRIBER_CAR * durationInMinutes;
                } else {
                    result = PRICE_PER_MINUTE_FOR_OCCASIONAL_CAR * durationInMinutes;
                }
                break;
            case MOTO:
                if(isSubscriber) {
                    result = PRICE_PER_MINUTE_FOR_SUBSCRIBER_MOTO * durationInMinutes;
                } else {
                    result = PRICE_PER_MINUTE_FOR_OCCASIONAL_MOTO * durationInMinutes;
                }
                break;
        }
        return result;
    }
}