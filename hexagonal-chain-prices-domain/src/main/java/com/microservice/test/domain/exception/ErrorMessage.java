package com.microservice.test.domain.exception;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ErrorMessage {

    private Integer statusCode;
    private OffsetDateTime errorTimestamp;
    private String message;
    private String description;
}