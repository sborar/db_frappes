package com.db.dto;

import java.sql.Timestamp;

public class Counterparty {
	private int counterpartyId;
	private String counterpartyName;
	private char counterpartyStatus;
	private Timestamp counterpartyDateRegistered;

	public int getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(int counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public char getCounterpartyStatus() {
		return counterpartyStatus;
	}

	public void setCounterpartyStatus(char counterpartyStatus) {
		this.counterpartyStatus = counterpartyStatus;
	}

	public Timestamp getCounterpartyDateRegistered() {
		return counterpartyDateRegistered;
	}

	public void setCounterpartyDateRegistered(Timestamp counterpartyDateRegistered) {
		this.counterpartyDateRegistered = counterpartyDateRegistered;
	}
}
