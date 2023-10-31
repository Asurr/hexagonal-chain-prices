package com.microservice.test.infrastructure.h2.repository.brand.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "brands")
@Getter
@Setter
@Table(name = "brands")
public class BrandEntity implements Serializable {

    @Id
    @Column(name = "brand_id", columnDefinition = "integer", unique = true,nullable = false)
    @NotNull
    private Integer brandId;
    @Column(name = "code", columnDefinition = "varchar", length = 2, nullable = false)
    private String code;
    @Column(name = "description", columnDefinition = "varchar", length = 255, nullable = false)
    private String description;
    @Column(name = "distribution_center", columnDefinition = "integer", unique = true,nullable = false)
    @NotNull
    private Integer distributionCenter;

}
