package com.trade.store.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "trade") // Define the table name in the database
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tradeId;
    private int version;
    private String counterPartyId;
    private String bookId;
    private Date maturityDate;
    private Date createdDate;
    private boolean expired;
	public String getTradeId() {
		// TODO Auto-generated method stub
		return tradeId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getCounterPartyId() {
		return counterPartyId;
	}
	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Date getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

    // getters and setters
}
