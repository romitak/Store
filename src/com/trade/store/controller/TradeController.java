/**
 * 
 */
package com.trade.store.controller;

/**
 * 
 */
import com.trade.store.dto.Trade;
import com.trade.store.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/process-trade")
    public void processTrade(@RequestBody Trade trade) {
        tradeService.processTrade(trade);
    }
}
