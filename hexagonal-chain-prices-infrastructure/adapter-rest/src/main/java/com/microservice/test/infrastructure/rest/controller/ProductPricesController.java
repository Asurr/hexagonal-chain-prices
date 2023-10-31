package com.microservice.test.infrastructure.rest.controller;

import com.microservice.test.api.dto.v1.FindPriceRequestDTO;
import com.microservice.test.api.dto.v1.FindPriceResponseDTO;
import com.microservice.test.controller.v1.HexagonalChainPricesApi;
import com.microservice.test.domain.entity.FindPriceResponse;
import com.microservice.test.domain.exception.PriceNotFoundException;
import com.microservice.test.domain.usecase.FindPriceUseCase;
import com.microservice.test.infrastructure.rest.mapper.FindPriceRequestMapper;
import com.microservice.test.infrastructure.rest.mapper.FindPriceResponseMapper;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Scope;

@RequiredArgsConstructor
@RestController
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class ProductPricesController implements HexagonalChainPricesApi {

    private final FindPriceUseCase findPriceUseCase;

    private final FindPriceRequestMapper findPriceRequestMapper;

    private final FindPriceResponseMapper findPriceResponseMapper;

    @Override
//    @Retry(name = "RetryfindPrice", fallbackMethod = "fallbackAfterRetry")
    @Bulkhead(name="BulkheadfindPrice")
    @RateLimiter(name = "RatelimiterfindPrice")
    public ResponseEntity<FindPriceResponseDTO> findPrice(FindPriceRequestDTO body) {

        FindPriceResponse response = findPriceUseCase.find(findPriceRequestMapper.toDomain(body));
        if(response==null){
            throw new PriceNotFoundException("Price not found");
        }
        return ResponseEntity.ok(findPriceResponseMapper.toDto(response));
    }

    public String fallbackAfterRetry(Exception ex) {
        return "all retries have exhausted";
    }

}
