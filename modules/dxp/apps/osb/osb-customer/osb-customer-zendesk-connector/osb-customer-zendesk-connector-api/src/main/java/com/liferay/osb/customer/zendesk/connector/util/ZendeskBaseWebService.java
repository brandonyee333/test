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

package com.liferay.osb.customer.zendesk.connector.util;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

/**
 * @author Kyle Bischof
 */
public interface ZendeskBaseWebService {

	public JSONObject delete(String endpoint, String json) throws Exception;

	public JSONObject get(String endpoint, String json) throws Exception;

	public JSONObject post(
			String endpoint, Map<String, String> params, String fileName,
			byte[] bytes)
		throws Exception;

	public JSONObject post(String endpoint, String json) throws Exception;

	public JSONObject put(String endpoint, String json) throws Exception;

}