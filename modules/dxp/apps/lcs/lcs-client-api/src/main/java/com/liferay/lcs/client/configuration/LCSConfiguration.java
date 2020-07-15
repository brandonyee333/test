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
		deflt = "86400", name = "command-schedule-default-interval",
		required = false
	)
	public int commandScheduleDefaultInterval();

	@Meta.AD(
		deflt = "localhost", name = "digital-signature-key-name",
		required = false
	)
	public String digitalSignatureKeyName();

	@Meta.AD(
		deflt = "classpath:/keystore.jks",
		name = "digital-signature-key-store-path", required = false
	)
	public String digitalSignatureKeyStorePath();

	@Meta.AD(
		deflt = "JKS", name = "digital-signature-key-store-type",
		required = false
	)
	public String digitalSignatureKeyStoreType();

	@Meta.AD(
		deflt = "MD5withRSA", name = "digital-signature-signing-algorithm",
		required = false
	)
	public String digitalSignatureSigningAlgorithm();

	@Meta.AD(
		deflt = "generator", name = "key-generator-key-alias", required = false
	)
	public String keyGeneratorKeyAlias();

	@Meta.AD(
		deflt = "123456", name = "key-generator-key-store-password",
		required = false
	)
	public String keyGeneratorKeyStorePassword();

	@Meta.AD(
		deflt = "classpath:/keystore.jceks",
		name = "key-generator-key-store-path", required = false
	)
	public String keyGeneratorKeyStorePath();

	@Meta.AD(
		deflt = "JCEKS", name = "key-generator-key-store-type", required = false
	)
	public String keyGeneratorKeyStoreType();

	@Meta.AD(
		deflt = "lcs-gateway.liferay.com",
		name = "platform-lcs-gateway-host-name", required = false
	)
	public String platformLcsGatewayHostName();

	@Meta.AD(
		deflt = "443", name = "platform-lcs-gateway-host-port", required = false
	)
	public int platformLcsGatewayHostPort();

	@Meta.AD(
		deflt = "classpath:/keystore.jks",
		name = "platform-lcs-gateway-key-store-path", required = false
	)
	public String platformLcsGatewayKeyStorePath();

	@Meta.AD(
		deflt = "JKS", name = "platform-lcs-gateway-key-store-type",
		required = false
	)
	public String platformLcsGatewayKeyStoreType();

	@Meta.AD(
		deflt = "https", name = "platform-lcs-gateway-web-protocol",
		required = false
	)
	public String platformLcsGatewayWebProtocol();

	@Meta.AD(
		deflt = "j8FxkmkTUBA5UqhSMc",
		name = "osb-lcs-gateway-web-secure-api-token", required = false
	)
	public String osbLCSGatewayWebSecureApiToken();

	@Meta.AD(
		deflt = "lcs.liferay.com", name = "lcs-platform-portal-host-name",
		required = false
	)
	public String lcsPlatformPortalHostName();

	@Meta.AD(
		deflt = "443", name = "lcs-platform-portal-host-port", required = false
	)
	public int lcsPlatformPortalHostPort();

	@Meta.AD(
		deflt = "classpath:/keystore.jks",
		name = "lcs-platform-portal-key-store-path", required = false
	)
	public String lcsPlatformPortalKeyStorePath();

	@Meta.AD(
		deflt = "JKS", name = "lcs-platform-portal-key-store-type",
		required = false
	)
	public String lcsPlatformPortalKeyStoreType();

	@Meta.AD(
		deflt = "/group/guest/environment",
		name = "lcs-platform-portal-layout-lcs-cluster-entry", required = false
	)
	public String lcsPlatformPortalLayoutLCSClusterEntry();

	@Meta.AD(
		deflt = "/group/guest/server",
		name = "lcs-platform-portal-layout-lcs-cluster-node", required = false
	)
	public String lcsPlatformPortalLayoutLCSClusterNode();

	@Meta.AD(
		deflt = "/group/guest/dashboard",
		name = "lcs-platform-portal-layout-lcs-project", required = false
	)
	public String lcsPlatformPortalLayoutLCSProject();

	@Meta.AD(
		deflt = "/c/portal/oauth/access_token",
		name = "lcs-platform-portal-oauth-access-token-uri", required = false
	)
	public String lcsPlatformPortalOauthAccessTokenUri();

	@Meta.AD(
		deflt = "/c/portal/oauth/authorize?oauth_token={0}",
		name = "lcs-platform-portal-oauth-authorize-uri", required = false
	)
	public String lcsPlatformPortalOauthAuthorizeUri();

	@Meta.AD(
		deflt = "1f477b50-f4b0-45bd-a27d-1eccb07d741c",
		name = "lcs-platform-portal-oauth-consumer-key", required = false
	)
	public String lcsPlatformPortalOauthConsumerKey();

	@Meta.AD(
		deflt = "eb33f55d4207e0529dde0932b75755a0",
		name = "lcs-platform-portal-oauth-consumer-secret", required = false
	)
	public String lcsPlatformPortalOauthConsumerSecret();

	@Meta.AD(
		deflt = "/c/portal/oauth/request_token",
		name = "lcs-platform-portal-oauth-request-token-uri", required = false
	)
	public String lcsPlatformPortalOauthRequestTokenUri();

	@Meta.AD(
		deflt = "https", name = "lcs-platform-portal-protocol", required = false
	)
	public String lcsPlatformPortalProtocol();

	@Meta.AD(deflt = "", name = "proxy-auth-type", required = false)
	public String proxyAuthType();

	@Meta.AD(deflt = "", name = "proxy-domain", required = false)
	public String proxyDomain();

	@Meta.AD(deflt = "", name = "proxy-host-login", required = false)
	public String proxyHostLogin();

	@Meta.AD(deflt = "", name = "proxy-host-name", required = false)
	public String proxyHostName();

	@Meta.AD(deflt = "", name = "proxy-host-password", required = false)
	public String proxyHostPassword();

	@Meta.AD(deflt = "0", name = "proxy-host-port", required = false)
	public int proxyHostPort();

	@Meta.AD(deflt = "", name = "proxy-work-station", required = false)
	public String proxyWorkstation();

	@Meta.AD(deflt = "100", name = "scheduled-task-page-size", required = false)
	public int scheduledTaskPageSize();

	@Meta.AD(
		deflt = "60000", name = "scheduled-task-pause-interval",
		required = false
	)
	public int scheduledTaskPauseInterval();

}