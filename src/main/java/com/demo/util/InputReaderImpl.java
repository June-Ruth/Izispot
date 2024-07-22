package com.demo.util;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputReaderImpl implements InputReader {

    private final Scanner scanner;

    public InputReaderImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readVehicleNumber() {
        return scanner.nextLine();
    }

    @Override
    public boolean readSubscription() {
        return scanner.nextBoolean();
    }

    @Override
    public int readVehicle() {
        return scanner.nextInt();
    }

    @Override
    public byte readOption() {
        return scanner.nextByte();
    }

}
