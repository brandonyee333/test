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
 * @author Rachael Koestartyo
 */
public enum SiteMetricType implements MetricType {

	ANONYMOUS_VISITORS(
		"anonymousVisitors", "anonymousVisitorsMetric",
		TrendClassification.Order.ASC),
	BOUNCE_RATE("bounce", "bounceRateMetric", TrendClassification.Order.DESC),
	ENGAGEMENT(
		"avgEngagementScore", "engagementScore", "engagementMetric",
		TrendClassification.Order.ASC),
	KNOWN_VISITORS(
		"knownVisitors", "knownVisitorsMetric", TrendClassification.Order.ASC),
	SESSION_DURATION(
		"avgTimeOnPage", "timeOnPage", "sessionDurationMetric",
		TrendClassification.Order.ASC),
	SESSIONS("sessionId", "sessionsMetric", TrendClassification.Order.ASC),
	SESSIONS_PER_VISITOR(
		"sessionsPerVisitor", "sessionsPerVisitorMetric",
		TrendClassification.Order.ASC),
	VIEWS("views", "viewsMetric", TrendClassification.Order.ASC),
	VISITORS("visitors", "visitorsMetric", TrendClassification.Order.ASC);

	public static SiteMetricType of(String name) {
		return Optional.ofNullable(
			_siteMetricTypes.get(name)
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

	private SiteMetricType(
		String aggregationName, String fieldName, String name,
		TrendClassification.Order order) {

		_aggregationName = aggregationName;
		_fieldName = fieldName;
		_name = name;
		_order = order;
	}

	private SiteMetricType(
		String fieldName, String name, TrendClassification.Order order) {

		_aggregationName = fieldName;
		_fieldName = fieldName;
		_name = name;
		_order = order;
	}

	private static Map<String, SiteMetricType> _siteMetricTypes =
		new HashMap<>();

	static {
		Stream.of(
			values()
		).forEach(
			metricType -> _siteMetricTypes.put(metricType.getName(), metricType)
		);
	}

	private final String _aggregationName;
	private final String _fieldName;
	private final String _name;
	private final TrendClassification.Order _order;

}