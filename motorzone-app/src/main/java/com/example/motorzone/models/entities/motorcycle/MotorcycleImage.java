package com.example.motorzone.models.entities.motorcycle;

import com.example.motorzone.models.entities.VehicleImage;
import jakarta.persistence.*;

@Entity
@Table(name = "motorcycle_images")
public class MotorcycleImage extends VehicleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_image_seq")
    @SequenceGenerator(name = "motorcycle_image_seq", sequenceName = "motorcycle_images_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "motorcycle_offer_id", nullable = false)
    private MotorcycleOffer motorcycleOffer;

    public MotorcycleImage() {}

    public Long getId() {
        return id;
    }

    public MotorcycleImage setId(Long id) {
        this.id = id;
        return this;
    }

    public MotorcycleOffer getMotorcycleOffer() {
        return motorcycleOffer;
    }

    public MotorcycleImage setMotorcycleOffer(MotorcycleOffer motorcycleOffer) {
        this.motorcycleOffer = motorcycleOffer;
        return this;
    }

}
