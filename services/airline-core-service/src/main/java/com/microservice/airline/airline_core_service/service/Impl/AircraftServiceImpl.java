package com.microservice.airline.airline_core_service.service.Impl;

import com.microservice.airline.airline_core_service.mapper.AircraftMapper;
import com.microservice.airline.airline_core_service.model.Aircraft;
import com.microservice.airline.airline_core_service.model.Airline;
import com.microservice.airline.airline_core_service.repository.AircraftRepository;
import com.microservice.airline.airline_core_service.repository.AirlineRepository;
import com.microservice.airline.airline_core_service.service.AircraftService;
import com.microservice.airline.payload.request.AircraftRequest;
import com.microservice.airline.payload.response.AircraftResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;

    @Override
    public AircraftResponse createAircraft(AircraftRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                ()-> new Exception("airline not exist for this owner")
        );

        Aircraft aircraft = AircraftMapper.toEntity(request,airline);

        if(aircraftRepository.existsByCode(aircraft.getCode())){
            throw new Exception("code already exist it needs to be unique");
        }

        if(aircraft.getSeatingCapacity() < aircraft.getTotalSeats()){
            throw new Exception("Seating capacity can't be exceed");
        }

        Aircraft savedAircraft = aircraftRepository.save(aircraft);

        return AircraftMapper.toResponse(savedAircraft);
    }

    @Override
    public AircraftResponse getAircraftById(Long id) throws Exception {

        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(
                ()-> new Exception("Aircraft not exist with this "+id)
        );

        return AircraftMapper.toResponse(aircraft);
    }

    @Override
    public List<AircraftResponse> listAllAircraftByOwner(Long ownerId) throws Exception {
        Airline airline  = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                ()-> new Exception("This owner does not have Airline")
        );
        List<Aircraft> allAircraftByAirline = aircraftRepository.findByAirlineId(airline.getId());
        return allAircraftByAirline.stream().map(
                AircraftMapper :: toResponse
        ).toList();
    }

    @Override
    public AircraftResponse updateAircraft(Long id, AircraftRequest request, Long ownerId) throws Exception {
        Airline airline  = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                ()-> new Exception("This owner does not have Airline")
        );
        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if(aircraft == null) {
            throw new Exception("Aircraft not exist with id");
        }
        if(request.getCode()!= null
                && !aircraft.getCode().equals(request.getCode())
                && aircraftRepository.existsByCode(request.getCode())){
            throw new Exception("code already exist it needs to be unique");
        }
        AircraftMapper.updateEntity(aircraft, request);
        return AircraftMapper.toResponse(
                aircraftRepository.save(aircraft)
        );
    }

    @Override
    public void deleteAircraft(Long id, Long ownerId) throws Exception {
        Airline airline  = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                ()-> new Exception("This owner does not have Airline")
        );
        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if(aircraft == null) {
            throw new Exception("Aircraft not exist with id");
        }
        aircraftRepository.delete(aircraft);
    }
}
