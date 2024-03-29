package com.raul.nymble.repository;

import com.raul.nymble.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long>, JpaSpecificationExecutor<Itinerary> {
}
