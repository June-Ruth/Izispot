package com.demo;

import java.util.Scanner;

public class InputReader {

    Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readVehicleNumber() {
        return scanner.nextLine();
    }

    public boolean readSubscription() {
        return scanner.nextBoolean();
    }

    public int readDuration() {
        return scanner.nextInt();
    }

    public int readVehicle() {
        return scanner.nextInt();
    }

    public byte readOption() {
        return scanner.nextByte();
    }

}
