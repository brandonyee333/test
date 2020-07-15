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

package com.liferay.lcs.client.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Igor Beslic
 */
@ExtendedObjectClassDefinition(category = "lcs")
@Meta.OCD(
	id = "com.liferay.lcs.client.configuration.LCSConfiguration",
	localization = "content/Language", name = "lcs-configuration-name"
)
public interface LCSConfiguration {

	@Meta.AD(
		deflt = "86400", name = "commandScheduleDefaultInterval",
		required = false
	)
	public int commandScheduleDefaultInterval();

	@Meta.AD(
		deflt = "localhost", name = "digitalSignatureKeyName", required = false
	)
	public String digitalSignatureKeyName();

	@Meta.AD(
		deflt = "classpath:/keystore.jks",
		name = "digitalSignatureKeyStorePath", required = false
	)
	public String digitalSignatureKeyStorePath();

	@Meta.AD(
		deflt = "JKS", name = "digitalSignatureKeyStoreType", required = false
	)
	public String digitalSignatureKeyStoreType();

	@Meta.AD(
		deflt = "MD5withRSA", name = "digitalSignatureSigningAlgorithm",
		required = false
	)
	public String digitalSignatureSigningAlgorithm();

	@Meta.AD(
		deflt = "generator", name = "keyGeneratorKeyAlias", required = false
	)
	public String keyGeneratorKeyAlias();

	@Meta.AD(
		deflt = "123456", name = "keyGeneratorKeyStorePassword",
		required = false
	)
	public String keyGeneratorKeyStorePassword();

	@Meta.AD(
		deflt = "classpath:/keystore.jceks", name = "keyGeneratorKeyStorePath",
		required = false
	)
	public String keyGeneratorKeyStorePath();

	@Meta.AD(
		deflt = "JCEKS", name = "keyGeneratorKeyStoreType", required = false
	)
	public String keyGeneratorKeyStoreType();

	@Meta.AD(
		deflt = "lcs-gateway.liferay.com", name = "platformLcsGatewayHostName",
		required = false
	)
	public String platformLcsGatewayHostName();

	@Meta.AD(
		deflt = "443", name = "platformLcsGatewayHostPort", required = false
	)
	public int platformLcsGatewayHostPort();

	@Meta.AD(
		deflt = "classpath:/keystore.jks",
		name = "platformLcsGatewayKeyStorePath", required = false
	)
	public String platformLcsGatewayKeyStorePath();

	@Meta.AD(
		deflt = "JKS", name = "platformLcsGatewayKeyStoreType", required = false
	)
	public String platformLcsGatewayKeyStoreType();

	@Meta.AD(
		deflt = "https", name = "platformLcsGatewayWebProtocol",
		required = false
	)
	public String platformLcsGatewayWebProtocol();

	@Meta.AD(
		deflt = "j8FxkmkTUBA5UqhSMc", name = "osbLCSGatewayWebSecureApiToken",
		required = false
	)
	public String osbLCSGatewayWebSecureApiToken();

	@Meta.AD(
		deflt = "lcs.liferay.com", name = "lcsPlatformPortalHostName",
		required = false
	)
	public String lcsPlatformPortalHostName();

	@Meta.AD(
		deflt = "443", name = "lcsPlatformPortalHostPort", required = false
	)
	public int lcsPlatformPortalHostPort();

	@Meta.AD(
		deflt = "classpath:/keystore.jks",
		name = "lcsPlatformPortalKeyStorePath", required = false
	)
	public String lcsPlatformPortalKeyStorePath();

	@Meta.AD(
		deflt = "JKS", name = "lcsPlatformPortalKeyStoreType", required = false
	)
	public String lcsPlatformPortalKeyStoreType();

	@Meta.AD(
		deflt = "/group/guest/environment",
		name = "lcsPlatformPortalLayoutLCSClusterEntry", required = false
	)
	public String lcsPlatformPortalLayoutLCSClusterEntry();

	@Meta.AD(
		deflt = "/group/guest/server",
		name = "lcsPlatformPortalLayoutLCSClusterNode", required = false
	)
	public String lcsPlatformPortalLayoutLCSClusterNode();

	@Meta.AD(
		deflt = "/group/guest/dashboard",
		name = "lcsPlatformPortalLayoutLCSProject", required = false
	)
	public String lcsPlatformPortalLayoutLCSProject();

	@Meta.AD(
		deflt = "/c/portal/oauth/access_token",
		name = "lcsPlatformPortalOauthAccessTokenUri", required = false
	)
	public String lcsPlatformPortalOauthAccessTokenUri();

	@Meta.AD(
		deflt = "/c/portal/oauth/authorize?oauth_token={0}",
		name = "lcsPlatformPortalOauthAuthorizeUri", required = false
	)
	public String lcsPlatformPortalOauthAuthorizeUri();

	@Meta.AD(
		deflt = "1f477b50-f4b0-45bd-a27d-1eccb07d741c",
		name = "lcsPlatformPortalOauthConsumerKey", required = false
	)
	public String lcsPlatformPortalOauthConsumerKey();

	@Meta.AD(
		deflt = "eb33f55d4207e0529dde0932b75755a0",
		name = "lcsPlatformPortalOauthConsumerSecret", required = false
	)
	public String lcsPlatformPortalOauthConsumerSecret();

	@Meta.AD(
		deflt = "/c/portal/oauth/request_token",
		name = "lcsPlatformPortalOauthRequestTokenUri", required = false
	)
	public String lcsPlatformPortalOauthRequestTokenUri();

	@Meta.AD(
		deflt = "https", name = "lcsPlatformPortalProtocol", required = false
	)
	public String lcsPlatformPortalProtocol();

	@Meta.AD(deflt = "", name = "proxyAuthType", required = false)
	public String proxyAuthType();

	@Meta.AD(deflt = "", name = "proxyDomain", required = false)
	public String proxyDomain();

	@Meta.AD(deflt = "", name = "proxyHostLogin", required = false)
	public String proxyHostLogin();

	@Meta.AD(deflt = "", name = "proxyHostName", required = false)
	public String proxyHostName();

	@Meta.AD(deflt = "", name = "proxyHostPassword", required = false)
	public String proxyHostPassword();

	@Meta.AD(deflt = "0", name = "proxyHostPort", required = false)
	public int proxyHostPort();

	@Meta.AD(deflt = "", name = "proxyWorkstation", required = false)
	public String proxyWorkstation();

	@Meta.AD(deflt = "100", name = "scheduledTaskPageSize", required = false)
	public int scheduledTaskPageSize();

	@Meta.AD(
		deflt = "60000", name = "scheduledTaskPauseInterval", required = false
	)
	public int scheduledTaskPauseInterval();

}