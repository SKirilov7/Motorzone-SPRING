package com.example.motorzone.models.entities.car;

import com.example.motorzone.models.entities.VehicleImage;
import jakarta.persistence.*;

@Entity
@Table(name = "car_images")
public class CarImage extends VehicleImage {

    @ManyToOne
    @JoinColumn(name = "car_offer_id")
    private CarOffer carOffer;

    public CarImage() {}

    public CarOffer getCarOffer() {
        return carOffer;
    }

    public CarImage setCarOffer(CarOffer carOffer) {
        this.carOffer = carOffer;
        return this;
    }

}
