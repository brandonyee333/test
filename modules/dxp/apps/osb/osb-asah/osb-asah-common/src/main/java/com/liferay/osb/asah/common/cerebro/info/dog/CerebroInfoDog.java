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

package com.liferay.osb.asah.common.cerebro.info.dog;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class CerebroInfoDog {

	@PostConstruct
	public void init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forCerebroInfo();

		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	public void updateSegmentNames(JSONObject individualSegmentJSONObject)
		throws Exception {

		JSONArray referencedAssetIdsJSONArray =
			individualSegmentJSONObject.optJSONArray("referencedAssetIds");

		if ((referencedAssetIdsJSONArray == null) ||
			(referencedAssetIdsJSONArray.length() == 0)) {

			return;
		}

		for (String collectionName : _CEREBRO_INFO_COLLECTION_NAMES) {
			ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
				_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

			elasticsearchBulkRequestBuilder.refreshPolicy(
				WriteRequest.RefreshPolicy.IMMEDIATE);

			JSONArrayIterator.of(
				collectionName, _elasticsearchInvoker,
				jsonObject -> _updateSegmentNames(
					collectionName, elasticsearchBulkRequestBuilder,
					individualSegmentJSONObject.getString("id"),
					individualSegmentJSONObject.getString("name"), jsonObject)
			).setQueryBuilder(
				QueryBuilders.termQuery(
					"channelId",
					individualSegmentJSONObject.getString("channelId"))
			).setStopOnExceptions(
				false
			).iterate();
		}
	}

	private ElasticsearchBulkRequestBuilder _updateSegmentNames(
		String collectionName,
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		String individualSegmentId, String individualSegmentName,
		JSONObject jsonObject) {

		JSONArray individualJSONArray = _faroInfoElasticsearchInvoker.get(
			"individuals",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"id", jsonObject.getString("individualId"))
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", individualSegmentId)
			));

		if ((individualJSONArray == null) ||
			(individualJSONArray.length() == 0)) {

			return elasticsearchBulkRequestBuilder;
		}

		JSONArray segmentNamesJSONArray = jsonObject.getJSONArray(
			"segmentNames");

		List<String> segmentNames = JSONUtil.toStringList(
			segmentNamesJSONArray);

		if (segmentNames.contains(individualSegmentName)) {
			return elasticsearchBulkRequestBuilder;
		}

		segmentNames.add(individualSegmentName);

		JSONArray jsonArray = new JSONArray();

		for (String segmentName : segmentNames) {
			jsonArray.put(segmentName);
		}

		jsonObject.put("segmentNames", jsonArray);

		return elasticsearchBulkRequestBuilder.replace(
			collectionName, jsonObject);
	}

	private static final String[] _CEREBRO_INFO_COLLECTION_NAMES = {
		"blogs", "custom-assets", "document-libraries", "forms", "journals",
		"pages"
	};

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}