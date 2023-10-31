package com.microservice.test.domain.usecase;

import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.FindPriceResponse;
import com.microservice.test.domain.entity.Price;

import java.util.List;

public interface FindPriceUseCase {

    FindPriceResponse find(FindPriceRequest findPriceRequest);

}
