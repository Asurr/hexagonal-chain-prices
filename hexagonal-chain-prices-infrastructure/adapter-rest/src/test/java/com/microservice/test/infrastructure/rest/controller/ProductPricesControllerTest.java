package com.microservice.test.infrastructure.rest.controller;

import com.microservice.test.api.dto.v1.FindPriceRequestDTO;
import com.microservice.test.api.dto.v1.FindPriceResponseDTO;
import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.FindPriceResponse;
import com.microservice.test.domain.entity.Price;
import com.microservice.test.domain.exception.PriceNotFoundException;
import com.microservice.test.domain.usecase.FindPriceUseCase;
import com.microservice.test.infrastructure.rest.mapper.FindPriceRequestMapper;
import com.microservice.test.infrastructure.rest.mapper.FindPriceResponseMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductPricesControllerTest {

    @InjectMocks
    private ProductPricesController productPricesController;

    @Mock
    private FindPriceUseCase findPriceUseCase;

    @Mock
    private FindPriceRequestMapper findPriceRequestMapper;

    @Mock
    private FindPriceResponseMapper findPriceResponseMapper;

    EasyRandom easyRandom = new EasyRandom(getEasyRandomParameters());

    /**
     * find a price with valid data.
     * given reference data from product that is stored in our system
     * then return find price response
     */
    @Test
    void given_exist_price_reference_return_find_price_response() {

        //GIVEN
        FindPriceRequestDTO findPriceRequestDTO = easyRandom.nextObject(FindPriceRequestDTO.class);
        FindPriceRequest findPriceRequest = easyRandom.nextObject(FindPriceRequest.class);
        FindPriceResponse findPriceResponse = easyRandom.nextObject(FindPriceResponse.class);
        FindPriceResponseDTO findPriceResponseDTO = easyRandom.nextObject(FindPriceResponseDTO.class);


        //MOCK
        when(this.findPriceRequestMapper.toDomain(ArgumentMatchers.any())).thenReturn(findPriceRequest);
        when(this.findPriceUseCase.find(ArgumentMatchers.any())).thenReturn(findPriceResponse);
        when(this.findPriceResponseMapper.toDto(ArgumentMatchers.any())).thenReturn(findPriceResponseDTO);

        //WHEN
        FindPriceResponseDTO result = productPricesController.findPrice(findPriceRequestDTO).getBody();

        //THEN
        assertEquals(result, findPriceResponseDTO);

    }

    /**
     * find a price with valid data.
     * given reference data from product that is not stored in our system
     * then return find price response
     */
    @Test
    void given_not_exist_price_reference_return_find_price_response() {

        //GIVEN
        FindPriceRequestDTO findPriceRequestDTO = easyRandom.nextObject(FindPriceRequestDTO.class);
        FindPriceRequest findPriceRequest = easyRandom.nextObject(FindPriceRequest.class);

        //MOCK
        when(this.findPriceRequestMapper.toDomain(ArgumentMatchers.any())).thenReturn(findPriceRequest);
        when(this.findPriceUseCase.find(ArgumentMatchers.any())).thenReturn(null);

        //THEN
        assertThrows(PriceNotFoundException.class,
                ()->{
                    productPricesController.findPrice(findPriceRequestDTO).getBody();
                });


    }


    private EasyRandomParameters getEasyRandomParameters() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.randomizationDepth(3);
        return parameters;
    }

}