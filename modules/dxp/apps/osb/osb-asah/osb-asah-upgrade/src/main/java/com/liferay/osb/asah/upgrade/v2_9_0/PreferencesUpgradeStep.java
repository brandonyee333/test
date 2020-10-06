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

package com.liferay.osb.asah.upgrade.v2_9_0;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class PreferencesUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		JSONArray preferencesJSONArray = _elasticsearchInvoker.get(
			"preferences", QueryBuilders.matchAllQuery());

		for (int i = 0; i < preferencesJSONArray.length(); i++) {
			JSONObject preferenceJSONObject =
				preferencesJSONArray.getJSONObject(i);

			_elasticsearchInvoker.add(
				"preferences",
				JSONUtil.put(
					"id", preferenceJSONObject.get("key")
				).put(
					"value", preferenceJSONObject.get("value")
				));

			_elasticsearchInvoker.delete("preferences", preferenceJSONObject);
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}