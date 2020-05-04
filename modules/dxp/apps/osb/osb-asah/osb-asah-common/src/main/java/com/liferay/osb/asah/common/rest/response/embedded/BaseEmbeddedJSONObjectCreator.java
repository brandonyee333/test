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

package com.liferay.osb.asah.common.rest.response.embedded;

import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Shinn Lok
 */
public abstract class BaseEmbeddedJSONObjectCreator
	implements EmbeddedJSONObjectCreator {

	@Override
	public Map<String, JSONObject> create(JSONArray jsonArray)
		throws Exception {

		Map<String, JSONObject> jsonObjects = new HashMap<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String id = jsonObject.getString("id");

			jsonObjects.put(id, create(id));
		}

		return jsonObjects;
	}

	protected JSONObject create(String id, JSONObject jsonObject)
		throws Exception {

		Map<String, JSONObject> jsonObjects = create(JSONUtil.put(jsonObject));

		if ((jsonObjects == null) || jsonObjects.isEmpty()) {
			return null;
		}

		return jsonObjects.get(id);
	}

}