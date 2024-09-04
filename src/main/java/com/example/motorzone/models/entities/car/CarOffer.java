package com.example.motorzone.models.entities.car;

import com.example.motorzone.models.entities.BaseOfferDetails;
import com.example.motorzone.models.enums.CarExtrasEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_offers")
public class CarOffer extends BaseOfferDetails {

    // extras collection is lazily loaded by default
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "car_extras_mapping", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "extra_enum_value")
    private List<CarExtrasEnum> extras;

    @OneToMany(
            mappedBy = "carOffer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CarImage> carImages = new ArrayList<>();

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    public CarOffer() {}

    public List<CarExtrasEnum> getExtras() {
        return extras;
    }

    public CarOffer setExtras(List<CarExtrasEnum> extras) {
        this.extras = extras;
        return this;
    }

    public List<CarImage> getCarImages() {
        return carImages;
    }

    public CarOffer setCarImages(List<CarImage> carImages) {
        this.carImages = carImages;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CarOffer setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public CarOffer setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public CarOffer setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }
}
