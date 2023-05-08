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

/**
 * @author Marcellus Tavares
 */
public enum GoalMetric {

	BOUNCE_RATE(true, PageMetricType.BOUNCE),
	CLICK_RATE(false, PageMetricType.CTA_CLICKS);

	public PageMetricType getPageMetricType() {
		return _pageMetricType;
	}

	public boolean isInverseMetric() {
		return _inverseMetric;
	}

	private GoalMetric(boolean inverseMetric, PageMetricType pageMetricType) {
		_inverseMetric = inverseMetric;
		_pageMetricType = pageMetricType;
	}

	private final boolean _inverseMetric;
	private final PageMetricType _pageMetricType;

}