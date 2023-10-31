package com.microservice.test.domain.entity;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Price {

    private Integer priceId;

    private Integer brandId;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private Integer priceList;

    private Integer productId;

    private Integer priority;

    private Double price;

    private String currency;

}
