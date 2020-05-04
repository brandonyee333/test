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

package com.liferay.osb.asah.salesforce.extractor.configuration.impl;

import com.liferay.osb.asah.common.bot.ConfigurableBot;
import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.FileConfiguration;
import com.liferay.osb.asah.common.configuration.RuntimeConfiguration;
import com.liferay.osb.asah.common.configuration.impl.BaseConfigurationManagerImpl;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.salesforce.extractor.bot.SalesforceExtractorConfigurableBot;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.oauth2.OAuth2Response;
import com.liferay.osb.asah.salesforce.extractor.oauth2.SalesforceOAuth2Client;
import com.liferay.petra.salesforce.client.partner.SalesforcePartnerClient;
import com.liferay.petra.salesforce.client.partner.SalesforcePartnerClientImpl;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
@Component
public class SalesforceExtractorConfigurationManagerImpl
	extends BaseConfigurationManagerImpl {

	@Override
	public JSONObject buildConfigurationsJSONObject(
		JSONObject dataSourceJSONObject) {

		JSONObject configurationsJSONObject =
			super.buildConfigurationsJSONObject(dataSourceJSONObject);

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		return configurationsJSONObject.put(
			"accountsConfiguration",
			providerJSONObject.optJSONObject("accountsConfiguration")
		).put(
			"contactsConfiguration",
			providerJSONObject.optJSONObject("contactsConfiguration")
		);
	}

	@Override
	public SalesforceExtractorConfiguration getConfiguration(
		String dataSourceId) {

		SalesforceExtractorConfiguration salesforceExtractorConfiguration =
			(SalesforceExtractorConfiguration)super.getConfiguration(
				dataSourceId);

		if (salesforceExtractorConfiguration instanceof
				SalesforceExtractorRuntimeConfigurationImpl) {

			try {
				_salesforceOAuth2Client.refreshOAuthToken(
					(SalesforceExtractorRuntimeConfigurationImpl)
						salesforceExtractorConfiguration);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to refresh OAuth token");
				}

				return null;
			}
		}

		return salesforceExtractorConfiguration;
	}

	@Override
	public SalesforceExtractorConfiguration[] getConfigurations() {
		Configuration[] configurations = super.getConfigurations();

		return Arrays.copyOf(
			configurations, configurations.length,
			SalesforceExtractorConfiguration[].class);
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

		if (!type.equals("OAuth 2 Authentication")) {
			return "CREDENTIALS_INVALID";
		}

		JSONObject jsonObject = JSONUtil.put(
			"credentials", credentialsJSONObject
		).put(
			"url", dataSourceJSONObject.getString("url")
		);

		if (!validate(jsonObject.toString())) {
			return "CREDENTIALS_INVALID";
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

		String responseJSON = _salesforceOAuth2Client.post(
			credentialsJSONObject.getString("oAuthClientId"),
			credentialsJSONObject.getString("oAuthClientSecret"),
			credentialsJSONObject.getString("oAuthRefreshToken"),
			configurationsJSONObject.getString("url"));

		if (StringUtils.isEmpty(responseJSON)) {
			return false;
		}

		JSONObject responseJSONObject = new JSONObject(responseJSON);

		if (!responseJSONObject.has("id")) {
			return false;
		}

		SalesforcePartnerClient salesforcePartnerClient =
			new SalesforcePartnerClientImpl();

		OAuth2Response response = _salesforceOAuth2Client.toResponse(
			responseJSONObject);

		salesforcePartnerClient.setAuthEndpoint(response.getAuthEndpoint());
		salesforcePartnerClient.setServiceEndpoint(
			response.getServiceEndpoint());

		salesforcePartnerClient.setSessionId(
			responseJSONObject.getString("access_token"));

		try {
			salesforcePartnerClient.describeGlobal(1);
		}
		catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	protected FileConfiguration buildFileConfiguration() {
		return new SalesforceExtractorFileConfigurationImpl();
	}

	@Override
	protected RuntimeConfiguration buildRuntimeConfiguration() {
		return new SalesforceExtractorRuntimeConfigurationImpl();
	}

	@Override
	protected ConfigurableBot getConfigurableBot() {
		return _salesforceExtractorConfigurableBot;
	}

	@Override
	protected String getProviderType() {
		return "SALESFORCE";
	}

	@Override
	protected void setRuntimeConfigurationAttributes(
		JSONObject configurationsJSONObject,
		RuntimeConfiguration runtimeConfiguration) {

		SalesforceExtractorRuntimeConfigurationImpl
			salesforceExtractorRuntimeConfigurationImpl =
				(SalesforceExtractorRuntimeConfigurationImpl)
					runtimeConfiguration;

		JSONObject accountsConfigurationJSONObject =
			configurationsJSONObject.optJSONObject("accountsConfiguration");

		if (accountsConfigurationJSONObject != null) {
			salesforceExtractorRuntimeConfigurationImpl.
				setAccountsConfigurationJSONObject(
					accountsConfigurationJSONObject);
		}

		JSONObject contactsConfigurationJSONObject =
			configurationsJSONObject.optJSONObject("contactsConfiguration");

		if (contactsConfigurationJSONObject != null) {
			salesforceExtractorRuntimeConfigurationImpl.
				setContactsConfigurationJSONObject(
					contactsConfigurationJSONObject);
		}

		salesforceExtractorRuntimeConfigurationImpl.setSalesforceAuthEndpoint(
			configurationsJSONObject.getString("url") +
				_salesforceOAuth2Client.getPath());
		salesforceExtractorRuntimeConfigurationImpl.setSalesforceURL(
			configurationsJSONObject.getString("url"));

		JSONObject credentialsJSONObject =
			configurationsJSONObject.getJSONObject("credentials");

		String type = credentialsJSONObject.getString("type");

		if (type.equals("Basic Authentication")) {
			salesforceExtractorRuntimeConfigurationImpl.setSalesforcePassword(
				credentialsJSONObject.getString("password"));
			salesforceExtractorRuntimeConfigurationImpl.setSalesforceUserName(
				credentialsJSONObject.getString("login"));
		}
		else if (type.equals("OAuth 2 Authentication")) {
			salesforceExtractorRuntimeConfigurationImpl.
				setSalesforceOAuthClientId(
					credentialsJSONObject.getString("oAuthClientId"));
			salesforceExtractorRuntimeConfigurationImpl.
				setSalesforceOAuthClientSecret(
					credentialsJSONObject.getString("oAuthClientSecret"));
			salesforceExtractorRuntimeConfigurationImpl.
				setSalesforceOAuthRefreshToken(
					credentialsJSONObject.getString("oAuthRefreshToken"));

			try {
				_salesforceOAuth2Client.refreshOAuthToken(
					salesforceExtractorRuntimeConfigurationImpl);
			}
			catch (Exception e) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to refresh OAuth token");
				}
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceExtractorConfigurationManagerImpl.class);

	@Autowired
	private SalesforceExtractorConfigurableBot
		_salesforceExtractorConfigurableBot;

	@Autowired
	private SalesforceOAuth2Client _salesforceOAuth2Client;

}