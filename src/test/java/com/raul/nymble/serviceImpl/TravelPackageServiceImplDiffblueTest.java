package com.raul.nymble.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.model.Destination;
import com.raul.nymble.model.Itinerary;
import com.raul.nymble.model.PackageEnrollment;
import com.raul.nymble.model.Passenger;
import com.raul.nymble.model.TravelPackage;
import com.raul.nymble.repository.DestinationRepository;
import com.raul.nymble.repository.ItineraryRepository;
import com.raul.nymble.repository.PackageEnrollmentRepository;
import com.raul.nymble.repository.PassengerRepository;
import com.raul.nymble.repository.TravelPackageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TravelPackageServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TravelPackageServiceImplDiffblueTest {
    @MockBean
    private DestinationRepository destinationRepository;

    @MockBean
    private ItineraryRepository itineraryRepository;

    @MockBean
    private PackageEnrollmentRepository packageEnrollmentRepository;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private TravelPackageServiceImpl travelPackageServiceImpl;

    /**
     * Method under test: {@link TravelPackageServiceImpl#getAllTravelPackages()}
     */
    @Test
    void testGetAllTravelPackages() {
        // Arrange
        when(travelPackageRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<TravelPackageDTO> actualAllTravelPackages = travelPackageServiceImpl.getAllTravelPackages();

        // Assert
        verify(travelPackageRepository).findAll();
        assertTrue(actualAllTravelPackages.isEmpty());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getAllTravelPackages()}
     */
    @Test
    void testGetAllTravelPackages2() {
        // Arrange
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(new ArrayList<>());
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");

        ArrayList<TravelPackage> travelPackageList = new ArrayList<>();
        travelPackageList.add(travelPackage);
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        // Act
        List<TravelPackageDTO> actualAllTravelPackages = travelPackageServiceImpl.getAllTravelPackages();

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(travelPackageRepository).findAll();
        assertEquals(1, actualAllTravelPackages.size());
        TravelPackageDTO getResult = actualAllTravelPackages.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(3, getResult.getCapacity());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getAllTravelPackages()}
     */
    @Test
    void testGetAllTravelPackages3() {
        // Arrange
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(new ArrayList<>());
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenThrow(new IllegalArgumentException("foo"));

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");

        ArrayList<TravelPackage> travelPackageList = new ArrayList<>();
        travelPackageList.add(travelPackage);
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> travelPackageServiceImpl.getAllTravelPackages());
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(travelPackageRepository).findAll();
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getAllTravelPackages()}
     */
    @Test
    void testGetAllTravelPackages4() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");

        ArrayList<TravelPackage> travelPackageList = new ArrayList<>();
        travelPackageList.add(travelPackage);
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        // Act
        List<TravelPackageDTO> actualAllTravelPackages = travelPackageServiceImpl.getAllTravelPackages();

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(destinationRepository).findById(Mockito.<Long>any());
        verify(travelPackageRepository).findAll();
        assertEquals(1, actualAllTravelPackages.size());
        TravelPackageDTO getResult = actualAllTravelPackages.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals(1, getResult.getItinerary().size());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(3, getResult.getCapacity());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getAllTravelPackages()}
     */
    @Test
    void testGetAllTravelPackages5() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        Itinerary itinerary2 = new Itinerary();
        itinerary2.setDestinationId(2L);
        itinerary2.setId(2L);
        itinerary2.setTravelPackageId(2L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary2);
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");

        ArrayList<TravelPackage> travelPackageList = new ArrayList<>();
        travelPackageList.add(travelPackage);
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        // Act
        List<TravelPackageDTO> actualAllTravelPackages = travelPackageServiceImpl.getAllTravelPackages();

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(destinationRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(travelPackageRepository).findAll();
        assertEquals(1, actualAllTravelPackages.size());
        TravelPackageDTO getResult = actualAllTravelPackages.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(2, getResult.getItinerary().size());
        assertEquals(3, getResult.getCapacity());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getAllTravelPackages()}
     */
    @Test
    void testGetAllTravelPackages6() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");

        ArrayList<TravelPackage> travelPackageList = new ArrayList<>();
        travelPackageList.add(travelPackage);
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        // Act
        List<TravelPackageDTO> actualAllTravelPackages = travelPackageServiceImpl.getAllTravelPackages();

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(destinationRepository).findById(Mockito.<Long>any());
        verify(passengerRepository).findById(Mockito.<Long>any());
        verify(travelPackageRepository).findAll();
        assertEquals(1, actualAllTravelPackages.size());
        TravelPackageDTO getResult = actualAllTravelPackages.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals(1, getResult.getItinerary().size());
        assertEquals(1, getResult.getPassengers().size());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(3, getResult.getCapacity());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getAllTravelPackages()}
     */
    @Test
    void testGetAllTravelPackages7() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        PackageEnrollment packageEnrollment2 = new PackageEnrollment();
        packageEnrollment2.setId(2L);
        packageEnrollment2.setPassengerId(2L);
        packageEnrollment2.setTravelPackageId(2L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment2);
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");

        ArrayList<TravelPackage> travelPackageList = new ArrayList<>();
        travelPackageList.add(travelPackage);
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        // Act
        List<TravelPackageDTO> actualAllTravelPackages = travelPackageServiceImpl.getAllTravelPackages();

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(destinationRepository).findById(Mockito.<Long>any());
        verify(passengerRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(travelPackageRepository).findAll();
        assertEquals(1, actualAllTravelPackages.size());
        TravelPackageDTO getResult = actualAllTravelPackages.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals(1, getResult.getItinerary().size());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(2, getResult.getPassengers().size());
        assertEquals(3, getResult.getCapacity());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getAllTravelPackages()}
     */
    @Test
    void testGetAllTravelPackages8() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");

        TravelPackage travelPackage2 = new TravelPackage();
        travelPackage2.setCapacity(1);
        travelPackage2.setId(2L);
        travelPackage2.setName("com.raul.nymble.model.TravelPackage");

        ArrayList<TravelPackage> travelPackageList = new ArrayList<>();
        travelPackageList.add(travelPackage2);
        travelPackageList.add(travelPackage);
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        // Act
        List<TravelPackageDTO> actualAllTravelPackages = travelPackageServiceImpl.getAllTravelPackages();

        // Assert
        verify(itineraryRepository, atLeast(1)).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository, atLeast(1)).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(destinationRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(passengerRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(travelPackageRepository).findAll();
        assertEquals(2, actualAllTravelPackages.size());
        TravelPackageDTO getResult = actualAllTravelPackages.get(1);
        assertEquals("Name", getResult.getName());
        TravelPackageDTO getResult2 = actualAllTravelPackages.get(0);
        assertEquals("com.raul.nymble.model.TravelPackage", getResult2.getName());
        assertEquals(1, getResult2.getCapacity());
        List<Destination> itinerary2 = getResult2.getItinerary();
        assertEquals(1, itinerary2.size());
        List<Destination> itinerary3 = getResult.getItinerary();
        assertEquals(1, itinerary3.size());
        List<Passenger> passengers = getResult2.getPassengers();
        assertEquals(1, passengers.size());
        List<Passenger> passengers2 = getResult.getPassengers();
        assertEquals(1, passengers2.size());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(2L, getResult2.getId().longValue());
        assertEquals(3, getResult.getCapacity());
        assertEquals(itinerary2, itinerary3);
        assertEquals(passengers, passengers2);
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#getTravelPackageById(Long)}
     */
    @Test
    void testGetTravelPackageById() {
        // Arrange
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(new ArrayList<>());
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        TravelPackageDTO actualTravelPackageById = travelPackageServiceImpl.getTravelPackageById(1L);

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        assertEquals("Name", actualTravelPackageById.getName());
        assertEquals(1L, actualTravelPackageById.getId().longValue());
        assertEquals(3, actualTravelPackageById.getCapacity());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#getTravelPackageById(Long)}
     */
    @Test
    void testGetTravelPackageById2() {
        // Arrange
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(new ArrayList<>());
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenThrow(new IllegalArgumentException("foo"));

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> travelPackageServiceImpl.getTravelPackageById(1L));
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#getTravelPackageById(Long)}
     */
    @Test
    void testGetTravelPackageById3() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult2 = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        TravelPackageDTO actualTravelPackageById = travelPackageServiceImpl.getTravelPackageById(1L);

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(destinationRepository).findById(Mockito.<Long>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        assertEquals("Name", actualTravelPackageById.getName());
        assertEquals(1, actualTravelPackageById.getItinerary().size());
        assertEquals(1L, actualTravelPackageById.getId().longValue());
        assertEquals(3, actualTravelPackageById.getCapacity());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#getTravelPackageById(Long)}
     */
    @Test
    void testGetTravelPackageById4() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        Itinerary itinerary2 = new Itinerary();
        itinerary2.setDestinationId(2L);
        itinerary2.setId(2L);
        itinerary2.setTravelPackageId(2L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary2);
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult2 = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        TravelPackageDTO actualTravelPackageById = travelPackageServiceImpl.getTravelPackageById(1L);

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        verify(destinationRepository, atLeast(1)).findById(Mockito.<Long>any());
        assertEquals("Name", actualTravelPackageById.getName());
        assertEquals(1L, actualTravelPackageById.getId().longValue());
        assertEquals(2, actualTravelPackageById.getItinerary().size());
        assertEquals(3, actualTravelPackageById.getCapacity());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#getTravelPackageById(Long)}
     */
    @Test
    void testGetTravelPackageById5() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult3 = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult3);

        // Act
        TravelPackageDTO actualTravelPackageById = travelPackageServiceImpl.getTravelPackageById(1L);

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(destinationRepository).findById(Mockito.<Long>any());
        verify(passengerRepository).findById(Mockito.<Long>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        assertEquals("Name", actualTravelPackageById.getName());
        assertEquals(1, actualTravelPackageById.getItinerary().size());
        assertEquals(1, actualTravelPackageById.getPassengers().size());
        assertEquals(1L, actualTravelPackageById.getId().longValue());
        assertEquals(3, actualTravelPackageById.getCapacity());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#getTravelPackageById(Long)}
     */
    @Test
    void testGetTravelPackageById6() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);

        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        itineraryList.add(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        PackageEnrollment packageEnrollment2 = new PackageEnrollment();
        packageEnrollment2.setId(2L);
        packageEnrollment2.setPassengerId(2L);
        packageEnrollment2.setTravelPackageId(2L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment2);
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult3 = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult3);

        // Act
        TravelPackageDTO actualTravelPackageById = travelPackageServiceImpl.getTravelPackageById(1L);

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(destinationRepository).findById(Mockito.<Long>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        verify(passengerRepository, atLeast(1)).findById(Mockito.<Long>any());
        assertEquals("Name", actualTravelPackageById.getName());
        assertEquals(1, actualTravelPackageById.getItinerary().size());
        assertEquals(1L, actualTravelPackageById.getId().longValue());
        assertEquals(2, actualTravelPackageById.getPassengers().size());
        assertEquals(3, actualTravelPackageById.getCapacity());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#addTravelPackage(TravelPackageDTO)}
     */
    @Test
    void testAddTravelPackage() {
        // Arrange
        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);
        when(itineraryRepository.save(Mockito.<Itinerary>any())).thenReturn(itinerary);

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);
        when(packageEnrollmentRepository.save(Mockito.<PackageEnrollment>any())).thenReturn(packageEnrollment);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        Passenger passenger = new Passenger();
        passenger.setBalance(1L);
        passenger.setId(2L);
        passenger.setName("com.raul.nymble.model.Passenger");
        passenger.setNumber(1);
        passenger.setTier(Passenger.tierEnum.GOLD);

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addPassenger(passenger);
        travelPackageDTO.addDestination(destination);

        // Act
        travelPackageServiceImpl.addTravelPackage(travelPackageDTO);

        // Assert
        verify(itineraryRepository).save(Mockito.<Itinerary>any());
        verify(packageEnrollmentRepository).save(Mockito.<PackageEnrollment>any());
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#addTravelPackage(TravelPackageDTO)}
     */
    @Test
    void testAddTravelPackage2() {
        // Arrange
        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);
        when(itineraryRepository.save(Mockito.<Itinerary>any())).thenReturn(itinerary);

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);
        when(packageEnrollmentRepository.save(Mockito.<PackageEnrollment>any())).thenReturn(packageEnrollment);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        Passenger passenger = new Passenger();
        passenger.setBalance(1L);
        passenger.setId(2L);
        passenger.setName("com.raul.nymble.model.Passenger");
        passenger.setNumber(1);
        passenger.setTier(Passenger.tierEnum.GOLD);

        Passenger passenger2 = new Passenger();
        passenger2.setBalance(0L);
        passenger2.setId(3L);
        passenger2.setName("com.raul.nymble.model.Passenger");
        passenger2.setNumber(0);
        passenger2.setTier(Passenger.tierEnum.PREMIUM);

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addPassenger(passenger2);
        travelPackageDTO.addPassenger(passenger);
        travelPackageDTO.addDestination(destination);

        // Act
        travelPackageServiceImpl.addTravelPackage(travelPackageDTO);

        // Assert
        verify(itineraryRepository).save(Mockito.<Itinerary>any());
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
        verify(packageEnrollmentRepository, atLeast(1)).save(Mockito.<PackageEnrollment>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#addTravelPackage(TravelPackageDTO)}
     */
    @Test
    void testAddTravelPackage3() {
        // Arrange
        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);
        when(itineraryRepository.save(Mockito.<Itinerary>any())).thenReturn(itinerary);

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);
        when(packageEnrollmentRepository.save(Mockito.<PackageEnrollment>any())).thenReturn(packageEnrollment);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        Passenger passenger = new Passenger();
        passenger.setBalance(1L);
        passenger.setId(2L);
        passenger.setName("com.raul.nymble.model.Passenger");
        passenger.setNumber(1);
        passenger.setTier(Passenger.tierEnum.GOLD);
        TravelPackageDTO travelPackageDTO = mock(TravelPackageDTO.class);
        when(travelPackageDTO.getCapacity()).thenReturn(3);
        when(travelPackageDTO.getName()).thenReturn("Name");
        when(travelPackageDTO.getItinerary()).thenReturn(new ArrayList<>());
        when(travelPackageDTO.getPassengers()).thenReturn(new ArrayList<>());
        doNothing().when(travelPackageDTO).addDestination(Mockito.<Destination>any());
        doNothing().when(travelPackageDTO).addPassenger(Mockito.<Passenger>any());
        travelPackageDTO.addPassenger(passenger);
        travelPackageDTO.addDestination(destination);

        // Act
        travelPackageServiceImpl.addTravelPackage(travelPackageDTO);

        // Assert
        verify(travelPackageDTO).addDestination(Mockito.<Destination>any());
        verify(travelPackageDTO).addPassenger(Mockito.<Passenger>any());
        verify(travelPackageDTO).getCapacity();
        verify(travelPackageDTO).getItinerary();
        verify(travelPackageDTO).getName();
        verify(travelPackageDTO).getPassengers();
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#deleteTravelPackage(Long)}
     */
    @Test
    void testDeleteTravelPackage() {
        // Arrange
        ArrayList<Itinerary> itineraryList = new ArrayList<>();
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(itineraryList);
        doNothing().when(itineraryRepository).deleteAll(Mockito.<Iterable<Itinerary>>any());
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());
        doNothing().when(packageEnrollmentRepository).deleteAll(Mockito.<Iterable<PackageEnrollment>>any());
        doNothing().when(travelPackageRepository).deleteById(Mockito.<Long>any());

        // Act
        travelPackageServiceImpl.deleteTravelPackage(1L);

        // Assert that nothing has changed
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(itineraryRepository).deleteAll(Mockito.<Iterable<Itinerary>>any());
        verify(packageEnrollmentRepository).deleteAll(Mockito.<Iterable<PackageEnrollment>>any());
        verify(travelPackageRepository).deleteById(Mockito.<Long>any());
        assertEquals(itineraryList, travelPackageServiceImpl.getAllTravelPackages());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#deleteTravelPackage(Long)}
     */
    @Test
    void testDeleteTravelPackage2() {
        // Arrange
        doThrow(new IllegalArgumentException("Travel Package deleted successfully")).when(travelPackageRepository)
                .deleteById(Mockito.<Long>any());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> travelPackageServiceImpl.deleteTravelPackage(1L));
        verify(travelPackageRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#updateTravelPackage(TravelPackageDTO, Long)}
     */
    @Test
    void testUpdateTravelPackage() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        when(travelPackageRepository.save(Mockito.<TravelPackage>any()))
                .thenThrow(new IllegalArgumentException("Travel Package updated successfully"));
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> travelPackageServiceImpl.updateTravelPackage(new TravelPackageDTO(), 1L));
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#updateTravelPackage(TravelPackageDTO, Long)}
     */
    @Test
    void testUpdateTravelPackage2() {
        // Arrange
        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);
        when(itineraryRepository.save(Mockito.<Itinerary>any())).thenReturn(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(new ArrayList<>());
        doNothing().when(itineraryRepository).deleteAll(Mockito.<Iterable<Itinerary>>any());

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);
        when(packageEnrollmentRepository.save(Mockito.<PackageEnrollment>any())).thenReturn(packageEnrollment);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());
        doNothing().when(packageEnrollmentRepository).deleteAll(Mockito.<Iterable<PackageEnrollment>>any());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);

        TravelPackage travelPackage2 = new TravelPackage();
        travelPackage2.setCapacity(3);
        travelPackage2.setId(1L);
        travelPackage2.setName("Name");
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage2);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Travel Package updated successfully");

        Passenger passenger = new Passenger();
        passenger.setBalance(1L);
        passenger.setId(2L);
        passenger.setName("Itinerary and Enrollment deleted successfully");
        passenger.setNumber(1);
        passenger.setTier(Passenger.tierEnum.GOLD);

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addPassenger(passenger);
        travelPackageDTO.addDestination(destination);

        // Act
        travelPackageServiceImpl.updateTravelPackage(travelPackageDTO, 1L);

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(itineraryRepository).deleteAll(Mockito.<Iterable<Itinerary>>any());
        verify(packageEnrollmentRepository).deleteAll(Mockito.<Iterable<PackageEnrollment>>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        verify(itineraryRepository).save(Mockito.<Itinerary>any());
        verify(packageEnrollmentRepository).save(Mockito.<PackageEnrollment>any());
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#updateTravelPackage(TravelPackageDTO, Long)}
     */
    @Test
    void testUpdateTravelPackage3() {
        // Arrange
        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);
        when(itineraryRepository.save(Mockito.<Itinerary>any())).thenReturn(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(new ArrayList<>());
        doNothing().when(itineraryRepository).deleteAll(Mockito.<Iterable<Itinerary>>any());

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);
        when(packageEnrollmentRepository.save(Mockito.<PackageEnrollment>any())).thenReturn(packageEnrollment);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());
        doNothing().when(packageEnrollmentRepository).deleteAll(Mockito.<Iterable<PackageEnrollment>>any());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);

        TravelPackage travelPackage2 = new TravelPackage();
        travelPackage2.setCapacity(3);
        travelPackage2.setId(1L);
        travelPackage2.setName("Name");
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage2);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Travel Package updated successfully");

        Passenger passenger = new Passenger();
        passenger.setBalance(1L);
        passenger.setId(2L);
        passenger.setName("Itinerary and Enrollment deleted successfully");
        passenger.setNumber(1);
        passenger.setTier(Passenger.tierEnum.GOLD);

        Passenger passenger2 = new Passenger();
        passenger2.setBalance(0L);
        passenger2.setId(3L);
        passenger2.setName("Itinerary and Enrollment added successfully");
        passenger2.setNumber(0);
        passenger2.setTier(Passenger.tierEnum.PREMIUM);

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addPassenger(passenger2);
        travelPackageDTO.addPassenger(passenger);
        travelPackageDTO.addDestination(destination);

        // Act
        travelPackageServiceImpl.updateTravelPackage(travelPackageDTO, 1L);

        // Assert
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(itineraryRepository).deleteAll(Mockito.<Iterable<Itinerary>>any());
        verify(packageEnrollmentRepository).deleteAll(Mockito.<Iterable<PackageEnrollment>>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        verify(itineraryRepository).save(Mockito.<Itinerary>any());
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
        verify(packageEnrollmentRepository, atLeast(1)).save(Mockito.<PackageEnrollment>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#updateTravelPackage(TravelPackageDTO, Long)}
     */
    @Test
    void testUpdateTravelPackage4() {
        // Arrange
        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationId(1L);
        itinerary.setId(1L);
        itinerary.setTravelPackageId(1L);
        when(itineraryRepository.save(Mockito.<Itinerary>any())).thenReturn(itinerary);
        when(itineraryRepository.findAll(Mockito.<Specification<Itinerary>>any())).thenReturn(new ArrayList<>());
        doNothing().when(itineraryRepository).deleteAll(Mockito.<Iterable<Itinerary>>any());

        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);
        when(packageEnrollmentRepository.save(Mockito.<PackageEnrollment>any())).thenReturn(packageEnrollment);
        when(packageEnrollmentRepository.findAll(Mockito.<Specification<PackageEnrollment>>any()))
                .thenReturn(new ArrayList<>());
        doNothing().when(packageEnrollmentRepository).deleteAll(Mockito.<Iterable<PackageEnrollment>>any());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setCapacity(3);
        travelPackage.setId(1L);
        travelPackage.setName("Name");
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);

        TravelPackage travelPackage2 = new TravelPackage();
        travelPackage2.setCapacity(3);
        travelPackage2.setId(1L);
        travelPackage2.setName("Name");
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage2);
        when(travelPackageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Travel Package updated successfully");

        Passenger passenger = new Passenger();
        passenger.setBalance(1L);
        passenger.setId(2L);
        passenger.setName("Itinerary and Enrollment deleted successfully");
        passenger.setNumber(1);
        passenger.setTier(Passenger.tierEnum.GOLD);
        TravelPackageDTO travelPackageDTO = mock(TravelPackageDTO.class);
        when(travelPackageDTO.getCapacity()).thenReturn(3);
        when(travelPackageDTO.getName()).thenReturn("Name");
        when(travelPackageDTO.getItinerary()).thenReturn(new ArrayList<>());
        when(travelPackageDTO.getPassengers()).thenReturn(new ArrayList<>());
        doNothing().when(travelPackageDTO).addDestination(Mockito.<Destination>any());
        doNothing().when(travelPackageDTO).addPassenger(Mockito.<Passenger>any());
        travelPackageDTO.addPassenger(passenger);
        travelPackageDTO.addDestination(destination);

        // Act
        travelPackageServiceImpl.updateTravelPackage(travelPackageDTO, 1L);

        // Assert
        verify(travelPackageDTO).addDestination(Mockito.<Destination>any());
        verify(travelPackageDTO).addPassenger(Mockito.<Passenger>any());
        verify(travelPackageDTO).getCapacity();
        verify(travelPackageDTO).getItinerary();
        verify(travelPackageDTO).getName();
        verify(travelPackageDTO).getPassengers();
        verify(itineraryRepository).findAll(Mockito.<Specification<Itinerary>>any());
        verify(packageEnrollmentRepository).findAll(Mockito.<Specification<PackageEnrollment>>any());
        verify(itineraryRepository).deleteAll(Mockito.<Iterable<Itinerary>>any());
        verify(packageEnrollmentRepository).deleteAll(Mockito.<Iterable<PackageEnrollment>>any());
        verify(travelPackageRepository).findById(Mockito.<Long>any());
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#addPassengerToPackage(Long, Long)}
     */
    @Test
    void testAddPassengerToPackage() {
        // Arrange
        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);
        when(packageEnrollmentRepository.save(Mockito.<PackageEnrollment>any())).thenReturn(packageEnrollment);

        // Act
        travelPackageServiceImpl.addPassengerToPackage(1L, 1L);

        // Assert
        verify(packageEnrollmentRepository).save(Mockito.<PackageEnrollment>any());
    }

    /**
     * Method under test:
     * {@link TravelPackageServiceImpl#addPassengerToPackage(Long, Long)}
     */
    @Test
    void testAddPassengerToPackage2() {
        // Arrange
        when(packageEnrollmentRepository.save(Mockito.<PackageEnrollment>any()))
                .thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> travelPackageServiceImpl.addPassengerToPackage(1L, 1L));
        verify(packageEnrollmentRepository).save(Mockito.<PackageEnrollment>any());
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getItineraries(Long)}
     */
    @Test
    void testGetItineraries() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     0x000000080158eb40.arg$1

        // Arrange and Act
        TravelPackageServiceImpl.getItineraries(1L);
    }

    /**
     * Method under test: {@link TravelPackageServiceImpl#getPassengers(Long)}
     */
    @Test
    void testGetPassengers() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     0x000000080158ed80.arg$1

        // Arrange and Act
        TravelPackageServiceImpl.getPassengers(1L);
    }
}
