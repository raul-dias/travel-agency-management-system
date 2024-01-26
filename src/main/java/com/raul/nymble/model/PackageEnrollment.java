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
    private Long customerId;
    private Long travelPackageId;

    protected PackageEnrollment() { }

    public PackageEnrollment(Long customerId, Long travelPackageId) {
        this.customerId = customerId;
        this.travelPackageId = travelPackageId;
    }
}
