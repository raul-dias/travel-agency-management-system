package com.raul.nymble.serviceImpl;

import com.raul.nymble.model.Destination;
import com.raul.nymble.repository.DestinationRepository;
import com.raul.nymble.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    DestinationRepository destinationRepository;

    @Override
    public void save(Destination destination) {
        destinationRepository.save(new Destination(destination.getName()));
    }

    @Override
    public void update(Destination destination) {
        destinationRepository.save(destination);
    }

    @Override
    public void delete(Long id) {
        destinationRepository.deleteById(id);
    }

    @Override
    public Destination findById(Long id) {
        return destinationRepository.findById(id).get();
    }

    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

}
