package com.microservice.airline.pricing_service.service;

import com.microservice.airline.payload.request.BaggagePolicyRequest;
import com.microservice.airline.payload.response.BaggagePolicyResponse;

import java.util.List;

public interface BaggagePolicyService {

    BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest request) throws Exception;

    BaggagePolicyResponse getBaggagePolicyById(Long id);

    BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId);

    List<BaggagePolicyResponse> getBaggagePoliciesByAirlineId(Long airlineId);

    BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest request);

    void deleteBaggagePolicy(Long id);

}
