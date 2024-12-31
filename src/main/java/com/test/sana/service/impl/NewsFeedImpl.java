package com.test.sana.service.impl;

import com.test.sana.advisor.CommonDataException;
import com.test.sana.dto.NewsFeedDTO;
import com.test.sana.dto.NewsFeedResponse;
import com.test.sana.mapper.NewsMapper;
import com.test.sana.model.NewsFeed;
import com.test.sana.repository.NewsFeedRepository;
import com.test.sana.service.NewsService;
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

@Service
public class NewsFeedImpl implements NewsService {
    private final RestTemplate restTemplate;
    private final String apiKey;
    private static final String BASE_URL = "https://www.alphavantage.co/query";
    private static final Logger logger = LoggerFactory.getLogger(NewsFeedImpl.class);
    private final NewsFeedRepository newsFeedRepository;

    public NewsFeedImpl(RestTemplateBuilder restTemplateBuilder, @Value("${custom.api-keys.alpha}")String apiKey, NewsFeedRepository newsFeedRepository){
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = apiKey;
        this.newsFeedRepository = newsFeedRepository;
    }
    @Override
    public NewsFeedResponse saveAllNewsFeed(String apiTicker) {
        String tickerString = String.join(",", apiTicker);
        try {
            String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("function","NEWS_SENTIMENT")
                    .queryParam("tickers",tickerString)
                    .queryParam("apikey", apiKey)
                    .build()
                    .toUriString();
            ResponseEntity<NewsFeedResponse> newsFeedResponse = restTemplate.getForEntity(url,NewsFeedResponse.class);

            if (newsFeedResponse.getStatusCode() == HttpStatus.OK && newsFeedResponse.getBody() != null) {
                try {
                    Long count = 1L;
                    for(NewsFeedDTO newsFeedDTO : newsFeedResponse.getBody().getFeed()){
                        newsFeedDTO.setId(count);
                        NewsFeed newsFeed = new NewsFeed();
                        NewsFeed mappedToNewsData = NewsMapper.mapToNewsData(newsFeedDTO,newsFeed);
                        newsFeedRepository.save(mappedToNewsData);
                        logger.info("[NewsFeedImpl] [saveAllNewsFeed] : success");
                        count++;
                    }
                }
                catch (Exception e){
                    throw new CommonDataException("Failed to save real time news feed"+ e);
                }
                return newsFeedResponse.getBody();
            }
            throw new CommonDataException("Failed to fetch news feed data");
        }
        catch (RestClientException e){
            logger.error("[NewsFeedImpl] [getAllNewsFeed] : failed - Error fetching news feed",e);
            throw new CommonDataException("Error communicating with news Feed API", e);
        }
    }
}

