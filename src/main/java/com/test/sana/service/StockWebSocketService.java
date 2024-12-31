package com.test.sana.service;

import com.test.sana.configuration.StockFeedWebSocketHandler;
import com.test.sana.dto.StockDTO;
import com.test.sana.mapper.StockDataMapper;
import com.test.sana.model.StockFeed;
import com.test.sana.repository.StockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockWebSocketService {
    private final StockFeedWebSocketHandler webSocketHandler;
    private final StockDataRepository stockDataRepository;
    @Autowired
    public StockWebSocketService(StockFeedWebSocketHandler webSocketHandler, StockDataRepository stockDataRepository) {
        this.webSocketHandler = webSocketHandler;
        this.stockDataRepository = stockDataRepository;
    }

    public void pushStockData(){
        List<StockFeed> stockFeeds = stockDataRepository.findAll();
        List<StockDTO> stockDTOS = new ArrayList<>();
        if(!stockFeeds.isEmpty()){
            stockFeeds.forEach(stockData -> stockDTOS.add(StockDataMapper.mapToStockDataDTO(stockData,new StockDTO())));
        }
        webSocketHandler.sendMarketData(stockDTOS.toString());
    }
}
