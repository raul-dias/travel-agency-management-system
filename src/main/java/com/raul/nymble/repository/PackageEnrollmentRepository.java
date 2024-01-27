package com.raul.nymble.repository;

import com.raul.nymble.model.Itinerary;
import com.raul.nymble.model.PackageEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PackageEnrollmentRepository extends JpaRepository<PackageEnrollment, Long>, JpaSpecificationExecutor<PackageEnrollment> {
    void deleteByPassengerId(Long id);
}
