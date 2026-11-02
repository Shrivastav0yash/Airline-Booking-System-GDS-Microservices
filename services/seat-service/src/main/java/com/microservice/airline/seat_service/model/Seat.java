package com.microservice.airline.seat_service.model;

import com.microservice.airline.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private Integer seatRow;

    private Character columnLetter;

    private SeatType seatType;

    private Double basePrice;

    private Double premiumSuperCharge;

    private Boolean isAvailable = true;

    private Boolean isBlocked = false;

    private Boolean isEmergencyExit = false;

    private Boolean isActive = true;

    private Boolean hasExtraLegroom = false;

    private Boolean hasPowerOutlet = false;

    private Boolean hasTvScreen = false;

    private Boolean hasExtraWidth = false;
    private Integer reclineAngle;

    private Integer seatPitch;
    private Integer seatWidth;

    @ManyToOne(fetch = FetchType.LAZY)
    private SeatMap seatMap;

    @ManyToOne(fetch = FetchType.LAZY)
    private CabinClass cabinClass;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false, nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Version
    private Long version;

    public Double getTotalPrice(){
        if(basePrice == null) basePrice = 0.0;
        if(premiumSuperCharge == null) premiumSuperCharge = 0.0;
        return basePrice + premiumSuperCharge;
    }

    public boolean isBookable() {
        return Boolean.TRUE.equals(isAvailable)
                && !Boolean.TRUE.equals(isBlocked)
                && Boolean.TRUE.equals(isActive);
    }

    public String getFullPosition() {
        return seatRow + String.valueOf(columnLetter);
    }
}
