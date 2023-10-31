package com.microservice.test.infrastructure.h2.repository.price.mapper;

import com.microservice.test.domain.entity.Price;
import com.microservice.test.infrastructure.h2.repository.brand.mapper.BrandEntityMapper;
import com.microservice.test.infrastructure.h2.repository.price.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = BrandEntityMapper.class)
public interface PriceEntityMapper {

    @Mapping(source="brand.brandId",target="brandId")
    Price toDomain(PriceEntity entity);
}
