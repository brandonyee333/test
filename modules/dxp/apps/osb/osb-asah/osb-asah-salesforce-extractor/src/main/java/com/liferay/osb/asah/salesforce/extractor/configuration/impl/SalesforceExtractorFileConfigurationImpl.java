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

import com.liferay.osb.asah.common.configuration.impl.BaseFileConfigurationImpl;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;

/**
 * @author Brian Wing Shun Chan
 */
public class SalesforceExtractorFileConfigurationImpl
	extends BaseFileConfigurationImpl
	implements SalesforceExtractorConfiguration {

	@Override
	public String getSalesforceAuthEndpoint() {
		return properties.getProperty("salesforce.auth.endpoint");
	}

	@Override
	public String getSalesforceOAuthAccessToken() {
		return properties.getProperty("salesforce.oauth.access.token");
	}

	@Override
	public String getSalesforceOAuthClientId() {
		return properties.getProperty("salesforce.oauth.client.id");
	}

	@Override
	public String getSalesforceOAuthClientSecret() {
		return properties.getProperty("salesforce.oauth.client.secret");
	}

	@Override
	public String getSalesforceOAuthRefreshToken() {
		return properties.getProperty("salesforce.oauth.refresh.token");
	}

	@Override
	public String getSalesforcePassword() {
		return properties.getProperty("salesforce.password");
	}

	@Override
	public String getSalesforceServiceEndpoint() {
		return properties.getProperty("salesforce.service.endpoint");
	}

	@Override
	public String getSalesforceURL() {
		return properties.getProperty("salesforce.url");
	}

	@Override
	public String getSalesforceUserName() {
		return properties.getProperty("salesforce.user.name");
	}

	@Override
	public String[] getTableNames() {
		return null;
	}

	@Override
	protected String getFileName() {
		return "osb-asah-salesforce-extractor";
	}

}