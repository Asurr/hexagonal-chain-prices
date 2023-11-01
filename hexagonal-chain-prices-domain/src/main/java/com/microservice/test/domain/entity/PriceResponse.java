package com.microservice.test.domain.entity;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PriceResponse {

    private Integer productId;

    private Integer brandId;

    private Integer priceList;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private Double price;
}
