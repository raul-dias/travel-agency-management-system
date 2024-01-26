package com.raul.nymble.serviceImpl;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.model.Itinerary;
import com.raul.nymble.model.PackageEnrollment;
import com.raul.nymble.model.Passenger;
import com.raul.nymble.model.TravelPackage;
import com.raul.nymble.repository.*;
import com.raul.nymble.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {
    @Autowired
    TravelPackageRepository travelPackageRepository;
    @Autowired
    ItineraryRepository itineraryRepository;
    @Autowired
    DestinationRepository destinationRepository;
    @Autowired
    PackageEnrollmentRepository packageEnrollmentRepository;
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public List<TravelPackageDTO> getAllTravelPackages(){
        List<TravelPackage> travelPackageList = travelPackageRepository.findAll();
        return travelPackageList.stream().map(travelPackage -> {
            TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
            travelPackageDTO.setId(travelPackage.getId());
            travelPackageDTO.setName(travelPackage.getName());
            travelPackageDTO.setCapacity(travelPackage.getCapacity());

            List<Itinerary> itineraryList = itineraryRepository.findAll(getItineraries(travelPackage.getId()));
            itineraryList.forEach(itinerary -> {
                destinationRepository.findById(itinerary.getDestinationId()).ifPresent(travelPackageDTO::addDestination);
            });

            List<PackageEnrollment> passengerList = packageEnrollmentRepository.findAll(getPassengers(travelPackage.getId()));
            passengerList.forEach(packageEnrollment -> {
                passengerRepository.findById(packageEnrollment.getCustomerId()).ifPresent(travelPackageDTO::addPassenger);
            });
            return travelPackageDTO;
        }).collect(Collectors.toList());
    }
    static Specification<Itinerary> getItineraries(Long id) {
        return (itinerary, cq, cb) -> cb.equal(itinerary.get("travelPackageId"), id);
    }

    static Specification<PackageEnrollment> getPassengers(Long id) {
        return (passengerEnrollment, cq, cb) -> cb.equal(passengerEnrollment.get("travelPackageId"), id);
    }
}