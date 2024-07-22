package com.demo.util;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputReaderImpl implements InputReader {

    private final Scanner scanner;

    public InputReaderImpl() {
        this.scanner = new Scanner(System.in);
    }

    public String readVehicleNumber() {
        return scanner.nextLine();
    }

    public boolean readSubscription() {
        return scanner.nextBoolean();
    }

    public int readVehicle() {
        return scanner.nextInt();
    }

    public byte readOption() {
        return scanner.nextByte();
    }

}
