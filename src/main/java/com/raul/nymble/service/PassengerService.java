package com.raul.nymble.service;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.model.Passenger;

import java.util.List;

public interface PassengerService {
    Boolean validatePassengerForActivity(Long passengerId, Long cost, Long destinationId);

    List<Passenger> findAll();

    Passenger findById(Long id);

    void save(Passenger passenger);

    void delete(Long id);

    List<TravelPackageDTO> getTravelPackages(Long id);
}
