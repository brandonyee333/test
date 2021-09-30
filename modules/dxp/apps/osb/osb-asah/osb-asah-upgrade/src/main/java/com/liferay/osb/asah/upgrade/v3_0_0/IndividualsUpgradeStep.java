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

package com.liferay.osb.asah.upgrade.v3_0_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class IndividualsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_upgradeIndividualsIndexMapping();

		_upgradeIndividualsJSONObjects();
	}

	private Void _upgradeIndividuals(JSONArray individualJSONArray) {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		for (int i = 0; i < individualJSONArray.length(); i++) {
			JSONObject individualJSONObject = individualJSONArray.getJSONObject(
				i);

			individualJSONObject.put("previousActivityDates", new JSONArray());

			elasticsearchBulkRequestBuilder.update(
				"individuals", individualJSONObject);
		}

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}

		return null;
	}

	private void _upgradeIndividualsIndexMapping() {
		_elasticsearchIndexManager.updateMapping(
			"individuals",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"previousActivityDates",
					JSONUtil.put(
						"properties",
						JSONUtil.put(
							"channelId", JSONUtil.put("type", "keyword")
						).put(
							"lastActivityDate", JSONUtil.put("type", "date")
						)
					).put(
						"type", "nested"
					))
			).toString(),
			"individuals", WeDeployDataService.OSB_ASAH_FARO_INFO);
	}

	private void _upgradeIndividualsJSONObjects() throws Exception {
		JSONArrayIterator.of(
			"individuals", _faroInfoElasticsearchInvoker, null
		).setProcessJSONArrayUnsafeFunction(
			this::_upgradeIndividuals
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("previousActivityDates"))
		).iterate();
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}