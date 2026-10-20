package com.microservice.airline.seat_service.service.Impl;

import com.microservice.airline.payload.request.SeatMapRequest;
import com.microservice.airline.payload.response.SeatMapResponse;
import com.microservice.airline.seat_service.mapper.SeatMapMapper;
import com.microservice.airline.seat_service.model.CabinClass;
import com.microservice.airline.seat_service.model.SeatMap;
import com.microservice.airline.seat_service.repository.CabinClassRepository;
import com.microservice.airline.seat_service.repository.SeatMapRepository;
import com.microservice.airline.seat_service.service.SeatMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatMapServiceImpl implements SeatMapService {

    private final CabinClassRepository cabinClassRepository;
    private final SeatMapRepository seatMapRepository;

    @Override
    public SeatMapResponse createSeatMap(Long airlineId, SeatMapRequest seatMapRequest) throws Exception {

        CabinClass cabinClass = cabinClassRepository.findById(seatMapRequest.getCabinClassId())
                .orElseThrow(() -> new Exception("Cabin class not found with id: " + seatMapRequest.getCabinClassId()));

        if(seatMapRepository.existsByAirlineIdAndCabinClassIdAndName(
                airlineId, seatMapRequest.getCabinClassId(), seatMapRequest.getName()
        )){
            throw new Exception("Seat map already exists");
        }

        SeatMap seatMap = SeatMapMapper.toEntity(seatMapRequest, cabinClass);
        seatMap.setAirlineId(airlineId);
        SeatMap savedSeatMap = seatMapRepository.save(seatMap);

        //todo : seat related data is missing now

        return SeatMapMapper.toResponse(savedSeatMap);
    }

    @Override
    public SeatMapResponse getSeatMapById(Long id) throws Exception {
        SeatMap seatMap = seatMapRepository.findById(id).orElseThrow(
                () -> new Exception("Seat map not found with id: " + id)
        );

        return SeatMapMapper.toResponse(seatMap);
    }

    @Override
    public SeatMapResponse getSeatMapByCabinClass(Long cabinClassId) {
        SeatMap seatMap = seatMapRepository.findByCabinClassId(cabinClassId);
        return SeatMapMapper.toResponse(seatMap);
    }

    @Override
    public SeatMapResponse updateSeatMap(Long id, SeatMapRequest seatMapRequest) throws Exception {

        SeatMap seatMap = seatMapRepository.findById(id).orElseThrow(
                () -> new Exception("Seat map not found with id: " + id)
        );

        SeatMapMapper.updateEntity(seatMap, seatMapRequest);
        SeatMap updatedSeatMap = seatMapRepository.save(seatMap);

        return SeatMapMapper.toResponse(updatedSeatMap);
    }

    @Override
    public void deleteSeatMap(Long id) throws Exception {

        SeatMap seatMap = seatMapRepository.findById(id).orElseThrow(
                () -> new Exception("Seat map not found with id: " + id)
        );
        seatMapRepository.delete(seatMap);
    }
}
