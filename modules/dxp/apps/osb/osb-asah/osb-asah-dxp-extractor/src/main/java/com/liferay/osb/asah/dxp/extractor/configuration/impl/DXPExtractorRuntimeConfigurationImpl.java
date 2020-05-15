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

import com.liferay.osb.asah.common.configuration.impl.BaseRuntimeConfigurationImpl;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;
import com.liferay.osb.asah.dxp.extractor.dog.OrganizationDog;
import com.liferay.osb.asah.dxp.extractor.dog.UserDog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
public class DXPExtractorRuntimeConfigurationImpl
	extends BaseRuntimeConfigurationImpl implements DXPExtractorConfiguration {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DXPExtractorRuntimeConfigurationImpl)) {
			return false;
		}

		DXPExtractorRuntimeConfigurationImpl
			dxpExtractorRuntimeConfigurationImpl =
				(DXPExtractorRuntimeConfigurationImpl)obj;

		if (super.equals(dxpExtractorRuntimeConfigurationImpl) &&
			Objects.equals(
				_dxpAuthenticationType,
				dxpExtractorRuntimeConfigurationImpl._dxpAuthenticationType) &&
			Objects.equals(
				_dxpLogin, dxpExtractorRuntimeConfigurationImpl._dxpLogin) &&
			Objects.equals(
				_dxpOAuth1AccessSecret,
				dxpExtractorRuntimeConfigurationImpl._dxpOAuth1AccessSecret) &&
			Objects.equals(
				_dxpOAuth1AccessToken,
				dxpExtractorRuntimeConfigurationImpl._dxpOAuth1AccessToken) &&
			Objects.equals(
				_dxpOAuth1ConsumerKey,
				dxpExtractorRuntimeConfigurationImpl._dxpOAuth1ConsumerKey) &&
			Objects.equals(
				_dxpOAuth1ConsumerSecret,
				dxpExtractorRuntimeConfigurationImpl.
					_dxpOAuth1ConsumerSecret) &&
			Objects.equals(
				_dxpOAuth2ClientId,
				dxpExtractorRuntimeConfigurationImpl._dxpOAuth2ClientId) &&
			Objects.equals(
				_dxpOAuth2ClientSecret,
				dxpExtractorRuntimeConfigurationImpl._dxpOAuth2ClientSecret) &&
			Objects.equals(
				_dxpOAuth2RefreshToken,
				dxpExtractorRuntimeConfigurationImpl._dxpOAuth2RefreshToken) &&
			Objects.equals(
				_dxpPassword,
				dxpExtractorRuntimeConfigurationImpl._dxpPassword) &&
			Objects.equals(
				_dxpPrivateKey,
				dxpExtractorRuntimeConfigurationImpl._dxpPrivateKey) &&
			Objects.equals(
				_dxpURL, dxpExtractorRuntimeConfigurationImpl._dxpURL) &&
			Objects.equals(
				_syncAllUsers,
				dxpExtractorRuntimeConfigurationImpl._syncAllUsers) &&
			Objects.equals(
				_syncGroupIds,
				dxpExtractorRuntimeConfigurationImpl._syncGroupIds) &&
			Objects.equals(
				_syncOrganizationIds,
				dxpExtractorRuntimeConfigurationImpl._syncOrganizationIds) &&
			Objects.equals(
				_syncUserGroupIds,
				dxpExtractorRuntimeConfigurationImpl._syncUserGroupIds)) {

			return true;
		}

		return false;
	}

	@Override
	public String getDXPAuthenticationType() {
		return _dxpAuthenticationType;
	}

	@Override
	public String getDXPLogin() {
		return _dxpLogin;
	}

	@Override
	public String getDXPOAuth1AccessSecret() {
		return _dxpOAuth1AccessSecret;
	}

	@Override
	public String getDXPOAuth1AccessToken() {
		return _dxpOAuth1AccessToken;
	}

	@Override
	public String getDXPOAuth1ConsumerKey() {
		return _dxpOAuth1ConsumerKey;
	}

	@Override
	public String getDXPOAuth1ConsumerSecret() {
		return _dxpOAuth1ConsumerSecret;
	}

	@Override
	public String getDXPOAuth2ClientId() {
		return _dxpOAuth2ClientId;
	}

	@Override
	public String getDXPOAuth2ClientSecret() {
		return _dxpOAuth2ClientSecret;
	}

	@Override
	public String getDXPOAuth2RefreshToken() {
		return _dxpOAuth2RefreshToken;
	}

	@Override
	public String getDXPPassword() {
		return _dxpPassword;
	}

	@Override
	public String getDXPPrivateKey() {
		return _dxpPrivateKey;
	}

	@Override
	public String getDXPURL() {
		return _dxpURL;
	}

	@Override
	public Set<Long> getSyncGroupIds() {
		if (_syncGroupIds == null) {
			return null;
		}

		return Collections.unmodifiableSet(_syncGroupIds);
	}

	@Override
	public Set<Long> getSyncOrganizationIds() {
		if (_syncOrganizationIds == null) {
			return null;
		}

		return Collections.unmodifiableSet(_syncOrganizationIds);
	}

	@Override
	public Set<Long> getSyncUserGroupIds() {
		if (_syncUserGroupIds == null) {
			return null;
		}

		return Collections.unmodifiableSet(_syncUserGroupIds);
	}

	@Override
	public int hashCode() {
		int hash = Objects.hash(
			_dxpAuthenticationType, _dxpLogin, _dxpOAuth1AccessSecret,
			_dxpOAuth1AccessToken, _dxpOAuth1ConsumerKey,
			_dxpOAuth1ConsumerSecret, _dxpOAuth2ClientId,
			_dxpOAuth2ClientSecret, _dxpOAuth2RefreshToken, _dxpPassword,
			_dxpPrivateKey, _dxpURL, _syncAllUsers, _syncGroupIds,
			_syncOrganizationIds, _syncUserGroupIds);

		return super.hashCode() ^ hash;
	}

	@Override
	public boolean isSyncAllUsers() {
		return _syncAllUsers;
	}

	public void setAnalyticsConfigurationJSONObject(
		JSONObject analyticsConfigurationJSONObject) {

		_syncGroupIds = JSONUtil.toLongSet(
			analyticsConfigurationJSONObject.getJSONArray("sites"), "id");
	}

	public void setContactsConfigurationJSONObject(
			JSONObject contactsConfigurationJSONObject)
		throws Exception {

		_syncAllUsers = contactsConfigurationJSONObject.getBoolean(
			"enableAllContacts");

		if (_syncAllUsers) {
			return;
		}

		JSONArray organizationsJSONArray =
			contactsConfigurationJSONObject.getJSONArray("organizations");

		Set<Long> organizationIds = new HashSet<>();

		for (int i = 0; i < organizationsJSONArray.length(); i++) {
			JSONObject organizationJSONObject =
				organizationsJSONArray.getJSONObject(i);

			long organizationId = organizationJSONObject.getLong("id");

			organizationIds.add(organizationId);

			if (organizationJSONObject.getBoolean("enableAllChildren")) {
				DXPExtractorConfiguration dxpExtractorConfiguration =
					_dxpExtractorConfigurationManagerImpl.getConfiguration(
						getDataSourceId());

				long companyId = _userDog.getCurrentUserCompanyId(
					dxpExtractorConfiguration);

				_organizationDog.getDescendantOrganizationIds(
					dxpExtractorConfiguration, organizationIds, companyId,
					organizationId);
			}
		}

		_syncOrganizationIds = organizationIds;

		_syncUserGroupIds = JSONUtil.toLongSet(
			contactsConfigurationJSONObject.getJSONArray("userGroups"), "id");
	}

	public void setDXPAuthenticationType(String dxpAuthenticationType) {
		_dxpAuthenticationType = dxpAuthenticationType;
	}

	public void setDXPLogin(String dxpLogin) {
		_dxpLogin = dxpLogin;
	}

	public void setDXPOAuth1AccessSecret(String dxpOAuth1AccessSecret) {
		_dxpOAuth1AccessSecret = dxpOAuth1AccessSecret;
	}

	public void setDXPOAuth1AccessToken(String dxpOAuth1AccessToken) {
		_dxpOAuth1AccessToken = dxpOAuth1AccessToken;
	}

	public void setDXPOAuth1ConsumerKey(String dxpOAuth1ConsumerKey) {
		_dxpOAuth1ConsumerKey = dxpOAuth1ConsumerKey;
	}

	public void setDXPOAuth1ConsumerSecret(String dxpOAuth1ConsumerSecret) {
		_dxpOAuth1ConsumerSecret = dxpOAuth1ConsumerSecret;
	}

	public void setDXPOAuth2ClientId(String dxpOAuth2ClientId) {
		_dxpOAuth2ClientId = dxpOAuth2ClientId;
	}

	public void setDXPOAuth2ClientSecret(String dxpOAuth2ClientSecret) {
		_dxpOAuth2ClientSecret = dxpOAuth2ClientSecret;
	}

	public void setDXPOAuth2RefreshToken(String dxpOAuth2RefreshToken) {
		_dxpOAuth2RefreshToken = dxpOAuth2RefreshToken;
	}

	public void setDXPPassword(String dxpPassword) {
		_dxpPassword = dxpPassword;
	}

	public void setDXPPrivateKey(String dxpPrivateKey) {
		_dxpPrivateKey = dxpPrivateKey;
	}

	public void setDXPURL(String dxpURL) {
		_dxpURL = dxpURL;
	}

	private String _dxpAuthenticationType;

	@Autowired
	private DXPExtractorConfigurationManagerImpl
		_dxpExtractorConfigurationManagerImpl;

	private String _dxpLogin;
	private String _dxpOAuth1AccessSecret;
	private String _dxpOAuth1AccessToken;
	private String _dxpOAuth1ConsumerKey;
	private String _dxpOAuth1ConsumerSecret;
	private String _dxpOAuth2ClientId;
	private String _dxpOAuth2ClientSecret;
	private String _dxpOAuth2RefreshToken;
	private String _dxpPassword;
	private String _dxpPrivateKey;
	private String _dxpURL;

	@Autowired
	private OrganizationDog _organizationDog;

	private boolean _syncAllUsers;
	private Set<Long> _syncGroupIds;
	private Set<Long> _syncOrganizationIds;
	private Set<Long> _syncUserGroupIds;

	@Autowired
	private UserDog _userDog;

}