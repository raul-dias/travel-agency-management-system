package com.raul.nymble.repository;

import com.raul.nymble.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
