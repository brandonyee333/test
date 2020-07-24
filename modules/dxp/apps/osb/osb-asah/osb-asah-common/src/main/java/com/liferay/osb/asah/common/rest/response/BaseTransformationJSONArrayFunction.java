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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import java.time.LocalDate;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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

	protected String computeEndDayDateString() throws Exception {
		if (_rangeEnd != null) {
			LocalDate endDate = LocalDate.parse(_rangeEnd);

			Calendar calendar = Calendar.getInstance();

			calendar.set(
				endDate.getYear(), endDate.getMonthValue() - 1,
				endDate.getDayOfMonth(), 23, 59, 59);

			endDayDateString = DateUtil.toString(calendar.getTime());

			return endDayDateString;
		}

		endDayDateString = DateUtil.newDateString();

		if (!_includeToday) {
			endDayDateString = DateUtil.newEndOfDayDateString(
				DateUtil.addDays(endDayDateString, -1));
		}

		return endDayDateString;
	}

	protected String computeStartDayDateString(int size) throws Exception {
		if (_rangeStart != null) {
			LocalDate startDate = LocalDate.parse(_rangeStart);

			Calendar calendar = Calendar.getInstance();

			calendar.set(
				startDate.getYear(), startDate.getMonthValue() - 1,
				startDate.getDayOfMonth(), 0, 0, 0);

			startDayDateString = DateUtil.toString(calendar.getTime());

			int deltaDays =
				DateUtil.getDeltaDays(startDayDateString, endDayDateString) + 1;

			startDayDateString = DateUtil.addDays(
				startDayDateString, -deltaDays);

			return startDayDateString;
		}

		if (size == 0) {
			startDayDateString = DateUtil.addHours(endDayDateString, -47);
		}
		else {
			startDayDateString = DateUtil.newDayDateString(
				DateUtil.addDays(DateUtil.newDateString(), 1 - size));
		}

		return startDayDateString;
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
		else if (computeFunctionString.equals("hour")) {
			return DateHistogramInterval.HOUR;
		}
		else if (computeFunctionString.equals("month")) {
			return DateHistogramInterval.MONTH;
		}
		else if (computeFunctionString.equals("week")) {
			return DateHistogramInterval.WEEK;
		}
		else {
			throw new IllegalArgumentException(
				"Unsupported compute function: " + computeFunctionString);
		}
	}

	protected String endDayDateString;
	protected String startDayDateString;

	private final boolean _includeToday;
	private String _rangeEnd;
	private String _rangeStart;
	private long _totalElements;

}