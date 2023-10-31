package com.microservice.test.infrastructure.rest.controller;

import com.microservice.test.api.dto.v1.FindPriceRequestDTO;
import com.microservice.test.api.dto.v1.FindPriceResponseDTO;
import com.microservice.test.controller.v1.HexagonalChainPricesApi;
import com.microservice.test.infrastructure.rest.exception.PriceNotFoundException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Scope;

@RequiredArgsConstructor
@RestController
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class ProductPricesController implements HexagonalChainPricesApi {

    @Override
    @Retry(name = "RetryfindPrice", fallbackMethod = "fallbackAfterRetry")
    @Bulkhead(name="BulkheadfindPrice")
    @RateLimiter(name = "RatelimiterfindPrice")
    public ResponseEntity<FindPriceResponseDTO> findPrice(FindPriceRequestDTO body) {

        FindPriceResponseDTO findPriceResponseDTO = null;
        //llamada al usecase
        if(findPriceResponseDTO==null){
            throw new PriceNotFoundException("Price not found");
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public String fallbackAfterRetry(Exception ex) {
        return "all retries have exhausted";
    }

}
