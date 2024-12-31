package com.test.sana.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "real_time_feed")
public class StockFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "symbol")
    @NotBlank
    private String symbol;
    @Column(name = "name")
    @NotBlank
    private String name;
    @Column(name = "price",precision = 19, scale = 4)
    private BigDecimal price;
    @Column(name = "changesPercentage",precision = 10, scale = 6)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal changesPercentage;
    @Column(name = "chg")
    private BigDecimal change;
    @Column(name = "dayLow")
    private BigDecimal dayLow;
    @Column(name = "dayHigh")
    private BigDecimal dayHigh;
    @Column(name = "yearHigh")
    private BigDecimal yearHigh;
    @Column(name = "marketCap")
    private Long marketCap;
    @Column(name = "priceAvg50")
    private BigDecimal priceAvg50;
    @Column(name = "priceAvg200")
    private BigDecimal priceAvg200;
    @Column(name = "exchg")
    private String exchange;
    @Column(name = "vol")
    private Long Volume;
    @Column(name = "avgVol")
    private Long avgVolume;
    @Column(name = "open")
    private BigDecimal open;
    @Column(name = "previousClose")
    private BigDecimal previousClose;
    @Column(name = "eps")
    private BigDecimal eps;
    @Column(name = "pe")
    private BigDecimal pe;
    @Column(name = "earningAnnouncement")
    private String earningAnnouncement;
    @Column(name = "sharesOutstanding")
    @JsonProperty("sharesOutstanding")
    private BigInteger sharesOutstanding;
    @Column(name = "timeStamp")
    private Instant timeStamp;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
