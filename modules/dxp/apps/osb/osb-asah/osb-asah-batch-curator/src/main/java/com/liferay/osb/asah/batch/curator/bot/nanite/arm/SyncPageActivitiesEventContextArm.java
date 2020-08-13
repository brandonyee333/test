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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.TotalHits;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class SyncPageActivitiesEventContextArm {

	public void execute() {
		long keepAliveSeconds = 120;

		SearchResponse searchResponse =
			_faroInfoElasticsearchInvoker.searchScroll(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.fetchSource(
						new String[] {
							"id", "object.url", "startTime", "userId"
						},
						null);
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Page")
						).mustNot(
							QueryBuilders.existsQuery("eventContext")
						));
					searchSourceBuilder.size(500);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort("id", SortOrder.DESC));
				},
				keepAliveSeconds);

		JSONArray activitiesJSONArray = _toJSONArray(searchResponse.getHits());

		String scrollId = searchResponse.getScrollId();

		while (activitiesJSONArray.length() > 0) {
			_syncPageActivitiesEventContext(activitiesJSONArray);

			searchResponse = _faroInfoElasticsearchInvoker.searchScroll(
				scrollId, keepAliveSeconds);

			activitiesJSONArray = _toJSONArray(searchResponse.getHits());

			scrollId = searchResponse.getScrollId();
		}
	}

	private SearchSourceBuilder _analyticsEventSearchSourceBuilder(
		JSONObject activityJSONObject) {

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		JSONObject objectJSONObject = activityJSONObject.getJSONObject(
			"object");

		searchSourceBuilder.fetchSource("context", null);
		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery(
					"context.url", objectJSONObject.getString("url"))
			).filter(
				QueryBuilders.termQuery(
					"eventDate", activityJSONObject.getString("startTime"))
			).filter(
				QueryBuilders.termQuery(
					"userId", activityJSONObject.getString("userId"))
			));
		searchSourceBuilder.size(1);

		return searchSourceBuilder;
	}

	@PostConstruct
	private void _init() {
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private void _syncPageActivitiesEventContext(
		JSONArray activitiesJSONArray) {

		List<SearchSourceBuilder> searchSourceBuilders = new ArrayList<>();

		for (int i = 0; i < activitiesJSONArray.length(); i++) {
			searchSourceBuilders.add(
				_analyticsEventSearchSourceBuilder(
					activitiesJSONArray.getJSONObject(i)));
		}

		MultiSearchResponse multiSearchResponse =
			_cerebroRawElasticsearchInvoker.multiSearch(
				"analytics-events", searchSourceBuilders);

		MultiSearchResponse.Item[] multiSearchItemResponses =
			multiSearchResponse.getResponses();

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		for (int i = 0; i < activitiesJSONArray.length(); i++) {
			JSONObject activityJSONObject = activitiesJSONArray.getJSONObject(
				i);

			MultiSearchResponse.Item multiSearchItemResponse =
				multiSearchItemResponses[i];

			if (multiSearchItemResponse.isFailure()) {
				_log.error(
					"Unable to sync event context for activity with ID " +
						activityJSONObject.getString("id"),
					multiSearchItemResponse.getFailure());

				continue;
			}

			SearchResponse searchResponse =
				multiSearchItemResponse.getResponse();

			SearchHits searchHits = searchResponse.getHits();

			TotalHits totalHits = searchHits.getTotalHits();

			if (totalHits.value == 0) {
				continue;
			}

			SearchHit searchHit = searchHits.getAt(0);

			JSONObject analyticsEventJSONObject = new JSONObject(
				searchHit.getSourceAsString());

			elasticsearchBulkRequestBuilder.update(
				"activities",
				JSONUtil.put(
					"eventContext",
					analyticsEventJSONObject.getJSONObject("context")
				).put(
					"id", activityJSONObject.getString("id")
				));
		}

		if (!elasticsearchBulkRequestBuilder.hasActions()) {
			return;
		}

		BulkResponse bulkResponse = elasticsearchBulkRequestBuilder.get();

		for (BulkItemResponse bulkItemResponse : bulkResponse.getItems()) {
			if (!bulkItemResponse.isFailed()) {
				continue;
			}

			BulkItemResponse.Failure failure = bulkItemResponse.getFailure();

			_log.error(failure.getMessage(), failure.getCause());
		}
	}

	private JSONArray _toJSONArray(SearchHits searchHits) {
		JSONArray jsonArray = new JSONArray();

		for (SearchHit searchHit : searchHits.getHits()) {
			jsonArray.put(new JSONObject(searchHit.getSourceAsString()));
		}

		return jsonArray;
	}

	private static final Log _log = LogFactory.getLog(
		SyncPageActivitiesEventContextArm.class);

	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}