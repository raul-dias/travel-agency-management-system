package com.raul.nymble.service;

import com.raul.nymble.model.Destination;

public interface DestinationService {
    void save(Destination destination);

    void update(Destination destination);

    void delete(Long id);

    Destination findById(Long id);

    Iterable<Destination> findAll();
}
