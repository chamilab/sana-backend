package com.test.sana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    private Long id;
    @NotEmpty(message = "Symbol cannot be empty")
    private String Symbol;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    private BigDecimal price;
    private BigDecimal changesPercentage;
    private BigDecimal change;
    private BigDecimal dayLow;
    private BigDecimal dayHigh;
    private BigDecimal yearHigh;
    private Long marketCap;
    private BigDecimal priceAvg50;
    private BigDecimal priceAvg200;
    private String exchange;
    private Long Volume;
    private Long avgVolume;
    private BigDecimal open;
    private BigDecimal previousClose;
    private BigDecimal eps;
    private BigDecimal pe;
    private String earningsAnnouncement;
    private BigInteger sharesOutstanding;
    private Instant timestamp;

    public StockDTO(String symbol, String name, BigDecimal price, BigDecimal changesPercentage, BigDecimal change, BigDecimal dayLow, BigDecimal dayHigh, BigDecimal yearHigh, Long marketCap, BigDecimal priceAvg50, BigDecimal priceAvg200, String exchange, Long volume, Long avgVolume, BigDecimal open, BigDecimal previousClose, BigDecimal eps, BigDecimal pe, String earningsAnnouncement, BigInteger sharesOutstanding, Instant timestamp) {
        Symbol = symbol;
        this.name = name;
        this.price = price;
        this.changesPercentage = changesPercentage;
        this.change = change;
        this.dayLow = dayLow;
        this.dayHigh = dayHigh;
        this.yearHigh = yearHigh;
        this.marketCap = marketCap;
        this.priceAvg50 = priceAvg50;
        this.priceAvg200 = priceAvg200;
        this.exchange = exchange;
        Volume = volume;
        this.avgVolume = avgVolume;
        this.open = open;
        this.previousClose = previousClose;
        this.eps = eps;
        this.pe = pe;
        this.earningsAnnouncement = earningsAnnouncement;
        this.sharesOutstanding = sharesOutstanding;
        this.timestamp = timestamp;
    }
}
