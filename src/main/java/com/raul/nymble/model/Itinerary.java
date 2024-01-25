package com.raul.nymble.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * This entity is maintained to hold a record of the destinations
 * associated with specific travel packages.
 */
@Entity
@Data
public class Itinerary {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int travelPackageId;
    private int destinationId;

    protected Itinerary() { }

    public Itinerary(int travelPackageId, int destinationId) {
        this.travelPackageId = travelPackageId;
        this.destinationId = destinationId;
    }
}
