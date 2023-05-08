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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Marcellus Tavares
 */
public enum PageMetricType implements MetricType {

	AVG_TIME_ON_PAGE(
		"avgTimeOnPage", "timeOnPage", "avgTimeOnPageMetric",
		TrendClassification.Order.ASC),
	BOUNCE("bounce", "bounceMetric", TrendClassification.Order.DESC),
	BOUNCE_RATE("bounce", "bounceRateMetric", TrendClassification.Order.DESC),
	CTA_CLICKS("ctaClicks", "ctaClicksMetric", TrendClassification.Order.ASC),
	DIRECT_ACCESS(
		"directAccess", "directAccessMetric", TrendClassification.Order.ASC),
	ENTRANCES("entrances", "entrancesMetric", TrendClassification.Order.ASC),
	EXIT_RATE("exits", "exitRateMetric", TrendClassification.Order.DESC),
	INDIRECT_ACCESS(
		"indirectAccess", "indirectAccessMetric",
		TrendClassification.Order.ASC),
	READS("reads", "readsMetric", TrendClassification.Order.ASC),
	SESSIONS(
		"sessions", "sessionId", "sessionsMetric", false,
		TrendClassification.Order.ASC),
	TIME_ON_PAGE(
		"timeOnPage", "timeOnPageMetric", TrendClassification.Order.ASC),
	VIEWS("views", "viewsMetric", TrendClassification.Order.ASC),
	VISITORS("visitors", "visitorsMetric", TrendClassification.Order.ASC);

	public static PageMetricType of(String name) {
		return Optional.ofNullable(
			_pageMetricTypes.get(name)
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

	@Override
	public boolean isFieldNumeric() {
		return _numericFieldType;
	}

	private PageMetricType(
		String aggregationName, String fieldName, String name,
		boolean numericFieldType, TrendClassification.Order order) {

		_aggregationName = aggregationName;
		_fieldName = fieldName;
		_name = name;
		_numericFieldType = numericFieldType;
		_order = order;
	}

	private PageMetricType(
		String aggregationName, String fieldName, String name,
		TrendClassification.Order order) {

		_aggregationName = aggregationName;
		_fieldName = fieldName;
		_name = name;
		_order = order;
	}

	private PageMetricType(
		String fieldName, String name, TrendClassification.Order order) {

		_fieldName = fieldName;
		_name = name;
		_order = order;

		_aggregationName = fieldName;
	}

	private static final Map<String, PageMetricType> _pageMetricTypes =
		new HashMap<>();

	static {
		Stream.of(
			values()
		).forEach(
			metricType -> _pageMetricTypes.put(metricType.getName(), metricType)
		);
	}

	private final String _aggregationName;
	private final String _fieldName;
	private final String _name;
	private boolean _numericFieldType = true;
	private final TrendClassification.Order _order;

}