package com.microservice.airline.pricing_service.service.impl;

import com.microservice.airline.payload.request.FareRequest;
import com.microservice.airline.payload.response.FareResponse;
import com.microservice.airline.pricing_service.mapper.FareMapper;
import com.microservice.airline.pricing_service.model.Fare;
import com.microservice.airline.pricing_service.repository.FareRepository;
import com.microservice.airline.pricing_service.service.FareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;

    @Override
    public FareResponse createFare(FareRequest fareRequest) throws Exception {

        if(fareRepository.existsByFlightIdAndCabinClassIdAndName(
fareRequest.getFlightId(), fareRequest.getCabinClassId(), fareRequest.getName()
        )){
            throw new Exception("Fare with the same name already exists for this flight and cabin class.");
        }

        Fare fare = FareMapper.toEntity(fareRequest);
        Fare saved = fareRepository.save(fare);

        return FareMapper.toResponse(saved);
    }

    @Override
    public FareResponse getFareById(Long id) throws Exception {

        Fare fare = fareRepository.findById(id).orElseThrow(
                () -> new Exception("Fare not found with id: " + id)
        );
        return FareMapper.toResponse(fare);
    }

    @Override
    public List<FareResponse> getFaresByFlightIdAndCabinClassId(Long flightId, Long cabinClassId) {

        return fareRepository.findByFlightIdAndCabinClassId(flightId, cabinClassId)
                .stream()
                .map(FareMapper::toResponse)
                .toList();
    }

    @Override
    public FareResponse updateFare(Long id, FareRequest fareRequest) throws Exception {

        Fare fare = fareRepository.findById(id).orElseThrow(
                () -> new Exception("Fare not found with id: " + id)
        );

        if(fareRepository.existsByFlightIdAndCabinClassIdAndNameAndIdNot(
                fareRequest.getFlightId(), fareRequest.getCabinClassId(), fareRequest.getName(), fare.getId()
        )){
            throw new Exception("Fare with the same name already exists for this flight and cabin class.");
        }

        FareMapper.updateEntity(fareRequest, fare);
        Fare saved = fareRepository.save(fare);

        return FareMapper.toResponse(saved);
    }

    @Override
    public void deleteFare(Long id) throws Exception {

        Fare fare = fareRepository.findById(id).orElseThrow(
                () -> new Exception("Fare not found with id: " + id)
        );

        fareRepository.delete(fare);
    }

    @Override
    public List<Fare> getFares() {

        return fareRepository.findAll();
    }

    @Override
    public Map<Long, FareResponse> getLowestFaresByFlightIds(List<Long> flightIds, Long cabinClassId) {

        if(flightIds == null || flightIds.isEmpty() || cabinClassId == null){
            return Map.of();
        }

        List<Fare> fares = fareRepository.findByFlightIdInAndCabinClassId(flightIds, cabinClassId);

        Map<Long, FareResponse> lowestFaresMap = new HashMap<>();
        for(Fare fare : fares){
                FareResponse fareResponse = FareMapper.toResponse(fare);
                Long flightId = fare.getFlightId();
                if(!lowestFaresMap.containsKey(flightId) || fareResponse.getTotalPrice() < lowestFaresMap.get(flightId).getTotalPrice()){
                    lowestFaresMap.put(flightId, fareResponse);
                }
            }
            return lowestFaresMap;
    }

    @Override
    public Map<Long, FareResponse> getFareByIds(List<Long> ids) {

        List<Fare> fares = fareRepository.findAllById(ids);
        return fares.stream().collect(Collectors.toMap(Fare::getId, FareMapper::toResponse));
    }
}
