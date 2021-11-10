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

package com.liferay.osb.asah.backend.dog.form;

import com.liferay.osb.asah.backend.dog.DataDog;
import com.liferay.osb.asah.backend.dog.DogUtil;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.AssetId;
import com.liferay.osb.asah.backend.model.FormFieldMetric;
import com.liferay.osb.asah.backend.model.FormFieldMetricType;
import com.liferay.osb.asah.backend.model.FormPageMetric;
import com.liferay.osb.asah.backend.model.FormPageMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.petra.string.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class FormPageDog {

	public List<FormPageMetric> getFormPageMetrics(
		SearchQueryContext searchQueryContext) {

		Aggregations aggregations = _dataDog.queryAggregations(
			"forms", _buildSearchSourceBuilder(searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Nested nested = aggregations.get("formPages");

		if (nested == null) {
			return Collections.emptyList();
		}

		List<FormPageMetric> formPageMetrics = new ArrayList<>();

		Aggregations nestedAggregations = nested.getAggregations();

		LongTerms longTerms = nestedAggregations.get("pageIndex");

		for (Terms.Bucket termsBucket : longTerms.getBuckets()) {
			FormPageMetric formPageMetric = new FormPageMetric();

			Aggregations bucketAggregations = termsBucket.getAggregations();

			formPageMetric.setFormFieldMetrics(
				_getFormFieldMetrics(bucketAggregations.get("formFields")));

			formPageMetric.setPageAbandonmentsMetric(
				_getMetric(termsBucket, FormPageMetricType.PAGE_ABANDONMENTS));
			formPageMetric.setPageIndex(termsBucket.getKeyAsString());
			formPageMetric.setPageName(
				_getFormPageName(bucketAggregations.get("name")));
			formPageMetric.setPageViewsMetric(
				_getMetric(termsBucket, FormPageMetricType.PAGE_VIEWS));

			formPageMetrics.add(formPageMetric);
		}

		return formPageMetrics;
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		SearchQueryContext searchQueryContext) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(
			_createFormPageNestedAggregationBuilder());

		QueryBuilder queries = _searchQueryHelper.createFilterBoolQueryBuilder(
			Optional.of(AssetId.of("assetId", searchQueryContext.getAssetId())),
			searchQueryContext, _timeZoneDog.getTimeZoneId());

		searchSourceBuilder.query(queries);

		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private AggregationBuilder _createFormFieldNestedAggregationBuilder() {
		NestedAggregationBuilder nestedAggregationBuilder =
			AggregationBuilders.nested("formFields", "formPages.formFields");

		nestedAggregationBuilder.subAggregation(
			_createFormFieldTermsAggregationBuilder());

		return nestedAggregationBuilder;
	}

	private AggregationBuilder _createFormFieldSumAggregationBuilder(
		FormFieldMetricType formFieldMetricType) {

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			formFieldMetricType.getAggregationName());

		sumAggregationBuilder.field(
			"formPages.formFields." + formFieldMetricType.getFieldName());

		return sumAggregationBuilder;
	}

	private AggregationBuilder _createFormFieldTermsAggregationBuilder() {
		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("name");

		termsAggregationBuilder.field("formPages.formFields.name");
		termsAggregationBuilder.size(1024);
		termsAggregationBuilder.subAggregation(
			_createFormFieldSumAggregationBuilder(
				FormFieldMetricType.FIELD_ABANDONMENTS));
		termsAggregationBuilder.subAggregation(
			_createFormFieldSumAggregationBuilder(
				FormFieldMetricType.FIELD_INTERACTION_DURATION));
		termsAggregationBuilder.subAggregation(
			_createFormFieldSumAggregationBuilder(
				FormFieldMetricType.FIELD_INTERACTIONS));
		termsAggregationBuilder.subAggregation(
			_createFormFieldSumAggregationBuilder(
				FormFieldMetricType.FIELD_REFILLED));

		return termsAggregationBuilder;
	}

	private AggregationBuilder _createFormPageNameAggregationBuilder() {
		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("name");

		termsAggregationBuilder.field("formPages.name");
		termsAggregationBuilder.order(
			BucketOrder.aggregation("viewDates", false));
		termsAggregationBuilder.size(1);

		MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max(
			"viewDates");

		maxAggregationBuilder.field("formPages.viewDates");

		termsAggregationBuilder.subAggregation(maxAggregationBuilder);

		return termsAggregationBuilder;
	}

	private AggregationBuilder _createFormPageNestedAggregationBuilder() {
		NestedAggregationBuilder nestedAggregationBuilder =
			AggregationBuilders.nested("formPages", "formPages");

		nestedAggregationBuilder.subAggregation(
			_createFormPageTermsAggregationBuilder());

		return nestedAggregationBuilder;
	}

	private AggregationBuilder _createFormPageSumAggregationBuilder(
		FormPageMetricType formPageMetricType) {

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			formPageMetricType.getAggregationName());

		sumAggregationBuilder.field(
			"formPages." + formPageMetricType.getFieldName());

		return sumAggregationBuilder;
	}

	private AggregationBuilder _createFormPageTermsAggregationBuilder() {
		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("pageIndex");

		termsAggregationBuilder.field("formPages.pageIndex");
		termsAggregationBuilder.size(1024);
		termsAggregationBuilder.subAggregation(
			_createFormFieldNestedAggregationBuilder());
		termsAggregationBuilder.subAggregation(
			_createFormPageNameAggregationBuilder());
		termsAggregationBuilder.subAggregation(
			_createFormPageSumAggregationBuilder(
				FormPageMetricType.PAGE_ABANDONMENTS));
		termsAggregationBuilder.subAggregation(
			_createFormPageSumAggregationBuilder(
				FormPageMetricType.PAGE_VIEWS));

		return termsAggregationBuilder;
	}

	private List<FormFieldMetric> _getFormFieldMetrics(Nested nested) {
		List<FormFieldMetric> formFieldMetrics = new ArrayList<>();

		Aggregations aggregations = nested.getAggregations();

		Terms terms = aggregations.get("name");

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			FormFieldMetric formFieldMetric = new FormFieldMetric();

			formFieldMetric.setFieldAbandonmentsMetric(
				_getMetric(
					termsBucket, FormFieldMetricType.FIELD_ABANDONMENTS));
			formFieldMetric.setFieldInteractionDurationMetric(
				_getMetric(
					termsBucket,
					FormFieldMetricType.FIELD_INTERACTION_DURATION));
			formFieldMetric.setFieldInteractionsMetric(
				_getMetric(
					termsBucket, FormFieldMetricType.FIELD_INTERACTIONS));
			formFieldMetric.setFieldName(termsBucket.getKeyAsString());
			formFieldMetric.setFieldRefilledMetric(
				_getMetric(termsBucket, FormFieldMetricType.FIELD_REFILLED));

			formFieldMetrics.add(formFieldMetric);
		}

		return formFieldMetrics;
	}

	private String _getFormPageName(StringTerms terms) {
		List<StringTerms.Bucket> buckets = terms.getBuckets();

		if (buckets.isEmpty()) {
			return StringPool.BLANK;
		}

		Terms.Bucket termsBucket = buckets.get(0);

		return termsBucket.getKeyAsString();
	}

	private Metric _getMetric(Terms.Bucket bucket, MetricType metricType) {
		Metric metric = new Metric(metricType);

		metric.setValue(_getSumValue(bucket, metricType));

		return metric;
	}

	private Double _getSumValue(Terms.Bucket bucket, MetricType metricType) {
		Aggregations aggregations = bucket.getAggregations();

		Sum sum = aggregations.get(metricType.getAggregationName());

		return sum.getValue();
	}

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}