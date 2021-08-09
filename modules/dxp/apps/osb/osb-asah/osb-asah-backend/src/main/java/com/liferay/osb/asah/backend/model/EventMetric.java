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

import java.util.Objects;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public class EventMetric {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventMetric)) {
			return false;
		}

		EventMetric eventMetric = (EventMetric)obj;

		if (Objects.equals(
				_totalEventsMetric, eventMetric._totalEventsMetric) &&
			Objects.equals(
				_totalSessionsMetric, eventMetric._totalSessionsMetric)) {

			return true;
		}

		return false;
	}

	public Metric getTotalEventsMetric() {
		return _totalEventsMetric;
	}

	public Metric getTotalSessionsMetric() {
		return _totalSessionsMetric;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_totalEventsMetric, _totalSessionsMetric);
	}

	public void setTotalEventsMetric(Metric totalEventsMetric) {
		_totalEventsMetric = totalEventsMetric;
	}

	public void setTotalSessionsMetric(Metric totalSessionsMetric) {
		_totalSessionsMetric = totalSessionsMetric;
	}

	private Metric _totalEventsMetric = new Metric(
		EventMetricType.TOTAL_EVENTS);
	private Metric _totalSessionsMetric = new Metric(
		EventMetricType.TOTAL_SESSIONS);

}