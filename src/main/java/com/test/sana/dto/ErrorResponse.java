package com.test.sana.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private final String title;
    private final String message;
    private final LocalDateTime timeStamp;
}
