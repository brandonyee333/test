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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
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
	public void addConfiguration(DataSource dataSource, String providerType) {
		_http.exchange(
			_getURL(providerType), "/configurations", HttpMethod.POST,
			_objectMapper.convertValue(dataSource, JSONObject.class));
	}

	@Override
	public void deleteConfiguration(String dataSourceId, String providerType) {
		_http.exchange(
			_getURL(providerType), "/configurations", HttpMethod.DELETE,
			JSONUtil.put("dataSourceId", dataSourceId));
	}

	@Override
	public String getState(DataSource dataSource, String providerType) {
		return _http.exchange(
			_getURL(providerType), "/configurations/state", HttpMethod.GET,
			_objectMapper.convertValue(dataSource, JSONObject.class));
	}

	@Override
	public DataSource refreshConfiguration(
		DataSource dataSource, String providerType) {

		return _objectMapper.convertValue(
			_http.exchange(
				_getURL(providerType), "/configurations/refresh",
				HttpMethod.POST,
				_objectMapper.convertValue(dataSource, JSONObject.class)),
			DataSource.class);
	}

	@Override
	public void updateConfiguration(
		DataSource dataSource, String providerType) {

		_http.exchange(
			_getURL(providerType), "/configurations", HttpMethod.PUT,
			_objectMapper.convertValue(dataSource, JSONObject.class));
	}

	private String _getURL(String providerType) {
		if (providerType.equals("SALESFORCE")) {
			return ServiceConstants.URL_SALESFORCE_EXTRACTOR;
		}

		throw new RuntimeException("Invalid data source type " + providerType);
	}

	@Autowired
	private Http _http;

	@Autowired
	private ObjectMapper _objectMapper;

}