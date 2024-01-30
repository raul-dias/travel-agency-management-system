package com.raul.nymble.serviceImpl;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.model.PackageEnrollment;
import com.raul.nymble.model.Passenger;
import com.raul.nymble.repository.EnrollmentRepository;
import com.raul.nymble.repository.PackageEnrollmentRepository;
import com.raul.nymble.repository.PassengerRepository;
import com.raul.nymble.service.PassengerService;
import com.raul.nymble.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PackageEnrollmentRepository packageEnrollmentRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    TravelPackageService travelPackageService;

    @Override
    public Boolean validatePassengerForActivity(Long passengerId, Long cost, Long destinationId) {
        Passenger passenger = passengerRepository.findById(passengerId).get();
        AtomicReference<Boolean> destinationValid = new AtomicReference<>(false);
        List<TravelPackageDTO> travelPackages = getTravelPackages(passengerId);
        travelPackages.forEach(travelPackageDTO -> travelPackageDTO.getItinerary().forEach(itinerary -> {
            if (Objects.equals(itinerary.getId(), destinationId)) {
                destinationValid.set(true);
            }
        }));
        if (destinationValid.get() && (passenger.getBalance() >= cost)) {
            passenger.setBalance(passenger.getBalance() - (cost*PassengerService.getDiscount(passenger)));
            passengerRepository.save(passenger);
            return true;
        }
        else
            return false;
    }

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

    @Override
    public List<TravelPackageDTO> getTravelPackages(Long id) {
        Passenger passenger = findById(id);
        List<TravelPackageDTO> travelPackages = new ArrayList<>();
        List <PackageEnrollment> packageEnrollments = packageEnrollmentRepository.findByPassengerId(passenger.getId());
        packageEnrollments.forEach(packageEnrollment -> travelPackages.add(travelPackageService.getTravelPackageById(packageEnrollment.getTravelPackageId())));
        return travelPackages;
    }
}
