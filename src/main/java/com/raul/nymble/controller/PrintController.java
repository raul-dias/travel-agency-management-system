package com.raul.nymble.controller;

import com.raul.nymble.DTO.print.PrintItineraryDTO;
import com.raul.nymble.DTO.print.PrintPassengerListDTO;
import com.raul.nymble.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintController {

    @Autowired
    PrintService printService;
    @GetMapping("/print/itinerary/{id}")
    public PrintItineraryDTO printItinerary(@PathVariable Long id) {
        return printService.printItinerary(id);
    }

    @GetMapping("/print/itinerary/{id}/passengerList")
    public PrintPassengerListDTO printPassengerList(@PathVariable Long id) {
        return printService.printPassengerList(id);
    }

    @GetMapping("/print/passenger/{id}")
    public String printPassenger(@PathVariable Long id) {

        return "test";
    }

    @GetMapping("/print/avaliableActivities")
    public String printAvaliableActivities() {
        return "test";
    }
}
