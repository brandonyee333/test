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

package com.liferay.osb.asah.common.configuration.dog;

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.Http;

import java.util.Objects;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 * @author Brian Wing Shun Chan
 */
public abstract class BaseConfigurationDog {

	public JSONObject addConfiguration(JSONObject dataSourceJSONObject) {
		String state = getState(dataSourceJSONObject);

		dataSourceJSONObject.put("state", state);

		try {
			_addConfiguration(dataSourceJSONObject.toString());
		}
		catch (Exception e) {
			dataSourceJSONObject.put("exceptionMessage", e.getMessage());
		}

		updateConfiguration(dataSourceJSONObject, state);

		return dataSourceJSONObject;
	}

	public void deleteConfiguration(String dataSourceId) {
		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId);

		String type = _faroInfoDataSourceDog.getDataSourceType(
			dataSourceJSONObject);

		if (!Objects.equals(type, getProviderType())) {
			return;
		}

		configurationHttp.deleteConfiguration(
			JSONUtil.put("dataSourceId", dataSourceJSONObject.getString("id")),
			getProviderType());
	}

	public String getState(JSONObject dataSourceJSONObject) {
		return configurationHttp.getState(
			dataSourceJSONObject, getProviderType());
	}

	public abstract void setUpDataSource(JSONObject dataSourceJSONObject);

	public JSONObject updateConfiguration(JSONObject dataSourceJSONObject) {
		String state = getState(dataSourceJSONObject);

		dataSourceJSONObject.put("state", state);

		try {
			_updateConfiguration(
				dataSourceJSONObject.getString("id"), dataSourceJSONObject);
		}
		catch (Exception e) {
			dataSourceJSONObject.put("exceptionMessage", e.getMessage());
		}

		updateConfiguration(dataSourceJSONObject, state);

		return dataSourceJSONObject;
	}

	protected JSONObject buildConfigurationsJSONObject(
		JSONObject dataSourceJSONObject, JSONObject providerJSONObject) {

		return JSONUtil.put(
			"credentials", dataSourceJSONObject.getJSONObject("credentials")
		).put(
			"dataSourceState", dataSourceJSONObject.getString("state")
		).put(
			"dataSourceStatus", dataSourceJSONObject.getString("status")
		).put(
			"url", dataSourceJSONObject.getString("url")
		);
	}

	protected abstract JSONObject buildOAuthOwnerJSONObject(
		JSONObject dataSourceJSONObject);

	protected abstract String getProviderType();

	protected abstract String getURL();

	protected void updateConfiguration(
		JSONObject dataSourceJSONObject, String state) {

		if (!Objects.equals(state, "CREDENTIALS_VALID")) {
			return;
		}

		JSONObject credentialsJSONObject = dataSourceJSONObject.getJSONObject(
			"credentials");

		if (Objects.equals(
				credentialsJSONObject.getString("type"),
				"Token Authentication")) {

			_faroInfoDataSourceDog.updateTokenDataSourceCredentials(
				dataSourceJSONObject);

			return;
		}

		JSONObject oAuthOwnerJSONObject = buildOAuthOwnerJSONObject(
			dataSourceJSONObject);

		if (oAuthOwnerJSONObject == null) {
			dataSourceJSONObject.put("state", "CREDENTIALS_INVALID");

			try {
				_updateConfiguration(
					dataSourceJSONObject.getString("id"), dataSourceJSONObject);
			}
			catch (Exception e) {
				dataSourceJSONObject.put("exceptionMessage", e.getMessage());
			}
		}
		else {
			credentialsJSONObject.put("oAuthOwner", oAuthOwnerJSONObject);
		}

		setUpDataSource(dataSourceJSONObject);
	}

	@Autowired
	protected ConfigurationHttp configurationHttp;

	@Autowired
	protected Http http;

	private void _addConfiguration(String json) {
		JSONObject dataSourceJSONObject = new JSONObject(json);

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		String type = providerJSONObject.getString("type");

		if (!Objects.equals(type, getProviderType())) {
			return;
		}

		JSONObject configurationsJSONObject = buildConfigurationsJSONObject(
			dataSourceJSONObject, providerJSONObject);

		configurationsJSONObject.put(
			"dataSourceId", dataSourceJSONObject.getString("id"));

		configurationHttp.addConfiguration(
			configurationsJSONObject, getProviderType());
	}

	private void _updateConfiguration(
			String dataSourceId, JSONObject dataSourceJSONObject)
		throws Exception {

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		String type = providerJSONObject.getString("type");

		if (!Objects.equals(type, getProviderType())) {
			return;
		}

		JSONObject configurationsJSONObject = buildConfigurationsJSONObject(
			dataSourceJSONObject, providerJSONObject);

		configurationsJSONObject.put("dataSourceId", dataSourceId);

		JSONObject existingDataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId);

		configurationsJSONObject.put(
			"existingDataSourceId",
			existingDataSourceJSONObject.getString("id"));

		configurationHttp.updateConfiguration(
			configurationsJSONObject, getProviderType());
	}

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}