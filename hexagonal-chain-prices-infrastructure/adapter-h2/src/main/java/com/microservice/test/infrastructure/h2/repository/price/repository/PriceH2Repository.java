package com.microservice.test.infrastructure.h2.repository.price.repository;

import com.microservice.test.infrastructure.h2.repository.price.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PriceH2Repository extends JpaRepository<PriceEntity, Integer> {

    @Query("SELECT pri FROM prices as pri WHERE start_date <= :applicationDate AND end_date >= :applicationDate AND product_id=:productId AND brand_id=:brandId")
    List<PriceEntity> findByFilter(@Param("applicationDate") OffsetDateTime applicationDate, @Param("productId") Integer productId, @Param("brandId") Integer brandId);
}
