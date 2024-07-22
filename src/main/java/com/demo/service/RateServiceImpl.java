package com.demo.service;

import com.demo.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {

    public RateServiceImpl() {}

    final double PRICE_PER_MINUTE_FOR_OCCASIONAL_CAR = 0.5;
    final double PRICE_PER_MINUTE_FOR_OCCASIONAL_MOTO = 0.4;
    final double PRICE_PER_MINUTE_FOR_SUBSCRIBER_CAR = 0.2;
    final double PRICE_PER_MINUTE_FOR_SUBSCRIBER_MOTO = 0.1;

    @Override
    public double calculateRate(Vehicle vehicle, boolean isSubscriber, long durationInMinutes) {
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
