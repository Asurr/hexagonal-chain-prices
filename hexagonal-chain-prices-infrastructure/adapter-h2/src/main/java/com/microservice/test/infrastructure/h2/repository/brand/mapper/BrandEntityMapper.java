package com.microservice.test.infrastructure.h2.repository.brand.mapper;


import com.microservice.test.domain.entity.Brand;
import com.microservice.test.infrastructure.h2.repository.brand.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BrandEntityMapper {

    Brand toDomain(BrandEntity entity);
}
