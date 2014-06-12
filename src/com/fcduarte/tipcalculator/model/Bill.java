package com.fcduarte.tipcalculator.model;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Bill {

	private static BigDecimal ZERO = new BigDecimal("0.00");

	private BigDecimal subTotal;
	private double tipPercentage;

	public Bill() {
		this.subTotal = ZERO;
		this.tipPercentage = 0;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public double getTipPercentage() {
		return tipPercentage;
	}

	public void setTipPercentage(double tipPercentage) {
		this.tipPercentage = tipPercentage;
	}

	public BigDecimal getTotalTip() {
		if (this.tipPercentage == 0d) {
			return ZERO;
		}

		return subTotal.multiply(BigDecimal.valueOf(this.tipPercentage))
				.setScale(2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getTotal() {
		return this.subTotal.add(this.getTotalTip());
	}

	private String getValueFormattedAsCurrency(BigDecimal value) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(value.doubleValue());
	}

	public String getTotalTipFormatted() {
		return getValueFormattedAsCurrency(this.getTotalTip());
	}

	public String getTotalFormatted() {
		return getValueFormattedAsCurrency(this.getTotal());
	}

	public String getTipPercentageFormatted() {
		NumberFormat formatter = NumberFormat.getPercentInstance();
		formatter.setMinimumFractionDigits(2);
		formatter.setMinimumIntegerDigits(1);

		return formatter.format(this.getTipPercentage());
	}

}
