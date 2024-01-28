/**
 * The {@code TradeServiceTest} class contains JUnit tests for the {@code TradeServiceImpl} class.
 * It uses Mockito for mocking dependencies and SpringBootTest to configure the application context.
 */
package com.test.trade.store;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.trade.store.TradeStoreApplication;
import com.trade.store.dto.Trade;
import com.trade.store.repository.TradeRepository;
import com.trade.store.service.TradeServiceImpl;

@SpringBootTest(classes = TradeStoreApplication.class)
@ExtendWith(MockitoExtension.class)
@Profile("test")

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeServiceImpl tradeService;
    /**
     * Tests the processing of a new trade by verifying that the save method is called.
     */
    @Test
    public void testProcessTrade_NewTrade() {
        Trade incomingTrade = createSampleTrade();
        tradeService.processTrade(incomingTrade);
        when(tradeRepository.findByTradeIdAndVersion("T1",1)).thenReturn(null);

        // Verify that saveTrade method is called
        verify(tradeRepository, times(1)).save(incomingTrade);
    }
    /**
     * Tests the processing of an existing trade by verifying that the updateTrade method is called.
     */
    @Test
    public void testProcessTrade_ExistingTrade() {
        Trade incomingTrade = createSampleTrade();
        Trade existingTrade = createSampleTrade();
        existingTrade.setVersion(1);

        when(tradeRepository.findByTradeIdAndVersion("T1", 1)).thenReturn(existingTrade);

        tradeService.processTrade(incomingTrade);

        // Verify that updateTrade method is called
        verify(tradeService, times(1)).updateTrade(existingTrade, incomingTrade);
    }
    /**
     * Tests the validation of a trade with a lower version.
     */
    @Test
    public void testValidateTrade_LowerVersion() {
        Trade incomingTrade = createSampleTrade();
        Trade existingTrade = createSampleTrade();
        existingTrade.setVersion(2);

        when(tradeRepository.findByTradeIdAndVersion("T1", 1)).thenReturn(existingTrade);

        // Ensure that the validation throws IllegalArgumentException for lower version
        assertThrows(IllegalArgumentException.class, () -> tradeService.validateTrade(incomingTrade));
    }
    /**
     * Tests the validation of an expired trade.
     */
    @Test
    public void testValidateTrade_Expired() {
        Trade incomingTrade = createSampleTrade();
        incomingTrade.setMaturityDate(new Date(System.currentTimeMillis() - 86400000)); // Set maturity date in the past

        // Ensure that the validation throws IllegalArgumentException for expired trade
        assertThrows(IllegalArgumentException.class, () -> tradeService.validateTrade(incomingTrade));
    }
    /**
     * Creates and returns a sample trade for testing purposes.
     *
     * @return A sample trade with predefined values.
     */
    private Trade createSampleTrade() {
        Trade trade = new Trade();
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setCounterPartyId("CP-1");
        trade.setBookId("B1");
        trade.setMaturityDate(new Date(System.currentTimeMillis() + 86400000)); // Set maturity date in the future
        trade.setCreatedDate(new Date());
        trade.setExpired(false);
        return trade;
    }
}


