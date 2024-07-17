package com.demo;

import java.util.Scanner;

public class InputReader {

    Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean readSubscription() {
        return scanner.nextBoolean();
    }

    public int readDuration() {
        return scanner.nextInt();
    }

    public Vehicle readVehicle() {
        return scanner.nextInt() == 1 ? Vehicle.CAR : Vehicle.MOTO;
    }

}
