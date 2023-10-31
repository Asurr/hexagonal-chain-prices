package com.microservice.test.domain.usecase;

import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.FindPriceResponse;

public interface FindPriceUseCase {

    FindPriceResponse find(FindPriceRequest findPriceRequest);

}
