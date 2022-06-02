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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.liferay.osb.asah.backend.model.Trend;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.model.OrderTotalValue;
import com.liferay.osb.asah.common.model.TrendClassification;

import java.math.BigDecimal;

/**
 * @author Riccardo Ferrari
 */
@GraphQLType("OrderTotalValue")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderTotalValueDTO {

	public OrderTotalValueDTO(OrderTotalValue orderTotalValue) {
		_currencyCode = orderTotalValue.getCurrencyCode();

		if (orderTotalValue.getPercentageVariation() != null) {
			_trend = new Trend();

			_trend.setPercentage(
				BigDecimal.valueOf(orderTotalValue.getPercentageVariation()));

			if (orderTotalValue.getPercentageVariation() > 0.0) {
				_trend.setTrendClassification(TrendClassification.POSITIVE);
			}
			else if (orderTotalValue.getPercentageVariation() < 0.0) {
				_trend.setTrendClassification(TrendClassification.NEGATIVE);
			}
			else {
				_trend.setTrendClassification(TrendClassification.NEUTRAL);
			}
		}
		else {
			_trend = null;
		}

		_value = orderTotalValue.getValue();
	}

	public String getCurrencyCode() {
		return _currencyCode;
	}

	public Trend getTrend() {
		return _trend;
	}

	public String getValue() {
		return _value.toPlainString();
	}

	private final String _currencyCode;
	private final Trend _trend;
	private final BigDecimal _value;

}