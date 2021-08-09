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

import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TrendClassification;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
public enum EventMetricType implements MetricType {

	TOTAL_EVENTS("totalEventsMetric"), TOTAL_SESSIONS("totalSessionsMetric");

	public static EventMetricType of(String name) {
		return _eventMetricTypes.get(name);
	}

	@Override
	public String getAggregationName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getFieldName() {
		return _name;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public TrendClassification.Order getTrendClassificationOrder() {
		return TrendClassification.Order.ASC;
	}

	private EventMetricType(String name) {
		_name = name;
	}

	private static final Map<String, EventMetricType> _eventMetricTypes =
		new HashMap<>();

	static {
		Stream.of(
			values()
		).forEach(
			metricType -> _eventMetricTypes.put(
				metricType.getName(), metricType)
		);
	}

	private final String _name;

}