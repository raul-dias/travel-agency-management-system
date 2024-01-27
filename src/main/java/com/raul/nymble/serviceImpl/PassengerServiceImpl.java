package com.raul.nymble.serviceImpl;

import com.raul.nymble.model.Passenger;
import com.raul.nymble.repository.EnrollmentRepository;
import com.raul.nymble.repository.PackageEnrollmentRepository;
import com.raul.nymble.repository.PassengerRepository;
import com.raul.nymble.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PackageEnrollmentRepository packageEnrollmentRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }
    @Override
    public Passenger findById(Long id) {
        return passengerRepository.findById(id).get();
    }
    @Override
    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }
    @Override
    public void delete(Long id) {
        passengerRepository.deleteById(id);
        packageEnrollmentRepository.deleteByPassengerId(id);
        enrollmentRepository.deleteByPassengerId(id);
    }
}
