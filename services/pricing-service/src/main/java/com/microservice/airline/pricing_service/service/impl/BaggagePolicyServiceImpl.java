package com.microservice.airline.pricing_service.service.impl;

import com.microservice.airline.payload.request.BaggagePolicyRequest;
import com.microservice.airline.payload.response.BaggagePolicyResponse;
import com.microservice.airline.pricing_service.mapper.BaggagePolicyMapper;
import com.microservice.airline.pricing_service.model.BaggagePolicy;
import com.microservice.airline.pricing_service.model.Fare;
import com.microservice.airline.pricing_service.repository.BaggagePolicyRepository;
import com.microservice.airline.pricing_service.repository.FareRepository;
import com.microservice.airline.pricing_service.service.BaggagePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BaggagePolicyServiceImpl implements BaggagePolicyService {

    private final BaggagePolicyRepository baggagePolicyRepository;
    private final FareRepository fareRepository;

    @Override
    public BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest request) throws Exception {

        Fare fare = fareRepository.findById(request.getFareId())
                .orElseThrow(() -> new Exception("Baggage Policy Not Found "));

        if(baggagePolicyRepository.existsByFareId(fare.getId())){
            throw new Exception("Baggage Policy Already Exists");
        }

        BaggagePolicy baggagePolicy = BaggagePolicyMapper.toEntity(request, fare);
        BaggagePolicy saved = baggagePolicyRepository.save(baggagePolicy);
        return BaggagePolicyMapper.toResponse(saved);

    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyById(Long id) {

        BaggagePolicy baggagePolicy = baggagePolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Baggage Policy Not Found"));
        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId) {
        BaggagePolicy baggagePolicy = baggagePolicyRepository.findByFareId(fareId);
        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public List<BaggagePolicyResponse> getBaggagePoliciesByAirlineId(Long airlineId) {
        return baggagePolicyRepository.findByAirlineId(airlineId).stream()
                .map(BaggagePolicyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest request) {
        BaggagePolicy baggagePolicy = baggagePolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Baggage Policy Not Found"));
        BaggagePolicyMapper.updateEntity(baggagePolicy, request);
        BaggagePolicy updated = baggagePolicyRepository.save(baggagePolicy);
        return BaggagePolicyMapper.toResponse(updated);
    }

    @Override
    public void deleteBaggagePolicy(Long id) {
        BaggagePolicy policy = baggagePolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Baggage Policy Not Found"));
        baggagePolicyRepository.delete(policy);
    }
}
