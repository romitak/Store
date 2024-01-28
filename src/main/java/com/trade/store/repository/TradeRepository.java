package com.trade.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trade.store.dto.Trade;

import java.util.Date;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findByCounterPartyId(String counterPartyId);

    Trade findByTradeIdAndVersion(String tradeId, int version);

    List<Trade> findByMaturityDateAfterAndExpiredIsFalseOrderByMaturityDate(Date currentDate);

    // You can add more custom queries as needed
}
