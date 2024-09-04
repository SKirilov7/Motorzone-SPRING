package com.example.motorzone.config;

import com.example.motorzone.models.dto.car.CarOfferDetailsDTO;
import com.example.motorzone.models.entities.car.CarOffer;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<CarOffer, CarOfferDetailsDTO>() {
            @Override
            protected void configure() {
                map(source.getModel().getId(), destination.getModelId());
            }
        });

        return modelMapper;
    }
}
