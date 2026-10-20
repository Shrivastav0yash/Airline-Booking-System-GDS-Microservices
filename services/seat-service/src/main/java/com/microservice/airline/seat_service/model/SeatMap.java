package com.microservice.airline.seat_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int totalRows;

    @Column(nullable = false)
    private int rightSeatsPerRow;

    @Column(nullable = false)
    private int leftSeatsPerRow;

    @Column(nullable = false)
    private Long airlineId;

    //todo : watch
    //@OneToMany(mappedBy = "seatMap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Seat> seats;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CabinClass cabinClass;
}
