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

package com.liferay.osb.asah.dxp.extractor.configuration.impl;

import com.liferay.osb.asah.common.bot.ConfigurableBot;
import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.FileConfiguration;
import com.liferay.osb.asah.common.configuration.RuntimeConfiguration;
import com.liferay.osb.asah.common.configuration.impl.BaseConfigurationManagerImpl;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.oauth2.DXPOAuth2Client;
import com.liferay.osb.asah.common.security.Encryptor;
import com.liferay.osb.asah.common.spring.http.Http;
import com.liferay.osb.asah.dxp.extractor.bot.DXPExtractorConfigurableBot;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;
import com.liferay.osb.asah.dxp.extractor.dog.PortalDog;

import java.util.Arrays;
import java.util.Objects;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@Component
public class DXPExtractorConfigurationManagerImpl
	extends BaseConfigurationManagerImpl {

	@Override
	public JSONObject buildConfigurationsJSONObject(
		JSONObject dataSourceJSONObject) {

		JSONObject configurationsJSONObject =
			super.buildConfigurationsJSONObject(dataSourceJSONObject);

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		return configurationsJSONObject.put(
			"analyticsConfiguration",
			providerJSONObject.optJSONObject("analyticsConfiguration")
		).put(
			"contactsConfiguration",
			providerJSONObject.optJSONObject("contactsConfiguration")
		);
	}

	@Override
	public boolean deleteRuntimeConfiguration(String json) {
		JSONObject jsonObject = new JSONObject(json);

		String dataSourceId = jsonObject.getString("dataSourceId");

		JSONObject dataSourceJSONObject = fetchDataSourceJSONObject(
			dataSourceId);

		if ((dataSourceJSONObject != null) &&
			Objects.equals(
				getState(dataSourceJSONObject.toString()),
				"CREDENTIALS_VALID") &&
			!Objects.equals(
				dataSourceJSONObject.query("/credentials/type"),
				"Token Authentication")) {

			_portalDog.removeCompanyPreferences(
				dataSourceJSONObject.toString());
		}

		super.deleteRuntimeConfiguration(json);

		return true;
	}

	@Override
	public DXPExtractorConfiguration getConfiguration(String dataSourceId) {
		return (DXPExtractorConfiguration)super.getConfiguration(dataSourceId);
	}

	@Override
	public DXPExtractorConfiguration[] getConfigurations() {
		Configuration[] configurations = super.getConfigurations();

		return Arrays.copyOf(
			configurations, configurations.length,
			DXPExtractorConfiguration[].class);
	}

	@Override
	public String getState(String dataSourceJSON) {
		JSONObject dataSourceJSONObject = new JSONObject(dataSourceJSON);

		JSONObject credentialsJSONObject = dataSourceJSONObject.optJSONObject(
			"credentials");

		if (credentialsJSONObject == null) {
			return "CREDENTIALS_INVALID";
		}

		String type = credentialsJSONObject.getString("type");

		if (type.equals("Dummy Authentication")) {
			return "DUMMY_CREDENTIALS";
		}

		if (type.equals("Token Authentication")) {
			return "CREDENTIALS_VALID";
		}

		if (!type.equals("Basic Authentication") &&
			!type.equals("OAuth 1 Authentication") &&
			!type.equals("OAuth 2 Authentication")) {

			_dxpOAuth2Client.removeAccessToken(
				credentialsJSONObject.optString("oAuthClientId", null));

			return "CREDENTIALS_INVALID";
		}

		String url = dataSourceJSONObject.getString("url");

		ResponseEntity<String> responseEntity = null;

		try {
			responseEntity = _exchangeResponseEntity(
				url, "/api/jsonws/portal/get-build-number", HttpMethod.GET,
				null, credentialsJSONObject);
		}
		catch (Exception e) {
			_dxpOAuth2Client.removeAccessToken(
				credentialsJSONObject.optString("oAuthClientId", null));

			return "CREDENTIALS_INVALID";
		}

		if (responseEntity.getStatusCode() == HttpStatus.FORBIDDEN) {
			_dxpOAuth2Client.removeAccessToken(
				credentialsJSONObject.optString("oAuthClientId", null));

			return "CREDENTIALS_INVALID";
		}
		else if (responseEntity.getStatusCode() != HttpStatus.OK) {
			_dxpOAuth2Client.removeAccessToken(
				credentialsJSONObject.optString("oAuthClientId", null));

			return "UNDEFINED_ERROR";
		}

		String response = responseEntity.getBody();

		if (!response.startsWith("7")) {
			_dxpOAuth2Client.removeAccessToken(
				credentialsJSONObject.optString("oAuthClientId", null));

			return "LIFERAY_VERSION_INVALID";
		}

		return "CREDENTIALS_VALID";
	}

	@Override
	public boolean validate(String json) {
		JSONObject configurationsJSONObject = new JSONObject(json);

		JSONObject credentialsJSONObject =
			configurationsJSONObject.optJSONObject("credentials");

		if (credentialsJSONObject == null) {
			return false;
		}

		String type = credentialsJSONObject.getString("type");

		if (type.equals("Dummy Authentication") ||
			type.equals("Token Authentication")) {

			return true;
		}

		if (!type.equals("Basic Authentication") &&
			!type.equals("OAuth 1 Authentication") &&
			!type.equals("OAuth 2 Authentication")) {

			return false;
		}

		ResponseEntity<String> responseEntity = null;

		try {
			responseEntity = _exchangeResponseEntity(
				configurationsJSONObject.getString("url"),
				"/api/jsonws/portal/get-build-number", HttpMethod.GET, null,
				credentialsJSONObject);
		}
		catch (Exception e) {
			return false;
		}

		if ((responseEntity == null) ||
			(responseEntity.getStatusCode() != HttpStatus.OK)) {

			return false;
		}

		String response = responseEntity.getBody();

		if (!response.startsWith("7")) {
			return false;
		}

		return true;
	}

	@Override
	protected FileConfiguration buildFileConfiguration() {
		return null;
	}

	@Override
	protected RuntimeConfiguration buildRuntimeConfiguration() {
		return new DXPExtractorRuntimeConfigurationImpl();
	}

	@Override
	protected ConfigurableBot getConfigurableBot() {
		return _dxpExtractorConfigurableBot;
	}

	@Override
	protected String getProviderType() {
		return "LIFERAY";
	}

	@Override
	protected QueryBuilder getQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("provider.type", getProviderType())
		).mustNot(
			QueryBuilders.termQuery("credentials.type", "Token Authentication")
		);
	}

	@Override
	protected void onBeforeUpdate(
		RuntimeConfiguration runtimeConfiguration,
		RuntimeConfiguration existingRuntimeConfiguration) {

		if (existingRuntimeConfiguration == null) {
			return;
		}

		DXPExtractorRuntimeConfigurationImpl
			existingDXPExtractorRuntimeConfigurationImpl =
				(DXPExtractorRuntimeConfigurationImpl)
					existingRuntimeConfiguration;

		if (!Objects.equals(
				existingDXPExtractorRuntimeConfigurationImpl.
					getDXPAuthenticationType(),
				"OAuth 2 Authentication")) {

			return;
		}

		DXPExtractorRuntimeConfigurationImpl
			dxpExtractorRuntimeConfigurationImpl =
				(DXPExtractorRuntimeConfigurationImpl)runtimeConfiguration;

		if (!Objects.equals(
				dxpExtractorRuntimeConfigurationImpl.getDXPOAuth2ClientId(),
				existingDXPExtractorRuntimeConfigurationImpl.
					getDXPOAuth2ClientId()) ||
			!Objects.equals(
				dxpExtractorRuntimeConfigurationImpl.getDXPOAuth2ClientSecret(),
				existingDXPExtractorRuntimeConfigurationImpl.
					getDXPOAuth2ClientSecret()) ||
			!Objects.equals(
				dxpExtractorRuntimeConfigurationImpl.getDXPOAuth2RefreshToken(),
				existingDXPExtractorRuntimeConfigurationImpl.
					getDXPOAuth2RefreshToken())) {

			_dxpOAuth2Client.removeAccessToken(
				existingDXPExtractorRuntimeConfigurationImpl.
					getDXPOAuth2ClientId());
		}
	}

	@Override
	protected void setRuntimeConfigurationAttributes(
			JSONObject configurationsJSONObject,
			RuntimeConfiguration runtimeConfiguration)
		throws Exception {

		DXPExtractorRuntimeConfigurationImpl
			dxpExtractorRuntimeConfigurationImpl =
				(DXPExtractorRuntimeConfigurationImpl)runtimeConfiguration;

		JSONObject analyticsConfigurationJSONObject =
			_getAnalyticsConfigurationJSONObject(configurationsJSONObject);

		if (analyticsConfigurationJSONObject != null) {
			dxpExtractorRuntimeConfigurationImpl.
				setAnalyticsConfigurationJSONObject(
					analyticsConfigurationJSONObject);
		}

		JSONObject contactsConfigurationJSONObject =
			_getContactsConfigurationJSONObject(configurationsJSONObject);

		if (contactsConfigurationJSONObject != null) {
			dxpExtractorRuntimeConfigurationImpl.
				setContactsConfigurationJSONObject(
					contactsConfigurationJSONObject);
		}

		dxpExtractorRuntimeConfigurationImpl.setDXPURL(
			configurationsJSONObject.getString("url"));

		JSONObject credentialsJSONObject =
			configurationsJSONObject.getJSONObject("credentials");

		String type = credentialsJSONObject.getString("type");

		dxpExtractorRuntimeConfigurationImpl.setDXPAuthenticationType(type);

		if (type.equals("Basic Authentication")) {
			dxpExtractorRuntimeConfigurationImpl.setDXPLogin(
				credentialsJSONObject.getString("login"));
			dxpExtractorRuntimeConfigurationImpl.setDXPPassword(
				credentialsJSONObject.getString("password"));
		}
		else if (type.equals("OAuth 1 Authentication")) {
			dxpExtractorRuntimeConfigurationImpl.setDXPOAuth1AccessSecret(
				credentialsJSONObject.getString("oAuthAccessSecret"));
			dxpExtractorRuntimeConfigurationImpl.setDXPOAuth1AccessToken(
				credentialsJSONObject.getString("oAuthAccessToken"));
			dxpExtractorRuntimeConfigurationImpl.setDXPOAuth1ConsumerKey(
				credentialsJSONObject.getString("oAuthConsumerKey"));
			dxpExtractorRuntimeConfigurationImpl.setDXPOAuth1ConsumerSecret(
				credentialsJSONObject.getString("oAuthConsumerSecret"));
		}
		else if (type.equals("OAuth 2 Authentication")) {
			dxpExtractorRuntimeConfigurationImpl.setDXPOAuth2ClientId(
				credentialsJSONObject.getString("oAuthClientId"));
			dxpExtractorRuntimeConfigurationImpl.setDXPOAuth2ClientSecret(
				credentialsJSONObject.getString("oAuthClientSecret"));
			dxpExtractorRuntimeConfigurationImpl.setDXPOAuth2RefreshToken(
				credentialsJSONObject.getString("oAuthRefreshToken"));
		}
	}

	private ResponseEntity<String> _exchangeResponseEntity(
		String url, String path, HttpMethod httpMethod, Object body,
		JSONObject credentialsJSONObject) {

		String type = credentialsJSONObject.getString("type");

		if (type.equals("Basic Authentication")) {
			return _http.exchangeResponseEntity(
				url, path, httpMethod, body,
				credentialsJSONObject.getString("login"),
				credentialsJSONObject.getString("password"));
		}
		else if (type.equals("OAuth 1 Authentication")) {
			return _http.exchangeResponseEntity(
				url, path, httpMethod, body,
				credentialsJSONObject.getString("oAuthAccessSecret"),
				credentialsJSONObject.getString("oAuthAccessToken"),
				credentialsJSONObject.getString("oAuthConsumerKey"),
				credentialsJSONObject.getString("oAuthConsumerSecret"));
		}
		else if (type.equals("OAuth 2 Authentication")) {
			try {
				return _http.exchangeResponseEntity(
					url, path, httpMethod, body,
					_dxpOAuth2Client.getAccessToken(
						url, credentialsJSONObject.getString("oAuthClientId"),
						credentialsJSONObject.getString("oAuthClientSecret"),
						credentialsJSONObject.getString("oAuthRefreshToken")));
			}
			catch (HttpClientErrorException hcee) {
				return new ResponseEntity<>(hcee.getStatusCode());
			}
		}
		else if (type.equals("Token Authentication")) {
			HttpHeaders httpHeaders = new HttpHeaders() {
				{
					set(
						"Liferay-Analytics-Cloud-Security-Timestamp",
						String.valueOf(System.currentTimeMillis()));
				}
			};

			httpHeaders.set(
				"Liferay-Analytics-Cloud-Security-Signature",
				_encryptor.getSignature(
					httpHeaders, path,
					_encryptor.decrypt(
						url, credentialsJSONObject.getString("privateKey"))));

			return _http.exchangeResponseEntity(
				url, path, httpMethod, body, httpHeaders);
		}

		return null;
	}

	private JSONObject _getAnalyticsConfigurationJSONObject(
		JSONObject configurationsJSONObject) {

		JSONObject analyticsConfigurationJSONObject =
			configurationsJSONObject.optJSONObject("analyticsConfiguration");

		if (analyticsConfigurationJSONObject != null) {
			return analyticsConfigurationJSONObject;
		}

		return JSONUtil.put(
			"enableAllContacts", false
		).put(
			"sites", new JSONArray()
		);
	}

	private JSONObject _getContactsConfigurationJSONObject(
		JSONObject configurationsJSONObject) {

		JSONObject contactsConfigurationJSONObject =
			configurationsJSONObject.optJSONObject("contactsConfiguration");

		if (contactsConfigurationJSONObject != null) {
			return contactsConfigurationJSONObject;
		}

		return JSONUtil.put(
			"enableAllContacts", false
		).put(
			"organizations", new JSONArray()
		).put(
			"userGroups", new JSONArray()
		);
	}

	@Autowired
	private DXPExtractorConfigurableBot _dxpExtractorConfigurableBot;

	@Autowired
	private DXPOAuth2Client _dxpOAuth2Client;

	@Autowired
	private Encryptor _encryptor;

	@Autowired
	private Http _http;

	@Autowired
	private PortalDog _portalDog;

}