package com.raul.nymble.repository;

import com.raul.nymble.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> { }
