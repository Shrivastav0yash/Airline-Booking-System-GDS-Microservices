package com.microservice.airline.seat_service.repository;

import com.microservice.airline.seat_service.model.SeatInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatInstanceRepository extends JpaRepository<SeatInstance, Long> {

}
