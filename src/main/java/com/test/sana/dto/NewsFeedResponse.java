package com.test.sana.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsFeedResponse {
    private List<NewsFeedDTO> feed;
    private String items;
    private String sentiment_score_definition;
    private String relevance_score_definition;
}
