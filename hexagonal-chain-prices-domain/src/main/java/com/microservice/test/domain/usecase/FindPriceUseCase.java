package com.microservice.test.domain.usecase;

import com.microservice.test.domain.entity.PriceRequest;
import com.microservice.test.domain.entity.PriceResponse;

public interface FindPriceUseCase {

    PriceResponse find(PriceRequest priceRequest);

}
