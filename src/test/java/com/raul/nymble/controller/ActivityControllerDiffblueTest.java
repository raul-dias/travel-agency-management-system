package com.raul.nymble.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raul.nymble.model.Activity;
import com.raul.nymble.service.ActivityService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ActivityController.class})
@ExtendWith(SpringExtension.class)
class ActivityControllerDiffblueTest {
    @Autowired
    private ActivityController activityController;

    @MockBean
    private ActivityService activityService;

    /**
     * Method under test:
     * {@link ActivityController#addPassengerToActivity(Long, Long)}
     */
    @Test
    void testAddPassengerToActivity() throws Exception {
        // Arrange
        doNothing().when(activityService).addPassengerToActivity(Mockito.<Long>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/activity/{id}/enroll", 1L)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ActivityController#deleteActivity(Long)}
     */
    @Test
    void testDeleteActivity() throws Exception {
        // Arrange
        doNothing().when(activityService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/activity/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ActivityController#deleteActivity(Long)}
     */
    @Test
    void testDeleteActivity2() throws Exception {
        // Arrange
        doNothing().when(activityService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/activity/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ActivityController#getActivity()}
     */
    @Test
    void testGetActivity() throws Exception {
        // Arrange
        when(activityService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activity");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ActivityController#getActivity(Long)}
     */
    @Test
    void testGetActivity2() throws Exception {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(1);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");
        when(activityService.findById(Mockito.<Long>any())).thenReturn(activity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activity/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"cost\":1,\"capasity"
                                        + "\":1,\"bookedCapasity\":1,\"destinationId\":1}"));
    }

    /**
     * Method under test:
     * {@link ActivityController#getActivityByDestination(String)}
     */
    @Test
    void testGetActivityByDestination() throws Exception {
        // Arrange
        when(activityService.findByDestination(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activity/destination/{name}", "Name");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ActivityController#getActivityByName(String)}
     */
    @Test
    void testGetActivityByName() throws Exception {
        // Arrange
        when(activityService.findByName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activity/name/{name}", "Name");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ActivityController#saveActivity(Activity)}
     */
    @Test
    void testSaveActivity() throws Exception {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(1);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");
        when(activityService.save(Mockito.<Activity>any())).thenReturn(activity);

        Activity activity2 = new Activity();
        activity2.setBookedCapasity(1);
        activity2.setCapasity(1);
        activity2.setCost(1L);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestinationId(1L);
        activity2.setId(1L);
        activity2.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(activity2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/activity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ActivityController#updateActivity(Long, Activity)}
     */
    @Test
    void testUpdateActivity() throws Exception {
        // Arrange
        Activity activity = new Activity();
        activity.setBookedCapasity(1);
        activity.setCapasity(1);
        activity.setCost(1L);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestinationId(1L);
        activity.setId(1L);
        activity.setName("Name");
        when(activityService.save(Mockito.<Activity>any())).thenReturn(activity);

        Activity activity2 = new Activity();
        activity2.setBookedCapasity(1);
        activity2.setCapasity(1);
        activity2.setCost(1L);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestinationId(1L);
        activity2.setId(1L);
        activity2.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(activity2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/activity/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
