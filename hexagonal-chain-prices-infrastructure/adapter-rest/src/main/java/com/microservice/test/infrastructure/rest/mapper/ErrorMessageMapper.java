package com.microservice.test.infrastructure.rest.mapper;

import com.microservice.test.api.dto.v1.ErrorMessageDTO;
import com.microservice.test.api.dto.v1.PriceRequestDTO;
import com.microservice.test.domain.exception.ErrorMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErrorMessageMapper {

    ErrorMessageDTO toDto(ErrorMessage source);

}
