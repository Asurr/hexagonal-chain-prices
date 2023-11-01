package com.microservice.test.application.usecase;

import com.microservice.test.application.mapper.PriceToPriceResponseMapper;
import com.microservice.test.domain.entity.PriceRequest;
import com.microservice.test.domain.entity.PriceResponse;
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
    public PriceResponse find(PriceRequest priceRequest) {
        Comparator<Price> cmp = Comparator.comparing(Price::getPriority);
        log.debug("FindPriceUseCaseImpl::find brandId: {} productId: {} applicationDate {}", priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getApplicationDate());
        return priceToPriceResponseMapper.toPriceResponse(priceRepository.findByFilter(priceRequest).
                stream().max(cmp).orElse(null));
    }
}
