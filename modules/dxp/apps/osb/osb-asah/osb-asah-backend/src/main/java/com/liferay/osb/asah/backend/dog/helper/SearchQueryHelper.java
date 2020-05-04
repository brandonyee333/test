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

package com.liferay.osb.asah.backend.dog.helper;

import com.liferay.osb.asah.backend.constants.DataConstants;
import com.liferay.osb.asah.backend.dog.resolver.AssetResolver;
import com.liferay.osb.asah.backend.model.AssetId;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.Geolocation;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.Technology;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.model.URL;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;

import java.time.LocalDate;

import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.joda.time.DateTimeZone;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class SearchQueryHelper {

	public BoolQueryBuilder createFilterBoolQueryBuilder(
		Optional<AssetId> assetIdOptional, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		BoolQueryBuilder boolQueryBuilder = createFilterBoolQueryBuilder(
			assetIdOptional, searchQueryContext);

		_addFieldRangeFilter(boolQueryBuilder, metricType);

		return boolQueryBuilder;
	}

	public BoolQueryBuilder createFilterBoolQueryBuilder(
		Optional<AssetId> assetIdOptional,
		SearchQueryContext searchQueryContext) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		_addAssetIdFilter(assetIdOptional, boolQueryBuilder);
		_addChannelIdFilter(
			boolQueryBuilder, searchQueryContext.getChannelId());
		_addDataSourceIdFilter(
			boolQueryBuilder, searchQueryContext.getDataSourceId());
		_addGeolocationFilter(
			boolQueryBuilder, searchQueryContext.getGeolocation());
		_addTechnologyFilter(
			boolQueryBuilder, searchQueryContext.getTechnology());
		_addTermsFilter(boolQueryBuilder, searchQueryContext.getTerms());
		_addTimeRangeFilter(
			boolQueryBuilder, searchQueryContext.getTimeRange(),
			searchQueryContext.getTimeZoneId());
		_addTitleFilter(boolQueryBuilder, searchQueryContext.getTitle());
		_addURLFilter(
			searchQueryContext.getAssetType(), boolQueryBuilder,
			searchQueryContext.getURL());

		return boolQueryBuilder;
	}

	public SearchSourceBuilder createPeriodRangeSearchSourceBuilder(
		Set<AggregationBuilder> aggregationBuilders,
		Optional<AssetId> assetIdOptional,
		Set<PipelineAggregationBuilder> pipelineAggregationBuilders,
		QueryBuilder queryBuilder, SearchQueryContext searchQueryContext) {

		DateRangeAggregationBuilder dateRangeAggregationBuilder =
			_createPeriodDateRangeAggregationBuilder(
				searchQueryContext.getTimeRange(),
				searchQueryContext.getTimeZoneId());

		aggregationBuilders.forEach(
			dateRangeAggregationBuilder::subAggregation);
		pipelineAggregationBuilders.forEach(
			dateRangeAggregationBuilder::subAggregation);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (queryBuilder != null) {
			boolQueryBuilder.filter(queryBuilder);
		}

		_addPeriodRangeFilter(
			boolQueryBuilder, searchQueryContext.getTimeRange(),
			searchQueryContext.getTimeZoneId());

		return createSearchSourceBuilder(
			dateRangeAggregationBuilder, assetIdOptional, boolQueryBuilder,
			searchQueryContext);
	}

	public RangeQueryBuilder createRangeQueryBuilder(
		String name, TimeRange timeRange) {

		return createRangeQueryBuilder(name, timeRange, "UTC");
	}

	public RangeQueryBuilder createRangeQueryBuilder(
		String name, TimeRange timeRange, String timeZoneId) {

		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(name);

		rangeQueryBuilder.timeZone(timeZoneId);

		if (TimeRange.LAST_24_HOURS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-23h/h");
			rangeQueryBuilder.lt("now");
		}
		else if (TimeRange.YESTERDAY.equals(timeRange)) {
			rangeQueryBuilder.gte("now-1d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else if (TimeRange.LAST_7_DAYS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-7d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else if (TimeRange.LAST_28_DAYS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-28d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else if (TimeRange.LAST_30_DAYS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-30d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else if (TimeRange.LAST_90_DAYS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-90d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else {
			LocalDate startLocalDate = timeRange.getStartLocalDate();

			rangeQueryBuilder.gte(startLocalDate.toString());

			LocalDate endLocalDate = timeRange.getEndLocalDate();

			endLocalDate = endLocalDate.plusDays(1);

			rangeQueryBuilder.lt(endLocalDate.toString());
		}

		return rangeQueryBuilder;
	}

	public SearchSourceBuilder createRangeSearchSourceBuilder(
		AggregationBuilder aggregationBuilder,
		Optional<AssetId> assetIdOptional, Set<String> assetIds,
		AssetResolver<?> assetResolver, QueryBuilder queryBuilder,
		SearchQueryContext searchQueryContext) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (queryBuilder != null) {
			boolQueryBuilder.filter(queryBuilder);
		}

		for (String assetId : assetIds) {
			boolQueryBuilder.should(
				QueryBuilders.termQuery(
					assetResolver.getAssetIdFieldName(), assetId));
		}

		_addTimeRangeFilter(
			assetIds, boolQueryBuilder, searchQueryContext.getTimeRange(),
			searchQueryContext.getTimeZoneId());

		return createSearchSourceBuilder(
			_createDateRangeAggregationBuilder(
				aggregationBuilder, searchQueryContext),
			assetIdOptional, boolQueryBuilder, searchQueryContext);
	}

	public SearchSourceBuilder createRangeSearchSourceBuilder(
		AggregationBuilder aggregationBuilder,
		Optional<AssetId> assetIdOptional, Set<String> assetIds,
		MetricType metricType, SearchQueryContext searchQueryContext) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		_addFieldRangeFilter(boolQueryBuilder, metricType);
		_addTimeRangeFilter(
			assetIds, boolQueryBuilder, searchQueryContext.getTimeRange(),
			searchQueryContext.getTimeZoneId());

		return createSearchSourceBuilder(
			_createDateRangeAggregationBuilder(
				aggregationBuilder, searchQueryContext),
			assetIdOptional, boolQueryBuilder, searchQueryContext);
	}

	public SearchSourceBuilder createRangeSearchSourceBuilder(
		Set<AggregationBuilder> aggregationBuilders,
		Optional<AssetId> assetIdOptional,
		Set<PipelineAggregationBuilder> pipelineAggregationBuilders,
		QueryBuilder queryBuilder, SearchQueryContext searchQueryContext) {

		DateRangeAggregationBuilder dateRangeAggregationBuilder =
			_createDateRangeAggregationBuilder(
				searchQueryContext.getTimeRange(),
				searchQueryContext.getTimeZoneId());

		aggregationBuilders.forEach(
			dateRangeAggregationBuilder::subAggregation);
		pipelineAggregationBuilders.forEach(
			dateRangeAggregationBuilder::subAggregation);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (queryBuilder != null) {
			boolQueryBuilder.filter(queryBuilder);
		}

		_addTimeRangeFilter(
			boolQueryBuilder, searchQueryContext.getTimeRange(),
			searchQueryContext.getTimeZoneId());

		return createSearchSourceBuilder(
			dateRangeAggregationBuilder, assetIdOptional, boolQueryBuilder,
			searchQueryContext);
	}

	public SearchSourceBuilder createSearchSourceBuilder(
		AggregationBuilder aggregationBuilder,
		Optional<AssetId> assetIdOptional, BoolQueryBuilder boolQueryBuilder,
		SearchQueryContext searchQueryContext) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(aggregationBuilder);

		_addAssetIdFilter(assetIdOptional, boolQueryBuilder);
		_addChannelIdFilter(
			boolQueryBuilder, searchQueryContext.getChannelId());
		_addDataSourceIdFilter(
			boolQueryBuilder, searchQueryContext.getDataSourceId());
		_addExperienceIdFilter(
			boolQueryBuilder, searchQueryContext.getExperienceId());
		_addExperimentIdFilter(
			boolQueryBuilder, searchQueryContext.getExperimentId());
		_addGeolocationFilter(
			boolQueryBuilder, searchQueryContext.getGeolocation());
		_addKeywordsFilter(
			searchQueryContext.getAssetType(), boolQueryBuilder,
			searchQueryContext.getKeywords());
		_addTechnologyFilter(
			boolQueryBuilder, searchQueryContext.getTechnology());
		_addTermsFilter(boolQueryBuilder, searchQueryContext.getTerms());
		_addTitleFilter(boolQueryBuilder, searchQueryContext.getTitle());
		_addURLFilter(
			searchQueryContext.getAssetType(), boolQueryBuilder,
			searchQueryContext.getURL());
		_addVariantIdFilter(
			boolQueryBuilder, searchQueryContext.getVariantId());

		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	public String getURLFieldName(AssetType assetType) {
		if (assetType == AssetType.PAGE) {
			return "url";
		}

		return "urls";
	}

	private void _addAssetIdFilter(
		Optional<AssetId> assetIdOptional, BoolQueryBuilder boolQueryBuilder) {

		if (!assetIdOptional.isPresent()) {
			return;
		}

		AssetId assetId = assetIdOptional.get();

		if (assetId.getFieldValue() != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					assetId.getFieldName(), assetId.getFieldValue()));
		}
	}

	private void _addChannelIdFilter(
		BoolQueryBuilder boolQueryBuilder, String channelId) {

		if (channelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("channelId", channelId));
		}
	}

	private void _addDataSourceIdFilter(
		BoolQueryBuilder boolQueryBuilder, String dataSourceId) {

		if (dataSourceId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("dataSourceId", dataSourceId));
		}
	}

	private void _addExperienceIdFilter(
		BoolQueryBuilder boolQueryBuilder, String experienceId) {

		if (experienceId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("experienceId", experienceId));
		}
	}

	private void _addExperimentIdFilter(
		BoolQueryBuilder boolQueryBuilder, String experimentId) {

		if (experimentId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("experimentId", experimentId));
		}
	}

	private void _addFieldRangeFilter(
		BoolQueryBuilder boolQueryBuilder, MetricType metricType) {

		if (metricType.isFieldNumeric()) {
			RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
				metricType.getFieldName());

			rangeQueryBuilder.gt(0);

			boolQueryBuilder.filter(rangeQueryBuilder);
		}
		else {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery(metricType.getFieldName()));
		}
	}

	private void _addGeolocationFilter(
		BoolQueryBuilder boolQueryBuilder, Geolocation geolocation) {

		if (geolocation.equals(Geolocation.any())) {
			return;
		}

		String country = geolocation.getCountry();

		if (country.equals(DataConstants.UNKNOWN)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("country")));
		}
		else {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("country", country));
		}
	}

	private void _addKeywordsFilter(
		AssetType assetType, BoolQueryBuilder boolQueryBuilder,
		String keywords) {

		if (StringUtils.isBlank(keywords)) {
			return;
		}

		BoolQueryBuilder keywordsQueryBuilder = BoolQueryBuilderUtil.should(
			QueryBuilders.queryStringQuery(
				String.format(
					"%s:*%s*", "title.search",
					QueryUtil.escapeKeywords(keywords)))
		).should(
			QueryBuilders.matchQuery(
				"title.search", keywords
			).fuzziness(
				Fuzziness.AUTO
			)
		);

		if (assetType == AssetType.PAGE) {
			keywordsQueryBuilder.should(
				QueryBuilders.wildcardQuery(
					"url.search", "*" + keywords + "*"));
		}

		boolQueryBuilder.filter(keywordsQueryBuilder);
	}

	private void _addPeriodRangeFilter(
		BoolQueryBuilder boolQueryBuilder, TimeRange timeRange,
		String timeZoneId) {

		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
			"eventDate");

		rangeQueryBuilder.timeZone(timeZoneId);

		if (TimeRange.LAST_24_HOURS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-47h/h");
			rangeQueryBuilder.lt("now");
		}
		else if (TimeRange.YESTERDAY.equals(timeRange)) {
			rangeQueryBuilder.gte("now-2d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else if (TimeRange.LAST_7_DAYS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-14d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else if (TimeRange.LAST_28_DAYS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-56d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else if (TimeRange.LAST_30_DAYS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-60d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else if (TimeRange.LAST_90_DAYS.equals(timeRange)) {
			rangeQueryBuilder.gte("now-180d/d");
			rangeQueryBuilder.lt("now/d");
		}
		else {
			LocalDate endLocalDate = timeRange.getEndLocalDate();
			LocalDate startLocalDate = timeRange.getStartLocalDate();

			endLocalDate = endLocalDate.plusDays(1);

			startLocalDate = startLocalDate.minusDays(timeRange.getRangeKey());

			rangeQueryBuilder.gte(startLocalDate.toString());

			rangeQueryBuilder.lt(endLocalDate.toString());
		}

		boolQueryBuilder.filter(rangeQueryBuilder);
	}

	private void _addTechnologyFilter(
		BoolQueryBuilder boolQueryBuilder, Technology technology) {

		if (!technology.equals(Technology.any())) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"deviceType", technology.getDeviceType()));
		}
	}

	private void _addTermsFilter(
		BoolQueryBuilder boolQueryBuilder, String terms) {

		if (StringUtils.isBlank(terms)) {
			return;
		}

		BoolQueryBuilder termsQueryBuilder = BoolQueryBuilderUtil.should(
			QueryBuilders.queryStringQuery(
				String.format("%s:*%s*", "title.search", terms))
		).should(
			QueryBuilders.matchQuery(
				"title.search", terms
			).fuzziness(
				Fuzziness.AUTO
			)
		);

		boolQueryBuilder.filter(termsQueryBuilder);
	}

	private void _addTimeRangeFilter(
		BoolQueryBuilder boolQueryBuilder, TimeRange timeRange,
		String timeZoneId) {

		boolQueryBuilder.filter(
			createRangeQueryBuilder("eventDate", timeRange, timeZoneId));
	}

	private void _addTimeRangeFilter(
		Set<String> assetIds, BoolQueryBuilder boolQueryBuilder,
		TimeRange timeRange, String timeZoneId) {

		if (assetIds.isEmpty()) {
			_addTimeRangeFilter(boolQueryBuilder, timeRange, timeZoneId);
		}
	}

	private void _addTitleFilter(
		BoolQueryBuilder boolQueryBuilder, String title) {

		if (title != null) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("title", title));
		}
	}

	private void _addURLFilter(
		AssetType assetType, BoolQueryBuilder boolQueryBuilder, URL url) {

		if (!url.equals(URL.any())) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					getURLFieldName(assetType), url.getURL()));
		}
	}

	private void _addVariantIdFilter(
		BoolQueryBuilder boolQueryBuilder, String variantId) {

		if (variantId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("variantId", variantId));
		}
	}

	private AggregationBuilder _createDateRangeAggregationBuilder(
		AggregationBuilder childAggregationBuilder,
		SearchQueryContext searchQueryContext) {

		DateRangeAggregationBuilder dateRangeAggregationBuilder =
			_createDateRangeAggregationBuilder(
				searchQueryContext.getTimeRange(),
				searchQueryContext.getTimeZoneId());

		dateRangeAggregationBuilder.subAggregation(childAggregationBuilder);

		return dateRangeAggregationBuilder;
	}

	private DateRangeAggregationBuilder _createDateRangeAggregationBuilder(
		TimeRange timeRange, String timeZoneId) {

		DateRangeAggregationBuilder dateRangeAggregationBuilder =
			AggregationBuilders.dateRange("ranges");

		dateRangeAggregationBuilder.field("eventDate");
		dateRangeAggregationBuilder.timeZone(DateTimeZone.forID(timeZoneId));

		if (TimeRange.LAST_24_HOURS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange("current", "now-23h/h", "now");
		}
		else if (TimeRange.YESTERDAY.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-1d/d", "now/d");
		}
		else if (TimeRange.LAST_7_DAYS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-7d/d", "now/d");
		}
		else if (TimeRange.LAST_28_DAYS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-28d/d", "now/d");
		}
		else if (TimeRange.LAST_30_DAYS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-30d/d", "now/d");
		}
		else if (TimeRange.LAST_90_DAYS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-90d/d", "now/d");
		}
		else {
			LocalDate endLocalDate = timeRange.getEndLocalDate();
			LocalDate startLocalDate = timeRange.getStartLocalDate();

			endLocalDate = endLocalDate.plusDays(1);

			dateRangeAggregationBuilder.addRange(
				"current", startLocalDate.toString(), endLocalDate.toString());
		}

		return dateRangeAggregationBuilder;
	}

	private DateRangeAggregationBuilder
		_createPeriodDateRangeAggregationBuilder(
			TimeRange timeRange, String timeZoneId) {

		DateRangeAggregationBuilder dateRangeAggregationBuilder =
			AggregationBuilders.dateRange("period_ranges");

		dateRangeAggregationBuilder.field("eventDate");
		dateRangeAggregationBuilder.timeZone(DateTimeZone.forID(timeZoneId));

		if (TimeRange.LAST_24_HOURS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange("current", "now-23h/h", "now");
			dateRangeAggregationBuilder.addRange(
				"previous", "now-47h/h", "now-23h/h");
		}
		else if (TimeRange.YESTERDAY.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-1d/d", "now/d");
			dateRangeAggregationBuilder.addRange(
				"previous", "now-2d/d", "now-1d/d");
		}
		else if (TimeRange.LAST_7_DAYS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-7d/d", "now/d");
			dateRangeAggregationBuilder.addRange(
				"previous", "now-14d/d", "now-7d/d");
		}
		else if (TimeRange.LAST_28_DAYS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-28d/d", "now/d");
			dateRangeAggregationBuilder.addRange(
				"previous", "now-56d/d", "now-28d/d");
		}
		else if (TimeRange.LAST_30_DAYS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-30d/d", "now/d");
			dateRangeAggregationBuilder.addRange(
				"previous", "now-60d/d", "now-30d/d");
		}
		else if (TimeRange.LAST_90_DAYS.equals(timeRange)) {
			dateRangeAggregationBuilder.addRange(
				"current", "now-90d/d", "now/d");
			dateRangeAggregationBuilder.addRange(
				"previous", "now-180d/d", "now-90d/d");
		}
		else {
			LocalDate endLocalDate = timeRange.getEndLocalDate();
			LocalDate startLocalDate = timeRange.getStartLocalDate();

			endLocalDate = endLocalDate.plusDays(1);

			dateRangeAggregationBuilder.addRange(
				"current", startLocalDate.toString(), endLocalDate.toString());

			endLocalDate = endLocalDate.minusDays(timeRange.getRangeKey());

			startLocalDate = startLocalDate.minusDays(timeRange.getRangeKey());

			dateRangeAggregationBuilder.addRange(
				"previous", startLocalDate.toString(), endLocalDate.toString());
		}

		return dateRangeAggregationBuilder;
	}

}