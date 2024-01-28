package com.raul.nymble.serviceImpl;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.model.Itinerary;
import com.raul.nymble.model.PackageEnrollment;
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

    /**
     * Function to get all Travel Packages
     * @return
     */
    @Override
    public List<TravelPackageDTO> getAllTravelPackages(){
        List<TravelPackage> travelPackageList = travelPackageRepository.findAll();
        return travelPackageList.stream().map(travelPackage -> {
            TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
            travelPackageDTO.setId(travelPackage.getId());
            travelPackageDTO.setName(travelPackage.getName());
            travelPackageDTO.setCapacity(travelPackage.getCapacity());
            addItinerary(travelPackageDTO, travelPackage.getId());
            addPassenger(travelPackageDTO, travelPackage.getId());
            return travelPackageDTO;
        }).collect(Collectors.toList());
    }

    /**
     * function to add itineraries to a travel package
     * @param travelPackageDTO
     * @param id
     */
    private void addItinerary(TravelPackageDTO travelPackageDTO, Long id) {
        List<Itinerary> itineraryList = itineraryRepository.findAll(getItineraries(id));
        itineraryList.forEach(itinerary -> {
            destinationRepository.findById(itinerary.getDestinationId()).ifPresent(travelPackageDTO::addDestination);
        });
    }

    /**
     * function to add passenger to a travel package
     * @param travelPackageDTO
     * @param id
     */
    private void addPassenger(TravelPackageDTO travelPackageDTO, Long id) {
        List<PackageEnrollment> passengerList = packageEnrollmentRepository.findAll(getPassengers(id));
        passengerList.forEach(packageEnrollment -> {
            passengerRepository.findById(packageEnrollment.getPassengerId()).ifPresent(travelPackageDTO::addPassenger);
        });
    }

    /**
     * Function to get itineraries by travel package id
     * @param id
     * @return
     */
    @Override
    public TravelPackageDTO getTravelPackageById(Long id) {
        TravelPackage travelPackage = travelPackageRepository.findById(id).get();
        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.setId(travelPackage.getId());
        travelPackageDTO.setName(travelPackage.getName());
        travelPackageDTO.setCapacity(travelPackage.getCapacity());
        addItinerary(travelPackageDTO, travelPackage.getId());
        addPassenger(travelPackageDTO, travelPackage.getId());
        return travelPackageDTO;
    }

    /**
     * Function to add a new Travel Package
     * TODO: add validation so only know destinations can be added
     * @param travelPackageDTO
     */
    @Override
    public void addTravelPackage(TravelPackageDTO travelPackageDTO) {
        TravelPackage savedTravelPackage = travelPackageRepository.save(new TravelPackage(travelPackageDTO.getName(), travelPackageDTO.getCapacity()));
        travelPackageDTO.getItinerary().forEach(destination -> itineraryRepository.save(new Itinerary(savedTravelPackage.getId(), destination.getId())));
        travelPackageDTO.getPassengers().forEach(passenger -> packageEnrollmentRepository.save(new PackageEnrollment(passenger.getId(), savedTravelPackage.getId())));
        System.out.println("Travel Package added successfully");
    }

    /**
     * Function to delete a Travel Package by id
     * @param id
     */
    @Override
    public void deleteTravelPackage(Long id) {
        travelPackageRepository.deleteById(id);
        System.out.println("Travel Package deleted successfully");
        itineraryRepository.deleteAll(itineraryRepository.findAll(getItineraries(id)));
        packageEnrollmentRepository.deleteAll(packageEnrollmentRepository.findAll(getPassengers(id)));
        System.out.println("Itinerary and Enrollment deleted successfully");
    }


    /**
     * Function to update a Travel Package by id
     * @param travelPackageDTO
     * @param id
     */
    @Override
    public void updateTravelPackage(TravelPackageDTO travelPackageDTO, Long id) {
        TravelPackage travelPackage = travelPackageRepository.findById(id).get();
        travelPackage.setName(travelPackageDTO.getName());
        travelPackage.setCapacity(travelPackageDTO.getCapacity());
        travelPackageRepository.save(travelPackage);
        System.out.println("Travel Package updated successfully");
        itineraryRepository.deleteAll(itineraryRepository.findAll(getItineraries(id)));
        packageEnrollmentRepository.deleteAll(packageEnrollmentRepository.findAll(getPassengers(id)));
        System.out.println("Itinerary and Enrollment deleted successfully");
        travelPackageDTO.getItinerary().forEach(destination -> itineraryRepository.save(new Itinerary(id, destination.getId())));
        travelPackageDTO.getPassengers().forEach(passenger -> packageEnrollmentRepository.save(new PackageEnrollment(passenger.getId(), id)));
        System.out.println("Itinerary and Enrollment added successfully");
        System.out.println("Travel Package updated successfully");
    }

    @Override
    public void addPassengerToPackage(Long id, Long passengerId) {
        packageEnrollmentRepository.save(new PackageEnrollment(passengerId, id));
    }

    /**
     * Function to get itineraries by travel package id
     * we pass the Travel Package id here
     * @param id
     * @return
     */
    static Specification<Itinerary> getItineraries(Long id) {
        return (itinerary, cq, cb) -> cb.equal(itinerary.get("travelPackageId"), id);
    }

    /**
     * Function to get Passengers of a specific travel package
     * we pass the Travel Package id here
     * @param id
     * @return
     */
    static Specification<PackageEnrollment> getPassengers(Long id) {
        return (passengerEnrollment, cq, cb) -> cb.equal(passengerEnrollment.get("travelPackageId"), id);
    }
}
