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

package com.liferay.osb.asah.common.rest.response;

import com.liferay.osb.asah.common.date.DateUtil;

import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Bowerman
 */
public class PutResponse extends PostResponse {

	@Override
	public String respond() throws Exception {
		JSONObject jsonObject = new JSONObject(json);

		onBeforeUpdate(jsonObject);

		JSONObject responseJSONObject = invokeElasticsearch(jsonObject);

		onBeforeReturn(responseJSONObject);

		return responseJSONObject.toString();
	}

	public void setId(String id) {
		_id = id;
	}

	@Override
	protected JSONObject invokeElasticsearch(JSONObject jsonObject)
		throws Exception {

		return elasticsearchInvoker.update(collectionName, _id, jsonObject);
	}

	@Override
	protected void onBeforeAdd(JSONObject jsonObject) {
		throw new UnsupportedOperationException();
	}

	protected void onBeforeUpdate(JSONObject jsonObject) {
		jsonObject.put("dateModified", DateUtil.newDateString());
		jsonObject.remove("_embedded");
	}

	private String _id;

}