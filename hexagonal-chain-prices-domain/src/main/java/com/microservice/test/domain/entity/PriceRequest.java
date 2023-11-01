package com.microservice.test.domain.entity;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PriceRequest {

    private OffsetDateTime applicationDate;

    private Integer productId;

    private Integer brandId;
}
