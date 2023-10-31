package com.microservice.test.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FindPriceRequest {

    private LocalDateTime applicationDate;

    private Integer productId;

    private Integer brandId;
}
