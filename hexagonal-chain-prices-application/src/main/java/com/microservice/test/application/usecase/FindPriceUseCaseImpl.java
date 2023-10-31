package com.microservice.test.application.usecase;

import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.FindPriceResponse;
import com.microservice.test.domain.repository.PriceRepository;
import com.microservice.test.domain.usecase.FindPriceUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class FindPriceUseCaseImpl implements FindPriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public FindPriceResponse find(FindPriceRequest findPriceRequest) {
        return null;
    }
}
