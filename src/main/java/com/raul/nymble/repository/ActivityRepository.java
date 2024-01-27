package com.raul.nymble.repository;

import com.raul.nymble.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {
    List<Activity> findByDestinationId(Long id);

    List<Activity> findByNameLike(String s);
}
