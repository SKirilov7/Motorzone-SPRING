package com.example.motorzone.models.entities.motorcycle;

import com.example.motorzone.models.entities.VehicleImage;
import jakarta.persistence.*;

@Entity
@Table(name = "motorcycle_images")
public class MotorcycleImage extends VehicleImage {

    @ManyToOne
    @JoinColumn(name = "motorcycle_offer_id", nullable = false)
    private MotorcycleOffer motorcycleOffer;

    public MotorcycleImage() {}

    public MotorcycleOffer getMotorcycleOffer() {
        return motorcycleOffer;
    }

    public MotorcycleImage setMotorcycleOffer(MotorcycleOffer motorcycleOffer) {
        this.motorcycleOffer = motorcycleOffer;
        return this;
    }

}
