/**
 * 
 */
package com.trade.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.store.dto.Trade;
import com.trade.store.repository.TradeRepository;

import java.util.Date;

@Service
public class TradeServiceImpl {

    @Autowired
    private TradeRepository tradeRepository;

    public void processTrade(Trade incomingTrade) {
        validateTrade(incomingTrade);

        Trade existingTrade = tradeRepository.findByTradeIdAndVersion(incomingTrade.getTradeId(), incomingTrade.getVersion());
        if (existingTrade != null) {
            updateTrade(existingTrade, incomingTrade);
        } else {
            saveTrade(incomingTrade);
        }
    }

    public void validateTrade(Trade trade) {
        // Validation 1: Check for lower version
        Trade existingTrade = tradeRepository.findByTradeIdAndVersion(trade.getTradeId(), trade.getVersion());
        if (existingTrade != null && existingTrade.getVersion() > trade.getVersion()) {
            throw new IllegalArgumentException("Invalid trade version. Lower version received.");
        }

        // Validation 2: Check maturity date
        if (trade.getMaturityDate().before(new Date())) {
            throw new IllegalArgumentException("Trade maturity date should be in the future.");
        }

        // Validation 3: Update expire flag if necessary
        if ( !trade.isExpired()) {
            trade.setExpired(trade.getMaturityDate().before(new Date()));
        }
    }

    private void saveTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    public void updateTrade(Trade existingTrade, Trade incomingTrade) {
        // Update existing trade logic
        // For example, set fields from incomingTrade to existingTrade
        existingTrade.setMaturityDate(incomingTrade.getMaturityDate());
        existingTrade.setExpired(incomingTrade.getMaturityDate().before(new Date()));
        tradeRepository.save(existingTrade);
    }
}
