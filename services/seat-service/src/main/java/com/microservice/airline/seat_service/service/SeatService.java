package com.microservice.airline.seat_service.service;

import com.microservice.airline.payload.request.SeatRequest;
import com.microservice.airline.payload.response.SeatResponse;

import java.util.List;

public interface SeatService {

    void generateSeats(Long seatMapId) throws Exception;

    SeatResponse updateSeats(Long seatId, SeatRequest seatRequest);

    List<SeatResponse> getAll();
}
