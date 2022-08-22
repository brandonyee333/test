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

import com.liferay.osb.asah.common.util.BeanUtils;

import java.math.BigDecimal;

import java.util.Map;

/**
 * @author Marcos Martins
 */
public class SiteVisitorBehaviorMetric implements AssetVisitorBehaviorMetric {

	public SiteVisitorBehaviorMetric() {
	}

	public SiteVisitorBehaviorMetric(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public long getAvgTimeOnPage() {
		if ((_timeOnPage == null) || (_sessions == null) ||
			(_sessions.longValue() <= 0)) {

			return 0;
		}

		return _timeOnPage.longValue() / _sessions.longValue();
	}

	public double getBounceRate() {
		if ((_bounces == null) || (_sessions == null) ||
			(_sessions.doubleValue() <= 0)) {

			return 0;
		}

		return _bounces.doubleValue() / _sessions.doubleValue();
	}

	public long getBounces() {
		if (_bounces == null) {
			return 0;
		}

		return _bounces.longValue();
	}

	public long getEntrances() {
		if (_entrances == null) {
			return 0;
		}

		return _entrances.longValue();
	}

	public double getExitRate() {
		if ((_exits == null) || (_sessions == null) ||
			(_sessions.doubleValue() <= 0)) {

			return 0;
		}

		return _exits.doubleValue() / _sessions.doubleValue();
	}

	public long getSessions() {
		if (_sessions == null) {
			return 0;
		}

		return _sessions.longValue();
	}

	public long getTimeOnPage() {
		if (_timeOnPage == null) {
			return 0;
		}

		return _timeOnPage.longValue();
	}

	@Override
	public long getViews() {
		if (_views == null) {
			return 0;
		}

		return _views.longValue();
	}

	public long getVisitors() {
		if (_visitors == null) {
			return 0;
		}

		return _visitors.longValue();
	}

	public void setBounces(BigDecimal bounces) {
		_bounces = bounces;
	}

	public void setEntrances(BigDecimal entrances) {
		_entrances = entrances;
	}

	public void setExits(BigDecimal exits) {
		_exits = exits;
	}

	public void setSessions(BigDecimal sessions) {
		_sessions = sessions;
	}

	public void setTimeOnPage(BigDecimal timeOnPage) {
		_timeOnPage = timeOnPage;
	}

	public void setViews(BigDecimal views) {
		_views = views;
	}

	public void setVisitors(BigDecimal visitors) {
		_visitors = visitors;
	}

	private BigDecimal _bounces;
	private BigDecimal _entrances;
	private BigDecimal _exits;
	private BigDecimal _sessions;
	private BigDecimal _timeOnPage;
	private BigDecimal _views;
	private BigDecimal _visitors;

}