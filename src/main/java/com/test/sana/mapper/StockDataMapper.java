package com.test.sana.mapper;

import com.test.sana.dto.StockDTO;
import com.test.sana.model.StockFeed;

public class StockDataMapper {
    public static StockFeed mapToStockDataFeed(StockDTO stockDTO, StockFeed stockFeed){
        stockFeed.setSymbol(stockDTO.getSymbol());
        stockFeed.setAvgVolume(stockDTO.getAvgVolume());
        stockFeed.setName(stockDTO.getName());
        stockFeed.setChange(stockDTO.getChange());
        stockFeed.setDayLow(stockDTO.getDayLow());
        stockFeed.setDayHigh(stockDTO.getDayHigh());
        stockFeed.setPrice(stockDTO.getPrice());
        stockFeed.setChangesPercentage(stockDTO.getChangesPercentage());
        stockFeed.setSharesOutstanding(stockDTO.getSharesOutstanding());
        stockFeed.setYearHigh(stockDTO.getYearHigh());
        stockFeed.setMarketCap(stockDTO.getMarketCap());
        stockFeed.setPriceAvg50(stockDTO.getPriceAvg50());
        stockFeed.setPriceAvg200(stockDTO.getPriceAvg200());
        stockFeed.setExchange(stockDTO.getExchange());
        stockFeed.setVolume(stockDTO.getVolume());
        stockFeed.setAvgVolume(stockDTO.getAvgVolume());
        stockFeed.setOpen(stockDTO.getOpen());
        stockFeed.setPreviousClose(stockDTO.getPreviousClose());
        stockFeed.setEps(stockDTO.getEps());
        stockFeed.setPe(stockDTO.getPe());
        stockFeed.setEarningAnnouncement(stockDTO.getEarningsAnnouncement());
        stockFeed.setTimeStamp(stockDTO.getTimestamp());
        return stockFeed;
    }

    public static StockDTO mapToStockDataDTO(StockFeed stockFeed, StockDTO stockDTO){
        stockDTO.setId(stockFeed.getId());
        stockDTO.setSymbol(stockFeed.getSymbol());
        stockDTO.setAvgVolume(stockFeed.getAvgVolume());
        stockDTO.setName(stockFeed.getName());
        stockDTO.setChange(stockFeed.getChange());
        stockDTO.setDayLow(stockFeed.getDayLow());
        stockDTO.setDayHigh(stockFeed.getDayHigh());
        stockDTO.setPrice(stockFeed.getPrice());
        stockDTO.setChangesPercentage(stockFeed.getChangesPercentage());
        stockDTO.setSharesOutstanding(stockFeed.getSharesOutstanding());
        stockDTO.setYearHigh(stockFeed.getYearHigh());
        stockDTO.setMarketCap(stockFeed.getMarketCap());
        stockDTO.setPriceAvg50(stockFeed.getPriceAvg50());
        stockDTO.setPriceAvg200(stockFeed.getPriceAvg200());
        stockDTO.setExchange(stockFeed.getExchange());
        stockDTO.setVolume(stockFeed.getVolume());
        stockDTO.setAvgVolume(stockFeed.getAvgVolume());
        stockDTO.setOpen(stockFeed.getOpen());
        stockDTO.setPreviousClose(stockFeed.getPreviousClose());
        stockDTO.setEps(stockFeed.getEps());
        stockDTO.setPe(stockFeed.getPe());
        stockDTO.setEarningsAnnouncement(stockFeed.getEarningAnnouncement());
        stockDTO.setTimestamp(stockFeed.getTimeStamp());
        return stockDTO;
    }
}
