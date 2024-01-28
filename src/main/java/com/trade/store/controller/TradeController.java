/**
 * 
 */
package com.trade.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import com.trade.store.dto.Trade;
import com.trade.store.service.TradeServiceImpl;


@RestController
public class TradeController {
    @Autowired
    private TradeServiceImpl tradeService;

    @KafkaListener(topics = "trade-topic", groupId = "trade-group")
    public void consumeTrade(Trade trade) {
        tradeService.processTrade(trade);
    }
}
