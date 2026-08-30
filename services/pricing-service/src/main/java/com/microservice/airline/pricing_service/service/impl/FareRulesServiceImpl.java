package com.microservice.airline.pricing_service.service.impl;

import com.microservice.airline.payload.request.FareRulesRequest;
import com.microservice.airline.payload.response.FareRulesResponse;
import com.microservice.airline.pricing_service.mapper.FareRulesMapper;
import com.microservice.airline.pricing_service.model.Fare;
import com.microservice.airline.pricing_service.model.FareRules;
import com.microservice.airline.pricing_service.repository.FareRepository;
import com.microservice.airline.pricing_service.repository.FareRulesRepository;
import com.microservice.airline.pricing_service.service.FareRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FareRulesServiceImpl implements FareRulesService {

    private final FareRulesRepository fareRulesRepository;
    private final FareRepository fareRepository;

    @Override
    public FareRulesResponse createFareRules(FareRulesRequest request) throws Exception {

        Fare fare = fareRepository.findById(request.getFareId()).orElseThrow(
                () -> new Exception("fare not found")
        );

        if(fareRulesRepository.existsByFareId(fare.getId())) {
            throw new Exception("fare already exists");
        }

        FareRules fareRules = FareRulesMapper.toEntity(request, fare);

        FareRules savedFareRules = fareRulesRepository.save(fareRules);

        return FareRulesMapper.toResponse(savedFareRules);
    }

    @Override
    public FareRulesResponse getFareRulesById(Long id) throws Exception {

        FareRules fareRules = fareRulesRepository.findById(id).orElseThrow(
                ()-> new Exception("Fare Rule not found")
        );

        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public FareRulesResponse getFareRulesByFareId(Long fareId) {

        FareRules fareRules = fareRulesRepository.findByFareId(fareId);
        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) {

        return fareRulesRepository.findByAirlineId(airlineId).stream()
                .map(FareRulesMapper :: toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FareRulesResponse updateFareRules(Long id, FareRulesRequest request) throws Exception {

        FareRules fareRules = fareRulesRepository.findById(id).orElseThrow(
                ()-> new Exception("Fare Rule not found")
        );
        FareRulesMapper.updateEntity(request, fareRules);
        FareRules savedFareRules = fareRulesRepository.save(fareRules);
        return FareRulesMapper.toResponse(savedFareRules);
    }

    @Override
    public void deleteFareRules(Long id) throws Exception {
        FareRules fareRules = fareRulesRepository.findById(id).orElseThrow(
                ()-> new Exception("Fare Rule not found")
        );
        fareRulesRepository.delete(fareRules);
    }
}
