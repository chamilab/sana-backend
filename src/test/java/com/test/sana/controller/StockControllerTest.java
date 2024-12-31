package com.test.sana.controller;

import com.test.sana.dto.StockDTO;
import com.test.sana.service.StockService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
public class StockControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StockService stockService;

    @Test
    public void getStockDataBySymbol_Success() throws Exception {
        String ticker = "GOOG";
        Instant specificTime = Instant.parse("2024-12-31T10:15:30.00Z");
        List<StockDTO> mockStockData = List.of(
          new StockDTO("GOOG","Alphabet Inc", BigDecimal.valueOf(192.6900),BigDecimal.valueOf(-0.65),BigDecimal.valueOf(-1.56),BigDecimal.valueOf(186.85),BigDecimal.valueOf(189.54),BigDecimal.valueOf(204.56), 5522266L,BigDecimal.valueOf(174.25),BigDecimal.valueOf(175.25),"NASDAQ",1802805L,18763506L,BigDecimal.valueOf(185.26),BigDecimal.valueOf(5.26),BigDecimal.valueOf(23.25),BigDecimal.valueOf(23.65),"2025-01-22T21:00:00.000+0000", BigInteger.valueOf(2558555), specificTime)
        );
        Mockito.when(stockService.filterStockDataBySymbol(ticker)).thenReturn(mockStockData);

        mockMvc.perform(get("/symbol")
                .param("ticker",ticker)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].symbol").value("GOOG"))
                .andExpect(jsonPath("$.data[0].name").value("Alphabet Inc"))
                .andExpect(jsonPath("$.data[0].price").value(BigDecimal.valueOf(192.6900)));

        Mockito.verify(stockService,times(1)).filterStockDataBySymbol(ticker);
    }

    @Test
    public void testGetStockDataSymbol_NotFound() throws Exception {
        String ticker = "Invalid";
        Mockito.when(stockService.filterStockDataBySymbol(ticker)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/symbol")
                        .param("ticker",ticker)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("No data found"))
                .andExpect(jsonPath("$.data").isEmpty());

        Mockito.verify(stockService,times(1)).filterStockDataBySymbol(ticker);
    }

    @Test
    public void testGetStockDataSymbol_InvalidInput() throws Exception {
        mockMvc.perform(get("/symbol")
                .param("ticker","ticker")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(stockService);

    }
}
