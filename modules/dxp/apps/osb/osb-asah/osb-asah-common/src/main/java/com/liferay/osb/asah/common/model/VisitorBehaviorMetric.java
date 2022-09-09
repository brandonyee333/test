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
 * @author Leslie Wong
 */
public abstract class VisitorBehaviorMetric {

	public VisitorBehaviorMetric() {
	}

	public VisitorBehaviorMetric(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public long getAnonymousVisitors() {
		return getVisitors() - getKnownVisitors();
	}

	public long getKnownVisitors() {
		if (_knownVisitors == null) {
			return 0;
		}

		return _knownVisitors.longValue();
	}

	public long getSessions() {
		if (_sessions == null) {
			return 0;
		}

		return _sessions.longValue();
	}

	public double getSessionsPerVisitor() {
		if ((_sessions != null) && (_visitors != null) &&
			!_visitors.equals(BigDecimal.ZERO)) {

			BigDecimal sessionsPerVisitor = _sessions.divide(_visitors);

			return sessionsPerVisitor.longValue();
		}

		return 0;
	}

	public long getVisitors() {
		if (_visitors == null) {
			return 0;
		}

		return _visitors.longValue();
	}

	public Boolean isPrevious() {
		return _previous;
	}

	public void setKnownVisitors(BigDecimal knownVisitors) {
		_knownVisitors = knownVisitors;
	}

	public void setPrevious(Boolean previous) {
		_previous = previous;
	}

	public void setSessions(BigDecimal sessions) {
		_sessions = sessions;
	}

	public void setVisitors(BigDecimal visitors) {
		_visitors = visitors;
	}

	private BigDecimal _knownVisitors;
	private Boolean _previous;
	private BigDecimal _sessions;
	private BigDecimal _visitors;

}