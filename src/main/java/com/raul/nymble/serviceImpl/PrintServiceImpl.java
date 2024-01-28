package com.raul.nymble.serviceImpl;

import com.raul.nymble.DTO.ActivityDetailsDTO;
import com.raul.nymble.DTO.DestinationDetailsDTO;
import com.raul.nymble.DTO.PassengerDetailsDTO;
import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.DTO.print.PrintAvaliableActivities;
import com.raul.nymble.DTO.print.PrintItineraryDTO;
import com.raul.nymble.DTO.print.PrintPassengerDetails;
import com.raul.nymble.DTO.print.PrintPassengerListDTO;
import com.raul.nymble.model.Activity;
import com.raul.nymble.model.Enrollment;
import com.raul.nymble.model.Passenger;
import com.raul.nymble.repository.ActivityRepository;
import com.raul.nymble.repository.EnrollmentRepository;
import com.raul.nymble.repository.TravelPackageRepository;
import com.raul.nymble.service.DestinationService;
import com.raul.nymble.service.PassengerService;
import com.raul.nymble.service.PrintService;
import com.raul.nymble.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PrintServiceImpl implements PrintService {

    @Autowired
    TravelPackageRepository travelPackageRepository;
    @Autowired
    ActivityRepository activiryRepository;
    @Autowired
    TravelPackageService travelPackageService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    DestinationService destinationService;
    @Override
    public PrintItineraryDTO printItinerary(Long id) {
        TravelPackageDTO travelPackage = travelPackageService.getTravelPackageById(id);
        PrintItineraryDTO printItineraryDTO = new PrintItineraryDTO();
        List<DestinationDetailsDTO> destinations= new ArrayList<>();
        travelPackage.getItinerary().forEach(destination -> {
            destinations.add(new DestinationDetailsDTO(destination.getName(), activiryRepository.findByDestinationId(destination.getId())));
        });
        printItineraryDTO.setDestinations(destinations);
        printItineraryDTO.setItineraryName(travelPackage.getName());
        return printItineraryDTO;
    }

    @Override
    public PrintPassengerListDTO printPassengerList(Long id){
        PrintPassengerListDTO printPassengerListDTO = new PrintPassengerListDTO();
        TravelPackageDTO travelPackage = travelPackageService.getTravelPackageById(id);
        printPassengerListDTO.setPackageName(travelPackage.getName());
        printPassengerListDTO.setPackagePassengerCapasity(travelPackage.getCapacity());
        printPassengerListDTO.setBookedPassenger(travelPackage.getPassengers().size());
        List<PassengerDetailsDTO> passengerList = new ArrayList<>();
        travelPackage.getPassengers().forEach(passenger -> passengerList.add(new PassengerDetailsDTO(passenger)));
        printPassengerListDTO.setPassengerList(passengerList);
        return printPassengerListDTO;
    }

    @Override
    public PrintPassengerDetails printPassengerDetails(Long id){
        Passenger passenger = passengerService.findById(id);
        Double discount = PassengerService.getDiscount(passenger);
        PrintPassengerDetails printPassengerDetails = new PrintPassengerDetails(passenger);
        List<Enrollment> enrollment = enrollmentRepository.findByPassengerId(id);
        enrollment.forEach(enrollment1 -> {
            Activity activity = activiryRepository.findById(enrollment1.getActivityId()).get();
            printPassengerDetails.getActivityDetailsList().add(
                    new ActivityDetailsDTO(
                            activity,
                            destinationService.findById(activity.getDestinationId()).getName(),
                            PassengerService.getDiscount(passenger)
                    )
                );
        });
        return printPassengerDetails;
    }

    @Override
    public List<PrintAvaliableActivities> printAvaliableActivities(){
        List<PrintAvaliableActivities> avaliableActivities= new ArrayList<>();
        activiryRepository.findAll().stream().filter(
                activity -> activity.getCapasity()>activity.getBookedCapasity()).forEach(
                        activity -> avaliableActivities.add(new PrintAvaliableActivities(activity)));
        return avaliableActivities;
    }
}
