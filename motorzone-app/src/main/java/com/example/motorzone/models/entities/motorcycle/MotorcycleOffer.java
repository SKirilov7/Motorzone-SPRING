package com.example.motorzone.models.entities.motorcycle;

import com.example.motorzone.models.entities.BaseOfferDetails;
import com.example.motorzone.models.enums.MotorcycleExtrasEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "motorcycle_offers")
public class MotorcycleOffer extends BaseOfferDetails {
    // extras collection is lazily loaded by default
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "motorcycle_extras_mapping", joinColumns = @JoinColumn(name = "motorcycle_id"))
    @Column(name = "extra_enum_value")
    private List<MotorcycleExtrasEnum> extras;

    @OneToMany(
            mappedBy = "motorcycleOffer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MotorcycleImage> motorcyleImages = new ArrayList<>();

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    public MotorcycleOffer() {}

    public List<MotorcycleExtrasEnum> getExtras() {
        return extras;
    }

    public MotorcycleOffer setExtras(List<MotorcycleExtrasEnum> extras) {
        this.extras = extras;
        return this;
    }

    public List<MotorcycleImage> getMotorcyleImages() {
        return motorcyleImages;
    }

    public MotorcycleOffer setMotorcyleImages(List<MotorcycleImage> motorcyleImages) {
        this.motorcyleImages = motorcyleImages;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public MotorcycleOffer setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public MotorcycleOffer setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public MotorcycleOffer setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }
}
