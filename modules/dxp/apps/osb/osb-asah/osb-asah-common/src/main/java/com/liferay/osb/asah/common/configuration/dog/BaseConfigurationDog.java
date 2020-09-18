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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.security.Encryptor;
import com.liferay.osb.asah.common.spring.http.Http;

import java.security.KeyPair;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	@PostConstruct
	public void init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
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

			try {
				JSONObject oldDataSourceJSONObject =
					_faroInfoDataSourceDog.getDataSourceJSONObject(
						dataSourceJSONObject.getString("id"));

				JSONObject oldCredentialsJSONObject =
					oldDataSourceJSONObject.getJSONObject("credentials");

				String privateKey = oldCredentialsJSONObject.optString(
					"privateKey");

				if (StringUtils.isBlank(privateKey)) {
					KeyPair keyPair = _encryptor.generateKeyPair();

					credentialsJSONObject.put(
						"privateKey",
						_encryptor.encrypt(
							dataSourceJSONObject.getString("url"),
							_encryptor.encode(keyPair.getPrivate())));
					credentialsJSONObject.put(
						"publicKey", _encryptor.encode(keyPair.getPublic()));
				}

				if (StringUtils.isBlank(
						oldDataSourceJSONObject.optString(
							"faroBackendSecuritySignature"))) {

					dataSourceJSONObject.put(
						"faroBackendSecuritySignature",
						String.valueOf(UUID.randomUUID()));
				}

				if (Objects.equals(
						oldCredentialsJSONObject.getString("type"),
						"OAuth 2 Authentication")) {

					_updateCredentials(
						credentialsJSONObject, oldDataSourceJSONObject);
				}
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}

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

	private void _updateCredentials(
		JSONObject credentialsJSONObject, JSONObject oldDataSourceJSONObject) {

		oldDataSourceJSONObject.put("credentials", credentialsJSONObject);

		_elasticsearchInvoker.replace("data-sources", oldDataSourceJSONObject);
	}

	private static final Log _log = LogFactory.getLog(
		BaseConfigurationDog.class);

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private Encryptor _encryptor;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}