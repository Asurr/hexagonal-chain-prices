package com.microservice.test.infrastructure.rest.mapper;

import com.microservice.test.api.dto.v1.PriceResponseDTO;
import com.microservice.test.domain.entity.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FindPriceResponseMapper {

    PriceResponseDTO toDto(PriceResponse source);
    
}
