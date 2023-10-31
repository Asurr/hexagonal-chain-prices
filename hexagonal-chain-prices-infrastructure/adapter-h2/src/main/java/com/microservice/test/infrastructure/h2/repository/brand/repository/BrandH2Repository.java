package com.microservice.test.infrastructure.h2.repository.brand.repository;

import com.microservice.test.infrastructure.h2.repository.brand.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandH2Repository extends JpaRepository<BrandEntity, Integer> {
}
