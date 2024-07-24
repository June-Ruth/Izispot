package com.demo.service;

import com.demo.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class RateServiceTest {

    private RateService rateService;

    @BeforeEach
    void setUp() {
        rateService = new RateServiceImpl();
    }

    //1 - vehicle = CAR + isSubscriber = false

    //void givenVehicleIsCarAndClientIsNotSubscriberAndParkingDurationIs10MinutesWhenCalculateRateThenRateEquals5() {}

    @Test
    void calculateRateWithCarAndNotSubscriber() {
        double rate = rateService.calculateRate(Vehicle.CAR, false, 10);
        assertEquals(5, rate);
    }

    //2 - vehicle = CAR + isSubscriber = true

    @Test
    void calculateRateWithCarAndSubscriber() {
        double rate = rateService.calculateRate(Vehicle.CAR, true, 10);
        assertEquals(2, rate);
    }

    //3 - vehicle = MOTO + isSubscriber = true

    @Test
    void calculateRateWithMotoAndSubscriber() {
        double rate = rateService.calculateRate(Vehicle.MOTO, true, 10);
        assertEquals(1, rate);
    }

    //4 - vehicle = MOTO + isSubscriber = false

    @Test
    void calculateRateWithMotoAndNotSubscriber() {
        double rate = rateService.calculateRate(Vehicle.MOTO, false, 10);
        assertEquals(4, rate);
    }

}
