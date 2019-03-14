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

	@Meta.AD(deflt = "Hibernate:name=statistics")
	public String cacheMetricsHibernateObjectName();

	@Meta.AD(
		deflt = "net.sf.ehcache:type=CacheStatistics\\,CacheManager=MULTI_VM_PORTAL_CACHE_MANAGER\\,name=*"
	)
	public String cacheMetricsMultiVMObjectName();

	@Meta.AD(
		deflt = "net.sf.ehcache:type=CacheStatistics\\,CacheManager=SINGLE_VM_PORTAL_CACHE_MANAGER\\,name=*"
	)
	public String cacheMetricsSingleVMObjectName();

	@Meta.AD(deflt = "86400")
	public String commandScheduleDefaultInterval();

	@Meta.AD(deflt = "60000")
	public String communicationHeartbeatInterval();

	@Meta.AD(deflt = "localhost")
	public String digitalSignatureKeyName();

	@Meta.AD(deflt = "classpath:/keystore.jks")
	public String digitalSignatureKeyStorePath();

	@Meta.AD(deflt = "JKS")
	public String digitalSignatureKeyStoreType();

	@Meta.AD(deflt = "MD5withRSA")
	public String digitalSignatureSigningAlgorithm();

	@Meta.AD(deflt = "generator")
	public String keyGeneratorKeyAlias();

	@Meta.AD(deflt = "123456")
	public String keyGeneratorKeyStorePassword();

	@Meta.AD(deflt = "classpath:/keystore.jceks")
	public String keyGeneratorKeyStorePath();

	@Meta.AD(deflt = "JCEKS")
	public String keyGeneratorKeyStoreType();

	@Meta.AD(deflt = "6.0.0")
	public String lcsClientVersion();

	@Meta.AD(
		deflt = "https://web.liferay.com/marketplace/-/mp/application/71774947"
	)
	public String lrdcomLCSClientDownloadUrl();

	@Meta.AD(
		deflt = "https://www.liferay.com/supporting-products/liferay-connected-services"
	)
	public String lrdcomLCSProductPageUrl();

	@Meta.AD(
		deflt = "https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/managing-liferay-with-liferay-connected-services"
	)
	public String lrdcomLCSUserDocumentationUrl();

	@Meta.AD(deflt = "sales@liferay.com")
	public String lrdcomSalesEmailAddress();

	@Meta.AD(
		deflt = "https://web.liferay.com/group/customer/support/-/support/ticket"
	)
	public String lrdcomSupportUrl();

	@Meta.AD(deflt = "lcs-gateway.liferay.com")
	public String platformLcsGatewayHostName();

	@Meta.AD(deflt = "443")
	public String platformLcsGatewayHostPort();

	@Meta.AD(deflt = "classpath:/keystore.jks")
	public String platformLcsGatewayKeyStorePath();

	@Meta.AD(deflt = "JKS")
	public String platformLcsGatewayKeyStoreType();

	@Meta.AD(deflt = "https")
	public String platformLcsGatewayWebProtocol();

	@Meta.AD(deflt = "j8FxkmkTUBA5UqhSMc")
	public String osbLCSGatewayWebSecureApiToken();

	@Meta.AD(deflt = "lcs.liferay.com")
	public String lcsPlatformPortalHostName();

	@Meta.AD(deflt = "443")
	public int lcsPlatformPortalHostPort();

	@Meta.AD(deflt = "classpath:/keystore.jks")
	public String lcsPlatformPortalKeyStorePath();

	@Meta.AD(deflt = "JKS")
	public String lcsPlatformPortalKeyStoreType();

	@Meta.AD(deflt = "/group/guest/environment")
	public String lcsPlatformPortalLayoutLCSClusterEntry();

	@Meta.AD(deflt = "/group/guest/server")
	public String lcsPlatformPortalLayoutLCSClusterNode();

	@Meta.AD(deflt = "/group/guest/dashboard")
	public String lcsPlatformPortalLayoutLCSProject();

	@Meta.AD(deflt = "/c/portal/oauth/access_token")
	public String lcsPlatformPortalOauthAccessTokenUri();

	@Meta.AD(deflt = "/c/portal/oauth/authorize?oauth_token={0}")
	public String lcsPlatformPortalOauthAuthorizeUri();

	@Meta.AD(deflt = "1f477b50-f4b0-45bd-a27d-1eccb07d741c")
	public String lcsPlatformPortalOauthConsumerKey();

	@Meta.AD(deflt = "eb33f55d4207e0529dde0932b75755a0")
	public String lcsPlatformPortalOauthConsumerSecret();

	@Meta.AD(deflt = "/c/portal/oauth/request_token")
	public String lcsPlatformPortalOauthRequestTokenUri();

	@Meta.AD(deflt = "https")
	public String lcsPlatformPortalProtocol();

	@Meta.AD(deflt = "")
	public String proxyHostLogin();

	@Meta.AD(deflt = "")
	public String proxyHostName();

	@Meta.AD(deflt = "")
	public String proxyHostPassword();

	@Meta.AD(deflt = "")
	public int proxyHostPort();

	@Meta.AD(deflt = "100")
	public String scheduledTaskPageSize();

	@Meta.AD(deflt = "60000")
	public String scheduledTaskPauseInterval();

}