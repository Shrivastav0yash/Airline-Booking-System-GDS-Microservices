package com.microservice.airline.seat_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class FlightInstanceCabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long flightInstanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    private CabinClass cabinClass;

    @Column(nullable = false)
    private Integer totalSeats;

    private Integer bookedSeats = 0;

    //todo : watch
    //
    //private List<SeatInstance> seats = new ArrayList<>();

    public Integer getAvailableSeats(){
        return totalSeats - bookedSeats;
    }

}
