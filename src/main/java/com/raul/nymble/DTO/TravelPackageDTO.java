package com.raul.nymble.DTO;

import com.raul.nymble.model.Destination;
import com.raul.nymble.model.Passenger;
import lombok.Data;

import java.util.List;

@Data
public class TravelPackageDTO {
    private Long id;
    private String name;
    private int capacity = 0;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public void addDestination(Destination destination){
        if (itinerary == null){
            itinerary = new java.util.ArrayList<>();
            itinerary.add(destination);
        } else{
            itinerary.add(destination);
        }
    }

    public void addPassenger(Passenger passenger){
        if (passengers == null)
            passengers = new java.util.ArrayList<>();
        passengers.add(passenger);
    }
}
