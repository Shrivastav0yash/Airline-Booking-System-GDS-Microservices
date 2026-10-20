package com.microservice.airline.seat_service.service;

import com.microservice.airline.payload.request.SeatMapRequest;
import com.microservice.airline.payload.response.SeatMapResponse;

public interface SeatMapService {

    SeatMapResponse createSeatMap(Long airlineId, SeatMapRequest seatMapRequest) throws Exception;

    SeatMapResponse getSeatMapById(Long id) throws Exception;

    SeatMapResponse getSeatMapByCabinClass(Long cabinClassId);

    SeatMapResponse updateSeatMap(Long id, SeatMapRequest seatMapRequest) throws Exception;

    void deleteSeatMap(Long id) throws Exception;
}
