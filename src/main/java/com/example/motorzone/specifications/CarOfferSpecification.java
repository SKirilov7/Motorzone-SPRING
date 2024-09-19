package com.example.motorzone.specifications;

import com.example.motorzone.models.entities.Brand;
import com.example.motorzone.models.entities.Model;
import com.example.motorzone.models.entities.car.CarOffer;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;

public class CarOfferSpecification {

    public static Specification<CarOffer> hasBrand(String brand) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (brand == null) {
                return builder.conjunction();
            }

            Join<CarOffer, Model> modelJoin = root.join("model", JoinType.INNER);

            Join<Model, Brand> brandJoin = modelJoin.join("brand", JoinType.INNER);

            // Now filter by brand name within the Brand entity
            return builder.equal(brandJoin.get("name"), brand);
        };
    }

    public static Specification<CarOffer> hasModel(String model) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (model == null) {
                return builder.conjunction();
            }

            Join<CarOffer, Model> modelJoin = root.join("model", JoinType.INNER);

            // Now filter by brand name within the Brand entity
            return builder.equal(modelJoin.get("name"), model);
        };
    }

    public static Specification<CarOffer> hasCategory(String category) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                category == null ? null : builder.equal(root.get("category"), category);
    }

    public static Specification<CarOffer> hasCity(String city) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                city == null ? null : builder.equal(root.get("city"), city);
    }

    public static Specification<CarOffer> yearBetween(Integer minYear, Integer maxYear) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            int finalMaxYear = maxYear != null ? maxYear : Year.now().getValue();

            return builder.between(root.get("yearOfManufacture"), minYear, finalMaxYear);
        };
    }

    public static Specification<CarOffer> priceBetween(Double minPrice, Double maxPrice) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                minPrice == null && maxPrice == null ? null :
                        builder.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<CarOffer> displacementBetween(Long minDisplacement, Long maxDisplacement) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                minDisplacement == null && maxDisplacement == null ? null :
                        builder.between(root.get("displacement"), minDisplacement, maxDisplacement);
    }

    public static Specification<CarOffer> horsePowerBetween(Long minHorsePower, Long maxHorsePower) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                minHorsePower == null && maxHorsePower == null ? null :
                        builder.between(root.get("horsePower"), minHorsePower, maxHorsePower);
    }

    public static Specification<CarOffer> hasVehicleCondition(String vehicleCondition) {
        return (Root<CarOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                vehicleCondition == null ? null : builder.equal(root.get("vehicleCondition"), vehicleCondition);
    }

}

