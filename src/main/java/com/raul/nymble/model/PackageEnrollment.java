package com.raul.nymble.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PackageEnrollment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long passengerId;
    private Long travelPackageId;

    public PackageEnrollment() { }

    public PackageEnrollment(Long passengerId, Long travelPackageId) {
        this.passengerId = passengerId;
        this.travelPackageId = travelPackageId;
    }
}
