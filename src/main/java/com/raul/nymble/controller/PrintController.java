package com.raul.nymble.controller;

import com.raul.nymble.DTO.print.PrintAvaliableActivities;
import com.raul.nymble.DTO.print.PrintItineraryDTO;
import com.raul.nymble.DTO.print.PrintPassengerDetails;
import com.raul.nymble.DTO.print.PrintPassengerListDTO;
import com.raul.nymble.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public PrintPassengerDetails printPassenger(@PathVariable Long id) {
        return printService.printPassengerDetails(id);
    }

    @GetMapping("/print/avaliableActivities")
    public List<PrintAvaliableActivities> printAvaliableActivities() {
        return printService.printAvaliableActivities();
    }
}
