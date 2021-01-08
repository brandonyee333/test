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
 * @author Marcellus Tavares
 */
public enum CustomAssetMetricType implements MetricType {

	ABANDONMENTS(
		"abandonments", "abandonmentsMetric", TrendClassification.Order.DESC),
	CLICKS("clicks", "clicksMetric", TrendClassification.Order.ASC),
	COMPLETION_TIME(
		"submissionsTime", "completionTimeMetric",
		TrendClassification.Order.DESC),
	DOWNLOADS("downloads", "downloadsMetric", TrendClassification.Order.ASC),
	READING_TIME(
		"avgReadTime", "readTime", "readingTimeMetric",
		TrendClassification.Order.ASC),
	SUBMISSIONS(
		"submissions", "submissionsMetric", TrendClassification.Order.ASC),
	VIEWS("views", "viewsMetric", TrendClassification.Order.ASC);

	public static CustomAssetMetricType of(String name) {
		return Optional.ofNullable(
			_customAssetMetricTypes.get(name)
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

	private CustomAssetMetricType(
		String aggregationName, String fieldName, String name,
		TrendClassification.Order order) {

		_aggregationName = aggregationName;
		_fieldName = fieldName;
		_name = name;
		_order = order;
	}

	private CustomAssetMetricType(
		String fieldName, String name, TrendClassification.Order order) {

		_aggregationName = fieldName;
		_fieldName = fieldName;
		_name = name;
		_order = order;
	}

	private static Map<String, CustomAssetMetricType> _customAssetMetricTypes =
		new HashMap<>();

	static {
		Stream.of(
			values()
		).forEach(
			metricType -> _customAssetMetricTypes.put(
				metricType.getName(), metricType)
		);
	}

	private final String _aggregationName;
	private final String _fieldName;
	private final String _name;
	private final TrendClassification.Order _order;

}