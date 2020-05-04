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

import com.liferay.osb.asah.backend.dog.configuration.IndividualMetricDogConfiguration;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.IndividualMetric;
import com.liferay.osb.asah.backend.model.IndividualMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Set;
import java.util.function.BiConsumer;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class IndividualMetricDog {

	public IndividualMetric getIndividualMetric(
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics) {

		IndividualMetric individualMetric = new IndividualMetric();

		IndividualMetricDogConfiguration individualMetricDogConfiguration =
			new IndividualMetricDogConfiguration();

		Set<MetricResolver> metricResolvers =
			individualMetricDogConfiguration.getMetricResolvers(
				selectedMetrics);

		for (MetricResolver metricResolver : metricResolvers) {
			BiConsumer<IndividualMetric, Metric> metricSetterBiConsumer =
				metricResolver.getSetterBiConsumer();

			metricSetterBiConsumer.accept(
				individualMetric,
				_getIndividualCountMetric(metricResolver, searchQueryContext));
		}

		return individualMetric;
	}

	public double getIndividualsCount(
		LocalDate localDate, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		LocalDateTime localDateTime = localDate.atTime(LocalTime.MAX);

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.rangeQuery(
				metricType.getFieldName()
			).lte(
				localDateTime.toString()
			));

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelIds", searchQueryContext.getChannelId());

		if (metricType == IndividualMetricType.ANONYMOUS_INDIVIDUALS) {
			boolQueryBuilder.mustNot(
				QueryBuilders.existsQuery("demographics.email"));
		}
		else if (metricType == IndividualMetricType.KNOWN_INDIVIDUALS) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("demographics.email"));
		}

		if (searchQueryContext.isActive() != null) {
			if (searchQueryContext.isActive()) {
				boolQueryBuilder.filter(
					QueryBuilders.rangeQuery(
						"engagementScore"
					).gt(
						0
					));
			}
			else {
				boolQueryBuilder.filter(
					BoolQueryBuilderUtil.should(
						QueryBuilders.termQuery("engagementScore", 0)
					).should(
						BoolQueryBuilderUtil.mustNot(
							QueryBuilders.existsQuery("engagementScore"))
					));
			}
		}

		return _elasticsearchInvoker.count("individuals", boolQueryBuilder);
	}

	private Metric _getIndividualCountMetric(
		MetricResolver metricResolver, SearchQueryContext searchQueryContext) {

		Metric metric = new Metric(metricResolver.getMetricType());

		LocalDate localDate = LocalDate.now(Clock.systemUTC());

		LocalDate previousLocalDate = _getPreviousLocalDate(
			localDate, searchQueryContext.getTimeRange());

		metric.setPreviousValue(
			getIndividualsCount(
				previousLocalDate, metricResolver.getMetricType(),
				searchQueryContext));
		metric.setPreviousValueKey(previousLocalDate.toString());

		metric.setValue(
			getIndividualsCount(
				localDate, metricResolver.getMetricType(), searchQueryContext));
		metric.setValueKey(localDate.toString());

		return metric;
	}

	private LocalDate _getPreviousLocalDate(
		LocalDate localDate, TimeRange timeRange) {

		LocalDate previousLocalDate = LocalDate.from(localDate);

		return previousLocalDate.minusDays(timeRange.getRangeKey());
	}

	@PostConstruct
	private void _init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}