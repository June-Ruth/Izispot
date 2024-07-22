package com.demo.service;

import com.demo.model.Vehicle;

public interface RateService {

    double calculateRate(Vehicle vehicle, boolean isSubscriber, long durationInMinutes);
}
