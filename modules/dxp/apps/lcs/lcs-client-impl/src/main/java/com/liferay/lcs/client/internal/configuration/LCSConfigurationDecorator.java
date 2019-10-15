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

package com.liferay.lcs.client.internal.configuration;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Ivica Cardic
 */
public class LCSConfigurationDecorator implements LCSConfiguration {

	public LCSConfigurationDecorator(LCSConfiguration lcsConfiguration) {
		_lcsConfiguration = lcsConfiguration;
	}

	@Override
	public String cacheMetricsHibernateObjectName() {
		return _lcsConfiguration.cacheMetricsHibernateObjectName();
	}

	@Override
	public String cacheMetricsMultiVMObjectName() {
		return _lcsConfiguration.cacheMetricsMultiVMObjectName();
	}

	@Override
	public String cacheMetricsSingleVMObjectName() {
		return _lcsConfiguration.cacheMetricsSingleVMObjectName();
	}

	@Override
	public int commandScheduleDefaultInterval() {
		return _lcsConfiguration.commandScheduleDefaultInterval();
	}

	@Override
	public String digitalSignatureKeyName() {
		return _lcsConfiguration.digitalSignatureKeyName();
	}

	@Override
	public String digitalSignatureKeyStorePath() {
		return _lcsConfiguration.digitalSignatureKeyStorePath();
	}

	@Override
	public String digitalSignatureKeyStoreType() {
		return _lcsConfiguration.digitalSignatureKeyStoreType();
	}

	@Override
	public String digitalSignatureSigningAlgorithm() {
		return _lcsConfiguration.digitalSignatureSigningAlgorithm();
	}

	@Override
	public String keyGeneratorKeyAlias() {
		return _lcsConfiguration.keyGeneratorKeyAlias();
	}

	@Override
	public String keyGeneratorKeyStorePassword() {
		return _lcsConfiguration.keyGeneratorKeyStorePassword();
	}

	@Override
	public String keyGeneratorKeyStorePath() {
		return _lcsConfiguration.keyGeneratorKeyStorePath();
	}

	@Override
	public String keyGeneratorKeyStoreType() {
		return _lcsConfiguration.keyGeneratorKeyStoreType();
	}

	@Override
	public String lcsPlatformPortalHostName() {
		return _getEnvironmentValue(
			"OSB_LCS_PORTLET_HOST_NAME",
			_lcsConfiguration.lcsPlatformPortalHostName());
	}

	@Override
	public int lcsPlatformPortalHostPort() {
		return _getEnvironmentValue(
			"OSB_LCS_PORTLET_HOST_PORT",
			_lcsConfiguration.lcsPlatformPortalHostPort());
	}

	@Override
	public String lcsPlatformPortalKeyStorePath() {
		return _lcsConfiguration.lcsPlatformPortalKeyStorePath();
	}

	@Override
	public String lcsPlatformPortalKeyStoreType() {
		return _lcsConfiguration.lcsPlatformPortalKeyStoreType();
	}

	@Override
	public String lcsPlatformPortalLayoutLCSClusterEntry() {
		return _lcsConfiguration.lcsPlatformPortalLayoutLCSClusterEntry();
	}

	@Override
	public String lcsPlatformPortalLayoutLCSClusterNode() {
		return _lcsConfiguration.lcsPlatformPortalLayoutLCSClusterNode();
	}

	@Override
	public String lcsPlatformPortalLayoutLCSProject() {
		return _lcsConfiguration.lcsPlatformPortalLayoutLCSProject();
	}

	@Override
	public String lcsPlatformPortalOauthAccessTokenUri() {
		return _lcsConfiguration.lcsPlatformPortalOauthAccessTokenUri();
	}

	@Override
	public String lcsPlatformPortalOauthAuthorizeUri() {
		return _lcsConfiguration.lcsPlatformPortalOauthAuthorizeUri();
	}

	@Override
	public String lcsPlatformPortalOauthConsumerKey() {
		return _getEnvironmentValue(
			"OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY",
			_lcsConfiguration.lcsPlatformPortalOauthConsumerKey());
	}

