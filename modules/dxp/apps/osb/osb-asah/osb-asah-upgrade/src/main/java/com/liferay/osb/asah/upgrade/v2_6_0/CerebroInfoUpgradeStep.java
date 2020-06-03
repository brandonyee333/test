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
public class CerebroInfoUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		List<String> channelIds = JSONUtil.toStringList(
			_faroInfoElasticsearchInvoker.get("channels"), "id");

		JSONArray individualSegmentsJSONArray =
			_faroInfoElasticsearchInvoker.get(
				"individual-segments",
				QueryBuilders.termsQuery("channelId", channelIds));

		Map<String, List<String>> channelIndividualSegmentNames =
			new HashMap<>();

		for (int i = 0; i < individualSegmentsJSONArray.length(); i++) {
			JSONObject individualSegmentJSONObject =
				individualSegmentsJSONArray.getJSONObject(i);

			String channelId = individualSegmentJSONObject.getString(
				"channelId");

			List<String> individualSegmentNames = new ArrayList<>();

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

		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		for (String collectionName : _CEREBRO_INFO_COLLECTION_NAMES) {
			ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
				_cerebroInfoElasticsearchInvoker.
					createElasticsearchBulkRequestBuilder();

			elasticsearchBulkRequestBuilder.refreshPolicy(
				WriteRequest.RefreshPolicy.IMMEDIATE);

			JSONArrayIterator.of(
				collectionName, _cerebroInfoElasticsearchInvoker,
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

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}