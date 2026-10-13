package com.microservice.airline.seat_service.model;

import com.microservice.airline.enums.CabinClassType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CabinClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CabinClassType name;

    @Column(nullable = false)
    private String code;

    private String description;

    //todo : watch
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "seat_map_id")
//    private SeatMap seatMap;

    @Column(nullable = false)
    private Long aircraftId;

    @Column(nullable = false)
    private Integer displayOrder = 0;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private Boolean isBookable = true;

    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;
    private Integer seatType;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(updatable = false, nullable = false)
    private Instant updatedAt;

}
