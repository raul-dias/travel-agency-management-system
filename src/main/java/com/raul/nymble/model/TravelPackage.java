package com.raul.nymble.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * This entity is maintained to hold a record of all the Travel Packages
 * Offered
 */
@Entity
@Data
public class TravelPackage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private int capacity;
    public TravelPackage(){ }
    public TravelPackage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
