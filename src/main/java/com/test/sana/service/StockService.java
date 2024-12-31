package com.test.sana.service;

import com.test.sana.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getAllStockData();
    List<StockDTO> saveRealTimeData(String... symbols);
    List<StockDTO> filterStockDataBySymbol(String symbol);

}
