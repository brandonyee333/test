/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.model;

import java.math.BigDecimal;

import java.util.Objects;

/**
 * @author Riccardo Ferrari
 */
public class OrderTotalValue {

	public OrderTotalValue() {
	}

	public OrderTotalValue(
		String currencyCode, Double percentageVariation, BigDecimal value) {

		_currencyCode = currencyCode;
		_percentageVariation = percentageVariation;
		_value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		OrderTotalValue orderTotalValue = (OrderTotalValue)obj;

		if (Objects.equals(_currencyCode, orderTotalValue._currencyCode) &&
			Objects.equals(
				_percentageVariation, orderTotalValue._percentageVariation) &&
			Objects.equals(_value, orderTotalValue._value)) {

			return true;
		}

		return false;
	}

	public String getCurrencyCode() {
		return _currencyCode;
	}

	public Double getPercentageVariation() {
		return _percentageVariation;
	}

	public BigDecimal getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_currencyCode, _percentageVariation, _value);
	}

	public void setCurrencyCode(String currencyCode) {
		_currencyCode = currencyCode;
	}

	public void setPercentageVariation(Double percentageVariation) {
		_percentageVariation = percentageVariation;
	}

	public void setValue(BigDecimal value) {
		_value = value;
	}

	private String _currencyCode;
	private Double _percentageVariation;
	private BigDecimal _value;

}