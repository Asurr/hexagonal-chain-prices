package com.microservice.test.application.mapper;

import com.microservice.test.domain.entity.FindPriceResponse;
import com.microservice.test.domain.entity.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceToPriceResponseMapper {

    FindPriceResponse toPriceResponse(Price source);

}
