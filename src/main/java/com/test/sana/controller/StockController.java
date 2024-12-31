package com.test.sana.controller;

import com.test.sana.dto.StockDTO;
import com.test.sana.dto.ResponseDTO;
import com.test.sana.service.StockService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/realTimeData")
@RequiredArgsConstructor
public class StockController {
    private static final Logger logger = LoggerFactory.getLogger(StockController.class);
    private final StockService stockService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<StockDTO>>> saveRealTimeData(@RequestParam List<String> symbols){
        List<StockDTO> saveRealTimeData = stockService.saveRealTimeData(symbols.toArray(symbols.toArray(new String[0])));
        logger.info("[StockController] [saveRealTimeData] : success");
        ResponseDTO<List<StockDTO>> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(saveRealTimeData);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<StockDTO>>> getAllStockData(){
        List<StockDTO> dataList = stockService.getAllStockData();
        logger.info("[StockController] [getAllStockData] : success");
        ResponseDTO<List<StockDTO>> responseList = new ResponseDTO<>();
        responseList.setCode(HttpStatus.OK.value());
        responseList.setMessage(HttpStatus.OK.getReasonPhrase());
        responseList.setData(dataList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/symbol")
    public ResponseEntity<ResponseDTO<List<StockDTO>>> getStockDataBySymbol(@RequestParam String ticker){
        List<StockDTO> dataList = stockService.filterStockDataBySymbol(ticker);
        logger.info("[StockController] [getStockDataBySymbol] : success");
        ResponseDTO<List<StockDTO>> responseList = new ResponseDTO<>();
        responseList.setCode(HttpStatus.OK.value());
        responseList.setMessage(HttpStatus.OK.getReasonPhrase());
        responseList.setData(dataList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
