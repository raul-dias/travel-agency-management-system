package com.raul.nymble.controller;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.model.Passenger;
import com.raul.nymble.model.TravelPackage;
import com.raul.nymble.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @GetMapping("/passenger")
    public List<Passenger> getPassenger(){
        return passengerService.findAll();
    }

    @GetMapping("/passenger/{id}")
    public Passenger getPassenger(@PathVariable Long id){
        return passengerService.findById(id);
    }

    @PostMapping("/passenger")
    public void savePassenger(@RequestBody Passenger passenger){
        passengerService.save(new Passenger(passenger));
    }

    @PutMapping("/passenger/{id}")
    public void updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger){
        passenger.setId(id);
        passengerService.save(passenger);
    }

    @DeleteMapping("/passenger/{id}")
    public void deletePassenger(@PathVariable Long id){
        passengerService.delete(id);
    }

    @GetMapping("/passenger/{id}/packages")
    public List<TravelPackageDTO> getPassengerPackages(@PathVariable Long id){
        return passengerService.getTravelPackages(id);
    }
}
