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
    private Long passengerId;
    private Long activityId;

    protected Enrollment() { }

    public Enrollment(Long passengerId, Long activityId) {
        this.passengerId = passengerId;
        this.activityId = activityId;
    }
}
