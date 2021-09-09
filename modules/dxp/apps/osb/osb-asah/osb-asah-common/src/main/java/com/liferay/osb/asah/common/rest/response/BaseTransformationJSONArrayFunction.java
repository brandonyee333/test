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

package com.liferay.osb.asah.common.rest.response;

import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

/**
 * @author Shinn Lok
 */
public abstract class BaseTransformationJSONArrayFunction
	implements TransformationJSONArrayFunction {

	public BaseTransformationJSONArrayFunction(boolean includeToday) {
		_includeToday = includeToday;
	}

	public BaseTransformationJSONArrayFunction(
		boolean includeToday, String rangeEnd, String rangeStart) {

		_includeToday = includeToday;
		_rangeEnd = rangeEnd;
		_rangeStart = rangeStart;
	}

	@Override
	public JSONArray apply(
			String collectionName, String computeFunctionString,
			ElasticsearchInvoker elasticsearchInvoker, int page, int size,
			List<Pair<String, SortOrder>> sortOrderPairs,
			String supportedFieldName, QueryBuilder queryBuilder)
		throws Exception {

		_totalElements = size;

		computeEndDayDateString();
		computeStartDayDateString(size);

		ExtendedBounds extendedBounds = new ExtendedBounds(
			startDayDateString, endDayDateString);

		QueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.rangeQuery(
				supportedFieldName
			).lte(
				endDayDateString
			)
		).filter(
			QueryBuilders.rangeQuery(
				supportedFieldName
			).gte(
				startDayDateString
			)
		);

		QueryBuilder finalQueryBuilder = null;

		if (queryBuilder != null) {
			finalQueryBuilder = BoolQueryBuilderUtil.filter(
				queryBuilder
			).filter(
				boolQueryBuilder
			);
		}
		else {
			finalQueryBuilder = boolQueryBuilder;
		}

		if (computeFunctionString.equals("day") && (size <= 2) &&
			(_rangeEnd == null) && (_rangeStart == null)) {

			computeFunctionString = "hour";
		}

		return apply(
			collectionName, computeFunctionString, elasticsearchInvoker,
			extendedBounds, finalQueryBuilder, supportedFieldName);
	}

	@Override
	public long getTotalElements() {
		return _totalElements;
	}

	protected JSONArray apply(
		String collectionName, String computeFunctionString,
		ElasticsearchInvoker elasticsearchInvoker,
		ExtendedBounds extendedBounds, QueryBuilder queryBuilder,
		String supportedFieldName) {

		return null;
	}

	protected void computeEndDayDateString() {
		if (_rangeEnd != null) {
			LocalDateTime endLocalDateTime = LocalDateTime.of(
				LocalDate.parse(_rangeEnd), LocalTime.MAX);

			endDayDateString = endLocalDateTime.toString();

			return;
		}

		LocalDateTime nowLocalDateTime = LocalDateTime.now(
			TimeZoneDogUtil.getZoneId());

		endDayDateString = nowLocalDateTime.toString();

		if (!_includeToday) {
			LocalDateTime localDateTime = nowLocalDateTime.minusDays(1);

			localDateTime = LocalDateTime.of(
				localDateTime.toLocalDate(), LocalTime.MAX);

			endDayDateString = localDateTime.toString();
		}
	}

	protected void computeStartDayDateString(int size) {
		if (_rangeStart != null) {
			LocalDateTime startLocalDateTime = LocalDateTime.of(
				LocalDate.parse(_rangeStart), LocalTime.MIDNIGHT);

			Duration duration = Duration.between(
				startLocalDateTime, LocalDateTime.parse(endDayDateString));

			startLocalDateTime = startLocalDateTime.minusDays(
				duration.toDays() + 1);

			startDayDateString = startLocalDateTime.toString();

			return;
		}

		if (size == 0) {
			LocalDateTime localDateTime = LocalDateTime.parse(endDayDateString);

			localDateTime = localDateTime.minusHours(47);

			startDayDateString = localDateTime.toString();
		}
		else {
			LocalDateTime localDateTime = LocalDateTime.now(
				TimeZoneDogUtil.getZoneId());

			localDateTime = LocalDateTime.of(
				localDateTime.toLocalDate(), LocalTime.MIDNIGHT);

			localDateTime = localDateTime.plusDays(1 - size);

			startDayDateString = localDateTime.toString();
		}
	}

	protected double getAggregationValue(
		Aggregations aggregations, String name) {

		NumericMetricsAggregation.SingleValue singleValue = aggregations.get(
			name);

		if ((singleValue == null) || Double.isNaN(singleValue.value()) ||
			(singleValue.value() < 0)) {

			return 0;
		}

		return singleValue.value();
	}

	protected DateHistogramInterval getDateHistogramInterval(
		String computeFunctionString) {

		if (computeFunctionString.equals("day")) {
			return DateHistogramInterval.DAY;
		}

		if (computeFunctionString.equals("hour")) {
			return DateHistogramInterval.HOUR;
		}

		if (computeFunctionString.equals("month")) {
			return DateHistogramInterval.MONTH;
		}

		if (computeFunctionString.equals("week")) {
			return DateHistogramInterval.WEEK;
		}

		throw new IllegalArgumentException(
			"Unsupported compute function: " + computeFunctionString);
	}

	protected boolean isEmpty(Aggregations aggregations) {
		if (aggregations == null) {
			return true;
		}

		List<Aggregation> aggregationsList = aggregations.asList();

		if (aggregationsList.isEmpty()) {
			return true;
		}

		return false;
	}

	protected String endDayDateString;
	protected String startDayDateString;

	private final boolean _includeToday;
	private String _rangeEnd;
	private String _rangeStart;
	private long _totalElements;

}