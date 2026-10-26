package com.microservice.airline.seat_service.service.Impl;

import com.microservice.airline.payload.request.FlightInstanceCabinRequest;
import com.microservice.airline.payload.response.FlightInstanceCabinResponse;
import com.microservice.airline.seat_service.mapper.FlightInstanceCabinMapper;
import com.microservice.airline.seat_service.model.CabinClass;
import com.microservice.airline.seat_service.model.FlightInstanceCabin;
import com.microservice.airline.seat_service.model.SeatMap;
import com.microservice.airline.seat_service.repository.CabinClassRepository;
import com.microservice.airline.seat_service.repository.FlightInstanceCabinRepository;
import com.microservice.airline.seat_service.repository.SeatMapRepository;
import com.microservice.airline.seat_service.service.FlightInstanceCabinService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightInstanceCabinServiceImpl implements FlightInstanceCabinService {

    private final CabinClassRepository cabinClassRepository;
    private final SeatMapRepository seatMapRepository;
    private final FlightInstanceCabinRepository flightInstanceCabinRepository;

    @Override
    public FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request) throws Exception {
        CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId())
                .orElseThrow(() -> new Exception("Cabin Class not found"));

        SeatMap seatMap = seatMapRepository.findByCabinClassId(cabinClass.getId());

        if(seatMap == null){
            throw new Exception("Seat Map not found");
        }

        if(seatMap.getSeats() == null || seatMap.getSeats().isEmpty()){
            throw new Exception("No Seats found in Seat Map");
        }

        int totalSeats = seatMap.getSeats().size();
        FlightInstanceCabin cabin = FlightInstanceCabin.builder()
                .flightInstanceId(request.getFlightInstanceId())
                .cabinClass(cabinClass)
                .totalSeats(totalSeats)
                .bookedSeats(0)
                .build();

        FlightInstanceCabin savedCabin = flightInstanceCabinRepository.save(cabin);

        //todo : generate Seats Instance

        return FlightInstanceCabinMapper.toResponse(savedCabin);
    }

    @Override
    public FlightInstanceCabinResponse getFlightInstanceCabinById(Long id) {

        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Flight instance cabin not found with id : " +id)
        );

        return FlightInstanceCabinMapper.toResponse(fic);
    }

    @Override
    public Page<FlightInstanceCabinResponse> getByFlightInstanceId(Long id, Pageable pageable) {

        return flightInstanceCabinRepository.findByFlightInstanceId(id, pageable)
                .map(FlightInstanceCabinMapper::toResponse);
    }

    @Override
    public FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId) {

        FlightInstanceCabin cabin = flightInstanceCabinRepository.findByFlightInstanceIdAndCabinClassId(
                flightInstanceId, cabinClassId
        );

        return FlightInstanceCabinMapper.toResponse(cabin);
    }

    @Override
    public FlightInstanceCabinResponse updateFlightInstanceCabin(Long id, FlightInstanceCabinRequest request) throws Exception {

        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Flight instance cabin not found with id : " +id)
        );

        if(request.getCabinClassId() != null){
            CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId())
                    .orElseThrow(() -> new Exception("Cabin Class not found"));
            fic.setCabinClass(cabinClass);
        }
        FlightInstanceCabin updated = flightInstanceCabinRepository.save(fic);
        return FlightInstanceCabinMapper.toResponse(updated);
    }

    @Override
    public void deleteFlightInstanceCabin(Long id) {

        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Flight instance cabin not found with id : " +id)
        );
        flightInstanceCabinRepository.delete(fic);
    }
}
