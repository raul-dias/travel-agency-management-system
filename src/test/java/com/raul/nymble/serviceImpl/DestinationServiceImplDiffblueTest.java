package com.raul.nymble.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.raul.nymble.model.Destination;
import com.raul.nymble.repository.DestinationRepository;

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

@ContextConfiguration(classes = {DestinationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DestinationServiceImplDiffblueTest {
    @MockBean
    private DestinationRepository destinationRepository;

    @Autowired
    private DestinationServiceImpl destinationServiceImpl;

    /**
     * Method under test: {@link DestinationServiceImpl#save(Destination)}
     */
    @Test
    void testSave() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        when(destinationRepository.save(Mockito.<Destination>any())).thenReturn(destination);

        Destination destination2 = new Destination();
        destination2.setId(1L);
        destination2.setName("Name");

        // Act
        destinationServiceImpl.save(destination2);

        // Assert
        verify(destinationRepository).save(Mockito.<Destination>any());
    }

    /**
     * Method under test: {@link DestinationServiceImpl#update(Destination)}
     */
    @Test
    void testUpdate() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        when(destinationRepository.save(Mockito.<Destination>any())).thenReturn(destination);

        Destination destination2 = new Destination();
        destination2.setId(1L);
        destination2.setName("Name");

        // Act
        destinationServiceImpl.update(destination2);

        // Assert that nothing has changed
        verify(destinationRepository).save(Mockito.<Destination>any());
        assertEquals("Name", destination2.getName());
        assertEquals(1L, destination2.getId().longValue());
        assertTrue(destinationServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link DestinationServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        // Arrange
        doNothing().when(destinationRepository).deleteById(Mockito.<Long>any());

        // Act
        destinationServiceImpl.delete(1L);

        // Assert that nothing has changed
        verify(destinationRepository).deleteById(Mockito.<Long>any());
        assertTrue(destinationServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link DestinationServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {
        // Arrange
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Name");
        Optional<Destination> ofResult = Optional.of(destination);
        when(destinationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Destination actualFindByIdResult = destinationServiceImpl.findById(1L);

        // Assert
        verify(destinationRepository).findById(Mockito.<Long>any());
        assertSame(destination, actualFindByIdResult);
    }

    /**
     * Method under test: {@link DestinationServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        // Arrange
        ArrayList<Destination> destinationList = new ArrayList<>();
        when(destinationRepository.findAll()).thenReturn(destinationList);

        // Act
        List<Destination> actualFindAllResult = destinationServiceImpl.findAll();

        // Assert
        verify(destinationRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(destinationList, actualFindAllResult);
    }
}
