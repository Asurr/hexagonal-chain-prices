package com.microservice.test.infrastructure.rest.mapper;

import com.microservice.test.api.dto.v1.FindPriceResponseDTO;
import com.microservice.test.domain.entity.FindPriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FindPriceResponseMapper {

    FindPriceResponseDTO toDto(FindPriceResponse source);
    
}
