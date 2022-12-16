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

package com.liferay.osb.asah.common.util;

import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Marcos Martins
 */
public class FieldValueListUtil {

	public static JSONArray toJSONArray(List<FieldValueList> fieldValueLists) {
		JSONArray jsonArray = new JSONArray();

		fieldValueLists.forEach(
			fieldValueList -> jsonArray.put(_toJSONObject(fieldValueList)));

		return jsonArray;
	}

	private static JSONObject _toJSONObject(FieldValueList fieldValueList) {
		JSONObject jsonObject = new JSONObject();

		FieldValue dataSourceIdFieldValue = fieldValueList.get(
			_FIELD_DATA_SOURCE_ID_INDEX);

		jsonObject.put("dataSourceId", dataSourceIdFieldValue.getValue());

		FieldValue nameFieldValue = fieldValueList.get(_FIELD_NAME_INDEX);

		jsonObject.put("name", nameFieldValue.getValue());

		FieldValue valueFieldValue = fieldValueList.get(_FIELD_VALUE_INDEX);

		jsonObject.put("value", valueFieldValue.getValue());

		return jsonObject;
	}

	private static final int _FIELD_DATA_SOURCE_ID_INDEX = 0;

	private static final int _FIELD_NAME_INDEX = 1;

	private static final int _FIELD_VALUE_INDEX = 2;

}