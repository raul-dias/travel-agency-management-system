package com.raul.nymble.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * This entity is maintained to hold the details of each specific activity
 * as well as the costs and capacity associated with it.
 */
@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long cost;
    private int capasity;
    private int bookedCapasity;
    private Long destinationId;

    public Activity() { }

    public Activity(String name, String description, Long cost, int capasity, Long destinationId) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capasity = capasity;
        this.bookedCapasity = 0;
        this.destinationId = destinationId;
    }
}

