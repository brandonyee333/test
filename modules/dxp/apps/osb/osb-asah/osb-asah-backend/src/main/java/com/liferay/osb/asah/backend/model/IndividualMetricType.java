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
 * @author Matthew Kong
 */
public enum IndividualMetricType implements MetricType {

	ANONYMOUS_INDIVIDUALS(
		"dateCreated", "anonymousIndividualsMetric",
		TrendClassification.Order.ASC),
	KNOWN_INDIVIDUALS(
		"firstEnrichmentDate", "knownIndividualsMetric",
		TrendClassification.Order.ASC),
	TOTAL_INDIVIDUALS(
		"dateCreated", "totalIndividualsMetric", TrendClassification.Order.ASC);

	public static IndividualMetricType of(String name) {
		return Optional.ofNullable(
			_individualMetricTypes.get(name)
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

	private IndividualMetricType(
		String fieldName, String name, TrendClassification.Order order) {

		_aggregationName = fieldName;
		_fieldName = fieldName;
		_name = name;
		_order = order;
	}

	private static Map<String, IndividualMetricType> _individualMetricTypes =
		new HashMap<>();

	static {
		Stream.of(
			values()
		).forEach(
			metricType -> _individualMetricTypes.put(
				metricType.getName(), metricType)
		);
	}

	private final String _aggregationName;
	private final String _fieldName;
	private final String _name;
	private final TrendClassification.Order _order;

}