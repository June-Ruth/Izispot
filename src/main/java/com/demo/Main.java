package com.demo;

public class Main {

    private static final InteractiveScreen INTERACTIVE_SCREEN_IMPL = new InteractiveScreenImpl();

    public static void main(String[] args) {
        INTERACTIVE_SCREEN_IMPL.displayScreen();
    }
}