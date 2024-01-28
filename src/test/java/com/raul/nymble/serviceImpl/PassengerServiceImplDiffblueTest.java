package com.raul.nymble.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.raul.nymble.DTO.TravelPackageDTO;
import com.raul.nymble.model.Destination;
import com.raul.nymble.model.PackageEnrollment;
import com.raul.nymble.model.Passenger;
import com.raul.nymble.repository.EnrollmentRepository;
import com.raul.nymble.repository.PackageEnrollmentRepository;
import com.raul.nymble.repository.PassengerRepository;
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

@ContextConfiguration(classes = {PassengerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PassengerServiceImplDiffblueTest {
    @MockBean
    private EnrollmentRepository enrollmentRepository;

    @MockBean
    private PackageEnrollmentRepository packageEnrollmentRepository;

    @MockBean
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerServiceImpl passengerServiceImpl;

    @MockBean
    private TravelPackageService travelPackageService;

    /**
     * Method under test:
     * {@link PassengerServiceImpl#validatePassengerForActivity(Long, Long, Long)}
     */
    @Test
    void testValidatePassengerForActivity() {
        // Arrange
        when(packageEnrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Boolean actualValidatePassengerForActivityResult = passengerServiceImpl.validatePassengerForActivity(1L, 1L, 1L);

        // Assert
        verify(packageEnrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(passengerRepository, atLeast(1)).findById(Mockito.<Long>any());
        assertFalse(actualValidatePassengerForActivityResult);
    }

    /**
     * Method under test:
     * {@link PassengerServiceImpl#validatePassengerForActivity(Long, Long, Long)}
     */
    @Test
    void testValidatePassengerForActivity2() {
        // Arrange
        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);

        Passenger passenger2 = new Passenger();
        passenger2.setBalance(42L);
        passenger2.setId(1L);
        passenger2.setName("Name");
        passenger2.setNumber(10);
        passenger2.setTier(Passenger.tierEnum.STANDARD);
        when(passengerRepository.save(Mockito.<Passenger>any())).thenReturn(passenger2);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addDestination(destination);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        Boolean actualValidatePassengerForActivityResult = passengerServiceImpl.validatePassengerForActivity(1L, 1L, 1L);

        // Assert
        verify(packageEnrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        verify(passengerRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(passengerRepository).save(Mockito.<Passenger>any());
        assertTrue(actualValidatePassengerForActivityResult);
    }

    /**
     * Method under test:
     * {@link PassengerServiceImpl#validatePassengerForActivity(Long, Long, Long)}
     */
    @Test
    void testValidatePassengerForActivity3() {
        // Arrange
        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        PackageEnrollment packageEnrollment2 = new PackageEnrollment();
        packageEnrollment2.setId(3L);
        packageEnrollment2.setPassengerId(3L);
        packageEnrollment2.setTravelPackageId(3L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment2);
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);

        Passenger passenger2 = new Passenger();
        passenger2.setBalance(42L);
        passenger2.setId(1L);
        passenger2.setName("Name");
        passenger2.setNumber(10);
        passenger2.setTier(Passenger.tierEnum.STANDARD);
        when(passengerRepository.save(Mockito.<Passenger>any())).thenReturn(passenger2);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addDestination(destination);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        Boolean actualValidatePassengerForActivityResult = passengerServiceImpl.validatePassengerForActivity(1L, 1L, 1L);

        // Assert
        verify(packageEnrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(travelPackageService, atLeast(1)).getTravelPackageById(Mockito.<Long>any());
        verify(passengerRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(passengerRepository).save(Mockito.<Passenger>any());
        assertTrue(actualValidatePassengerForActivityResult);
    }

    /**
     * Method under test:
     * {@link PassengerServiceImpl#validatePassengerForActivity(Long, Long, Long)}
     */
    @Test
    void testValidatePassengerForActivity4() {
        // Arrange
        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(packageEnrollmentList);
        Passenger passenger = mock(Passenger.class);
        when(passenger.getBalance()).thenReturn(-1L);
        when(passenger.getId()).thenReturn(1L);
        doNothing().when(passenger).setBalance(Mockito.<Long>any());
        doNothing().when(passenger).setId(Mockito.<Long>any());
        doNothing().when(passenger).setName(Mockito.<String>any());
        doNothing().when(passenger).setNumber(anyInt());
        doNothing().when(passenger).setTier(Mockito.<Passenger.tierEnum>any());
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.addDestination(destination);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(travelPackageDTO);

        // Act
        Boolean actualValidatePassengerForActivityResult = passengerServiceImpl.validatePassengerForActivity(1L, 1L, 1L);

        // Assert
        verify(passenger).getBalance();
        verify(passenger).getId();
        verify(passenger).setBalance(Mockito.<Long>any());
        verify(passenger).setId(Mockito.<Long>any());
        verify(passenger).setName(Mockito.<String>any());
        verify(passenger).setNumber(anyInt());
        verify(passenger).setTier(Mockito.<Passenger.tierEnum>any());
        verify(packageEnrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        verify(passengerRepository, atLeast(1)).findById(Mockito.<Long>any());
        assertFalse(actualValidatePassengerForActivityResult);
    }

    /**
     * Method under test: {@link PassengerServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        // Arrange
        ArrayList<Passenger> passengerList = new ArrayList<>();
        when(passengerRepository.findAll()).thenReturn(passengerList);

        // Act
        List<Passenger> actualFindAllResult = passengerServiceImpl.findAll();

        // Assert
        verify(passengerRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(passengerList, actualFindAllResult);
    }

    /**
     * Method under test: {@link PassengerServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {
        // Arrange
        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Passenger actualFindByIdResult = passengerServiceImpl.findById(1L);

        // Assert
        verify(passengerRepository).findById(Mockito.<Long>any());
        assertSame(passenger, actualFindByIdResult);
    }

    /**
     * Method under test: {@link PassengerServiceImpl#save(Passenger)}
     */
    @Test
    void testSave() {
        // Arrange
        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        when(passengerRepository.save(Mockito.<Passenger>any())).thenReturn(passenger);

        Passenger passenger2 = new Passenger();
        passenger2.setBalance(42L);
        passenger2.setId(1L);
        passenger2.setName("Name");
        passenger2.setNumber(10);
        passenger2.setTier(Passenger.tierEnum.STANDARD);

        // Act
        passengerServiceImpl.save(passenger2);

        // Assert that nothing has changed
        verify(passengerRepository).save(Mockito.<Passenger>any());
        assertEquals("Name", passenger2.getName());
        assertEquals(10, passenger2.getNumber());
        assertEquals(1L, passenger2.getId().longValue());
        assertEquals(42L, passenger2.getBalance().longValue());
        assertEquals(Passenger.tierEnum.STANDARD, passenger2.getTier());
        assertTrue(passengerServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link PassengerServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        // Arrange
        doNothing().when(enrollmentRepository).deleteByPassengerId(Mockito.<Long>any());
        doNothing().when(packageEnrollmentRepository).deleteByPassengerId(Mockito.<Long>any());
        doNothing().when(passengerRepository).deleteById(Mockito.<Long>any());

        // Act
        passengerServiceImpl.delete(1L);

        // Assert that nothing has changed
        verify(enrollmentRepository).deleteByPassengerId(Mockito.<Long>any());
        verify(packageEnrollmentRepository).deleteByPassengerId(Mockito.<Long>any());
        verify(passengerRepository).deleteById(Mockito.<Long>any());
        assertTrue(passengerServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link PassengerServiceImpl#getTravelPackages(Long)}
     */
    @Test
    void testGetTravelPackages() {
        // Arrange
        when(packageEnrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<TravelPackageDTO> actualTravelPackages = passengerServiceImpl.getTravelPackages(1L);

        // Assert
        verify(packageEnrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(passengerRepository).findById(Mockito.<Long>any());
        assertTrue(actualTravelPackages.isEmpty());
    }

    /**
     * Method under test: {@link PassengerServiceImpl#getTravelPackages(Long)}
     */
    @Test
    void testGetTravelPackages2() {
        // Arrange
        PackageEnrollment packageEnrollment = new PackageEnrollment();
        packageEnrollment.setId(1L);
        packageEnrollment.setPassengerId(1L);
        packageEnrollment.setTravelPackageId(1L);

        ArrayList<PackageEnrollment> packageEnrollmentList = new ArrayList<>();
        packageEnrollmentList.add(packageEnrollment);
        when(packageEnrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(new TravelPackageDTO());

        // Act
        List<TravelPackageDTO> actualTravelPackages = passengerServiceImpl.getTravelPackages(1L);

        // Assert
        verify(packageEnrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(travelPackageService).getTravelPackageById(Mockito.<Long>any());
        verify(passengerRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualTravelPackages.size());
    }

    /**
     * Method under test: {@link PassengerServiceImpl#getTravelPackages(Long)}
     */
    @Test
    void testGetTravelPackages3() {
        // Arrange
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
        when(packageEnrollmentRepository.findByPassengerId(Mockito.<Long>any())).thenReturn(packageEnrollmentList);

        Passenger passenger = new Passenger();
        passenger.setBalance(42L);
        passenger.setId(1L);
        passenger.setName("Name");
        passenger.setNumber(10);
        passenger.setTier(Passenger.tierEnum.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(travelPackageService.getTravelPackageById(Mockito.<Long>any())).thenReturn(new TravelPackageDTO());

        // Act
        List<TravelPackageDTO> actualTravelPackages = passengerServiceImpl.getTravelPackages(1L);

        // Assert
        verify(packageEnrollmentRepository).findByPassengerId(Mockito.<Long>any());
        verify(travelPackageService, atLeast(1)).getTravelPackageById(Mockito.<Long>any());
        verify(passengerRepository).findById(Mockito.<Long>any());
        assertEquals(2, actualTravelPackages.size());
    }
}
