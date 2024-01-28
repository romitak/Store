/**
 * The {@code TradeRepository} interface represents a Spring Data JPA repository for managing trade entities.
 * It extends the {@code JpaRepository} interface, providing standard CRUD operations for the {@code Trade} entity.
 */
package com.trade.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trade.store.dto.Trade;

import java.util.Date;
import java.util.List;

/**
 * The {@code TradeRepository} interface is annotated with {@code @Repository}, indicating that it is a Spring
 * component responsible for handling data access to the underlying database.
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    /**
     * Custom query method to find trades by counterparty ID.
     *
     * @param counterPartyId The counterparty ID to search for.
     * @return A list of trades associated with the specified counterparty ID.
     */
    List<Trade> findByCounterPartyId(String counterPartyId);

    /**
     * Custom query method to find a trade by trade ID and version.
     *
     * @param tradeId The trade ID to search for.
     * @param version The version of the trade to search for.
     * @return The trade matching the specified trade ID and version.
     */
    Trade findByTradeIdAndVersion(String tradeId, int version);

    /**
     * Custom query method to find trades with maturity date after the current date and not expired,
     * ordered by maturity date.
     *
     * @param currentDate The current date used as a reference for finding trades.
     * @return A list of trades with maturity date after the current date and not expired, ordered by maturity date.
     */
    List<Trade> findByMaturityDateAfterAndExpiredIsFalseOrderByMaturityDate(Date currentDate);

    // You can add more custom queries as needed

}

