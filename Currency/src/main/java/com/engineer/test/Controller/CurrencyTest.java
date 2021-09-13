package com.engineer.test.Controller;

public class CurrencyTest {

	private Integer Id;

	private String code;

	private String symbol;

	private String rate;

	private String description;

	private Integer rateFloat;

	private String updateDate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(Integer rateFloat) {
		this.rateFloat = rateFloat;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
