package com.test.sana.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final StockFeedWebSocketHandler stockFeedWebSocketHandler;

    public WebSocketConfig(StockFeedWebSocketHandler stockFeedWebSocketHandler) {
        this.stockFeedWebSocketHandler = stockFeedWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(stockFeedWebSocketHandler,"/ws/stock-feed")
                .setAllowedOrigins("");
    }
}
