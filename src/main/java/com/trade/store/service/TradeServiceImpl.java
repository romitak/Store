/**
/**
 * The {@code TradeServiceImpl} class represents the service layer responsible for processing trade-related operations.
 * It is annotated with {@code @Service}, indicating that it is a Spring service component.
 */
 
package com.trade.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.store.dto.Trade;
import com.trade.store.repository.TradeRepository;

import java.util.Date;

/**
 * The {@code TradeServiceImpl} class contains methods for processing and validating trade entities.
 * It interacts with the {@code TradeRepository} for data access operations.
 */
@Service
public class TradeServiceImpl {

    /**
     * The {@code TradeRepository} instance is injected using the {@code @Autowired} annotation,
     * establishing a dependency on the repository for data access.
     */
    @Autowired
    private TradeRepository tradeRepository;
    /**
     * Processes an incoming trade, validates it, and performs the necessary actions based on its existence.
     *
     * @param incomingTrade The incoming trade to process.
     */
    public void processTrade(Trade incomingTrade) {
        validateTrade(incomingTrade);

        Trade existingTrade = tradeRepository.findByTradeIdAndVersion(incomingTrade.getTradeId(), incomingTrade.getVersion());
        if (existingTrade != null) {
            updateTrade(existingTrade, incomingTrade);
        } else {
            saveTrade(incomingTrade);
        }
    }
    /**
     * Validates a trade by checking for lower version, maturity date, and updating the expiration flag if necessary.
     *
     * @param trade The trade to validate.
     * @throws IllegalArgumentException if the trade fails validation.
     */
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
    /**
     * Saves a trade to the database using the {@code TradeRepository}.
     *
     * @param trade The trade to save.
     */
    private void saveTrade(Trade trade) {
        tradeRepository.save(trade);
    }
    /**
     * Updates an existing trade with the values from an incoming trade.
     *
     * @param existingTrade The existing trade to update.
     * @param incomingTrade The incoming trade containing the updated values.
     */
    public void updateTrade(Trade existingTrade, Trade incomingTrade) {
        // Update existing trade logic
        // For example, set fields from incomingTrade to existingTrade
        existingTrade.setMaturityDate(incomingTrade.getMaturityDate());
        existingTrade.setExpired(incomingTrade.getMaturityDate().before(new Date()));
        tradeRepository.save(existingTrade);
    }
}





