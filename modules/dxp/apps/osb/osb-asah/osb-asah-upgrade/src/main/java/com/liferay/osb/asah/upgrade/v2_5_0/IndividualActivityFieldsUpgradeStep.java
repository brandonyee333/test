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

package com.liferay.osb.asah.upgrade.v2_5_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class IndividualActivityFieldsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_elasticsearchIndexManager.updateMapping(
			_elasticsearchIndexManager.getIndexName(
				"individuals", WeDeployDataService.OSB_ASAH_FARO_INFO),
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"activitiesCounts",
					JSONUtil.put(
						"properties",
						JSONUtil.put(
							"activitiesCount", JSONUtil.put("type", "long")
						).put(
							"channelId", JSONUtil.put("type", "keyword")
						)
					).put(
						"type", "nested"
					)
				).put(
					"lastActivityDates",
					JSONUtil.put(
						"properties",
						JSONUtil.put(
							"channelId", JSONUtil.put("type", "keyword")
						).put(
							"lastActivityDate", JSONUtil.put("type", "date")
						)
					).put(
						"type", "nested"
					)
				)
			).toString(),
			"individuals");

		Map<Pair<String, String>, Pair<String, Long>> channelActivityInfo =
			_getChannelActivityInfo();

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONArrayIterator.of(
			"individuals", _faroInfoElasticsearchInvoker,
			individualJSONObject -> _updateActivityFields(
				channelActivityInfo, elasticsearchBulkRequestBuilder,
				individualJSONObject)
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termQuery("activitiesCount", 0))
		).setStopOnExceptions(
			true
		).iterate();
	}

	private Map<Pair<String, String>, Pair<String, Long>>
		_getChannelActivityInfo() {

		Map<Pair<String, String>, Pair<String, Long>> channelActivityInfo =
			new HashMap<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder channelTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("channels");

		channelTermsValuesSourceBuilder.field("channelId");

		TermsValuesSourceBuilder individualTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("individuals");

		individualTermsValuesSourceBuilder.field("ownerId");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				new ArrayList<CompositeValuesSourceBuilder<?>>() {
					{
						add(channelTermsValuesSourceBuilder);
						add(individualTermsValuesSourceBuilder);
					}
				}
			).size(
				10000
			).subAggregation(
				AggregationBuilders.max(
					"lastActivityDate"
				).field(
					"endTime"
				)
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		while (true) {
			SearchResponse searchResponse =
				_faroInfoElasticsearchInvoker.search(
					"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				Aggregations compositeAggregations = bucket.getAggregations();

				Max lastActivityDate = compositeAggregations.get(
					"lastActivityDate");

				channelActivityInfo.put(
					Pair.of(
						(String)keys.get("individuals"),
						(String)keys.get("channels")),
					Pair.of(
						lastActivityDate.getValueAsString(),
						bucket.getDocCount()));
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return channelActivityInfo;
	}

	private ElasticsearchBulkRequestBuilder _updateActivityFields(
		Map<Pair<String, String>, Pair<String, Long>> channelActivitiesInfo,
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		JSONObject individualJSONObject) {

		JSONArray channelIdsJSONArray = individualJSONObject.getJSONArray(
			"channelIds");

		Spliterator<Object> spliterator = channelIdsJSONArray.spliterator();

		Stream<Object> stream = StreamSupport.stream(spliterator, true);

		List<JSONObject> activitiesCount = new ArrayList<>();
		List<JSONObject> lastActivityDates = new ArrayList<>();

		stream.map(
			String::valueOf
		).forEach(
			channelId -> {
				Pair<String, Long> channelActivityInfo =
					channelActivitiesInfo.get(
						Pair.of(
							individualJSONObject.getString("id"), channelId));

				long activityCount = 0;
				String lastActivityDate = null;

				if (channelActivityInfo != null) {
					activityCount = channelActivityInfo.getValue();
					lastActivityDate = channelActivityInfo.getKey();
				}

				activitiesCount.add(
					JSONUtil.put(
						"activitiesCount", activityCount
					).put(
						"channelId", channelId
					));
				lastActivityDates.add(
					JSONUtil.put(
						"channelId", channelId
					).put(
						"lastActivityDate", lastActivityDate
					));
			}
		);

		individualJSONObject.remove("activitiesCount");
		individualJSONObject.remove("lastActivityDate");

		individualJSONObject.put("activitiesCounts", activitiesCount);
		individualJSONObject.put("lastActivityDates", lastActivityDates);

		return elasticsearchBulkRequestBuilder.replace(
			"individuals", individualJSONObject);
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}