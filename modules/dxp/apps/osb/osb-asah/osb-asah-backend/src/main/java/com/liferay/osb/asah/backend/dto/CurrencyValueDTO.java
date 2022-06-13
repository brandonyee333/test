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
import com.liferay.osb.asah.common.model.CurrencyValue;
import com.liferay.osb.asah.common.model.TrendClassification;

import java.math.BigDecimal;

/**
 * @author Riccardo Ferrari
 */
@GraphQLType("CurrencyValue")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyValueDTO {

	public CurrencyValueDTO(CurrencyValue currencyValue) {
		_currencyCode = currencyValue.getCurrencyCode();

		if (currencyValue.getPercentageVariation() != null) {
			_trend = new Trend();

			_trend.setPercentage(
				BigDecimal.valueOf(currencyValue.getPercentageVariation()));

			if (currencyValue.getPercentageVariation() > 0.0) {
				_trend.setTrendClassification(TrendClassification.POSITIVE);
			}
			else if (currencyValue.getPercentageVariation() < 0.0) {
				_trend.setTrendClassification(TrendClassification.NEGATIVE);
			}
			else {
				_trend.setTrendClassification(TrendClassification.NEUTRAL);
			}
		}
		else {
			_trend = null;
		}

		_value = currencyValue.getValue();
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