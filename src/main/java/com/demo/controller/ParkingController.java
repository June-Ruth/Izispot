package com.demo.controller;

import com.demo.dto.TicketOutDto;
import com.demo.dto.converter.TicketConverter;
import com.demo.model.Ticket;
import com.demo.model.Vehicle;
import com.demo.dto.TicketInDto;
import com.demo.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    /*
    Screen 1 : Sélectionnez une option :
        1 - Véhicule entrant
        2 - Véhicule sortant
        3 - Quitter le système
     */

    /*
    Screen 2 : Sélection = 1
        1 - Merci de renseigner votre numéro de plaque - Gestion exception : numéro de plaque incorrecte
        2 - Votre véhicule est : 1. Une voiture OU 2. Une moto - Gestion exception : type incorrect
        3 - "Etes-vous abonné ?  (true ou false)"
    */

    //@RequestMapping(value = "/ticket", method = RequestMethod.POST)
    @PostMapping("/ticket")
    @ResponseBody
    public ResponseEntity<TicketInDto> fillAndRegisterTicket(@RequestParam("number") String vehicleNumber,
                                                             @RequestParam("type") Vehicle vehicleType,
                                                             @RequestParam("subscription") boolean isSubscriber) {
        boolean isSaved = parkingService.processIncomingVehicle(vehicleNumber, vehicleType, isSubscriber);
        if(isSaved) {
            return ResponseEntity.status(201).body(new TicketInDto(vehicleNumber, vehicleType, isSubscriber));
        } else {
            return ResponseEntity.badRequest().build();
        }

        // return isSaved ? ResponseEntity.status(201).body(new TicketInDto(vehicleNumber, vehicleType, isSubscriber)) : ResponseEntity.badRequest().build();
    }

    /*
    Etape 3 : Selection = 2
        1 - Merci de renseigner votre numéro de plaque - Gestion exception : numéro de plaque incorrecte
     */

    @GetMapping("/ticket/{number}/in_progress")
    @ResponseBody
    public ResponseEntity<TicketInDto> getTicketInProgress(@PathVariable("number") String vehicleNumber) {
        Ticket ticket = parkingService.getTicketInProgress(vehicleNumber);
        if(ticket == null) {
             return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(TicketConverter.convertToTicketInDto(ticket));
        }
    }

    /*
        2 - Service : process outcoming vehicle
     */

    @PutMapping("/ticket/{id}")
    @ResponseBody
    public ResponseEntity<TicketOutDto> processOutGoing(@PathVariable("id") int id) {
        Ticket ticket = parkingService.processOutComingVehicle(id);
        if(ticket == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(TicketConverter.convertToTicketOutDto(ticket));
        }
    }

    /*
    Etape 4 : Selection = 3
        - Quitter le système
     */
}
