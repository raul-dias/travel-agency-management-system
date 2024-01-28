package com.raul.nymble.repository;

import com.raul.nymble.model.PackageEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface PackageEnrollmentRepository extends JpaRepository<PackageEnrollment, Long>, JpaSpecificationExecutor<PackageEnrollment> {
    void deleteByPassengerId(Long id);

    List<PackageEnrollment> findByPassengerId(Long id);
}
