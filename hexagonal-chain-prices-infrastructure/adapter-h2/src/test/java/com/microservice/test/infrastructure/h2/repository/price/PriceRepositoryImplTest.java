package com.microservice.test.infrastructure.h2.repository.price;

import com.microservice.test.application.mapper.PriceToPriceResponseMapper;
import com.microservice.test.application.usecase.FindPriceUseCaseImpl;
import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.FindPriceResponse;
import com.microservice.test.domain.entity.Price;
import com.microservice.test.domain.repository.PriceRepository;
import com.microservice.test.infrastructure.h2.repository.price.entity.PriceEntity;
import com.microservice.test.infrastructure.h2.repository.price.mapper.PriceEntityMapper;
import com.microservice.test.infrastructure.h2.repository.price.repository.PriceH2Repository;
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
class PriceRepositoryImplTest {

    @InjectMocks
    private PriceRepositoryImpl priceRepository;

    @Mock
    private PriceH2Repository priceH2Repository;

    @Mock
    private PriceEntityMapper priceEntityMapper;

    EasyRandom easyRandom = new EasyRandom(getEasyRandomParameters());

    /**
     * find a price with valid data.
     * given reference data from product that is not stored in our system
     * then return empty list
     */
    @Test
    void given_not_exist_price_reference_return_empty_list() {

        //GIVEN
        FindPriceRequest findPriceRequest = easyRandom.nextObject(FindPriceRequest.class);

        //MOCK
        when(this.priceH2Repository.findByFilter(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(Collections.emptyList());

        //WHEN
        List<Price> result = priceRepository.findByFilter(findPriceRequest);

        //THEN
        assertEquals(result, Collections.emptyList());

    }

    /**
     * find a price with valid data.
     * given reference data from product that is stored in our system
     * then return empty list
     */
    @Test
    void given_exist_price_reference_return_empty_list() {

        //GIVEN
        FindPriceRequest findPriceRequest = easyRandom.nextObject(FindPriceRequest.class);
        List<PriceEntity> priceEntities = easyRandom.objects(PriceEntity.class,2).collect(Collectors.toList());
        List<Price> prices = easyRandom.objects(Price.class,2).collect(Collectors.toList());

        //MOCK
        when(this.priceH2Repository.findByFilter(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(priceEntities);
        when(this.priceEntityMapper.toDomain(ArgumentMatchers.any())).thenReturn(prices.get(0)).thenReturn(prices.get(1));

        //WHEN
        List<Price> result = priceRepository.findByFilter(findPriceRequest);

        //THEN
        assertEquals(result, prices);

    }

    private EasyRandomParameters getEasyRandomParameters() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.randomizationDepth(3);
        return parameters;
    }

}