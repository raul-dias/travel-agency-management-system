package com.raul.nymble.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * This entity is maintained to hold a record between customers and
 * the activities they have signed up for.
 */
@Entity
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private Long activityId;

    protected Enrollment() { }

    public Enrollment(Long customerId, Long activityId) {
        this.customerId = customerId;
        this.activityId = activityId;
    }
}
