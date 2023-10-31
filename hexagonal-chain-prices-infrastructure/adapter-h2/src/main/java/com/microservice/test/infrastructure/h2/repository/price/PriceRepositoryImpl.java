package com.microservice.test.infrastructure.h2.repository.price;

import com.microservice.test.domain.entity.FindPriceRequest;
import com.microservice.test.domain.entity.Price;
import com.microservice.test.domain.repository.PriceRepository;
import com.microservice.test.infrastructure.h2.repository.price.entity.PriceEntity;
import com.microservice.test.infrastructure.h2.repository.price.mapper.PriceEntityMapper;
import com.microservice.test.infrastructure.h2.repository.price.repository.PriceH2Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class PriceRepositoryImpl implements PriceRepository {

    PriceH2Repository priceH2Repository;

    PriceEntityMapper priceEntityMapper;

    @Override
    public List<Price> findByFilter(FindPriceRequest findPriceRequest) {
        log.debug("PriceRepositoryImpl::findByFilter");
        return priceH2Repository.findByFilter(findPriceRequest.getApplicationDate(),findPriceRequest.getProductId(),findPriceRequest.getBrandId())
                .stream().map(priceEntityMapper::toDomain).collect(Collectors.toList());
    }
}
