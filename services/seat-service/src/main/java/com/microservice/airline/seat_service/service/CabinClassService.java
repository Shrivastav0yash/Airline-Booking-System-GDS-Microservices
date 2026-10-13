package com.microservice.airline.seat_service.service;

import com.microservice.airline.enums.CabinClassType;
import com.microservice.airline.payload.request.CabinClassRequest;
import com.microservice.airline.payload.response.CabinClassResponse;

import java.util.List;

public interface CabinClassService {

    CabinClassResponse createCabinClass(CabinClassRequest cabinClassRequest) throws Exception;

    CabinClassResponse getCabinClassById(Long id);

    List<CabinClassResponse> getCabinClassByAircraftId(Long aircraftId);

    CabinClassResponse getCabinClassByAircraftIdAndName(Long aircraftId, CabinClassType name);

    CabinClassResponse updateCabinClass(Long id, CabinClassRequest cabinClassRequest) throws Exception;

    void deleteCabinClass(Long id);

}
