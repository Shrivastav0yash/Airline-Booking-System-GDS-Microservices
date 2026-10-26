package com.microservice.airline.seat_service.service.Impl;

import com.microservice.airline.enums.SeatType;
import com.microservice.airline.payload.request.SeatRequest;
import com.microservice.airline.payload.response.SeatResponse;
import com.microservice.airline.seat_service.mapper.SeatMapper;
import com.microservice.airline.seat_service.model.Seat;
import com.microservice.airline.seat_service.model.SeatMap;
import com.microservice.airline.seat_service.repository.SeatMapRepository;
import com.microservice.airline.seat_service.repository.SeatRepository;
import com.microservice.airline.seat_service.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapRepository seatMapRepository;

    @Override
    public void generateSeats(Long seatMapId) throws Exception {

        boolean exists = seatRepository.existsBySeatMapId(seatMapId);
        if(exists){
            throw new Exception("Seats already generated for this seat map");
        }
        SeatMap seatMap = seatMapRepository.findById(seatMapId).orElseThrow(() -> new Exception("Seat map not found"));

        int leftSeatsPerRow = seatMap.getLeftSeatsPerRow();
        int rightSeatsPerRow = seatMap.getRightSeatsPerRow();
        int rows = seatMap.getTotalRows();
        int seatPerRow = leftSeatsPerRow + rightSeatsPerRow;

        List<Seat> seats = new ArrayList<>();
        
        for(int row = 1; row <= rows; row++){
            for(int col =0; col <= seatPerRow; col++){
                String seatNum = row + getSeatLetter(col);
                SeatType type = getSeatType(col, leftSeatsPerRow, rightSeatsPerRow);

                Seat seat = Seat.builder()
                        .seatNumber(seatNum)
                        .seatRow(row)
                        .columnLetter(getSeatLetter(col).charAt(0))
                        .seatType(type)
                        .seatMap(seatMap)
                        .build();

                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);

    }

    private SeatType getSeatType(int col, int leftSeatsPerRow, int rightSeatsPerRow) {
        int totalSeats = leftSeatsPerRow + rightSeatsPerRow;

        //windows
        if(col == 0 || col == totalSeats -1) return SeatType.WINDOW;

        //left aisle
        if(col == leftSeatsPerRow -1) return SeatType.AISLE;

        //right aisle
        if(col == rightSeatsPerRow -1) return SeatType.AISLE;

        return SeatType.MIDDLE;
    }

    private String getSeatLetter(int col) {
        StringBuilder sb = new StringBuilder();
        while(col >= 0){
            sb.insert(0, (char) ('A' + (col % 26)));
            col = col/26 -1;
        }
        return sb.toString();
    }

    @Override
    public SeatResponse updateSeats(Long seatId, SeatRequest seatRequest) {

        return null;
    }

    @Override
    public List<SeatResponse> getAll() {
        return seatRepository.findAll()
                .stream()
                .map(SeatMapper :: toResponse)
                .collect(Collectors.toList());
    }
}
