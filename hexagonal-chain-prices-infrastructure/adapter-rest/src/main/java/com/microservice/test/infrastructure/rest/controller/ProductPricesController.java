package com.microservice.test.infrastructure.rest.controller;

import com.microservice.test.api.dto.v1.ErrorMessageDTO;
import com.microservice.test.api.dto.v1.PriceRequestDTO;
import com.microservice.test.api.dto.v1.PriceResponseDTO;
import com.microservice.test.controller.v1.HexagonalChainPricesApi;
import com.microservice.test.domain.entity.PriceResponse;
import com.microservice.test.domain.exception.PriceNotFoundException;
import com.microservice.test.domain.usecase.FindPriceUseCase;
import com.microservice.test.infrastructure.rest.exception.ControllerExceptionHandler;
import com.microservice.test.infrastructure.rest.mapper.FindPriceRequestMapper;
import com.microservice.test.infrastructure.rest.mapper.FindPriceResponseMapper;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

    private final ControllerExceptionHandler controllerExceptionHandler;


    @Override
    @Retry(name = "RetryfindPrice", fallbackMethod = "fallbackAfterRetry")
    @Bulkhead(name="BulkheadfindPrice")
    @RateLimiter(name = "RatelimiterfindPrice")
    public ResponseEntity<PriceResponseDTO> findPrice(PriceRequestDTO body) {

        PriceResponse response = findPriceUseCase.find(findPriceRequestMapper.toDomain(body));
        if(response==null){
            throw new PriceNotFoundException("Price not found");
        }
        return ResponseEntity.ok(findPriceResponseMapper.toDto(response));
    }

    public ResponseEntity<ErrorMessageDTO> fallbackAfterRetry(Exception ex) {
        return controllerExceptionHandler.handleAfterRetries(ex);
    }

}
