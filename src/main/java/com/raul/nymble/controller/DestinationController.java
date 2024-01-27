package com.raul.nymble.controller;

import com.raul.nymble.model.Destination;
import com.raul.nymble.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @GetMapping("/destination")
    public List<Destination> getDestinations() {
        return (List<Destination>) destinationService.findAll();
    }

    @GetMapping("/destination/{id}")
    public Destination getDestination(@PathVariable Long id){
        return destinationService.findById(id);
    }

    @PostMapping("/destination")
    public void addDestination(@RequestBody Destination destination){
        destinationService.save(destination);
    }

    @PutMapping("/destination/{id}")
    public void updateDestination(@PathVariable Long id, @RequestBody Destination destination){
        destination.setId(id);
        destinationService.update(destination);
    }

    @DeleteMapping("/destination/{id}")
    public void deleteDestination(@PathVariable Long id){
        destinationService.delete(id);
    }
}
