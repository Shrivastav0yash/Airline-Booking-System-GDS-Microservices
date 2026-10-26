package com.microservice.airline.seat_service.repository;

import com.microservice.airline.seat_service.model.FlightInstanceCabin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightInstanceCabinRepository extends JpaRepository<FlightInstanceCabin, Long> {

    Page<FlightInstanceCabin> findByFlightInstanceId(long flightInstanceId, Pageable pageable);
    FlightInstanceCabin findByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId);

}
