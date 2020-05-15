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
import com.liferay.osb.asah.common.http.PortalPreferencesHttp;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
import com.liferay.osb.asah.common.spring.http.Http;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@MonolithExclude
public class PortalPreferencesHttpImpl implements PortalPreferencesHttp {

	@Override
	public ResponseEntity<String> updatePortalPreferences(
		JSONObject jsonObject) {

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR, "/portal-preferences",
			HttpMethod.POST, jsonObject);
	}

	@Autowired
	private Http _http;

}