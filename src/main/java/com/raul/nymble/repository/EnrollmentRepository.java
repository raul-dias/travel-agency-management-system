package com.raul.nymble.repository;

import com.raul.nymble.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, JpaSpecificationExecutor<Enrollment> {
    void deleteByPassengerId(Long id);

    List<Enrollment> findByPassengerId(Long id);
}
