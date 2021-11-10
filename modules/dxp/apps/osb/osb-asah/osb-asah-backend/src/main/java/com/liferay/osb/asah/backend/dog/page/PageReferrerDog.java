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
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.petra.string.StringPool;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class PageReferrerDog {

	public Map<String, Double> getAcquisitionChannels(
		SearchQueryContext searchQueryContext) {

		Aggregations aggregations = _dataDog.queryAggregations(
			"page-referrers",
			_buildAcquisitionChannelSearchSourceBuilder(searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyMap();
		}

		Terms terms = aggregations.get("acquisition");

		if (terms == null) {
			return Collections.emptyMap();
		}

		Map<String, Double> acquisitions = new HashMap<>();

		for (Terms.Bucket bucket : terms.getBuckets()) {
			acquisitions.put(bucket.getKeyAsString(), _getAccessValue(bucket));
		}

		return acquisitions;
	}

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

		for (Terms.Bucket bucket : terms.getBuckets()) {
			Metric accessMetric = _getAccessMetric(bucket);

			PageReferrerMetric pageReferrerMetric = _createPageReferrerMetric(
				accessMetric, bucket);

			partial = partial.add(BigDecimal.valueOf(accessMetric.getValue()));

			pageReferrerMetrics.add(pageReferrerMetric);
		}

		pageReferrerMetrics.add(
			_getOthersPageReferrerMetrics(partial, searchQueryContext));

		return pageReferrerMetrics;
	}

	public Map<String, Double> getPageReferrers(
		String fieldName, SearchQueryContext searchQueryContext, int size) {

		Aggregations aggregations = _dataDog.queryAggregations(
			"page-referrers",
			_buildSearchSourceBuilder(fieldName, searchQueryContext, size));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyMap();
		}

		Terms terms = aggregations.get("pageReferrers");

		if (terms == null) {
			return Collections.emptyMap();
		}

		Map<String, Double> pageReferrers = new LinkedHashMap<>();

		for (Terms.Bucket bucket : terms.getBuckets()) {
			pageReferrers.put(bucket.getKeyAsString(), _getAccessValue(bucket));
		}

		return pageReferrers;
	}

	public Map<String, Double> getSocialPageReferrers(
		SearchQueryContext searchQueryContext) {

		Map<String, Double> socialReferrers = new HashMap<>();

		for (Map.Entry<String, List<String>> entry :
				_socialHostNames.entrySet()) {

			Aggregations aggregations = _dataDog.queryAggregations(
				"page-referrers",
				_buildSocialSearchSourceBuilder(
					entry.getValue(), searchQueryContext));

			if (!DogUtil.isEmpty(aggregations)) {
				Sum sum = aggregations.get("access");

				if ((sum != null) && (sum.getValue() != 0)) {
					socialReferrers.put(entry.getKey(), sum.getValue());
				}
			}
		}

		Aggregations aggregations = _dataDog.queryAggregations(
			"page-referrers",
			_buildOtherSocialSearchSourceBuilder(searchQueryContext));

		if (!DogUtil.isEmpty(aggregations)) {
			Sum sum = aggregations.get("access");

			if ((sum != null) && (sum.getValue() != 0)) {
				socialReferrers.put("other", sum.getValue());
			}
		}

		Set<Map.Entry<String, Double>> set = socialReferrers.entrySet();

		Stream<Map.Entry<String, Double>> stream = set.stream();

		return stream.sorted(
			Map.Entry.comparingByValue(Comparator.reverseOrder())
		).collect(
			Collectors.toMap(
				Map.Entry::getKey, Map.Entry::getValue,
				(value1, value2) -> value1, LinkedHashMap::new)
		);
	}

	private SearchSourceBuilder _buildAcquisitionChannelSearchSourceBuilder(
		SearchQueryContext searchQueryContext) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(
			AggregationBuilders.terms(
				"acquisition"
			).field(
				"acquisitionChannel"
			).size(
				Integer.MAX_VALUE
			).subAggregation(
				_createAccessesAggregationBuilder()
			));
		searchSourceBuilder.query(
			_searchQueryHelper.createFilterBoolQueryBuilder(
				Optional.empty(), searchQueryContext,
				_timeZoneDog.getTimeZoneId()));
		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private SearchSourceBuilder _buildOtherSocialSearchSourceBuilder(
		SearchQueryContext searchQueryContext) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(
			_createAccessesAggregationBuilder()
		).size(
			Integer.MAX_VALUE
		);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		for (List<String> socialHostNames : _socialHostNames.values()) {
			for (String socialHostName : socialHostNames) {
				boolQueryBuilder.mustNot(
					QueryBuilders.termsQuery(
						"referrerHost", socialHostName,
						"www." + socialHostName));
			}
		}

		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				_searchQueryHelper.createFilterBoolQueryBuilder(
					Optional.empty(), searchQueryContext,
					_timeZoneDog.getTimeZoneId())
			).filter(
				QueryBuilders.termQuery("acquisitionChannel", "social")
			).filter(
				boolQueryBuilder
			));

		searchSourceBuilder.size(0);

		return searchSourceBuilder;
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

	private SearchSourceBuilder _buildSearchSourceBuilder(
		String fieldName, SearchQueryContext searchQueryContext, int size) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(
			AggregationBuilders.terms(
				"pageReferrers"
			).field(
				fieldName
			).size(
				size
			).subAggregation(
				_createAccessesAggregationBuilder()
			).order(
				BucketOrder.aggregation("access", false)
			));
		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				_searchQueryHelper.createFilterBoolQueryBuilder(
					Optional.empty(), searchQueryContext,
					_timeZoneDog.getTimeZoneId())
			).filter(
				QueryBuilders.existsQuery("acquisitionChannel")
			).mustNot(
				QueryBuilders.termsQuery(
					"acquisitionChannel", "organic", "social")
			).filter(
				QueryBuilders.existsQuery("referrerHost")
			).mustNot(
				QueryBuilders.matchQuery(fieldName, "")
			));
		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private SearchSourceBuilder _buildSocialSearchSourceBuilder(
		List<String> referrerHosts, SearchQueryContext searchQueryContext) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(
			_createAccessesAggregationBuilder()
		).size(
			Integer.MAX_VALUE
		);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		for (String referrerHost : referrerHosts) {
			boolQueryBuilder.should(
				QueryBuilders.termsQuery(
					"referrerHost", referrerHost, "www." + referrerHost));
		}

		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				_searchQueryHelper.createFilterBoolQueryBuilder(
					Optional.empty(), searchQueryContext,
					_timeZoneDog.getTimeZoneId())
			).filter(
				QueryBuilders.termQuery("acquisitionChannel", "social")
			).filter(
				boolQueryBuilder
			));

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
				Optional.empty(), searchQueryContext,
				_timeZoneDog.getTimeZoneId());

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

	private static final Map<String, List<String>> _socialHostNames =
		new HashMap<String, List<String>>() {
			{
				put("facebook", Collections.singletonList("facebook.com"));
				put("instagram", Collections.singletonList("instagram.com"));
				put("linkedin", Collections.singletonList("linkedin.com"));
				put("pinterest", Collections.singletonList("pinterest.com"));
				put("snapchat", Collections.singletonList("snapchat.com"));
				put("tiktok", Collections.singletonList("tiktok.com"));
				put("twitter", Arrays.asList("twitter.com", "t.co"));
				put("youtube", Collections.singletonList("youtube.com"));
			}
		};

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private PageDog _pageDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

	@Autowired
	private TitleDog _titleDog;

}