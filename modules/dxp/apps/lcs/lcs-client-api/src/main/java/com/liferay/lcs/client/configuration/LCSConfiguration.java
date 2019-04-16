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
@ExtendedObjectClassDefinition(category = "osb-lcs")
@Meta.OCD(id = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration")
public interface LCSConfiguration {

	@Meta.AD(deflt = "Hibernate:name=statistics", required = false)
	public String cacheMetricsHibernateObjectName();

	@Meta.AD(
		deflt = "net.sf.ehcache:type=CacheStatistics\\,CacheManager=MULTI_VM_PORTAL_CACHE_MANAGER\\,name=*",
		required = false
	)
	public String cacheMetricsMultiVMObjectName();

	@Meta.AD(
		deflt = "net.sf.ehcache:type=CacheStatistics\\,CacheManager=SINGLE_VM_PORTAL_CACHE_MANAGER\\,name=*",
		required = false
	)
	public String cacheMetricsSingleVMObjectName();

	@Meta.AD(deflt = "86400", required = false)
	public int commandScheduleDefaultInterval();

	@Meta.AD(deflt = "60000", required = false)
	public int communicationHeartbeatInterval();

	@Meta.AD(deflt = "localhost", required = false)
	public String digitalSignatureKeyName();

	@Meta.AD(deflt = "classpath:/keystore.jks", required = false)
	public String digitalSignatureKeyStorePath();

	@Meta.AD(deflt = "JKS", required = false)
	public String digitalSignatureKeyStoreType();

	@Meta.AD(deflt = "MD5withRSA", required = false)
	public String digitalSignatureSigningAlgorithm();

	@Meta.AD(deflt = "generator", required = false)
	public String keyGeneratorKeyAlias();

	@Meta.AD(deflt = "123456", required = false)
	public String keyGeneratorKeyStorePassword();

	@Meta.AD(deflt = "classpath:/keystore.jceks", required = false)
	public String keyGeneratorKeyStorePath();

	@Meta.AD(deflt = "JCEKS", required = false)
	public String keyGeneratorKeyStoreType();

	@Meta.AD(deflt = "6.0.0", required = false)
	public String lcsClientVersion();

	@Meta.AD(deflt = "600", required = false)
	public int lcsClientBuildNumber();

	@Meta.AD(
		deflt = "https://web.liferay.com/marketplace/-/mp/application/71774947",
		required = false
	)
	public String lrdcomLCSClientDownloadUrl();

	@Meta.AD(
		deflt = "https://www.liferay.com/supporting-products/liferay-connected-services",
		required = false
	)
	public String lrdcomLCSProductPageUrl();

	@Meta.AD(
		deflt = "https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/managing-liferay-with-liferay-connected-services",
		required = false
	)
	public String lrdcomLCSUserDocumentationUrl();

	@Meta.AD(deflt = "sales@liferay.com", required = false)
	public String lrdcomSalesEmailAddress();

	@Meta.AD(
		deflt = "https://web.liferay.com/group/customer/support/-/support/ticket",
		required = false
	)
	public String lrdcomSupportUrl();

	@Meta.AD(deflt = "lcs-gateway.liferay.com", required = false)
	public String platformLcsGatewayHostName();

	@Meta.AD(deflt = "443", required = false)
	public int platformLcsGatewayHostPort();

	@Meta.AD(deflt = "classpath:/keystore.jks", required = false)
	public String platformLcsGatewayKeyStorePath();

	@Meta.AD(deflt = "JKS", required = false)
	public String platformLcsGatewayKeyStoreType();

	@Meta.AD(deflt = "https", required = false)
	public String platformLcsGatewayWebProtocol();

	@Meta.AD(deflt = "j8FxkmkTUBA5UqhSMc", required = false)
	public String osbLCSGatewayWebSecureApiToken();

	@Meta.AD(deflt = "lcs.liferay.com", required = false)
	public String lcsPlatformPortalHostName();

	@Meta.AD(deflt = "443", required = false)
	public int lcsPlatformPortalHostPort();

	@Meta.AD(deflt = "classpath:/keystore.jks", required = false)
	public String lcsPlatformPortalKeyStorePath();

	@Meta.AD(deflt = "JKS", required = false)
	public String lcsPlatformPortalKeyStoreType();

	@Meta.AD(deflt = "/group/guest/environment", required = false)
	public String lcsPlatformPortalLayoutLCSClusterEntry();

	@Meta.AD(deflt = "/group/guest/server", required = false)
	public String lcsPlatformPortalLayoutLCSClusterNode();

	@Meta.AD(deflt = "/group/guest/dashboard", required = false)
	public String lcsPlatformPortalLayoutLCSProject();

	@Meta.AD(deflt = "/c/portal/oauth/access_token", required = false)
	public String lcsPlatformPortalOauthAccessTokenUri();

	@Meta.AD(
		deflt = "/c/portal/oauth/authorize?oauth_token={0}", required = false
	)
	public String lcsPlatformPortalOauthAuthorizeUri();

	@Meta.AD(deflt = "1f477b50-f4b0-45bd-a27d-1eccb07d741c", required = false)
	public String lcsPlatformPortalOauthConsumerKey();

	@Meta.AD(deflt = "eb33f55d4207e0529dde0932b75755a0", required = false)
	public String lcsPlatformPortalOauthConsumerSecret();

	@Meta.AD(deflt = "/c/portal/oauth/request_token", required = false)
	public String lcsPlatformPortalOauthRequestTokenUri();

	@Meta.AD(deflt = "https", required = false)
	public String lcsPlatformPortalProtocol();

	@Meta.AD(deflt = "", required = false)
	public String proxyAuthType();

	@Meta.AD(deflt = "", required = false)
	public String proxyDomain();

	@Meta.AD(deflt = "", required = false)
	public String proxyHostLogin();

	@Meta.AD(deflt = "", required = false)
	public String proxyHostName();

	@Meta.AD(deflt = "", required = false)
	public String proxyHostPassword();

	@Meta.AD(deflt = "0", required = false)
	public int proxyHostPort();

	@Meta.AD(deflt = "", required = false)
	public String proxyWorkstation();

	@Meta.AD(deflt = "100", required = false)
	public int scheduledTaskPageSize();

	@Meta.AD(deflt = "60000", required = false)
	public int scheduledTaskPauseInterval();

}