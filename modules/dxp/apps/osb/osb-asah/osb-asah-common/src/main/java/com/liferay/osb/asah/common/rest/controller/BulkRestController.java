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

package com.liferay.osb.asah.common.rest.controller;

import com.liferay.osb.asah.common.spring.http.Http;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@RequestMapping(produces = "application/json", value = "/bulk")
@RestController
public class BulkRestController {

	@PostMapping
	public String post(@RequestBody String json) {
		JSONObject responseJSONObject = new JSONObject();

		JSONArray responseJSONArray = new JSONArray();

		JSONObject jsonObject = new JSONObject(json);

		JSONArray operationsJSONArray = jsonObject.getJSONArray("operations");

		for (int i = 0; i < operationsJSONArray.length(); i++) {
			JSONObject operationJSONObject = operationsJSONArray.getJSONObject(
				i);

			String response = StringUtils.trim(
				_http.exchange(
					"http://localhost:" + _getPort(),
					operationJSONObject.getString("url"),
					HttpMethod.resolve(operationJSONObject.getString("method")),
					operationJSONObject.optJSONObject("body")));

			if (StringUtils.startsWith(response, "[")) {
				responseJSONArray.put(new JSONArray(response));
			}
			else {
				responseJSONArray.put(new JSONObject(response));
			}
		}

		responseJSONObject.put("responses", responseJSONArray);

		return responseJSONObject.toString();
	}

	private int _getPort() {
		if (_embeddedWebApplicationContext != null) {
			EmbeddedServletContainer embeddedServletContainer =
				_embeddedWebApplicationContext.getEmbeddedServletContainer();

			return embeddedServletContainer.getPort();
		}

		return 8080;
	}

	@Autowired(required = false)
	private EmbeddedWebApplicationContext _embeddedWebApplicationContext;

	@Autowired
	private Http _http;

}