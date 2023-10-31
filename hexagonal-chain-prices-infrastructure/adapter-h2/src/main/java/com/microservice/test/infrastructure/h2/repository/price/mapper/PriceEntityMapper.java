package com.microservice.test.infrastructure.h2.repository.price.mapper;

import com.microservice.test.domain.entity.Price;
import com.microservice.test.infrastructure.h2.repository.price.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PriceEntityMapper {

    Price toDomain(PriceEntity entity);
}
