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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Bowerman
 */
public class PostResponse {

	public String respond() throws Exception {
		JSONObject jsonObject = new JSONObject(json);

		onBeforeAdd(jsonObject);

		JSONObject responseJSONObject = invokeElasticsearch(jsonObject);

		onBeforeReturn(responseJSONObject);

		return responseJSONObject.toString();
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public void setElasticsearchInvoker(
		ElasticsearchInvoker elasticsearchInvoker) {

		this.elasticsearchInvoker = elasticsearchInvoker;
	}

	public void setJSON(String json) {
		this.json = json;
	}

	protected JSONObject invokeElasticsearch(JSONObject jsonObject)
		throws Exception {

		return elasticsearchInvoker.add(collectionName, jsonObject);
	}

	protected void onBeforeAdd(JSONObject jsonObject) {
		String dateString = DateUtil.newDateString();

		jsonObject.put("dateCreated", dateString);
		jsonObject.put("dateModified", dateString);

		jsonObject.remove("_embedded");
		jsonObject.remove("id");
	}

	protected void onBeforeReturn(JSONObject responseJSONObject) {
	}

	protected String collectionName;
	protected ElasticsearchInvoker elasticsearchInvoker;
	protected String json;

}