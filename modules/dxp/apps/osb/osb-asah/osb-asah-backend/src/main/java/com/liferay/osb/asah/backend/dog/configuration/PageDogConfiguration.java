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

package com.liferay.osb.asah.backend.dog.configuration;

import com.liferay.osb.asah.backend.dog.DogUtil;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.resolver.AssetResolver;
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.backend.model.PageMetricType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.SingleBucketAggregation;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Cardinality;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalSum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;

import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class PageDogConfiguration extends BaseDogConfiguration {

	@Override
	public AssetResolver<PageMetric> getAssetResolver() {
		AssetResolver.Builder<PageMetric> builder = AssetResolver.builder(
			"url");

		builder.searchableFieldNames("url", "title");
		builder.supplier(PageMetric::new);

		return builder.build();
	}

	@Override
	public AssetType getAssetType() {
		return AssetType.PAGE;
	}

	@Override
	public String getCollection() {
		return "pages";
	}

	@Override
	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics) {
		Set<MetricResolver> metricResolvers = new HashSet<>();

		if (selectedMetrics.contains(
				PageMetricType.AVG_TIME_ON_PAGE.getName())) {

			metricResolvers.add(_buildAvgTimeOnPageMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.BOUNCE.getName())) {
			metricResolvers.add(_buildBounceMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.BOUNCE_RATE.getName())) {
			metricResolvers.add(_buildBounceRateMetricResolver());
		}

		if (selectedMetrics.contains(
				PageMetricType.CLICK_THROUGH_PROBABILITY.getName())) {

			metricResolvers.add(_buildCTPMetricResolver());
		}

		if (selectedMetrics.contains(
				PageMetricType.CLICK_THROUGH_RATE.getName())) {

			metricResolvers.add(_buildCTRMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.DIRECT_ACCESS.getName())) {
			metricResolvers.add(_buildDirectAccessMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.ENGAGEMENT.getName())) {
			metricResolvers.add(_buildEngagementMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.ENTRANCES.getName())) {
			metricResolvers.add(_buildEntrancesMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.EXIT_RATE.getName())) {
			metricResolvers.add(_buildExitRateMetricResolver());
		}

		if (selectedMetrics.contains(
				PageMetricType.INDIRECT_ACCESS.getName())) {

			metricResolvers.add(_buildIndirectAccessMetricResolver());
		}

		if (selectedMetrics.contains(
				PageMetricType.MAX_SCROLL_DEPTH.getName())) {

			metricResolvers.add(_buildMaxScrollDepthMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.READS.getName())) {
			metricResolvers.add(_buildReadsMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.SESSIONS.getName())) {
			metricResolvers.add(_buildSessionsMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.TIME_ON_PAGE.getName())) {
			metricResolvers.add(_buildTimeOnPageMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.VIEWS.getName())) {
			metricResolvers.add(_buildViewsMetricResolver());
		}

		if (selectedMetrics.contains(PageMetricType.VISITORS.getName())) {
			metricResolvers.add(_buildVisitorsMetricResolver());
		}

		if (selectedMetrics.contains("defaultMetric") ||
			metricResolvers.isEmpty()) {

			metricResolvers.add(_buildViewsMetricResolver());
		}

		return metricResolvers;
	}

	@Override
	public Function<String, MetricType> getMetricTypeResolverFunction() {
		return PageMetricType::of;
	}

	@Override
	public QueryBuilder getQueryBuilder(SearchQueryContext searchQueryContext) {
		if (searchQueryContext.isIncludeActiveSessions()) {
			return null;
		}

		return QueryBuilders.existsQuery(
			PageMetricType.SESSIONS.getFieldName());
	}

	@Override
	protected AggregationBuilder createCardinalityAggregationBuilder(
		String cardinalityName, String fieldName) {

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			(CardinalityAggregationBuilder)
				super.createCardinalityAggregationBuilder(
					cardinalityName, fieldName);

		cardinalityAggregationBuilder.precisionThreshold(2000);

		return cardinalityAggregationBuilder;
	}

	private MetricResolver _buildAvgTimeOnPageMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.AVG_TIME_ON_PAGE);

		builder.aggregate(_createAvgTimeOnPageAggregationBuilder());
		builder.mapperFunction(this::_getAvgTimeOnPageAggregationValue);
		builder.setterBiConsumer(PageMetric::setAvgTimeOnPageMetric);

		return builder.build();
	}

	private MetricResolver _buildBounceMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.BOUNCE);

		builder.aggregate(
			_createSumAggregationBuilder(
				PageMetricType.BOUNCE,
				PageMetricType.BOUNCE.getAggregationName() + "_field"));
		builder.mapperFunction(
			aggregations -> DogUtil.getSingleValue(
				aggregations.get(
					PageMetricType.BOUNCE.getFieldName() + "_field")));
		builder.setterBiConsumer(PageMetric::setBounceMetric);

		return builder.build();
	}

	private MetricResolver _buildBounceRateMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.BOUNCE_RATE);

		_createBounceRateAggregations(builder);

		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, PageMetricType.BOUNCE_RATE));
		builder.setterBiConsumer(PageMetric::setBounceRateMetric);

		return builder.build();
	}

	private MetricResolver _buildCTPMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.CLICK_THROUGH_PROBABILITY);

		_createCTAClicksAggregations(
			builder, PageMetricType.CLICK_THROUGH_PROBABILITY,
			createCardinalityAggregationBuilder(
				"individuals_count", "individualId"));

		builder.mapperFunction(
			aggregations -> _getCTAClicksAggregationValue(
				aggregations,
				PageMetricType.CLICK_THROUGH_PROBABILITY.getName(),
				"individuals_count"));
		builder.setterBiConsumer(PageMetric::setCTPMetric);

		return builder.build();
	}

	private MetricResolver _buildCTRMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.CLICK_THROUGH_RATE);

		_createCTAClicksAggregations(
			builder, PageMetricType.CLICK_THROUGH_RATE,
			createCardinalityAggregationBuilder(
				"sessions_count", PageMetricType.SESSIONS.getFieldName()));

		builder.mapperFunction(
			aggregations -> _getCTAClicksAggregationValue(
				aggregations, PageMetricType.CLICK_THROUGH_RATE.getName(),
				"sessions_count"));
		builder.setterBiConsumer(PageMetric::setCTRMetric);

		return builder.build();
	}

	private MetricResolver _buildDirectAccessMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.DIRECT_ACCESS);

		builder.setterBiConsumer(PageMetric::setDirectAccessMetric);

		return builder.build();
	}

	private MetricResolver _buildEngagementMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.ENGAGEMENT);

		builder.aggregate(_createEngagementAggregationBuilder());
		builder.mapperFunction(this::_getEngagementAggregationValue);
		builder.setterBiConsumer(PageMetric::setEngagementMetric);

		return builder.build();
	}

	private MetricResolver _buildEntrancesMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.ENTRANCES);

		builder.aggregate(
			_createSumAggregationBuilder(PageMetricType.ENTRANCES));
		builder.mapperFunction(
			aggregations -> {
				InternalSum internalSum = aggregations.get(
					PageMetricType.ENTRANCES.getAggregationName());

				return internalSum.getValue();
			});
		builder.setterBiConsumer(PageMetric::setEntrancesMetric);

		return builder.build();
	}

	private MetricResolver _buildExitRateMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.EXIT_RATE);

		_createExitRateAggregations(builder);

		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, PageMetricType.EXIT_RATE));
		builder.setterBiConsumer(PageMetric::setExitRateMetric);

		return builder.build();
	}

	private MetricResolver _buildIndirectAccessMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.INDIRECT_ACCESS);

		builder.setterBiConsumer(PageMetric::setIndirectAccessMetric);

		return builder.build();
	}

	private MetricResolver _buildMaxScrollDepthMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.MAX_SCROLL_DEPTH);

		builder.aggregate(_createMaxScrollDepthAggregationBuilder());
		builder.mapperFunction(this::_getMaxScrollDepthAggregationValue);
		builder.setterBiConsumer(PageMetric::setMaxScrollDepthMetric);

		return builder.build();
	}

	private MetricResolver _buildReadsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.READS);

		builder.bucketOrderTermsAggregationBuilderConsumer(
			createBucketOrderTermsAggregationBuilderConsumer(
				PageMetricType.READS));
		builder.setterBiConsumer(PageMetric::setReadsMetric);

		return builder.build();
	}

	private MetricResolver _buildSessionsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.SESSIONS);

		builder.aggregate(
			createCardinalityAggregationBuilder(
				PageMetricType.SESSIONS.getAggregationName(),
				PageMetricType.SESSIONS.getFieldName()));
		builder.mapperFunction(
			aggregations -> DogUtil.getSingleValue(
				aggregations.get(
					PageMetricType.SESSIONS.getAggregationName())));

		builder.setterBiConsumer(PageMetric::setSessionsMetric);

		return builder.build();
	}

	private MetricResolver _buildTimeOnPageMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.TIME_ON_PAGE);

		builder.setterBiConsumer(PageMetric::setTimeOnPageMetric);

		return builder.build();
	}

	private MetricResolver _buildViewsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.VIEWS);

		builder.bucketOrderTermsAggregationBuilderConsumer(
			createBucketOrderTermsAggregationBuilderConsumer(
				PageMetricType.VIEWS));
		builder.setterBiConsumer(PageMetric::setViewsMetric);

		return builder.build();
	}

	private MetricResolver _buildVisitorsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			PageMetricType.VISITORS);

		builder.aggregate(
			createTotalIndividualsAggregationBuilder(PageMetricType.VIEWS));
		builder.aggregate(_createVisitorsPipelineAggregationBuilder());
		builder.mapperFunction(this::_getUsersAggregationValue);
		builder.setterBiConsumer(PageMetric::setVisitorsMetric);

		return builder.build();
	}

	private AvgAggregationBuilder _createAvgAggregationBuilder(
		MetricType metricType) {

		AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg(
			metricType.getAggregationName());

		avgAggregationBuilder.field(metricType.getFieldName());

		return avgAggregationBuilder;
	}

	private AggregationBuilder _createAvgTimeOnPageAggregationBuilder() {
		return _createAvgAggregationBuilder(PageMetricType.AVG_TIME_ON_PAGE);
	}

	private void _createBounceRateAggregations(MetricResolver.Builder builder) {
		builder.aggregate(
			_createSumAggregationBuilder(
				PageMetricType.BOUNCE_RATE,
				PageMetricType.BOUNCE_RATE.getAggregationName() + "_field"));
		builder.aggregate(
			createCardinalityAggregationBuilder("sessions_count", "sessionId"));
		builder.aggregate(
			createDivisionPipelineAggregationBuilder(
				PageMetricType.BOUNCE_RATE.getAggregationName(),
				"sessions_count"));
	}

	private void _createCTAClicksAggregations(
		MetricResolver.Builder builder, MetricType metricType,
		AggregationBuilder aggregationBuilder) {

		QueryBuilder filterQueryBuilder = QueryBuilders.rangeQuery(
			metricType.getFieldName()
		).gt(
			0
		);

		FilterAggregationBuilder filterAggregationBuilder =
			AggregationBuilders.filter(
				metricType.getName(), filterQueryBuilder);

		filterAggregationBuilder.subAggregation(aggregationBuilder);

		builder.aggregate(filterAggregationBuilder);
	}

	private AggregationBuilder _createEngagementAggregationBuilder() {
		return _createAvgAggregationBuilder(PageMetricType.ENGAGEMENT);
	}

	private void _createExitRateAggregations(MetricResolver.Builder builder) {
		builder.aggregate(
			_createSumAggregationBuilder(
				PageMetricType.EXIT_RATE,
				PageMetricType.EXIT_RATE.getAggregationName() + "_field"));
		builder.aggregate(
			createCardinalityAggregationBuilder("sessions_count", "sessionId"));
		builder.aggregate(
			createDivisionPipelineAggregationBuilder(
				PageMetricType.EXIT_RATE.getAggregationName(),
				"sessions_count"));
	}

	private NestedAggregationBuilder _createMaxScrollDepthAggregationBuilder() {
		String fieldName = PageMetricType.MAX_SCROLL_DEPTH.getAggregationName();

		NestedAggregationBuilder nestedAggregationBuilder =
			AggregationBuilders.nested("nested_" + fieldName, fieldName);

		return nestedAggregationBuilder.subAggregation(
			AggregationBuilders.max(
				fieldName
			).field(
				PageMetricType.MAX_SCROLL_DEPTH.getFieldName() + ".depth"
			));
	}

	private SumAggregationBuilder _createSumAggregationBuilder(
		MetricType metricType) {

		return _createSumAggregationBuilder(
			metricType, metricType.getAggregationName());
	}

	private SumAggregationBuilder _createSumAggregationBuilder(
		MetricType metricType, String name) {

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			name);

		sumAggregationBuilder.field(metricType.getFieldName());

		return sumAggregationBuilder;
	}

	private PipelineAggregationBuilder
		_createVisitorsPipelineAggregationBuilder() {

		return PipelineAggregatorBuilders.bucketScript(
			"visitors",
			new HashMap<String, String>() {
				{
					put(
						"sessions_count",
						"total > missing_count > sessions_count");
					put("total_users_count", "total > users_count");
				}
			},
			new Script("params.sessions_count + params.total_users_count"));
	}

	private Double _getAvgTimeOnPageAggregationValue(
		Aggregations aggregations) {

		return getAvgAggregationValue(
			aggregations, PageMetricType.AVG_TIME_ON_PAGE);
	}

	private Double _getCTAClicksAggregationValue(
		Aggregations aggregations, String aggregationName,
		String subaggregationName) {

		SingleBucketAggregation singleBucketAggregation = aggregations.get(
			aggregationName);

		Aggregations singleBucketAggregationAggregations =
			singleBucketAggregation.getAggregations();

		Cardinality cardinality = singleBucketAggregationAggregations.get(
			subaggregationName);

		return DogUtil.getSingleValue(cardinality);
	}

	private Double _getEngagementAggregationValue(Aggregations aggregations) {
		return getAvgAggregationValue(aggregations, PageMetricType.ENGAGEMENT);
	}

	private Double _getMaxScrollDepthAggregationValue(
		Aggregations aggregations) {

		InternalNested internalNested = aggregations.get(
			"nested_" + PageMetricType.MAX_SCROLL_DEPTH.getAggregationName());

		Aggregations nestedAggregations = internalNested.getAggregations();

		return DogUtil.getSingleValue(
			nestedAggregations.get(
				PageMetricType.MAX_SCROLL_DEPTH.getAggregationName()));
	}

	private Double _getUsersAggregationValue(Aggregations aggregations) {
		return DogUtil.getSingleValue(aggregations.get("visitors"));
	}

}