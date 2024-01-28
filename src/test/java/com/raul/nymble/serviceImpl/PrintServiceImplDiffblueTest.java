package com.raul.nymble.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.raul.nymble.DTO.ActivityDetailsDTO;
import com.raul.nymble.DTO.PassengerDetailsDTO;
import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.DTO.print.PrintAvaliableActivities;
import com.raul.nymble.DTO.print.PrintItineraryDTO;
import com.raul.nymble.DTO.print.PrintPassengerDetails;
import com.raul.nymble.DTO.print.PrintPassengerListDTO;
import com.raul.nymble.model.Activity;
import com.raul.nymble.model.Destination;
import com.raul.nymble.model.Enrollment;
import com.raul.nymble.model.Passenger;
import com.raul.nymble.repository.ActivityRepository;
import com.raul.nymble.repository.EnrollmentRepository;
import com.raul.nymble.repository.TravelPackageRepository;
import com.raul.nymble.service.DestinationService;
import com.raul.nymble.service.PassengerService;
import com.raul.nymble.service.TravelPackageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PrintServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PrintServiceImplDiffblueTest {
    @MockBean
    private ActivityRepository activityRepository;

    @MockBean
    private DestinationService destinationService;

    @MockBean
    private EnrollmentRepository enrollmentRepository;

    @MockBean
    private PassengerService passengerService;

    @Autowired
    private PrintServiceImpl printServiceImpl;

    @MockBean
    private TravelPackageRepository travelPackageRepository;

    @MockBean
    private TravelPackageService travelPackageService;

    /**
     * Method under test: {@link PrintServiceImpl#printItinerary(Long)}
     */
    @Test
    void testPrintItinerary() {
        // Arrange
        TravelPackageDTO travelPackageDTO = mock(TravelPackageDTO.class);
        when(travelPackageDTO.getName()).thenReturn("Name");
        ArrayList<Destination> destinationList = new ArrayList<>();
        when(travelPackageDTO.getItinerary()).thenReturn(destinationList);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        PrintItineraryDTO actualPrintItineraryResult = printServiceImpl.printItinerary(1L);

        // Assert
        verify(travelPackageDTO).getItinerary();
        verify(travelPackageDTO).getName();
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        assertEquals("Name", actualPrintItineraryResult.getItineraryName());
        assertEquals(destinationList, actualPrintItineraryResult.getDestinations());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printItinerary(Long)}
     */
    @Test
    void testPrintItinerary2() {
        // Arrange
        when(activityRepository.findByDestinationId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        ArrayList<Destination> destinationList = new ArrayList<>();
        destinationList.add(destination);
        TravelPackageDTO travelPackageDTO = mock(TravelPackageDTO.class);
        when(travelPackageDTO.getName()).thenReturn("Name");
        when(travelPackageDTO.getItinerary()).thenReturn(destinationList);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        PrintItineraryDTO actualPrintItineraryResult = printServiceImpl.printItinerary(1L);

        // Assert
        verify(travelPackageDTO).getItinerary();
        verify(travelPackageDTO).getName();
        verify(activityRepository).findByDestinationId(Mockito.<Long>any());
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        assertEquals("Name", actualPrintItineraryResult.getItineraryName());
        assertEquals(1, actualPrintItineraryResult.getDestinations().size());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printItinerary(Long)}
     */
    @Test
    void testPrintItinerary3() {
        // Arrange
        when(activityRepository.findByDestinationId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        Destination destination2 = new Destination();
        destination2.setId(2L);
        destination2.setName("com.raul.nymble.model.Destination");

        ArrayList<Destination> destinationList = new ArrayList<>();
        destinationList.add(destination2);
        destinationList.add(destination);
        TravelPackageDTO travelPackageDTO = mock(TravelPackageDTO.class);
        when(travelPackageDTO.getName()).thenReturn("Name");
        when(travelPackageDTO.getItinerary()).thenReturn(destinationList);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        PrintItineraryDTO actualPrintItineraryResult = printServiceImpl.printItinerary(1L);

        // Assert
        verify(travelPackageDTO).getItinerary();
        verify(travelPackageDTO).getName();
        verify(activityRepository, atLeast(1)).findByDestinationId(Mockito.<Long>any());
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        assertEquals("Name", actualPrintItineraryResult.getItineraryName());
        assertEquals(2, actualPrintItineraryResult.getDestinations().size());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printPassengerList(Long)}
     */
    @Test
    void testPrintPassengerList() {
        // Arrange
        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addPassenger(passenger);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        PrintPassengerListDTO actualPrintPassengerListResult = printServiceImpl.printPassengerList(1L);

        // Assert
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        List<PassengerDetailsDTO> passengerList = actualPrintPassengerListResult.getPassengerList();
        assertEquals(1, passengerList.size());
        PassengerDetailsDTO getResult = passengerList.get(0);
        assertEquals("Name", getResult.getName());
        assertNull(actualPrintPassengerListResult.getPackageName());
        assertEquals(0, actualPrintPassengerListResult.getPackagePassengerCapasity());
        assertEquals(1, actualPrintPassengerListResult.getBookedPassenger());
        assertEquals(10, getResult.getNumber());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printPassengerList(Long)}
     */
    @Test
    void testPrintPassengerList2() {
        // Arrange
        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        ArrayList<Passenger> passengers = new ArrayList<>();
        travelPackageDTO.setPassengers(passengers);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        PrintPassengerListDTO actualPrintPassengerListResult = printServiceImpl.printPassengerList(1L);

        // Assert
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        assertNull(actualPrintPassengerListResult.getPackageName());
        assertEquals(0, actualPrintPassengerListResult.getBookedPassenger());
        assertEquals(0, actualPrintPassengerListResult.getPackagePassengerCapasity());
        assertEquals(passengers, actualPrintPassengerListResult.getPassengerList());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printPassengerList(Long)}
     */
    @Test
    void testPrintPassengerList3() {
        // Arrange
        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);

        Passenger passenger2 = new Passenger();
        passenger2.setBalance(1L);
        passenger2.setId(2L);
        passenger2.setName("com.raul.nymble.model.Passenger");
        passenger2.setNumber(1);
        passenger2.setTier(Passenger.tierEnum.GOLD);

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addPassenger(passenger2);
        travelPackageDTO.addPassenger(passenger);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        PrintPassengerListDTO actualPrintPassengerListResult = printServiceImpl.printPassengerList(1L);

        // Assert
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        List<PassengerDetailsDTO> passengerList = actualPrintPassengerListResult.getPassengerList();
        assertEquals(2, passengerList.size());
        PassengerDetailsDTO getResult = passengerList.get(1);
        assertEquals("Name", getResult.getName());
        PassengerDetailsDTO getResult2 = passengerList.get(0);
        assertEquals("com.raul.nymble.model.Passenger", getResult2.getName());
        assertNull(actualPrintPassengerListResult.getPackageName());
        assertEquals(0, actualPrintPassengerListResult.getPackagePassengerCapasity());
        assertEquals(1, getResult2.getNumber());
        assertEquals(10, getResult.getNumber());
        assertEquals(2, actualPrintPassengerListResult.getBookedPassenger());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printPassengerDetails(Long)}
     */
    @Test
    void testPrintPassengerDetails() {
        // Arrange
        ArrayList<Enrollment> enrollmentList = new ArrayList<>();
        when(enrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(enrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        when(passengerService.findById(Mockito.<Long>any())).thenReturn(passenger);

        // Act
        PrintPassengerDetails actualPrintPassengerDetailsResult = printServiceImpl.printPassengerDetails(1L);

        // Assert
        verify(enrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(passengerService).findById(Mockito.<Long>any());
        assertEquals("Name", actualPrintPassengerDetailsResult.getName());
        assertEquals(10, actualPrintPassengerDetailsResult.getNumber());
        assertEquals(42L, actualPrintPassengerDetailsResult.getBalance().longValue());
        assertEquals(enrollmentList, actualPrintPassengerDetailsResult.getActivityDetailsList());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printPassengerDetails(Long)}
     */
    @Test
    void testPrintPassengerDetails2() {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(1);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");
        Optional<Activity> ofResult = Optional.of(activity);
        when(activityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        when(destinationService.findById(Mockito.<Long>any())).thenReturn(destination);

        Enrollment enrollment = new Enrollment();
        enrollment.setActivityId(1L);
        enrollment.setId(1L);
        enrollment.setPassengerId(1L);

        ArrayList<Enrollment> enrollmentList = new ArrayList<>();
        enrollmentList.add(enrollment);
        when(enrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(enrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        when(passengerService.findById(Mockito.<Long>any())).thenReturn(passenger);

        // Act
        PrintPassengerDetails actualPrintPassengerDetailsResult = printServiceImpl.printPassengerDetails(1L);

        // Assert
        verify(enrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(destinationService).findById(Mockito.<Long>any());
        verify(passengerService).findById(Mockito.<Long>any());
        verify(activityRepository).findById(Mockito.<Long>any());
        List<ActivityDetailsDTO> activityDetailsList = actualPrintPassengerDetailsResult.getActivityDetailsList();
        assertEquals(1, activityDetailsList.size());
        ActivityDetailsDTO getResult = activityDetailsList.get(0);
        assertEquals("Name", getResult.getDestination());
        assertEquals("Name", getResult.getName());
        assertEquals("Name", actualPrintPassengerDetailsResult.getName());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals(1.0d, getResult.getPrice().doubleValue());
        assertEquals(10, actualPrintPassengerDetailsResult.getNumber());
        assertEquals(42L, actualPrintPassengerDetailsResult.getBalance().longValue());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printPassengerDetails(Long)}
     */
    @Test
    void testPrintPassengerDetails3() {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(1);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");
        Optional<Activity> ofResult = Optional.of(activity);
        when(activityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        when(destinationService.findById(Mockito.<Long>any())).thenReturn(destination);

        Enrollment enrollment = new Enrollment();
        enrollment.setActivityId(1L);
        enrollment.setId(1L);
        enrollment.setPassengerId(1L);

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setActivityId(2L);
        enrollment2.setId(2L);
        enrollment2.setPassengerId(2L);

        ArrayList<Enrollment> enrollmentList = new ArrayList<>();
        enrollmentList.add(enrollment2);
        enrollmentList.add(enrollment);
        when(enrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(enrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        when(passengerService.findById(Mockito.<Long>any())).thenReturn(passenger);

        // Act
        PrintPassengerDetails actualPrintPassengerDetailsResult = printServiceImpl.printPassengerDetails(1L);

        // Assert
        verify(enrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(destinationService, atLeast(1)).findById(Mockito.<Long>any());
        verify(passengerService).findById(Mockito.<Long>any());
        verify(activityRepository, atLeast(1)).findById(Mockito.<Long>any());
        List<ActivityDetailsDTO> activityDetailsList = actualPrintPassengerDetailsResult.getActivityDetailsList();
        assertEquals(2, activityDetailsList.size());
        ActivityDetailsDTO getResult = activityDetailsList.get(0);
        assertEquals("Name", getResult.getDestination());
        ActivityDetailsDTO getResult2 = activityDetailsList.get(1);
        assertEquals("Name", getResult2.getDestination());
        assertEquals("Name", getResult.getName());
        assertEquals("Name", getResult2.getName());
        assertEquals("Name", actualPrintPassengerDetailsResult.getName());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("The characteristics of someone or something", getResult2.getDescription());
        assertEquals(1.0d, getResult.getPrice().doubleValue());
        assertEquals(1.0d, getResult2.getPrice().doubleValue());
        assertEquals(10, actualPrintPassengerDetailsResult.getNumber());
        assertEquals(42L, actualPrintPassengerDetailsResult.getBalance().longValue());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printAvaliableActivities()}
     */
    @Test
    void testPrintAvaliableActivities() {
        // Arrange
        when(activityRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<PrintAvaliableActivities> actualPrintAvaliableActivitiesResult = printServiceImpl.printAvaliableActivities();

        // Assert
        verify(activityRepository).findAll();
        assertTrue(actualPrintAvaliableActivitiesResult.isEmpty());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printAvaliableActivities()}
     */
    @Test
    void testPrintAvaliableActivities2() {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(1);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");

        ArrayList<Activity> activityList = new ArrayList<>();
        activityList.add(activity);
        when(activityRepository.findAll()).thenReturn(activityList);

        // Act
        List<PrintAvaliableActivities> actualPrintAvaliableActivitiesResult = printServiceImpl.printAvaliableActivities();

        // Assert
        verify(activityRepository).findAll();
        assertTrue(actualPrintAvaliableActivitiesResult.isEmpty());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printAvaliableActivities()}
     */
    @Test
    void testPrintAvaliableActivities3() {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(1);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");

        Activity activity2 = new Activity();
        activity2.setBookedCapasity(0);
        activity2.setCapasity(0);
        activity2.setCost(0L);
        activity2.setDescription("Description");
        activity2.setDestinationId(2L);
        activity2.setId(2L);
        activity2.setName("com.raul.nymble.model.Activity");

        ArrayList<Activity> activityList = new ArrayList<>();
        activityList.add(activity2);
        activityList.add(activity);
        when(activityRepository.findAll()).thenReturn(activityList);

        // Act
        List<PrintAvaliableActivities> actualPrintAvaliableActivitiesResult = printServiceImpl.printAvaliableActivities();

        // Assert
        verify(activityRepository).findAll();
        assertTrue(actualPrintAvaliableActivitiesResult.isEmpty());
    }

    /**
     * Method under test: {@link PrintServiceImpl#printAvaliableActivities()}
     */
    @Test
    void testPrintAvaliableActivities4() {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(0);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");

        Activity activity2 = new Activity();
        activity2.setBookedCapasity(0);
        activity2.setCapasity(0);
        activity2.setCost(0L);
        activity2.setDescription("Description");
        activity2.setDestinationId(2L);
        activity2.setId(2L);
        activity2.setName("com.raul.nymble.model.Activity");

        ArrayList<Activity> activityList = new ArrayList<>();
        activityList.add(activity2);
        activityList.add(activity);
        when(activityRepository.findAll()).thenReturn(activityList);

        // Act
        List<PrintAvaliableActivities> actualPrintAvaliableActivitiesResult = printServiceImpl.printAvaliableActivities();

        // Assert
        verify(activityRepository).findAll();
        assertEquals(1, actualPrintAvaliableActivitiesResult.size());
        PrintAvaliableActivities getResult = actualPrintAvaliableActivitiesResult.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals(1, getResult.getRemainingCapasity());
    }
}
