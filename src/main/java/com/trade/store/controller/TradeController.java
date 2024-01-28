/**
 * The {@code TradeController} class represents the controller for handling trade-related operations.
 * It is responsible for processing trades received from a Kafka topic and delegating the processing
 * to the underlying service layer.
 */
package com.trade.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import com.trade.store.dto.Trade;
import com.trade.store.service.TradeServiceImpl;

/**
 * The {@code TradeController} class is annotated with {@code @RestController}, indicating that it
 * handles HTTP requests and produces JSON responses. It also contains a method annotated with
 * {@code @KafkaListener}, specifying that it listens for messages from the "trade-topic" Kafka topic
 * with the "trade-group" group ID.
 */
@RestController
public class TradeController {

    /**
     * The {@code TradeServiceImpl} instance is injected using the {@code @Autowired} annotation,
     * establishing a dependency on the service layer for trade processing.
     */
    @Autowired
    private TradeServiceImpl tradeService;

    /**
     * Listens for messages from the "trade-topic" Kafka topic and processes incoming trades.
     * The received trade is delegated to the {@code processTrade} method of the {@code TradeServiceImpl}
     * for further processing.
     *
     * @param trade The trade object received from the Kafka topic.
     */
    @KafkaListener(topics = "trade-topic", groupId = "trade-group")
    public void consumeTrade(Trade trade) {
        tradeService.processTrade(trade);
    }
}