	@Override
	public String lcsPlatformPortalOauthConsumerSecret() {
		return _getEnvironmentValue(
			"OSB_LCS_PORTLET_OAUTH_CONSUMER_SECRET",
			_lcsConfiguration.lcsPlatformPortalOauthConsumerSecret());
	}

	@Override
	public String lcsPlatformPortalOauthRequestTokenUri() {
		return _lcsConfiguration.lcsPlatformPortalOauthRequestTokenUri();
	}

	@Override
	public String lcsPlatformPortalProtocol() {
		return _getEnvironmentValue(
			"OSB_LCS_PORTLET_PROTOCOL",
			_lcsConfiguration.lcsPlatformPortalProtocol());
	}

	@Override
	public String osbLCSGatewayWebSecureApiToken() {
		return _lcsConfiguration.osbLCSGatewayWebSecureApiToken();
	}

	@Override
	public String platformLcsGatewayHostName() {
		return _getEnvironmentValue(
			"OSB_LCS_GATEWAY_WEB_HOST_NAME",
			_lcsConfiguration.platformLcsGatewayHostName());
	}

	@Override
	public int platformLcsGatewayHostPort() {
		return _getEnvironmentValue(
			"OSB_LCS_GATEWAY_WEB_HOST_PORT",
			_lcsConfiguration.platformLcsGatewayHostPort());
	}

	@Override
	public String platformLcsGatewayKeyStorePath() {
		return _lcsConfiguration.platformLcsGatewayKeyStorePath();
	}

	@Override
	public String platformLcsGatewayKeyStoreType() {
		return _lcsConfiguration.platformLcsGatewayKeyStoreType();
	}

	@Override
	public String platformLcsGatewayWebProtocol() {
		return _getEnvironmentValue(
			"OSB_LCS_GATEWAY_WEB_PROTOCOL",
			_lcsConfiguration.platformLcsGatewayWebProtocol());
	}

	@Override
	public String proxyAuthType() {
		return _getSystemValue(
			"http.proxyAuthType", _lcsConfiguration.proxyAuthType());
	}

	@Override
	public String proxyDomain() {
		return _getSystemValue(
			"http.proxyDomain", _lcsConfiguration.proxyDomain());
	}

	@Override
	public String proxyHostLogin() {
		return _getSystemValue(
			"http.proxyUser", _lcsConfiguration.proxyHostLogin());
	}

	@Override
	public String proxyHostName() {
		return _getSystemValue(
			"http.proxyHost", _lcsConfiguration.proxyHostName());
	}

	@Override
	public String proxyHostPassword() {
		return _getSystemValue(
			"http.proxyPassword", _lcsConfiguration.proxyHostPassword());
	}

	@Override
	public int proxyHostPort() {
		return _getSystemValue(
			"http.proxyPort", _lcsConfiguration.proxyHostPort());
	}

	@Override
	public String proxyWorkstation() {
		return _getSystemValue(
			"http.proxyWorkstation", _lcsConfiguration.proxyWorkstation());
	}

	@Override
	public int scheduledTaskPageSize() {
		return _lcsConfiguration.scheduledTaskPageSize();
	}

	@Override
	public int scheduledTaskPauseInterval() {
		return _lcsConfiguration.scheduledTaskPauseInterval();
	}

	private int _getEnvironmentValue(String name, int defaultValue) {
		String value = System.getenv(name);

		if (Validator.isNull(value)) {
			return defaultValue;
		}

		return GetterUtil.getInteger(value);
	}

	private String _getEnvironmentValue(String name, String defaultValue) {
		String value = System.getenv(name);

		if (Validator.isNull(value)) {
			return defaultValue;
		}

		return value;
	}

	private int _getSystemValue(String name, int defaultValue) {
		String value = System.getProperty(name);

		if (Validator.isNull(value)) {
			return defaultValue;
		}

		return GetterUtil.getInteger(value);
	}

	private String _getSystemValue(String name, String defaultValue) {
		String value = System.getProperty(name);

		if (Validator.isNull(value)) {
			return defaultValue;
		}

		return value;
	}

	private final LCSConfiguration _lcsConfiguration;

}