package com.microservice.airline.location_service.mapper;

import com.microservice.airline.location_service.model.Airport;
import com.microservice.airline.payload.request.AirportRequest;
import com.microservice.airline.payload.response.AirportResponse;

import java.time.ZoneId;

public class AirportMapper {

    public static Airport toEntity(AirportRequest request){
        if(request == null) return null;

        return Airport.builder()
                .iataCode(request.getIataCode())
                .name(request.getName())
                .timeZone(request.getTimeZone().getId())
                .address(request.getAddress())
                .geoCode(request.getGeoCode())
                .build();
    }

    public static AirportResponse toResponse(Airport airport){
        if(airport == null) return null;

        return AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .detailedName(airport.getDetailedName())
                .timeZone(ZoneId.of(airport.getTimeZone()))
                .address(airport.getAddress())
                .city(CityMapper.toResponse(airport.getCity()))
                .geoCode(airport.getGeoCode())
                .build();
    }

    public static void updateEntity(AirportRequest request, Airport exisingAirport){

        if(request == null || exisingAirport == null ) return ;

        if(request.getIataCode() != null) {
            exisingAirport.setIataCode(request.getIataCode());
        }
        if(request.getName() != null){
            exisingAirport.setName(request.getName());
        }
        if(request.getAddress() != null){
            exisingAirport.setAddress(request.getAddress());
        }
        if(request.getGeoCode() != null){
            exisingAirport.setGeoCode(request.getGeoCode());
        }
    }
}
