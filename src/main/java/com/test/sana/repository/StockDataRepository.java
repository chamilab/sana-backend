package com.test.sana.repository;

import com.test.sana.model.StockFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockDataRepository extends JpaRepository<StockFeed,Long> {
    List<StockFeed> findBySymbol(String symbol);
}
