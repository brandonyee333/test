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
import com.liferay.osb.asah.backend.dog.resolver.AssetResolver;
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.SiteMetric;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;

import org.springframework.context.annotation.Configuration;

/**
 * @author Rachael Koestartyo
 */
@Configuration
public class SiteDogConfiguration extends BaseDogConfiguration {

	@Override
	public AssetResolver<SiteMetric> getAssetResolver() {
		AssetResolver.Builder<SiteMetric> builder = AssetResolver.builder(
			"channelId");

		builder.searchableFieldNames("channelId");
		builder.supplier(SiteMetric::new);

		return builder.build();
	}

	@Override
	public AssetType getAssetType() {
		return AssetType.SITE;
	}

	@Override
	public String getCollection() {
		return "pages";
	}

	@Override
	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics) {
		Set<MetricResolver> metricResolvers = new HashSet<>();

		if (selectedMetrics.contains(
				SiteMetricType.ANONYMOUS_VISITORS.getName())) {

			metricResolvers.add(_buildAnonymousVisitorsMetricResolver());
		}

		if (selectedMetrics.contains(SiteMetricType.BOUNCE_RATE.getName())) {
			metricResolvers.add(_buildBounceRateMetricResolver());
		}

		if (selectedMetrics.contains(SiteMetricType.ENGAGEMENT.getName())) {
			metricResolvers.add(_buildEngagementMetricResolver());
		}

		if (selectedMetrics.contains(SiteMetricType.KNOWN_VISITORS.getName())) {
			metricResolvers.add(_buildKnownVisitorsMetricResolver());
		}

		if (selectedMetrics.contains(
				SiteMetricType.SESSION_DURATION.getName())) {

			metricResolvers.add(_buildSessionDurationMetricResolver());
		}

		if (selectedMetrics.contains(SiteMetricType.SESSIONS.getName())) {
			metricResolvers.add(_buildSessionsMetricResolver());
		}

		if (selectedMetrics.contains(
				SiteMetricType.SESSIONS_PER_VISITOR.getName())) {

			metricResolvers.add(_buildSessionsPerVisitorMetricResolver());
		}

		if (selectedMetrics.contains(SiteMetricType.VISITORS.getName())) {
			metricResolvers.add(_buildVisitorsMetricResolver());
		}

		if (selectedMetrics.contains("defaultMetric") ||
			metricResolvers.isEmpty()) {

			metricResolvers.add(_buildVisitorsMetricResolver());
		}

		return metricResolvers;
	}

	@Override
	public Function<String, MetricType> getMetricTypeResolverFunction() {
		return SiteMetricType::of;
	}

	private MetricResolver _buildAnonymousVisitorsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			SiteMetricType.ANONYMOUS_VISITORS);

		builder.aggregate(
			createTotalIndividualsAggregationBuilder(SiteMetricType.VIEWS));
		builder.aggregate(_createKnownIndividualsAggregationBuilder());
		builder.aggregate(
			_createVisitorsPipelineAggregationBuilder(
				SiteMetricType.ANONYMOUS_VISITORS));
		builder.mapperFunction(
			aggregations -> _getAggregationValue(
				aggregations, SiteMetricType.ANONYMOUS_VISITORS));
		builder.setterBiConsumer(SiteMetric::setAnonymousVisitorsMetric);

		return builder.build();
	}

	private MetricResolver _buildBounceRateMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			SiteMetricType.BOUNCE_RATE);

		_createBounceRateAggregations(builder);
		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, SiteMetricType.BOUNCE_RATE));
		builder.setterBiConsumer(SiteMetric::setBounceRateMetric);

		return builder.build();
	}

	private MetricResolver _buildEngagementMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			SiteMetricType.ENGAGEMENT);

		builder.aggregate(_createEngagementAggregationBuilder());
		builder.mapperFunction(this::_getEngagementAggregationValue);
		builder.setterBiConsumer(SiteMetric::setEngagementMetric);

		return builder.build();
	}

	private MetricResolver _buildKnownVisitorsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			SiteMetricType.KNOWN_VISITORS);

		builder.aggregate(_createKnownIndividualsAggregationBuilder());
		builder.aggregate(
			_createVisitorsPipelineAggregationBuilder(
				SiteMetricType.KNOWN_VISITORS));
		builder.mapperFunction(
			aggregations -> _getAggregationValue(
				aggregations, SiteMetricType.KNOWN_VISITORS));
		builder.setterBiConsumer(SiteMetric::setKnownVisitorsMetric);

		return builder.build();
	}

	private MetricResolver _buildSessionDurationMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			SiteMetricType.SESSION_DURATION);

		builder.aggregate(_createSessionDurationAggregationBuilder());
		builder.mapperFunction(this::_getSessionDurationAggregationValue);
		builder.setterBiConsumer(SiteMetric::setSessionDurationMetric);

		return builder.build();
	}

	private MetricResolver _buildSessionsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			SiteMetricType.SESSIONS);

		builder.aggregate(_createSessionsAggregationBuilder());
		builder.mapperFunction(
			aggregations -> _getAggregationValue(
				aggregations, SiteMetricType.SESSIONS));
		builder.setterBiConsumer(SiteMetric::setSessionsMetric);

		return builder.build();
	}

	private MetricResolver _buildSessionsPerVisitorMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			SiteMetricType.SESSIONS_PER_VISITOR);

		builder.aggregate(
			createTotalIndividualsAggregationBuilder(SiteMetricType.VIEWS));
		builder.aggregate(_createSessionsAggregationBuilder());
		builder.aggregate(
			_createSessionsPerVisitorPipelineAggregationBuilder());
		builder.mapperFunction(
			aggregations -> _getAggregationValue(
				aggregations, SiteMetricType.SESSIONS_PER_VISITOR));
		builder.setterBiConsumer(SiteMetric::setSessionsPerVisitorMetric);

		return builder.build();
	}

	private MetricResolver _buildVisitorsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			SiteMetricType.VISITORS);

		builder.aggregate(
			createTotalIndividualsAggregationBuilder(SiteMetricType.VIEWS));
		builder.aggregate(
			_createVisitorsPipelineAggregationBuilder(SiteMetricType.VISITORS));
		builder.mapperFunction(
			aggregations -> _getAggregationValue(
				aggregations, SiteMetricType.VISITORS));
		builder.setterBiConsumer(SiteMetric::setVisitorsMetric);

		return builder.build();
	}

	private AvgAggregationBuilder _createAvgAggregationBuilder(
		MetricType metricType) {

		AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg(
			metricType.getAggregationName());

		avgAggregationBuilder.field(metricType.getFieldName());

		return avgAggregationBuilder;
	}

	private void _createBounceRateAggregations(MetricResolver.Builder builder) {
		builder.aggregate(
			_createSumAggregationBuilder(
				SiteMetricType.BOUNCE_RATE,
				SiteMetricType.BOUNCE_RATE.getAggregationName() + "_field"));
		builder.aggregate(_createSumAggregationBuilder(SiteMetricType.VIEWS));
		builder.aggregate(
			createDivisionPipelineAggregationBuilder(
				SiteMetricType.BOUNCE_RATE, SiteMetricType.VIEWS));
	}

	private AggregationBuilder _createEngagementAggregationBuilder() {
		return _createAvgAggregationBuilder(SiteMetricType.ENGAGEMENT);
	}

	private AggregationBuilder _createKnownIndividualsAggregationBuilder() {
		FilterAggregationBuilder filterAggregationBuilder =
			AggregationBuilders.filter(
				"known",
				BoolQueryBuilderUtil.filter(
					BoolQueryBuilderUtil.should(
						QueryBuilders.termQuery("knownIndividual", true)
					).should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.existsQuery("individualId")
						).mustNot(
							QueryBuilders.existsQuery("knownIndividual")
						)
					)
				).filter(
					QueryBuilders.rangeQuery(
						SiteMetricType.VIEWS.getFieldName()
					).gt(
						0
					)
				));

		filterAggregationBuilder.subAggregation(
			createCardinalityAggregationBuilder("users_count", "individualId"));

		return filterAggregationBuilder;
	}

	private AggregationBuilder _createSessionDurationAggregationBuilder() {
		return _createAvgAggregationBuilder(SiteMetricType.SESSION_DURATION);
	}

	private AggregationBuilder _createSessionsAggregationBuilder() {
		return createCardinalityAggregationBuilder(
			SiteMetricType.SESSIONS.getAggregationName(),
			SiteMetricType.SESSIONS.getFieldName());
	}

	private PipelineAggregationBuilder
		_createSessionsPerVisitorPipelineAggregationBuilder() {

		return PipelineAggregatorBuilders.bucketScript(
			SiteMetricType.SESSIONS_PER_VISITOR.getAggregationName(),
			new HashMap<String, String>() {
				{
					put(
						"missing_sessions_count",
						"total > missing_count > sessions_count");
					put(
						"sessions_count",
						SiteMetricType.SESSIONS.getAggregationName());
					put("total_users_count", "total > users_count");
				}
			},
			new Script(
				"params.sessions_count / (params.missing_sessions_count + " +
					"params.total_users_count)"));
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
		_createVisitorsPipelineAggregationBuilder(
			SiteMetricType siteMetricType) {

		Map<String, String> bucketPaths = null;
		Script script = null;

		if (siteMetricType == SiteMetricType.ANONYMOUS_VISITORS) {
			bucketPaths = new HashMap<String, String>() {
				{
					put("known_users_count", "known > users_count");
					put(
						"sessions_count",
						"total > missing_count > sessions_count");
					put("total_users_count", "total > users_count");
				}
			};

			script = new Script(
				"params.sessions_count + params.total_users_count - " +
					"params.known_users_count");
		}
		else if (siteMetricType == SiteMetricType.KNOWN_VISITORS) {
			bucketPaths = new HashMap<String, String>() {
				{
					put("known_users_count", "known > users_count");
				}
			};

			script = new Script("params.known_users_count");
		}
		else if (siteMetricType == SiteMetricType.VISITORS) {
			bucketPaths = new HashMap<String, String>() {
				{
					put(
						"sessions_count",
						"total > missing_count > sessions_count");
					put("total_users_count", "total > users_count");
				}
			};

			script = new Script(
				"params.sessions_count + params.total_users_count");
		}

		return PipelineAggregatorBuilders.bucketScript(
			siteMetricType.getAggregationName(), bucketPaths, script);
	}

	private Double _getAggregationValue(
		Aggregations aggregations, SiteMetricType siteMetricType) {

		return DogUtil.getSingleValue(
			aggregations.get(siteMetricType.getAggregationName()));
	}

	private Double _getEngagementAggregationValue(Aggregations aggregations) {
		return getAvgAggregationValue(aggregations, SiteMetricType.ENGAGEMENT);
	}

	private Double _getSessionDurationAggregationValue(
		Aggregations aggregations) {

		return getAvgAggregationValue(
			aggregations, SiteMetricType.SESSION_DURATION);
	}

}