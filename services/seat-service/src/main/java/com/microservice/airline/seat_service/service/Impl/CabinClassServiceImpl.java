package com.microservice.airline.seat_service.service.Impl;

import com.microservice.airline.enums.CabinClassType;
import com.microservice.airline.payload.request.CabinClassRequest;
import com.microservice.airline.payload.response.CabinClassResponse;
import com.microservice.airline.seat_service.mapper.CabinClassMapper;
import com.microservice.airline.seat_service.model.CabinClass;
import com.microservice.airline.seat_service.repository.CabinClassRepository;
import com.microservice.airline.seat_service.service.CabinClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CabinClassServiceImpl implements CabinClassService {

    private final CabinClassRepository cabinClassRepository;

    @Override
    public CabinClassResponse createCabinClass(CabinClassRequest cabinClassRequest) throws Exception {

        if(cabinClassRepository.existsByCodeAndAircraftId(cabinClassRequest.getCode(), cabinClassRequest.getAircraftId())) {
            throw new Exception("Cabin class with code " + cabinClassRequest.getCode() + " already exists for aircraft with ID " + cabinClassRequest.getAircraftId());
        }
        CabinClass cabinClass = CabinClassMapper.toEntity(cabinClassRequest);
        CabinClass savedCabinClass = cabinClassRepository.save(cabinClass);
        return CabinClassMapper.toResponse(savedCabinClass);
    }

    @Override
    public CabinClassResponse getCabinClassById(Long id) {

        CabinClass cabinClass = cabinClassRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cabin class with ID " + id + " not found")
        );
        return CabinClassMapper.toResponse(cabinClass);
    }

    @Override
    public List<CabinClassResponse> getCabinClassByAircraftId(Long aircraftId) {
        return cabinClassRepository.findByAircraftId(aircraftId)
                .stream()
                .map(CabinClassMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CabinClassResponse getCabinClassByAircraftIdAndName(Long aircraftId, CabinClassType name) {
        CabinClass cabinClass = cabinClassRepository.findByAircraftIdAndName(aircraftId, name);
        return CabinClassMapper.toResponse(cabinClass);
    }

    @Override
    public CabinClassResponse updateCabinClass(Long id, CabinClassRequest cabinClassRequest) throws Exception {
        CabinClass cabinClass = cabinClassRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cabin class with ID " + id + " not found")
        );
        if(cabinClassRepository.existsByCodeAndAircraftIdAndIdNot(cabinClassRequest.getCode().toUpperCase(), cabinClass.getAircraftId(), cabinClass.getId())) {
            throw new Exception("Cabin class with code " + cabinClassRequest.getCode() + " already exists for aircraft with ID " + cabinClassRequest.getAircraftId());
        }
        CabinClassMapper.updateEntity(cabinClass, cabinClassRequest);
        CabinClass updatedCabinClass = cabinClassRepository.save(cabinClass);
        return CabinClassMapper.toResponse(updatedCabinClass);
    }

    @Override
    public void deleteCabinClass(Long id) {
        CabinClass cabinClass = cabinClassRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cabin class with ID " + id + " not found")
        );
        cabinClassRepository.delete(cabinClass);
    }
}
