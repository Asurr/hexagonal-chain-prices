package com.microservice.test.infrastructure.h2.repository.price.repository;

import com.microservice.test.infrastructure.h2.repository.price.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceH2Repository extends JpaRepository<PriceEntity, Integer> {
}
