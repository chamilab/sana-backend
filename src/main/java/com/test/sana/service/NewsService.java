package com.test.sana.service;

import com.test.sana.dto.NewsFeedResponse;
public interface NewsService {
    NewsFeedResponse saveAllNewsFeed(String symbol);
}
