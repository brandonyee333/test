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

import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.Composition;
import com.liferay.osb.asah.backend.model.CompositionResultBag;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SiteInterestCompositionDog {

	public CompositionResultBag getCompositionResultBag(
		String channelId, String dataSourceId, int size, int start,
		TimeRange timeRange) {

		List<Composition> compositions = new ArrayList<>();

		Map<String, Set<String>> keywords = _getKeywords(
			channelId, dataSourceId, timeRange);

		for (Map.Entry<String, Set<String>> keyword : keywords.entrySet()) {
			Set<String> sessionIds = keyword.getValue();

			compositions.add(
				new Composition(sessionIds.size(), keyword.getKey()));
		}

		Comparator<Composition> comparator = Comparator.comparing(
			Composition::getCount, Comparator.reverseOrder());

		comparator.thenComparing(Composition::getName);

		compositions.sort(comparator);

		int end = start + size;

		if (compositions.size() < end) {
			end = compositions.size();
		}

		return new CompositionResultBag(
			compositions.subList(start, end), compositions.size(),
			_getTotalUsers(channelId, dataSourceId, timeRange));
	}

	private BoolQueryBuilder _getActivitiesBoolQueryBuilder(
		String channelId, String dataSourceId, TimeRange timeRange) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			_searchQueryHelper.createRangeQueryBuilder("endTime", timeRange)
		).filter(
			QueryBuilders.termQuery("applicationId", "Page")
		).filter(
			QueryBuilders.termQuery("eventId", "pageViewed")
		);

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelId", channelId);
		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "dataSourceId", dataSourceId);

		return boolQueryBuilder;
	}

	private Map<String, Set<String>> _getKeywords(
		String channelId, String dataSourceId, TimeRange timeRange) {

		Map<String, Set<String>> keywords = new HashMap<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder assetIdTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("assetIds");

		assetIdTermsValuesSourceBuilder.field("id");

		TermsValuesSourceBuilder keywordTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("keywords/keyword");

		keywordTermsValuesSourceBuilder.field("keywords.keyword");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				new ArrayList<CompositeValuesSourceBuilder<?>>() {
					{
						add(assetIdTermsValuesSourceBuilder);
						add(keywordTermsValuesSourceBuilder);
					}
				}
			).size(
				10000
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("assetType", "Page")
		).filter(
			QueryBuilders.existsQuery("keywords.keyword")
		);

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelIds", channelId);
		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "dataSourceId", dataSourceId);

		searchSourceBuilder.query(boolQueryBuilder);

		searchSourceBuilder.size(0);

		Map<String, Set<String>> assets = new HashMap<>();

		while (true) {
			SearchResponse searchResponse =
				_faroInfoElasticsearchInvoker.search(
					"assets", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			if (DogUtil.isEmpty(aggregations)) {
				return keywords;
			}

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				String keyword = (String)keys.get("keywords/keyword");

				Set<String> assetIds = assets.getOrDefault(
					keyword, new HashSet<>());

				assetIds.add((String)keys.get("assetIds"));

				assets.put(keyword, assetIds);
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		Map<String, Set<String>> users = _getUsers(
			channelId, dataSourceId, timeRange);

		for (Map.Entry<String, Set<String>> entry : assets.entrySet()) {
			String keyword = entry.getKey();

			Set<String> userIds = new HashSet<>();

			for (String assetId : entry.getValue()) {
				if (users.containsKey(assetId)) {
					userIds.addAll(users.get(assetId));
				}
			}

			if (userIds.isEmpty()) {
				if (_log.isInfoEnabled()) {
					_log.info("No users found for keyword " + keyword);
				}

				continue;
			}

			keywords.put(keyword, userIds);
		}

		return keywords;
	}

	private long _getTotalUsers(
		String channelId, String dataSourceId, TimeRange timeRange) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"activities",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.cardinality(
						"totalUsers"
					).script(
						new Script(
							Script.DEFAULT_SCRIPT_TYPE,
							Script.DEFAULT_SCRIPT_LANG,
							"doc['day'] + ' ' + doc['userId'] + ' ' + " +
								"doc['object.id']",
							Collections.emptyMap())
					));
				searchSourceBuilder.query(
					_getActivitiesBoolQueryBuilder(
						channelId, dataSourceId, timeRange));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		InternalCardinality internalCardinality = aggregations.get(
			"totalUsers");

		return internalCardinality.getValue();
	}

	private Map<String, Set<String>> _getUsers(
		String channelId, String dataSourceId, TimeRange timeRange) {

		Map<String, Set<String>> users = new HashMap<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder assetIdsTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("assetIds");

		assetIdsTermsValuesSourceBuilder.field("object.id");

		TermsValuesSourceBuilder dayTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("days");

		dayTermsValuesSourceBuilder.field("day");

		TermsValuesSourceBuilder userIdTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("userIds");

		userIdTermsValuesSourceBuilder.field("userId");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				new ArrayList<CompositeValuesSourceBuilder<?>>() {
					{
						add(assetIdsTermsValuesSourceBuilder);
						add(dayTermsValuesSourceBuilder);
						add(userIdTermsValuesSourceBuilder);
					}
				}
			).size(
				10000
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		searchSourceBuilder.query(
			_getActivitiesBoolQueryBuilder(channelId, dataSourceId, timeRange));
		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse =
				_faroInfoElasticsearchInvoker.search(
					"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			if (DogUtil.isEmpty(aggregations)) {
				return users;
			}

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				String assetId = (String)keys.get("assetIds");

				Set<String> userCount = users.getOrDefault(
					assetId, new HashSet<>());

				Long days = (Long)keys.get("days");
				String userIds = (String)keys.get("userIds");

				userCount.add(userIds + days);

				users.put(assetId, userCount);
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return users;
	}

	private static final Log _log = LogFactory.getLog(
		SiteInterestCompositionDog.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}