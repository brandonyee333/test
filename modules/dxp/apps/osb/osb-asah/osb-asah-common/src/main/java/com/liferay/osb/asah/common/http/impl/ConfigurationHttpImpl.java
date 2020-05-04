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
import com.liferay.osb.asah.common.http.ConfigurationHttp;
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
public class ConfigurationHttpImpl implements ConfigurationHttp {

	@Override
	public void addConfiguration(JSONObject jsonObject, String providerType) {
		_http.exchange(
			_getURL(providerType), "/configurations", HttpMethod.POST,
			jsonObject);
	}

	@Override
	public void deleteConfiguration(
		JSONObject jsonObject, String providerType) {

		_http.exchange(
			_getURL(providerType), "/configurations", HttpMethod.DELETE,
			jsonObject);
	}

	@Override
	public String getState(JSONObject jsonObject, String providerType) {
		return _http.exchange(
			_getURL(providerType), "/configurations/state", HttpMethod.GET,
			jsonObject);
	}

	@Override
	public String refreshConfiguration(
		JSONObject jsonObject, String providerType) {

		return _http.exchange(
			_getURL(providerType), "/configurations/refresh", HttpMethod.POST,
			jsonObject);
	}

	@Override
	public void updateConfiguration(
		JSONObject jsonObject, String providerType) {

		_http.exchange(
			_getURL(providerType), "/configurations", HttpMethod.PUT,
			jsonObject);
	}

	private String _getURL(String providerType) {
		if (providerType.equals("LIFERAY")) {
			return ServiceConstants.URL_DXP_EXTRACTOR;
		}
		else if (providerType.equals("SALESFORCE")) {
			return ServiceConstants.URL_SALESFORCE_EXTRACTOR;
		}

		throw new RuntimeException("Invalid data source type " + providerType);
	}

	@Autowired
	private Http _http;

}