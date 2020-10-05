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

package com.liferay.osb.asah.common.faro.info.dog;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Preference;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class FaroInfoPreferenceDog {

	public Preference addPreference(String key, String value) {
		return new Preference(
			_faroInfoElasticsearchInvoker.add(
				"preferences",
				JSONUtil.put(
					"id", key
				).put(
					"value", value
				),
				true));
	}

	public Preference getPreference(String key) {
		JSONObject preferenceJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"preferences", key, true);

		if (preferenceJSONObject == null) {
			return new Preference(key, _defaultPreferences.get(key));
		}

		return new Preference(preferenceJSONObject);
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private static final Map<String, String> _defaultPreferences =
		new HashMap<String, String>() {
			{
				put(
					"data-retention-period",
					String.valueOf(TimeUnit.DAYS.toMillis(30 * 13)));
			}
		};

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}