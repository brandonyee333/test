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

import org.json.JSONObject;

/**
 * @author Vishal Reddy
 */
public class DeleteResponse extends BaseGetResponse {

	@Override
	public String respond() {
		JSONObject responseJSONObject = new JSONObject();

		boolean succeeded = elasticsearchInvoker.delete(collectionName, _id);

		responseJSONObject.put("deleted", succeeded);

		return responseJSONObject.toString();
	}

	public void setId(String id) {
		_id = id;
	}

	private String _id;

}