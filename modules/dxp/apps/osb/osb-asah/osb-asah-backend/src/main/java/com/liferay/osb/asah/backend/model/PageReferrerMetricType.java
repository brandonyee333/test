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
public enum PageReferrerMetricType implements MetricType {

	ACCESS("access", "accessMetric", TrendClassification.Order.ASC);

	public static PageReferrerMetricType of(String name) {
		return Optional.ofNullable(
			_pageReferrerMetricTypes.get(name)
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

	private PageReferrerMetricType(
		String fieldName, String name, TrendClassification.Order order) {

		_aggregationName = fieldName;
		_fieldName = fieldName;
		_name = name;
		_order = order;
	}

	private static Map<String, PageReferrerMetricType>
		_pageReferrerMetricTypes = new HashMap<>();

	static {
		Stream.of(
			values()
		).forEach(
			metricType -> _pageReferrerMetricTypes.put(
				metricType.getName(), metricType)
		);
	}

	private final String _aggregationName;
	private final String _fieldName;
	private final String _name;
	private final TrendClassification.Order _order;

}