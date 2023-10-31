package com.microservice.test.infrastructure.h2.repository.price.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservice.test.infrastructure.h2.repository.brand.entity.BrandEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity(name = "prices")
@Getter
@Setter
@Table(name = "prices")
public class PriceEntity implements Serializable {

    @Id
    @Column(name = "price_id", columnDefinition = "integer", unique = true,nullable = false)
    @NotNull
    private Integer priceId;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BrandEntity brand;
    @Column(name = "start_date")
    private OffsetDateTime startDate;
    @Column(name = "end_date")
    private OffsetDateTime endDate;
    @Column(name = "price_list", columnDefinition = "integer",nullable = false)
    @NotNull
    private Integer priceList;
    @Column(name = "product_id", columnDefinition = "integer",nullable = false)
    @NotNull
    private Integer productId;
    @Column(name = "priority", columnDefinition = "integer",nullable = false)
    @NotNull
    private Integer priority;
    @Column(name = "price", columnDefinition = "decimal", nullable = false)
    private Double price;
    @Column(name = "currency", columnDefinition = "varchar", length = 5, nullable = false)
    private String currency;
}
