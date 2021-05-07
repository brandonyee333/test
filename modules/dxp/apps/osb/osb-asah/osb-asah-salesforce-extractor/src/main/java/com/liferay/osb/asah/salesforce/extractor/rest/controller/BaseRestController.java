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

import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class BaseRestController {

	protected String getFields(
		Long dataSourceId, int end, int start,
		SalesforceEntity.Type salesforceEntityType) {

		JSONArray fieldsJSONArray = new JSONArray();

		int size = end - start;

		for (SalesforceEntity salesforceEntity :
				_salesforceEntityDog.getSalesforceEntities(
					dataSourceId, start / size, size, salesforceEntityType)) {

			fieldsJSONArray.put(salesforceEntity.getFieldsJSONObject());
		}

		return _getFields(fieldsJSONArray);
	}

	private String _getFields(JSONArray fieldsJSONArray) {
		JSONArray jsonArray = new JSONArray();

		Map<String, Set<String>> fieldValuesMap = new HashMap<>();

		for (int i = 0; i < fieldsJSONArray.length(); i++) {
			JSONObject fieldsJSONObject = fieldsJSONArray.getJSONObject(i);

			Iterator<String> keys = fieldsJSONObject.keys();

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

				String value = String.valueOf(fieldsJSONObject.get(key));

				if (!StringUtils.isBlank(value)) {
					values.add(value);
				}
			}
		}

		for (Map.Entry<String, Set<String>> entry : fieldValuesMap.entrySet()) {
			jsonArray.put(
				JSONUtil.put(
					"name", entry.getKey()
				).put(
					"values", entry.getValue()
				));
		}

		return jsonArray.toString();
	}

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

}