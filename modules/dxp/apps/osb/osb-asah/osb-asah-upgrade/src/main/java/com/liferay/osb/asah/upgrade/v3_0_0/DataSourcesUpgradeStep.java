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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class DataSourcesUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		JSONArray dataSources = _faroInfoElasticsearchInvoker.get(
			"data-sources");

		Iterator<Object> iterator = dataSources.iterator();

		while (iterator.hasNext()) {
			JSONObject jsonObject = (JSONObject)iterator.next();

			if (!jsonObject.has("channelId")) {
				continue;
			}

			_upgradeChannel(jsonObject.getString("channelId"));

			jsonObject.remove("channelId");

			_faroInfoElasticsearchInvoker.replace("data-sources", jsonObject);
		}
	}

	private void _upgradeChannel(String channelId) {
		JSONObject jsonObject = _faroInfoElasticsearchInvoker.get(
			"channels", channelId);

		jsonObject.put("defaultChannel", true);

		_faroInfoElasticsearchInvoker.update("channels", channelId, jsonObject);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}