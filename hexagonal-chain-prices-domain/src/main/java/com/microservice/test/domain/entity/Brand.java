package com.microservice.test.domain.entity;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Brand {

    private Integer brandId;

    private String code;

    private String description;

    private Integer distributionCenter;
}
