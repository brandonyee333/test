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

package com.liferay.osb.asah.common.http.impl;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.http.ReportHttp;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
import com.liferay.osb.asah.common.spring.http.Http;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@MonolithExclude
public class ReportHttpImpl implements ReportHttp {

	@Override
	public JSONObject getAccountsJSONObject(String after) {
		return _httpGetJSONObject(
			String.format("/reports/accounts?after=%s", after));
	}

	@Override
	public JSONObject getIndividualsJSONObject(String after) {
		return _httpGetJSONObject(
			String.format("/reports/individuals?after=%s", after));
	}

	@Override
	public JSONObject getSegmentsJSONObject(String after) {
		return _httpGetJSONObject(
			String.format("/reports/segments?after=%s", after));
	}

	private JSONObject _httpGetJSONObject(String path) {
		String response = _http.exchange(
			ServiceConstants.URL_BACKEND_INTERNAL, path, HttpMethod.GET, null);

		return new JSONObject(response);
	}

	@Autowired
	private Http _http;

}