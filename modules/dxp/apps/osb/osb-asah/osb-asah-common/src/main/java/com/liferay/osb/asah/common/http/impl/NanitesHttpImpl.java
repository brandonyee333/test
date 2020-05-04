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
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
import com.liferay.osb.asah.common.spring.http.Http;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@MonolithExclude
public class NanitesHttpImpl implements NanitesHttp {

	@Override
	public void executeOSBAsahTask(JSONObject jsonObject) {
		_http.exchangeIfUp(
			ServiceConstants.URL_BATCH_CURATOR, "/nanites", HttpMethod.POST,
			jsonObject.toString());
	}

	@Override
	public void refreshAnalytics() {
		_http.exchangeIfUp(
			ServiceConstants.URL_BATCH_CURATOR, "/nanites/analytics",
			HttpMethod.POST, null);
	}

	@Override
	public void run(String json) {
		_http.exchangeIfUp(
			ServiceConstants.URL_BATCH_CURATOR, "/nanites/run", HttpMethod.POST,
			json);
	}

	@Override
	public void scheduleOSBAsahTask(JSONObject jsonObject) {
		_http.exchangeIfUp(
			ServiceConstants.URL_BATCH_CURATOR, "/nanites/schedule",
			HttpMethod.POST, jsonObject.toString());
	}

	@Autowired
	private Http _http;

}