package com.raul.nymble.service;

import com.raul.nymble.model.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> findAll();

    Passenger findById(Long id);

    void save(Passenger passenger);

    void delete(Long id);
}
