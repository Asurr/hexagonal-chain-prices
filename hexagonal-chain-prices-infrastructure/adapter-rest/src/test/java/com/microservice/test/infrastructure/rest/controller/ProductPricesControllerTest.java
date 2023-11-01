package com.microservice.test.infrastructure.rest.controller;

import com.microservice.test.api.dto.v1.PriceRequestDTO;
import com.microservice.test.api.dto.v1.PriceResponseDTO;
import com.microservice.test.domain.entity.PriceRequest;
import com.microservice.test.domain.entity.PriceResponse;
import com.microservice.test.domain.exception.PriceNotFoundException;
import com.microservice.test.domain.usecase.FindPriceUseCase;
import com.microservice.test.infrastructure.rest.mapper.PriceRequestMapper;
import com.microservice.test.infrastructure.rest.mapper.PriceResponseMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductPricesControllerTest {

    @InjectMocks
    private ProductPricesController productPricesController;

    @Mock
    private FindPriceUseCase findPriceUseCase;

    @Mock
    private PriceRequestMapper findPriceRequestMapper;

    @Mock
    private PriceResponseMapper findPriceResponseMapper;

    EasyRandom easyRandom = new EasyRandom(getEasyRandomParameters());

    /**
     * find a price with valid data.
     * given reference data from product that is stored in our system
     * then return find price response
     */
    @Test
    void given_exist_price_reference_return_find_price_response() {

        //GIVEN
        PriceRequestDTO findPriceRequestDTO = easyRandom.nextObject(PriceRequestDTO.class);
        PriceRequest priceRequest = easyRandom.nextObject(PriceRequest.class);
        PriceResponse priceResponse = easyRandom.nextObject(PriceResponse.class);
        PriceResponseDTO findPriceResponseDTO = easyRandom.nextObject(PriceResponseDTO.class);


        //MOCK
        when(this.findPriceRequestMapper.toDomain(ArgumentMatchers.any())).thenReturn(priceRequest);
        when(this.findPriceUseCase.find(ArgumentMatchers.any())).thenReturn(priceResponse);
        when(this.findPriceResponseMapper.toDto(ArgumentMatchers.any())).thenReturn(findPriceResponseDTO);

        //WHEN
        PriceResponseDTO result = productPricesController.findPrice(findPriceRequestDTO).getBody();

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
        PriceRequestDTO findPriceRequestDTO = easyRandom.nextObject(PriceRequestDTO.class);
        PriceRequest priceRequest = easyRandom.nextObject(PriceRequest.class);

        //MOCK
        when(this.findPriceRequestMapper.toDomain(ArgumentMatchers.any())).thenReturn(priceRequest);
        when(this.findPriceUseCase.find(ArgumentMatchers.any())).thenReturn(null);

        //THEN
        assertThrows(PriceNotFoundException.class,
                ()-> productPricesController.findPrice(findPriceRequestDTO).getBody());

    }


    private EasyRandomParameters getEasyRandomParameters() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.randomizationDepth(3);
        return parameters;
    }

}