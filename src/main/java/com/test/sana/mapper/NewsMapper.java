package com.test.sana.mapper;

import com.test.sana.dto.NewsFeedDTO;
import com.test.sana.model.NewsFeed;

public class NewsMapper {
    public static NewsFeed mapToNewsData(NewsFeedDTO newsFeedDTO, NewsFeed newsFeed){
        newsFeed.setId(newsFeedDTO.getId());
        newsFeed.setTitle(newsFeedDTO.getTitle());
        newsFeed.setUrl(newsFeedDTO.getUrl());
        newsFeed.setSummary(newsFeedDTO.getSummary());
        newsFeed.setSource(newsFeedDTO.getSource());
        return newsFeed;
    }
}
