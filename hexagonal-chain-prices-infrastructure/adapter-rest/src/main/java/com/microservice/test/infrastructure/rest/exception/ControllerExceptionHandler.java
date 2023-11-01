package com.microservice.test.infrastructure.rest.exception;

import com.microservice.test.api.dto.v1.ErrorMessageDTO;
import com.microservice.test.domain.exception.ErrorMessage;
import com.microservice.test.domain.exception.PriceNotFoundException;
import com.microservice.test.infrastructure.rest.mapper.ErrorMessageMapper;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorMessageMapper errorMessageMapper;

    @ExceptionHandler({ CallNotPermittedException.class })
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorMessageDTO> handleCallNotPermittedException(CallNotPermittedException ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .errorTimestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessageMapper.toDto(errorMessage), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({ TimeoutException.class })
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ResponseEntity<ErrorMessageDTO> handleTimeoutException(TimeoutException ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.REQUEST_TIMEOUT.value())
                .errorTimestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessageMapper.toDto(errorMessage), HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler({ BulkheadFullException.class })
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public ResponseEntity<ErrorMessageDTO> handleBulkheadFullException(BulkheadFullException ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value())
                .errorTimestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessageMapper.toDto(errorMessage), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

    @ExceptionHandler({ RequestNotPermitted.class })
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResponseEntity<ErrorMessageDTO> handleRequestNotPermitted(RequestNotPermitted ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.TOO_MANY_REQUESTS.value())
                .errorTimestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessageMapper.toDto(errorMessage), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handlePriceNotFoundHandler(PriceNotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorTimestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessageMapper.toDto(errorMessage), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> handleGlobalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorTimestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessageMapper.toDto(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ErrorMessageDTO> handleAfterRetries(Exception ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .errorTimestamp(OffsetDateTime.now())
                .message(ex.getMessage())
                .description("all retries have exhausted")
                .build();

        return new ResponseEntity<>(errorMessageMapper.toDto(errorMessage), HttpStatus.SERVICE_UNAVAILABLE);
    }

}
