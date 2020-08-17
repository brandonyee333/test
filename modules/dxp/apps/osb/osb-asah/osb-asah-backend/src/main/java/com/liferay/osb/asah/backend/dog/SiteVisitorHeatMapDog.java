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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.HeatMapMetric;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class SiteVisitorHeatMapDog {

	public List<HeatMapMetric> getHeatMapMetrics(
		String assetId, String channelId, TimeRange timeRange,
		String timeZoneId) {

		Aggregations aggregations = _dataDog.queryAggregations(
			"pages",
			_buildSearchSourceBuilder(
				assetId, channelId, timeRange, timeZoneId));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Range range = aggregations.get("ranges");

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		if (rangeBuckets.isEmpty()) {
			return Collections.emptyList();
		}

		Range.Bucket rangeBucket = rangeBuckets.get(0);

		Aggregations bucketAggregations = rangeBucket.getAggregations();

		return _createHeatMapMetrics(
			SiteMetricType.VISITORS, bucketAggregations.get("visitor_by_time"));
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		String assetId, String channelId, TimeRange timeRange,
		String timeZoneId) {

		AggregationBuilder aggregationBuilder = _getTermsAggregationBuilder(
			timeZoneId);

		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setDataSourceId(assetId);
		searchQueryContext.setChannelId(channelId);
		searchQueryContext.setTimeRange(timeRange);
		searchQueryContext.setTimeZoneId(timeZoneId);

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			aggregationBuilder, Optional.empty(), Collections.emptySet(), null,
			null, searchQueryContext);
	}

	private List<HeatMapMetric> _createHeatMapMetrics(
		MetricType metricType, Terms terms) {

		Map<Pair<Integer, Integer>, HeatMapMetric> heatMapMetrics =
			new TreeMap<>();

		for (int day = 0; day < 7; day++) {
			for (int hour = 0; hour < 24; hour++) {
				Metric metric = new Metric(metricType);

				metric.setValue(0D);

				heatMapMetrics.put(
					Pair.of(day, hour),
					new HeatMapMetric(
						String.valueOf(day), metric, String.valueOf(hour)));
			}
		}

		for (Terms.Bucket bucket : terms.getBuckets()) {
			Matcher matcher = _pattern.matcher(bucket.getKeyAsString());

			if (matcher.matches()) {
				String dayOfWeek = matcher.group(1);
				String hourOfDay = matcher.group(2);

				Aggregations aggregations = bucket.getAggregations();

				InternalCardinality internalCardinality = aggregations.get(
					"visitor_count");

				Metric metric = new Metric(metricType);

				metric.setValue(internalCardinality.value());

				HeatMapMetric heatMapMetric = new HeatMapMetric(
					dayOfWeek, metric, hourOfDay);

				heatMapMetrics.put(
					Pair.of(
						Integer.valueOf(dayOfWeek), Integer.valueOf(hourOfDay)),
					heatMapMetric);
			}
		}

		return new ArrayList<>(heatMapMetrics.values());
	}

	private AggregationBuilder _getTermsAggregationBuilder(String timeZoneId) {
		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("visitor_by_time");

		termsAggregationBuilder.script(
			ScriptUtil.createScript(
				getClass(), "visitor_heat_map_aggregation_script.painless",
				Collections.singletonMap("timeZoneId", timeZoneId)));
		termsAggregationBuilder.size(24 * 7);
		termsAggregationBuilder.subAggregation(
			AggregationBuilders.cardinality(
				"visitor_count"
			).field(
				"individualId"
			));

		return termsAggregationBuilder;
	}

	private static final Pattern _pattern = Pattern.compile("(\\d+) (\\d+)");

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}