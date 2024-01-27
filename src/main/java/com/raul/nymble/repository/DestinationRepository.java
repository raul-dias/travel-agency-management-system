package com.raul.nymble.repository;

import com.raul.nymble.model.Activity;
import com.raul.nymble.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long>, JpaSpecificationExecutor<Destination> {
    List<Destination> findByNameLike(String name);
}
