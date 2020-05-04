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

import com.liferay.osb.asah.common.configuration.impl.BaseRuntimeConfigurationImpl;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;

import java.util.Arrays;
import java.util.Objects;

import org.json.JSONObject;

/**
 * @author Rachael Koestartyo
 */
public class SalesforceExtractorRuntimeConfigurationImpl
	extends BaseRuntimeConfigurationImpl
	implements SalesforceExtractorConfiguration {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SalesforceExtractorRuntimeConfigurationImpl)) {
			return false;
		}

		SalesforceExtractorRuntimeConfigurationImpl
			salesforceExtractorRuntimeConfigurationImpl =
				(SalesforceExtractorRuntimeConfigurationImpl)obj;

		if (super.equals(salesforceExtractorRuntimeConfigurationImpl) &&
			Objects.equals(
				_salesforceAuthEndpoint,
				salesforceExtractorRuntimeConfigurationImpl.
					_salesforceAuthEndpoint) &&
			Objects.equals(
				_salesforceOAuthAccessToken,
				salesforceExtractorRuntimeConfigurationImpl.
					_salesforceOAuthAccessToken) &&
			Objects.equals(
				_salesforceOAuthClientId,
				salesforceExtractorRuntimeConfigurationImpl.
					_salesforceOAuthClientId) &&
			Objects.equals(
				_salesforceOAuthClientSecret,
				salesforceExtractorRuntimeConfigurationImpl.
					_salesforceOAuthClientSecret) &&
			Objects.equals(
				_salesforceOAuthRefreshToken,
				salesforceExtractorRuntimeConfigurationImpl.
					_salesforceOAuthRefreshToken) &&
			Objects.equals(
				_salesforcePassword,
				salesforceExtractorRuntimeConfigurationImpl.
					_salesforcePassword) &&
			Objects.equals(
				_salesforceServiceEndpoint,
				salesforceExtractorRuntimeConfigurationImpl.
					_salesforceServiceEndpoint) &&
			Objects.equals(
				_salesforceURL,
				salesforceExtractorRuntimeConfigurationImpl._salesforceURL) &&
			Objects.equals(
				_salesforceUserName,
				salesforceExtractorRuntimeConfigurationImpl.
					_salesforceUserName) &&
			Arrays.equals(
				_tableNames,
				salesforceExtractorRuntimeConfigurationImpl.getTableNames())) {

			return true;
		}

		return false;
	}

	@Override
	public String getSalesforceAuthEndpoint() {
		return _salesforceAuthEndpoint;
	}

	@Override
	public String getSalesforceOAuthAccessToken() {
		return _salesforceOAuthAccessToken;
	}

	@Override
	public String getSalesforceOAuthClientId() {
		return _salesforceOAuthClientId;
	}

	@Override
	public String getSalesforceOAuthClientSecret() {
		return _salesforceOAuthClientSecret;
	}

	@Override
	public String getSalesforceOAuthRefreshToken() {
		return _salesforceOAuthRefreshToken;
	}

	@Override
	public String getSalesforcePassword() {
		return _salesforcePassword;
	}

	@Override
	public String getSalesforceServiceEndpoint() {
		return _salesforceServiceEndpoint;
	}

	@Override
	public String getSalesforceURL() {
		return _salesforceURL;
	}

	@Override
	public String getSalesforceUserName() {
		return _salesforceUserName;
	}

	@Override
	public String[] getTableNames() {
		return Arrays.copyOf(_tableNames, _tableNames.length);
	}

	@Override
	public int hashCode() {
		int hash = Objects.hash(
			_salesforceAuthEndpoint, _salesforceOAuthAccessToken,
			_salesforceOAuthClientId, _salesforceOAuthClientSecret,
			_salesforceOAuthRefreshToken, _salesforcePassword,
			_salesforceServiceEndpoint, _salesforceURL, _salesforceUserName);

		return super.hashCode() ^ hash ^ Arrays.hashCode(_tableNames);
	}

	public void setAccountsConfigurationJSONObject(
		JSONObject accountsConfigurationJSONObject) {

		if (accountsConfigurationJSONObject.getBoolean("enableAllAccounts")) {
			_addTableName("Account");
		}
	}

	public void setContactsConfigurationJSONObject(
		JSONObject contactsConfigurationJSONObject) {

		if (contactsConfigurationJSONObject.getBoolean("enableAllContacts")) {
			_addTableName("Contact");
		}

		if (contactsConfigurationJSONObject.getBoolean("enableAllLeads")) {
			_addTableName("Lead");
		}
	}

	public void setSalesforceAuthEndpoint(String salesforceAuthEndpoint) {
		_salesforceAuthEndpoint = salesforceAuthEndpoint;
	}

	public void setSalesforceOAuthAccessToken(
		String salesforceOAuthAccessToken) {

		_salesforceOAuthAccessToken = salesforceOAuthAccessToken;
	}

	public void setSalesforceOAuthClientId(String salesforceOAuthClientId) {
		_salesforceOAuthClientId = salesforceOAuthClientId;
	}

	public void setSalesforceOAuthClientSecret(
		String salesforceOAuthClientSecret) {

		_salesforceOAuthClientSecret = salesforceOAuthClientSecret;
	}

	public void setSalesforceOAuthRefreshToken(
		String salesforceOAuthRefreshToken) {

		_salesforceOAuthRefreshToken = salesforceOAuthRefreshToken;
	}

	public void setSalesforcePassword(String salesforcePassword) {
		_salesforcePassword = salesforcePassword;
	}

	public void setSalesforceServiceEndpoint(String salesforceServiceEndpoint) {
		_salesforceServiceEndpoint = salesforceServiceEndpoint;
	}

	public void setSalesforceURL(String salesforceURL) {
		_salesforceURL = salesforceURL;
	}

	public void setSalesforceUserName(String salesforceUserName) {
		_salesforceUserName = salesforceUserName;
	}

	private void _addTableName(String tableName) {
		_tableNames = Arrays.copyOf(_tableNames, _tableNames.length + 1);

		_tableNames[_tableNames.length - 1] = tableName;
	}

	private String _salesforceAuthEndpoint;
	private String _salesforceOAuthAccessToken;
	private String _salesforceOAuthClientId;
	private String _salesforceOAuthClientSecret;
	private String _salesforceOAuthRefreshToken;
	private String _salesforcePassword;
	private String _salesforceServiceEndpoint;
	private String _salesforceURL;
	private String _salesforceUserName;
	private String[] _tableNames = new String[0];

}