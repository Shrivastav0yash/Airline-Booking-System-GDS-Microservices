package com.microservice.airline.pricing_service.service;

import com.microservice.airline.payload.request.FareRequest;
import com.microservice.airline.payload.response.FareResponse;
import com.microservice.airline.pricing_service.model.Fare;

import java.util.List;
import java.util.Map;

public interface FareService {

    FareResponse createFare(FareRequest fareRequest) throws Exception;

    FareResponse getFareById(Long id) throws Exception;

    List<FareResponse> getFaresByFlightIdAndCabinClassId(Long flightId, Long cabinClassId);

    FareResponse updateFare(Long id, FareRequest fareRequest) throws Exception;

    void deleteFare(Long id) throws Exception;

    List<Fare> getFares(); // developer method to get all fares

    Map<Long, FareResponse> getLowestFaresByFlightIds(List<Long> flightIds, Long cabinClassId);


    Map<Long, FareResponse> getFareByIds(List<Long> ids);

}
