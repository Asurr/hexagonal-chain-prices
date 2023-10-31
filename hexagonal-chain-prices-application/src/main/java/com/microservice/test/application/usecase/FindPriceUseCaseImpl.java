package com.microservice.test.application.usecase;

import com.microservice.test.application.mapper.PriceToPriceResponseMapper;
import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.FindPriceResponse;
import com.microservice.test.domain.entity.Price;
import com.microservice.test.domain.repository.PriceRepository;
import com.microservice.test.domain.usecase.FindPriceUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
@AllArgsConstructor
@Slf4j
public class FindPriceUseCaseImpl implements FindPriceUseCase {

    private final PriceRepository priceRepository;

    private final PriceToPriceResponseMapper priceToPriceResponseMapper;

    @Override
    public FindPriceResponse find(FindPriceRequest findPriceRequest) {
        Comparator<Price> cmp = Comparator.comparing(Price::getPriority);
        Price price = priceRepository.findByFilter(findPriceRequest).
        stream().max(cmp).orElse(null);
        return priceToPriceResponseMapper.toPriceResponse(price);
    }
}
