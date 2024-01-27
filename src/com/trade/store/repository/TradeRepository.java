package com.trade.store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TradeRepository extends MongoRepository<Trade, String> {

    Optional<Trade> findByTradeIdAndVersion(String tradeId, int version);
}
