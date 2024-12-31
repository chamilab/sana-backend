package com.test.sana.controller;


import com.test.sana.dto.NewsFeedDTO;
import com.test.sana.dto.NewsFeedResponse;
import com.test.sana.dto.ResponseDTO;
import com.test.sana.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/newsFeed")
@RequiredArgsConstructor
public class NewsFeedController {
    private static final Logger logger = LoggerFactory.getLogger(NewsFeedController.class);
    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<ResponseDTO<NewsFeedDTO>> saveNewsFeed(@RequestParam(value = "tickers")String ticker){
        NewsFeedResponse newsFeed = newsService.saveAllNewsFeed(ticker);
        logger.info("[NewsFeedController] [getAllNewsFeed] : success");
        ResponseDTO response = new ResponseDTO();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(newsFeed.getFeed());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
