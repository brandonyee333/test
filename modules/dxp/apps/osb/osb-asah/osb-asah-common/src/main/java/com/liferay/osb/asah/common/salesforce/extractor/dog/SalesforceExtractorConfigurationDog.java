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
import com.liferay.osb.asah.common.dto.DataSourceDTO;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.model.DataSource;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	public DataSource addConfiguration(DataSource dataSource) {
		String state = getState(dataSource);

		dataSource.setState(state);

		try {
			_addConfiguration(dataSource);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_updateConfiguration(dataSource, state);

		return dataSource;
	}

	public void deleteConfiguration(Long dataSourceId) {
		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		if (!Objects.equals(dataSource.getProviderType(), _getProviderType())) {
			return;
		}

		_configurationHttp.deleteConfiguration(
			String.valueOf(dataSource.getId()), _getProviderType());
	}

	public String getState(DataSource dataSource) {
		return _configurationHttp.getState(dataSource, _getProviderType());
	}

	public DataSource updateConfiguration(DataSource dataSource) {
		String state = getState(dataSource);

		dataSource.setState(state);

		try {
			_updateConfiguration(dataSource);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_updateConfiguration(dataSource, state);

		return dataSource;
	}

	private void _addConfiguration(DataSource dataSource) {
		if (!Objects.equals(dataSource.getProviderType(), _getProviderType())) {
			return;
		}

		_configurationHttp.addConfiguration(dataSource, _getProviderType());
	}

	private JSONObject _buildOAuthOwnerJSONObject(DataSource dataSource) {
		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getSalesforceOwner(new DataSourceDTO(dataSource));

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return new JSONObject(responseEntity.getBody());
		}

		return null;
	}

	private String _getProviderType() {
		return "SALESFORCE";
	}

	private void _updateConfiguration(DataSource dataSource) throws Exception {
		if (!Objects.equals(dataSource.getProviderType(), _getProviderType())) {
			return;
		}

		_configurationHttp.updateConfiguration(dataSource, _getProviderType());
	}

	private void _updateConfiguration(DataSource dataSource, String state) {
		if (!Objects.equals(state, "CREDENTIALS_VALID")) {
			return;
		}

		JSONObject oAuthOwnerJSONObject = _buildOAuthOwnerJSONObject(
			dataSource);

		if (oAuthOwnerJSONObject == null) {
			dataSource.setState("CREDENTIALS_INVALID");

			try {
				_updateConfiguration(dataSource);
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}
		else {
			dataSource.setOAuthOwnerEmailAddress(
				oAuthOwnerJSONObject.optString("emailAddress"));
			dataSource.setOAuthOwnerName(
				oAuthOwnerJSONObject.optString("name"));
		}
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceExtractorConfigurationDog.class);

	@Autowired
	private ConfigurationHttp _configurationHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DataSourceHttp _dataSourceHttp;

}