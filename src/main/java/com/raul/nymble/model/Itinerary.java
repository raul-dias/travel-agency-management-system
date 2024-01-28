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
    private Long id;
    private Long travelPackageId;
    private Long destinationId;

    public Itinerary() { }

    public Itinerary(Long travelPackageId, Long destinationId) {
        this.travelPackageId = travelPackageId;
        this.destinationId = destinationId;
    }
}
