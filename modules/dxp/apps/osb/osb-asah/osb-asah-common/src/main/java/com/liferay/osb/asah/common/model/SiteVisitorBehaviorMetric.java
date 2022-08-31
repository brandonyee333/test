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

import java.util.Date;
import java.util.Map;

/**
 * @author Marcos Martins
 */
public class SiteVisitorBehaviorMetric extends VisitorBehaviorMetric {

	public SiteVisitorBehaviorMetric() {
	}

	public SiteVisitorBehaviorMetric(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public long getAverageSessionDuration() {
		if (_averageSessionDuration == null) {
			return 0;
		}

		return _averageSessionDuration.longValue();
	}

	public double getBounceRate() {
		if ((_bounces == null) || (getSessions() <= 0)) {
			return 0;
		}

		return _bounces.doubleValue() / getSessions();
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

	public Date getEventDate() {
		if (_eventDate == null) {
			return null;
		}

		return new Date(_eventDate.getTime());
	}

	public void setAverageSessionDuration(BigDecimal averageSessionDuration) {
		_averageSessionDuration = averageSessionDuration;
	}

	public void setBounces(BigDecimal bounces) {
		_bounces = bounces;
	}

	public void setEntrances(BigDecimal entrances) {
		_entrances = entrances;
	}

	public void setEventDate(Date eventDate) {
		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}
		else {
			_eventDate = null;
		}
	}

	private BigDecimal _averageSessionDuration;
	private BigDecimal _bounces;
	private BigDecimal _entrances;
	private Date _eventDate;

}