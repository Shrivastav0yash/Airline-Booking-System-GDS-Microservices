package com.microservice.airline.seat_service.repository;

import com.microservice.airline.seat_service.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    boolean existsBySeatMapId(Long seatMapId);
}
