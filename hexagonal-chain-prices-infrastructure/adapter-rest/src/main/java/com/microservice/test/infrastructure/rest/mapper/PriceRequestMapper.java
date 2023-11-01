package com.microservice.test.infrastructure.rest.mapper;

import com.microservice.test.api.dto.v1.PriceRequestDTO;
import com.microservice.test.domain.entity.PriceRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceRequestMapper {

    PriceRequest toDomain(PriceRequestDTO source);

}
