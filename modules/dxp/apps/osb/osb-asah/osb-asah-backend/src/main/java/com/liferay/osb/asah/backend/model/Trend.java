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

package com.liferay.osb.asah.backend.model;

import java.math.BigDecimal;

import java.util.Objects;

/**
 * @author Inácio Nery
 */
public class Trend {

	public Trend() {
	}

	public Trend(
		TrendClassification trendClassification, BigDecimal percentage) {

		_trendClassification = trendClassification;
		_percentage = percentage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Trend)) {
			return false;
		}

		Trend trend = (Trend)obj;

		if (Objects.equals(_percentage, trend._percentage) &&
			Objects.equals(_trendClassification, trend._trendClassification)) {

			return true;
		}

		return false;
	}

	public BigDecimal getPercentage() {
		return _percentage;
	}

	public TrendClassification getTrendClassification() {
		return _trendClassification;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_trendClassification, _percentage);
	}

	public void setPercentage(BigDecimal percentage) {
		_percentage = percentage;
	}

	public void setTrendClassification(
		TrendClassification trendClassification) {

		_trendClassification = trendClassification;
	}

	private BigDecimal _percentage;
	private TrendClassification _trendClassification;

}