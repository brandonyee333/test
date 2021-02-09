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

package com.liferay.osb.asah.common.salesforce.extractor.dog;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Objects;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SalesforceExtractorConfigurationDog {

	public JSONObject addConfiguration(JSONObject dataSourceJSONObject) {
		String state = getState(dataSourceJSONObject);

		dataSourceJSONObject.put("state", state);

		try {
			_addConfiguration(dataSourceJSONObject.toString());
		}
		catch (Exception e) {
			dataSourceJSONObject.put("exceptionMessage", e.getMessage());
		}

		_updateConfiguration(dataSourceJSONObject, state);

		return dataSourceJSONObject;
	}

	public void deleteConfiguration(String dataSourceId) {
		JSONObject dataSourceJSONObject =
			_dataSourceDog.getDataSourceJSONObject(dataSourceId);

		String type = _dataSourceDog.getDataSourceType(dataSourceJSONObject);

		if (!Objects.equals(type, _getProviderType())) {
			return;
		}

		_configurationHttp.deleteConfiguration(
			JSONUtil.put("dataSourceId", dataSourceJSONObject.getString("id")),
			_getProviderType());
	}

	public String getState(JSONObject dataSourceJSONObject) {
		return _configurationHttp.getState(
			dataSourceJSONObject, _getProviderType());
	}

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

		_updateConfiguration(dataSourceJSONObject, state);

		return dataSourceJSONObject;
	}

	private void _addConfiguration(String json) {
		JSONObject dataSourceJSONObject = new JSONObject(json);

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		String type = providerJSONObject.getString("type");

		if (!Objects.equals(type, _getProviderType())) {
			return;
		}

		JSONObject configurationsJSONObject = _buildConfigurationsJSONObject(
			dataSourceJSONObject, providerJSONObject);

		configurationsJSONObject.put(
			"dataSourceId", dataSourceJSONObject.getString("id"));

		_configurationHttp.addConfiguration(
			configurationsJSONObject, _getProviderType());
	}

	private JSONObject _buildConfigurationsJSONObject(
		JSONObject dataSourceJSONObject, JSONObject providerJSONObject) {

		JSONObject configurationsJSONObject = JSONUtil.put(
			"credentials", dataSourceJSONObject.getJSONObject("credentials")
		).put(
			"dataSourceState", dataSourceJSONObject.getString("state")
		).put(
			"dataSourceStatus", dataSourceJSONObject.getString("status")
		).put(
			"url", dataSourceJSONObject.getString("url")
		);

		return configurationsJSONObject.put(
			"accountsConfiguration",
			providerJSONObject.optJSONObject("accountsConfiguration")
		).put(
			"contactsConfiguration",
			providerJSONObject.optJSONObject("contactsConfiguration")
		);
	}

	private JSONObject _buildOAuthOwnerJSONObject(
		JSONObject dataSourceJSONObject) {

		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getSalesforceOwner(dataSourceJSONObject.toString());

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return new JSONObject(responseEntity.getBody());
		}

		return null;
	}

	private String _getProviderType() {
		return "SALESFORCE";
	}

	private void _updateConfiguration(
		JSONObject dataSourceJSONObject, String state) {

		if (!Objects.equals(state, "CREDENTIALS_VALID")) {
			return;
		}

		JSONObject oAuthOwnerJSONObject = _buildOAuthOwnerJSONObject(
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
			JSONObject credentialsJSONObject =
				dataSourceJSONObject.getJSONObject("credentials");

			credentialsJSONObject.put("oAuthOwner", oAuthOwnerJSONObject);
		}
	}

	private void _updateConfiguration(
			String dataSourceId, JSONObject dataSourceJSONObject)
		throws Exception {

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		String type = providerJSONObject.getString("type");

		if (!Objects.equals(type, _getProviderType())) {
			return;
		}

		JSONObject configurationsJSONObject = _buildConfigurationsJSONObject(
			dataSourceJSONObject, providerJSONObject);

		configurationsJSONObject.put("dataSourceId", dataSourceId);

		JSONObject existingDataSourceJSONObject =
			_dataSourceDog.getDataSourceJSONObject(dataSourceId);

		configurationsJSONObject.put(
			"existingDataSourceId",
			existingDataSourceJSONObject.getString("id"));

		_configurationHttp.updateConfiguration(
			configurationsJSONObject, _getProviderType());
	}

	@Autowired
	private ConfigurationHttp _configurationHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DataSourceHttp _dataSourceHttp;

}