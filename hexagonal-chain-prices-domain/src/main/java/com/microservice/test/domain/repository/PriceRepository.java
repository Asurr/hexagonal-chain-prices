package com.microservice.test.domain.repository;

import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.Price;

import java.util.List;

public interface PriceRepository {

    List<Price> findByFilter(FindPriceRequest findPriceRequest);

}
