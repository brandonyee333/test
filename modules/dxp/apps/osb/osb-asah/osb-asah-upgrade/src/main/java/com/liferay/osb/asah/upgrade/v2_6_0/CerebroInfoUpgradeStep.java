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

package com.liferay.osb.asah.upgrade.v2_6_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class CerebroInfoUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		List<String> channelIds = _getChannelIds();

		if (channelIds.isEmpty()) {
			return;
		}

		Map<String, List<String>> channelIndividualSegmentNames =
			new HashMap<>();

		JSONArray individualSegmentsJSONArray =
			_faroInfoElasticsearchInvoker.get(
				"individual-segments",
				QueryBuilders.termsQuery("channelId", channelIds));

		for (int i = 0; i < individualSegmentsJSONArray.length(); i++) {
			List<String> individualSegmentNames = new ArrayList<>();

			JSONObject individualSegmentJSONObject =
				individualSegmentsJSONArray.getJSONObject(i);

			String channelId = individualSegmentJSONObject.getString(
				"channelId");

			if (channelIndividualSegmentNames.containsKey(channelId)) {
				individualSegmentNames = channelIndividualSegmentNames.get(
					channelId);
			}

			individualSegmentNames.add(
				individualSegmentJSONObject.getString("name"));

			channelIndividualSegmentNames.put(
				individualSegmentJSONObject.getString("channelId"),
				individualSegmentNames);
		}

		ElasticsearchInvoker cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		for (String collectionName : _CEREBRO_INFO_COLLECTION_NAMES) {
			ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
				cerebroInfoElasticsearchInvoker.
					createElasticsearchBulkRequestBuilder();

			elasticsearchBulkRequestBuilder.refreshPolicy(
				WriteRequest.RefreshPolicy.IMMEDIATE);

			JSONArrayIterator.of(
				collectionName, cerebroInfoElasticsearchInvoker,
				jsonObject -> _updateSegmentNames(
					channelIndividualSegmentNames.get(
						jsonObject.getString("channelId")),
					collectionName, elasticsearchBulkRequestBuilder, jsonObject)
			).setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.existsQuery("channelId")
				).filter(
					QueryBuilders.existsQuery("segmentNames")
				)
			).setStopOnExceptions(
				false
			).iterate();
		}
	}

	private List<String> _getChannelIds() {
		List<String> dataSourceIds = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"channels",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"dataSources"
					).field(
						"dataSources.id"
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						PipelineAggregatorBuilders.bucketSelector(
							"bucket_filter",
							new HashMap() {
								{
									put("doc_count", "_count");
								}
							},
							new Script("params.doc_count > 1"))
					));

				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return dataSourceIds;
		}

		Terms terms = aggregations.get("dataSources");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			dataSourceIds.add(bucket.getKeyAsString());
		}

		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"channels",
			QueryBuilders.termsQuery("dataSources.id", dataSourceIds));

		return JSONUtil.toStringList(jsonArray, "id");
	}

	private ElasticsearchBulkRequestBuilder _updateSegmentNames(
		List<String> channelSegmentNames, String collectionName,
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		JSONObject jsonObject) {

		if (channelSegmentNames == null) {
			channelSegmentNames = new ArrayList<>();
		}

		JSONArray segmentNamesJSONArray = jsonObject.getJSONArray(
			"segmentNames");

		List<String> segmentNames = JSONUtil.toStringList(
			segmentNamesJSONArray);

		if (segmentNames.retainAll(channelSegmentNames)) {
			JSONArray jsonArray = new JSONArray();

			for (String segmentName : segmentNames) {
				jsonArray.put(segmentName);
			}

			jsonObject.put("segmentNames", jsonArray);

			return elasticsearchBulkRequestBuilder.replace(
				collectionName, jsonObject);
		}

		return elasticsearchBulkRequestBuilder;
	}

	private static final String[] _CEREBRO_INFO_COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "document-libraries", "forms", "journal-clicks",
		"journals", "page-referrers", "pages"
	};

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}