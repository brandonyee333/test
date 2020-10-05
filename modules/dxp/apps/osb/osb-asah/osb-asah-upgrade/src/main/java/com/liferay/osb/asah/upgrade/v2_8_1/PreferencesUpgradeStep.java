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

package com.liferay.osb.asah.upgrade.v2_8_1;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	private void _init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}