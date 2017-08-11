package com.db.dto;

public class Deal {
	private int dealId;
	private String dealTime;
	private int dealCounterpartyId;
	private int dealInstrumentId;
	private char dealType;
	private double dealAmount;
	private int dealQuantity;

	public int getDealId() {
		return dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public int getDealCounterpartyId() {
		return dealCounterpartyId;
	}

	public void setDealCounterpartyId(int dealCounterpartyId) {
		this.dealCounterpartyId = dealCounterpartyId;
	}

	public int getDealInstrumentId() {
		return dealInstrumentId;
	}

	public void setDealInstrumentId(int dealInstrumentId) {
		this.dealInstrumentId = dealInstrumentId;
	}

	public char getDealType() {
		return dealType;
	}

	public void setDealType(char dealType) {
		this.dealType = dealType;
	}

	public double getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(double dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getDealQuantity() {
		return dealQuantity;
	}

	public void setDealQuantity(int dealQuantity) {
		this.dealQuantity = dealQuantity;
	}

}
