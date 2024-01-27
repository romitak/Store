/**
 * 
 */
package com.trade.store.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.trade.store.dto.Trade;
import com.trade.store.repository.TradeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;


/**
 * 
 */

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeServiceImpl tradeService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessTrade_LowerVersion_ShouldThrowException() {
        Trade existingTrade = new Trade();
        existingTrade.setTradeId("T1");
        existingTrade.setVersion(2);

        when(tradeRepository.findByTradeIdAndVersion("T1", 2)).thenReturn(Optional.of(existingTrade));

        Trade newTrade = new Trade();
        newTrade.setTradeId("T1");
        newTrade.setVersion(1);

        assertThrows(InvalidTradeException.class, () -> tradeService.processTrade(newTrade));

        verify(tradeRepository, never()).save(newTrade);
    }

    // Implement other test cases for maturity date and expire flag

    // Add more test cases to cover different scenarios
}



