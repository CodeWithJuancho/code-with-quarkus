package com.vass.repository;

import com.vass.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p " +
        "    FROM Price p " +
        "    WHERE p.brand.id = :brandId " +
        "    AND p.product.id = :productId " +
        "    AND p.startDate <= :date AND p.endDate >= :date")
    List<Price> getPrice(
        @Param("brandId") Long brandId,
        @Param("productId") Long productId,
        @Param("date") Instant date
    );

}
