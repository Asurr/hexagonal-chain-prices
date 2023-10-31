package com.microservice.test.application.usecase;

import com.microservice.test.application.mapper.PriceToPriceResponseMapper;
import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.FindPriceResponse;
import com.microservice.test.domain.entity.Price;
import com.microservice.test.domain.repository.PriceRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPriceUseCaseImplTest {

    @InjectMocks
    private FindPriceUseCaseImpl findPriceUseCase;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceToPriceResponseMapper priceToPriceResponseMapper;



    EasyRandom easyRandom = new EasyRandom(getEasyRandomParameters());

    /**
     * find a product with valid data.
     * given reference data from product that is not stored in our system
     * then return null
     */
    @Test
    void given_not_exist_product_reference_return_null() {


        //GIVEN
        FindPriceRequest findPriceRequest = easyRandom.nextObject(FindPriceRequest.class);

        //MOCK
        when(this.priceRepository.findByFilter(ArgumentMatchers.any())).thenReturn(Collections.emptyList());
        when(this.priceToPriceResponseMapper.toPriceResponse(ArgumentMatchers.any())).thenReturn(null);

        //WHEN
        FindPriceResponse result = findPriceUseCase.find(findPriceRequest);

        //THEN
        assertEquals(result, null);

    }

    /**
     * find a product with valid data.
     * given reference data from product that is stored in our system
     * then return the price response
     */
    @Test
    void given_exist_product_reference_return_price_response() {


        //GIVEN
        FindPriceRequest findPriceRequest = easyRandom.nextObject(FindPriceRequest.class);
        FindPriceResponse findPriceResponse = easyRandom.nextObject(FindPriceResponse.class);
        Price price = easyRandom.nextObject(Price.class);

        //MOCK
        when(this.priceRepository.findByFilter(ArgumentMatchers.any())).thenReturn(Arrays.asList(price));
        when(this.priceToPriceResponseMapper.toPriceResponse(ArgumentMatchers.any())).thenReturn(findPriceResponse);

        //WHEN
        FindPriceResponse result = findPriceUseCase.find(findPriceRequest);

        //THEN
        assertEquals(result, findPriceResponse);

    }

    /**
     * find a product with valid data.
     * given reference data from two prices that is stored in our system
     * then return the price response
     */
    @Test
    void given_exist_two_product_reference_return_price_response() {


        //GIVEN
        FindPriceRequest findPriceRequest = easyRandom.nextObject(FindPriceRequest.class);
        FindPriceResponse findPriceResponse = easyRandom.nextObject(FindPriceResponse.class);
        List<Price> prices = easyRandom.objects(Price.class,2).collect(Collectors.toList());

        //MOCK
        when(this.priceRepository.findByFilter(ArgumentMatchers.any())).thenReturn(prices);
        when(this.priceToPriceResponseMapper.toPriceResponse(ArgumentMatchers.any())).thenReturn(findPriceResponse);

        //WHEN
        FindPriceResponse result = findPriceUseCase.find(findPriceRequest);

        //THEN
        assertEquals(result, findPriceResponse);

    }


    private EasyRandomParameters getEasyRandomParameters() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.randomizationDepth(3);
        return parameters;
    }

}