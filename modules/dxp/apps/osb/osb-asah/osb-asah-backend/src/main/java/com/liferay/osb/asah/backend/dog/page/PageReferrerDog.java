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

package com.liferay.osb.asah.backend.dog.page;

import com.liferay.osb.asah.backend.dog.DataDog;
import com.liferay.osb.asah.backend.dog.DogUtil;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.dog.title.TitleDog;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.PageReferrerMetric;
import com.liferay.osb.asah.backend.model.PageReferrerMetricType;
import com.liferay.petra.string.StringPool;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class PageReferrerDog {

	public List<PageReferrerMetric> getPageReferrerMetrics(
		SearchQueryContext searchQueryContext) {

		Aggregations aggregations = _dataDog.queryAggregations(
			"page-referrers", _buildSearchSourceBuilder(searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Terms terms = aggregations.get("referrer");

		if (terms == null) {
			return Collections.emptyList();
		}

		List<PageReferrerMetric> pageReferrerMetrics = new ArrayList<>();

		BigDecimal partial = BigDecimal.ZERO;

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			Metric accessMetric = _getAccessMetric(termsBucket);

			PageReferrerMetric pageReferrerMetric = _createPageReferrerMetric(
				accessMetric, termsBucket);

			partial = partial.add(BigDecimal.valueOf(accessMetric.getValue()));

			pageReferrerMetrics.add(pageReferrerMetric);
		}

		pageReferrerMetrics.add(
			_getOthersPageReferrerMetrics(partial, searchQueryContext));

		return pageReferrerMetrics;
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		SearchQueryContext searchQueryContext) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(_createReferrerAggregationBuilder());
		searchSourceBuilder.query(_createQueryBuilder(searchQueryContext));
		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private AggregationBuilder _createAccessesAggregationBuilder() {
		PageReferrerMetricType pageReferrerMetric =
			PageReferrerMetricType.ACCESS;

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			pageReferrerMetric.getAggregationName());

		sumAggregationBuilder.field(pageReferrerMetric.getFieldName());

		return sumAggregationBuilder;
	}

	private PageReferrerMetric _createPageReferrerMetric(
		Metric accessMetric, Terms.Bucket termsBucket) {

		PageReferrerMetric pageReferrerMetric = new PageReferrerMetric();

		pageReferrerMetric.setAccessMetric(accessMetric);

		String key = termsBucket.getKeyAsString();

		boolean external = _isPageExternal(key);

		String title = _getPageTitle(external, key);

		pageReferrerMetric.setAssetTitle(title);

		pageReferrerMetric.setExternal(external);
		pageReferrerMetric.setReferrer(key);

		return pageReferrerMetric;
	}

	private QueryBuilder _createQueryBuilder(
		SearchQueryContext searchQueryContext) {

		BoolQueryBuilder boolQueryBuilder =
			_searchQueryHelper.createFilterBoolQueryBuilder(
				Optional.empty(), searchQueryContext);

		boolQueryBuilder.mustNot(
			QueryBuilders.termQuery("referrer", StringPool.BLANK));

		return boolQueryBuilder;
	}

	private AggregationBuilder _createReferrerAggregationBuilder() {
		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("referrer");

		termsAggregationBuilder.field("referrer");
		termsAggregationBuilder.order(
			BucketOrder.aggregation(
				PageReferrerMetricType.ACCESS.getAggregationName(), false));
		termsAggregationBuilder.size(3);
		termsAggregationBuilder.subAggregation(
			_createAccessesAggregationBuilder());

		return termsAggregationBuilder;
	}

	private Metric _getAccessMetric(Terms.Bucket bucket) {
		Metric metric = new Metric(PageReferrerMetricType.ACCESS);

		metric.setValue(_getAccessValue(bucket));

		return metric;
	}

	private Double _getAccessValue(Terms.Bucket bucket) {
		PageReferrerMetricType pageReferrerMetric =
			PageReferrerMetricType.ACCESS;

		Aggregations aggregations = bucket.getAggregations();

		Sum sum = aggregations.get(pageReferrerMetric.getAggregationName());

		return sum.getValue();
	}

	private PageReferrerMetric _getOthersPageReferrerMetrics(
		BigDecimal partial, SearchQueryContext searchQueryContext) {

		PageReferrerMetric pageReferrerMetric = new PageReferrerMetric();

		Metric metric = new Metric(PageReferrerMetricType.ACCESS);

		BigDecimal total = BigDecimal.valueOf(
			_pageDog.getIndirectAccessMetricValue(searchQueryContext));

		BigDecimal others = total.subtract(partial);

		metric.setValue(others.doubleValue());

		pageReferrerMetric.setAccessMetric(metric);
		pageReferrerMetric.setExternal(true);
		pageReferrerMetric.setReferrer("others");

		return pageReferrerMetric;
	}

	private String _getPageTitle(boolean external, String url) {
		if (external) {
			return null;
		}

		Map<String, String> titleMap = _titleDog.getTitle(
			AssetType.PAGE, Collections.singleton(url));

		return titleMap.get(url);
	}

	private boolean _isPageExternal(String url) {
		long views = _pageDog.getViewsMetricValue(
			Optional.empty(), Optional.empty(), Optional.of(url));

		if (views == 0) {
			return true;
		}

		return false;
	}

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private PageDog _pageDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TitleDog _titleDog;

}