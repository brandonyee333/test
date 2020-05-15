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

package com.liferay.osb.asah.salesforce.extractor.rest.controller;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class BaseRestController {

	protected String getFields(
		String collectionName, String dataSourceId, int end, int start) {

		JSONArray fieldsJSONArray = new JSONArray();

		Map<String, Set<String>> fieldValuesMap = new HashMap<>();

		JSONArray jsonArray = new JSONArray(
			_elasticsearchInvoker.get(
				collectionName,
				searchSourceBuilder -> {
					searchSourceBuilder.from(start);
					searchSourceBuilder.query(
						QueryBuilders.termQuery(
							"osbAsahDataSourceId", dataSourceId));
					searchSourceBuilder.size(end - start);
				}));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userJSONObject = jsonArray.getJSONObject(i);

			Iterator<String> keys = userJSONObject.keys();

			while (keys.hasNext()) {
				Set<String> values = null;

				String key = keys.next();

				if (fieldValuesMap.containsKey(key)) {
					values = fieldValuesMap.get(key);
				}
				else {
					values = new HashSet<>();

					fieldValuesMap.put(key, values);
				}

				String value = String.valueOf(userJSONObject.get(key));

				if (!StringUtils.isBlank(value)) {
					values.add(value);
				}
			}
		}

		for (Map.Entry<String, Set<String>> entry : fieldValuesMap.entrySet()) {
			fieldsJSONArray.put(
				JSONUtil.put(
					"name", entry.getKey()
				).put(
					"values", entry.getValue()
				));
		}

		return fieldsJSONArray.toString();
	}

	@PostConstruct
	protected void init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forSalesforceRaw();
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}