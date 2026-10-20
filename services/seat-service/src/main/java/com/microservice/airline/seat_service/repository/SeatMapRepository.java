package com.microservice.airline.seat_service.repository;

import com.microservice.airline.seat_service.model.SeatMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatMapRepository extends JpaRepository<SeatMap, Long> {

    SeatMap findByCabinClassId(Long cabinClassId);

    boolean existsByAirlineIdAndCabinClassIdAndName(Long airlineId, Long cabinClassId, String name);
}
