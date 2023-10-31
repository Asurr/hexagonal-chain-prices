package com.microservice.test.infrastructure.h2.repository.price;

import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.Price;
import com.microservice.test.domain.repository.PriceRepository;
import com.microservice.test.infrastructure.h2.repository.price.repository.PriceH2Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class PriceRepositoryImpl implements PriceRepository {

    PriceH2Repository priceH2Repository;

    @Override
    public List<Price> findByFilter(FindPriceRequest findPriceRequest) {
        log.debug("PriceRepositoryImpl::findByFilter");
        return null;
    }
}
