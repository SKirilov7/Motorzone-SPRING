package com.example.motorzone.models.entities.car;

import com.example.motorzone.models.entities.VehicleImage;
import jakarta.persistence.*;

@Entity
@Table(name = "car_images")
public class CarImage extends VehicleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_image_seq")
    @SequenceGenerator(name = "car_image_seq", sequenceName = "car_images_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_offer_id")
    private CarOffer carOffer;

    public CarImage() {}

    public Long getId() {
        return id;
    }

    public CarImage setId(Long id) {
        this.id = id;
        return this;
    }

    public CarOffer getCarOffer() {
        return carOffer;
    }

    public CarImage setCarOffer(CarOffer carOffer) {
        this.carOffer = carOffer;
        return this;
    }

}
