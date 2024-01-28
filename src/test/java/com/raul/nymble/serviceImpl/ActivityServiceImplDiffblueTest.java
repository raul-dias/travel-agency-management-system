package com.raul.nymble.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.raul.nymble.model.Activity;
import com.raul.nymble.model.Destination;
import com.raul.nymble.model.Enrollment;
import com.raul.nymble.repository.ActivityRepository;
import com.raul.nymble.repository.DestinationRepository;
import com.raul.nymble.repository.EnrollmentRepository;
import com.raul.nymble.service.PassengerService;

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

@ContextConfiguration(classes = {ActivityServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ActivityServiceImplDiffblueTest {
    @MockBean
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityServiceImpl activityServiceImpl;

    @MockBean
    private DestinationRepository destinationRepository;

    @MockBean
    private EnrollmentRepository enrollmentRepository;

    @MockBean
    private PassengerService passengerService;

    /**
     * Method under test: {@link ActivityServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        // Arrange
        ArrayList<Activity> activityList = new ArrayList<>();
        when(activityRepository.findAll()).thenReturn(activityList);

        // Act
        List<Activity> actualFindAllResult = activityServiceImpl.findAll();

        // Assert
        verify(activityRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(activityList, actualFindAllResult);
    }

    /**
     * Method under test: {@link ActivityServiceImpl#findByDestination(String)}
     */
    @Test
    void testFindByDestination() {
        // Arrange
        when(destinationRepository.findByNameLike(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<Activity> actualFindByDestinationResult = activityServiceImpl.findByDestination("Name");

        // Assert
        verify(destinationRepository).findByNameLike(Mockito.<String>any());
        assertTrue(actualFindByDestinationResult.isEmpty());
    }

    /**
     * Method under test: {@link ActivityServiceImpl#findByDestination(String)}
     */
    @Test
    void testFindByDestination2() {
        // Arrange
        when(activityRepository.findByDestinationId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");

        ArrayList<Destination> destinationList = new ArrayList<>();
        destinationList.add(destination);
        when(destinationRepository.findByNameLike(Mockito.<String>any())).thenReturn(destinationList);

        // Act
        List<Activity> actualFindByDestinationResult = activityServiceImpl.findByDestination("Name");

        // Assert
        verify(activityRepository).findByDestinationId(Mockito.<Long>any());
        verify(destinationRepository).findByNameLike(Mockito.<String>any());
        assertTrue(actualFindByDestinationResult.isEmpty());
    }

    /**
     * Method under test: {@link ActivityServiceImpl#findByDestination(String)}
     */
    @Test
    void testFindByDestination3() {
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
        when(destinationRepository.findByNameLike(Mockito.<String>any())).thenReturn(destinationList);

        // Act
        List<Activity> actualFindByDestinationResult = activityServiceImpl.findByDestination("Name");

        // Assert
        verify(activityRepository, atLeast(1)).findByDestinationId(Mockito.<Long>any());
        verify(destinationRepository).findByNameLike(Mockito.<String>any());
        assertTrue(actualFindByDestinationResult.isEmpty());
    }

    /**
     * Method under test: {@link ActivityServiceImpl#findByName(String)}
     */
    @Test
    void testFindByName() {
        // Arrange
        ArrayList<Activity> activityList = new ArrayList<>();
        when(activityRepository.findByNameLike(Mockito.<String>any())).thenReturn(activityList);

        // Act
        List<Activity> actualFindByNameResult = activityServiceImpl.findByName("Name");

        // Assert
        verify(activityRepository).findByNameLike(Mockito.<String>any());
        assertTrue(actualFindByNameResult.isEmpty());
        assertSame(activityList, actualFindByNameResult);
    }

    /**
     * Method under test: {@link ActivityServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {
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

        // Act
        Activity actualFindByIdResult = activityServiceImpl.findById(1L);

        // Assert
        verify(activityRepository).findById(Mockito.<Long>any());
        assertSame(activity, actualFindByIdResult);
    }

    /**
     * Method under test: {@link ActivityServiceImpl#save(Activity)}
     */
    @Test
    void testSave() {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(1);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity);

        Activity activity2 = new Activity();
        activity2.setBookedCapasity(1);
        activity2.setCapasity(1);
        activity2.setCost(1L);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestinationId(1L);
        activity2.setId(1L);
        activity2.setName("Name");

        // Act
        Activity actualSaveResult = activityServiceImpl.save(activity2);

        // Assert
        verify(activityRepository).save(Mockito.<Activity>any());
        assertSame(activity, actualSaveResult);
    }

    /**
     * Method under test: {@link ActivityServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        // Arrange
        doNothing().when(activityRepository).deleteById(Mockito.<Long>any());

        // Act
        activityServiceImpl.delete(1L);

        // Assert that nothing has changed
        verify(activityRepository).deleteById(Mockito.<Long>any());
        assertTrue(activityServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test:
     * {@link ActivityServiceImpl#addPassengerToActivity(Long, Long)}
     */
    @Test
    void testAddPassengerToActivity() {
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

        Activity activity2 = new Activity();
        activity2.setBookedCapasity(1);
        activity2.setCapasity(1);
        activity2.setCost(1L);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestinationId(1L);
        activity2.setId(1L);
        activity2.setName("Name");
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity2);
        when(activityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Enrollment enrollment = new Enrollment();
        enrollment.setActivityId(1L);
        enrollment.setId(1L);
        enrollment.setPassengerId(1L);
        when(enrollmentRepository.save(Mockito.<Enrollment>any())).thenReturn(enrollment);
        when(passengerService.validatePassengerForActivity(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(true);

        // Act
        activityServiceImpl.addPassengerToActivity(1L, 1L);

        // Assert
        verify(passengerService).validatePassengerForActivity(Mockito.<Long>any(), Mockito.<Long>any(),
                Mockito.<Long>any());
        verify(activityRepository).findById(Mockito.<Long>any());
        verify(activityRepository).save(Mockito.<Activity>any());
        verify(enrollmentRepository).save(Mockito.<Enrollment>any());
    }

    /**
     * Method under test:
     * {@link ActivityServiceImpl#addPassengerToActivity(Long, Long)}
     */
    @Test
    void testAddPassengerToActivity2() {
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

        Activity activity2 = new Activity();
        activity2.setBookedCapasity(1);
        activity2.setCapasity(1);
        activity2.setCost(1L);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestinationId(1L);
        activity2.setId(1L);
        activity2.setName("Name");
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity2);
        when(activityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Enrollment enrollment = new Enrollment();
        enrollment.setActivityId(1L);
        enrollment.setId(1L);
        enrollment.setPassengerId(1L);
        when(enrollmentRepository.save(Mockito.<Enrollment>any())).thenReturn(enrollment);
        when(passengerService.validatePassengerForActivity(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(false);

        // Act
        activityServiceImpl.addPassengerToActivity(1L, 1L);

        // Assert
        verify(passengerService).validatePassengerForActivity(Mockito.<Long>any(), Mockito.<Long>any(),
                Mockito.<Long>any());
        verify(activityRepository).findById(Mockito.<Long>any());
        verify(activityRepository, atLeast(1)).save(Mockito.<Activity>any());
    }
}
