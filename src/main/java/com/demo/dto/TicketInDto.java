package com.demo.dto;

import com.demo.model.Vehicle;

public class TicketInDto {

    private int id;

    private String vehicleNumber;

    private Vehicle vehicle;

    private boolean isSubscriber;

    public TicketInDto() {}

    public TicketInDto(String vehicleNumber, Vehicle vehicle, boolean isSubscriber) {
        this.vehicleNumber = vehicleNumber;
        this.vehicle = vehicle;
        this.isSubscriber = isSubscriber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
