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
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.petra.string.StringBundler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.missing.MissingAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseDogConfiguration implements DogConfiguration {

	@Override
	public QueryBuilder getQueryBuilder(SearchQueryContext searchQueryContext) {
		return null;
	}

	protected Consumer<TermsAggregationBuilder>
		createBucketOrderTermsAggregationBuilderConsumer(
			MetricType metricType) {

		BucketOrder bucketOrder = BucketOrder.aggregation(
			metricType.getAggregationName(), false);

		return (TermsAggregationBuilder termsAggregationBuilder) ->
			termsAggregationBuilder.order(bucketOrder);
	}

	protected AggregationBuilder createCardinalityAggregationBuilder(
		String cardinalityName, String fieldName) {

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality(cardinalityName);

		cardinalityAggregationBuilder.field(fieldName);
		cardinalityAggregationBuilder.precisionThreshold(1000);

		return cardinalityAggregationBuilder;
	}

	protected PipelineAggregationBuilder
		createDivisionPipelineAggregationBuilder(
			MetricType metricType1, MetricType metricType2) {

		Map<String, String> paths = Stream.of(
			metricType1.getAggregationName() + "_field",
			metricType2.getAggregationName()
		).collect(
			Collectors.toMap(Function.identity(), Function.identity())
		);

		return PipelineAggregatorBuilders.bucketScript(
			metricType1.getAggregationName(), paths,
			_createDivisionScript(
				metricType1.getAggregationName(),
				metricType2.getAggregationName()));
	}

	protected PipelineAggregationBuilder
		createDivisionPipelineAggregationBuilder(
			String metricFieldName1, String metricFieldName2) {

		Map<String, String> paths = Stream.of(
			metricFieldName1 + "_field", metricFieldName2
		).collect(
			Collectors.toMap(Function.identity(), Function.identity())
		);

		return PipelineAggregatorBuilders.bucketScript(
			metricFieldName1, paths,
			_createDivisionScript(metricFieldName1, metricFieldName2));
	}

	protected MissingAggregationBuilder
		createMissingCardinalityAggregationBuilder(String fieldName) {

		MissingAggregationBuilder missingAggregationBuilder =
			AggregationBuilders.missing("missing_count");

		missingAggregationBuilder.field(fieldName);

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality("sessions_count");

		cardinalityAggregationBuilder.field("sessionId");
		cardinalityAggregationBuilder.precisionThreshold(1000);

		missingAggregationBuilder.subAggregation(cardinalityAggregationBuilder);

		return missingAggregationBuilder;
	}

	protected void createRatingsWeightedAvgAggregations(
		MetricResolver.Builder builder, String valueFieldName,
		String weightFieldName) {

		String valueSumFieldName = valueFieldName + "Sum";

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			valueSumFieldName);

		sumAggregationBuilder.script(
			_createMultiplicationScript(valueFieldName, weightFieldName));

		builder.aggregate(sumAggregationBuilder);

		String weightSumFieldName = weightFieldName + "Sum";

		sumAggregationBuilder = AggregationBuilders.sum(weightSumFieldName);

		sumAggregationBuilder.field(weightFieldName);

		builder.aggregate(sumAggregationBuilder);

		builder.aggregate(
			PipelineAggregatorBuilders.bucketScript(
				valueFieldName,
				new HashMap() {
					{
						put("valueSumFieldName", valueSumFieldName);
						put("weightSumFieldName", weightSumFieldName);
					}
				},
				_weightedAverageAggregationScript));
	}

	protected AggregationBuilder createTotalIndividualsAggregationBuilder(
		MetricType metricType) {

		FilterAggregationBuilder filterAggregationBuilder =
			AggregationBuilders.filter(
				"total",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						metricType.getAggregationName()
					).gt(
						0
					)));

		filterAggregationBuilder.subAggregation(
			createCardinalityAggregationBuilder("users_count", "individualId"));
		filterAggregationBuilder.subAggregation(
			createMissingCardinalityAggregationBuilder("individualId"));

		return filterAggregationBuilder;
	}

	protected Double getAvgAggregationValue(
		Aggregations aggregations, MetricType metricType) {

		double value = DogUtil.getSingleValue(
			aggregations.get(metricType.getAggregationName()));

		return Math.max(0, value);
	}

	protected Double getBucketScriptAggregationValue(
		Aggregations aggregations, MetricType metricType) {

		return DogUtil.getSingleValue(
			aggregations.get(metricType.getAggregationName()));
	}

	private Script _createDivisionScript(
		String metricFieldName1, String metricFieldName2) {

		StringBundler sb = new StringBundler(7);

		sb.append("params.");
		sb.append(metricFieldName2);
		sb.append(" > 0 ? params.");
		sb.append(metricFieldName1);
		sb.append("_field / params.");
		sb.append(metricFieldName2);
		sb.append(" : 0");

		return new Script(sb.toString());
	}

	private Script _createMultiplicationScript(
		String metricFieldName1, String metricFieldName2) {

		StringBundler sb = new StringBundler(9);

		sb.append("(doc['");
		sb.append(metricFieldName1);
		sb.append("'].length > 0 ? doc['");
		sb.append(metricFieldName1);
		sb.append("'].value : 0) * (doc['");
		sb.append(metricFieldName2);
		sb.append("'].length > 0 ? doc['");
		sb.append(metricFieldName2);
		sb.append("'].value : 0)");

		return new Script(sb.toString());
	}

	@PostConstruct
	private void _init() {
		_weightedAverageAggregationScript = ScriptUtil.createScript(
			getClass(), "weighted_average_aggregation_script.painless");
	}

	private Script _weightedAverageAggregationScript;

}