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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Inácio Nery
 */
public enum FormMetricType implements MetricType {

	ABANDONMENTS(
		"abandonmentsMetric", "abandonments", TrendClassification.Order.DESC),
	COMPLETION_TIME(
		"completionTimeMetric", "submissionsTime",
		TrendClassification.Order.DESC),
	SUBMISSIONS(
		"submissionsMetric", "submissions", TrendClassification.Order.ASC),
	VIEWS("viewsMetric", "views", TrendClassification.Order.ASC);

	public static FormMetricType of(String name) {
		return Optional.ofNullable(
			_formMetricTypes.get(name)
		).orElseThrow(
			IllegalArgumentException::new
		);
	}

	@Override
	public String getAggregationName() {
		return _aggregationName;
	}

	@Override
	public String getFieldName() {
		return _fieldName;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public TrendClassification.Order getTrendClassificationOrder() {
		return _order;
	}

	private FormMetricType(
		String name, String fieldName, TrendClassification.Order order) {

		_name = name;
		_fieldName = fieldName;
		_aggregationName = fieldName;
		_order = order;
	}

	private static Map<String, FormMetricType> _formMetricTypes =
		new HashMap<>();

	static {
		Stream.of(
			values()
		).forEach(
			metricType -> _formMetricTypes.put(metricType.getName(), metricType)
		);
	}

	private final String _aggregationName;
	private final String _fieldName;
	private final String _name;
	private final TrendClassification.Order _order;

}