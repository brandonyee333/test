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

package com.liferay.osb.asah.upgrade.v2_12_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.HashSet;
import java.util.Set;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class RunLogUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_upgradeRunLogIndexMapping(WeDeployDataService.OSB_ASAH_DXP_RAW);
		_upgradeRunLogIndexMapping(WeDeployDataService.OSB_ASAH_FARO_INFO);
		_upgradeRunLogIndexMapping(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);

		_upgradeRunLogJSONObjects(_dxpRawElasticsearchInvoker);
		_upgradeRunLogJSONObjects(_faroInfoElasticsearchInvoker);
		_upgradeRunLogJSONObjects(_salesforceRawElasticsearchInvoker);
	}

	private void _upgradeRunLogIndexMapping(
		WeDeployDataService weDeployDataService) {

		_elasticsearchIndexManager.updateMapping(
			"run-logs",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"context",
					JSONUtil.put(
						"properties",
						JSONUtil.put(
							"totalOperations", JSONUtil.put("type", "long"))))
			).toString(),
			"run-logs", weDeployDataService);
	}

	private JSONObject _upgradeRunLogJSONObject(
		JSONObject oldRunLogJSONObject) {

		JSONObject newRunLogJSONObject = new JSONObject();

		JSONObject newRunLogContextJSONObject = new JSONObject();

		for (String key : oldRunLogJSONObject.keySet()) {
			if (_runLogProperties.contains(key)) {
				newRunLogJSONObject.put(key, oldRunLogJSONObject.get(key));
			}
			else {
				newRunLogContextJSONObject.put(
					key, oldRunLogJSONObject.get(key));
			}
		}

		newRunLogJSONObject.put("context", newRunLogContextJSONObject);

		return newRunLogJSONObject;
	}

	private void _upgradeRunLogJSONObjects(
			ElasticsearchInvoker elasticsearchInvoker)
		throws Exception {

		JSONArrayIterator.of(
			"run-logs", elasticsearchInvoker,
			runLogJSONObject -> elasticsearchInvoker.replace(
				"run-logs", _upgradeRunLogJSONObject(runLogJSONObject))
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(QueryBuilders.existsQuery("context"))
		).iterate();
	}

	private static final Set<String> _runLogProperties = new HashSet<String>() {
		{
			add("dataSourceId");
			add("dateLogged");
			add("id");
			add("naniteClassName");
			add("status");
		}
	};

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}