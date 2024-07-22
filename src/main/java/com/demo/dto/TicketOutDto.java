package com.demo.dto;

import com.demo.model.Vehicle;

public class TicketOutDto {

    private String vehicleNumber;

    private Vehicle vehicle;

    private boolean isSubscriber;

    private double price;

    public TicketOutDto() {}

    public TicketOutDto(String vehicleNumber, Vehicle vehicle, boolean isSubscriber, double price) {
        this.vehicleNumber = vehicleNumber;
        this.vehicle = vehicle;
        this.isSubscriber = isSubscriber;
        this.price = price;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isSubscriber() {
        return isSubscriber;
    }

    public void setSubscriber(boolean subscriber) {
        isSubscriber = subscriber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
