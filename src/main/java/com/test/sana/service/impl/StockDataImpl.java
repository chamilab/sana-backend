package com.test.sana.service.impl;

import com.test.sana.advisor.CommonDataException;
import com.test.sana.dto.StockDTO;
import com.test.sana.mapper.StockDataMapper;
import com.test.sana.model.StockFeed;
import com.test.sana.repository.StockDataRepository;
import com.test.sana.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StockDataImpl implements StockService {
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://fmpcloud.io/api/v3/quote/";
    private final String apiKey;
    private static final Logger logger = LoggerFactory.getLogger(StockDataImpl.class);
    private final StockDataRepository stockDataRepository;

    public StockDataImpl(RestTemplateBuilder restTemplateBuilder, @Value("${custom.api-keys.fmCloud}") String apiKey, StockDataRepository stockDataRepository){
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = apiKey;
        this.stockDataRepository = stockDataRepository;
    }

    @Override
    public List<StockDTO> getAllStockData() {
        List<StockFeed> stockFeeds = stockDataRepository.findAll();
        List<StockDTO> stockDTOS = new ArrayList<>();
        if(!stockFeeds.isEmpty()){
            stockFeeds.forEach(stockData -> stockDTOS.add(StockDataMapper.mapToStockDataDTO(stockData,new StockDTO())));
        }
        return stockDTOS;
    }

    @Override
    public List<StockDTO> saveRealTimeData(String... symbols) {
        String symbolString = String.join(",", symbols);
        try {
            String url = UriComponentsBuilder.fromUriString(BASE_URL)
                    .path(symbolString)
                    .queryParam("apikey", apiKey)
                    .build()
                    .toUriString();
            ResponseEntity<StockDTO[]> realTimeDataResponse = restTemplate.getForEntity(url, StockDTO[].class);

            if (realTimeDataResponse.getStatusCode() == HttpStatus.OK && realTimeDataResponse.getBody() != null) {
                StockDTO[] responseBody = realTimeDataResponse.getBody();
                 Long count = 1L;
                for (StockDTO stockDTO : responseBody) {
                    stockDTO.setId(count);
                    try {
                        StockFeed stockFeed = new StockFeed();
                        StockFeed mappedEntity = StockDataMapper
                                .mapToStockDataFeed(stockDTO, stockFeed);

                        stockDataRepository.save(mappedEntity);
                        logger.info("[RealTimeImpl] [saveRealtimeData] : success");
                    }
                    catch (Exception e){
                        throw new CommonDataException("Failed to save real time data"+ e);
                    }
                    count++;
                }
                return Arrays.asList(realTimeDataResponse.getBody());
            }
            throw new CommonDataException("Failed to fetch real time stock data");
        }
        catch (RestClientException e){
            logger.error("[RealTimeImpl] [getAllRealTimeData] : failed - Error fetching stock quotes",e);
            throw new CommonDataException("Error communicating with real-time data Feed API", e);
        }
    }

    @Override
    public List<StockDTO> filterStockDataBySymbol(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new CommonDataException("Symbol cannot be null or empty");
        }
        List<StockFeed> stockFeeds = stockDataRepository.findBySymbol(symbol);
        List<StockDTO> stockDTOS = new ArrayList<>();
        if(!stockFeeds.isEmpty()){
            stockFeeds.forEach(stockData -> stockDTOS.add(StockDataMapper.mapToStockDataDTO(stockData,new StockDTO())));
        }
        return stockDTOS;
    }
}
