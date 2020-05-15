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

package com.liferay.osb.asah.dxp.extractor.configuration;

import com.liferay.osb.asah.common.configuration.Configuration;

import java.util.Set;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
public interface DXPExtractorConfiguration extends Configuration {

	public String getDXPAuthenticationType();

	public String getDXPLogin();

	public String getDXPOAuth1AccessSecret();

	public String getDXPOAuth1AccessToken();

	public String getDXPOAuth1ConsumerKey();

	public String getDXPOAuth1ConsumerSecret();

	public String getDXPOAuth2ClientId();

	public String getDXPOAuth2ClientSecret();

	public String getDXPOAuth2RefreshToken();

	public String getDXPPassword();

	public String getDXPPrivateKey();

	public String getDXPURL();

	public Set<Long> getSyncGroupIds();

	public Set<Long> getSyncOrganizationIds();

	public Set<Long> getSyncUserGroupIds();

	public boolean isSyncAllUsers();

}