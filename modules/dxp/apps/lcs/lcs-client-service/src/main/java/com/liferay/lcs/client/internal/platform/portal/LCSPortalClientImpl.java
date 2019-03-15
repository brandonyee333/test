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

package com.liferay.lcs.client.internal.platform.portal;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.internal.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.internal.oauth.OAuthUtil;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.URI;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthException;

import org.apache.http.client.methods.HttpRequestBase;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSPortalClientImpl.class)
public class LCSPortalClientImpl
	extends BaseJSONWebServiceClientImpl implements LCSPortalClient {

	@Activate
	public void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		setHostName(lcsConfiguration.lcsPlatformPortalHostName());
		setHostPort(lcsConfiguration.lcsPlatformPortalHostPort());
		setProtocol(lcsConfiguration.lcsPlatformPortalProtocol());

		setOAuthConsumerKey(
			lcsConfiguration.lcsPlatformPortalOauthConsumerKey());
		setOAuthConsumerSecret(
			lcsConfiguration.lcsPlatformPortalOauthConsumerSecret());
		setProxyHostName(lcsConfiguration.proxyHostName());
		setProxyHostPort(lcsConfiguration.proxyHostPort());
		setProxyLogin(lcsConfiguration.proxyHostLogin());
		setProxyPassword(lcsConfiguration.proxyHostPassword());
	}

	@Override
	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		return super.doGetToList(clazz, url, parametersArray);
	}

	@Override
	public <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		return super.doGetToObject(clazz, url, parametersArray);
	}

	public synchronized boolean isAuthorized(
			String oauthAccessSecret, String oauthAccessToken)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		if (System.currentTimeMillis() <
				_lcsAccessTokenNextValidityCheckMillis.get()) {

			return true;
		}

		_accessToken = oauthAccessToken;
		_accessSecret = oauthAccessSecret;

		try {
			testOAuthRequest();
		}
		catch (JSONWebServiceTransportException.AuthenticationFailure
					jsonwsteaf) {

			_log.error(
				"There was an error in communication with LCS: " +
					jsonwsteaf.getMessage());

			return false;
		}

		_lcsAccessTokenNextValidityCheckMillis.set(
			System.currentTimeMillis() + 300000);

		return true;
	}

	@Override
	public void resetHttpClient() {
	}

	@Override
	public void testOAuthRequest()
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		String json = doGet(_URL_OSB_LCS_REST_TEST);

		if (Validator.isNull(json)) {
			throw new JSONWebServiceInvocationException(
				"Unable to perform test OAuth request");
		}

		if (json.contains("exception\":\"")) {
			int exceptionMessageStart = json.indexOf("exception\":\"") + 12;

			int exceptionMessageEnd = json.indexOf("\"", exceptionMessageStart);

			throw new JSONWebServiceInvocationException(
				json.substring(exceptionMessageStart, exceptionMessageEnd));
		}
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase)
		throws JSONWebServiceTransportException.SigningFailure {

		if ((_accessToken == null) && (_accessSecret == null)) {
			throw new JSONWebServiceTransportException.SigningFailure(
				"OAuth credentials are not set", -1);
		}

		OAuthConsumer oAuthConsumer = OAuthUtil.getOAuthConsumer(
			_accessToken, _accessSecret);

		String requestURL = OAuthUtil.buildURL(
			getHostName(), getHostPort(), getProtocol(),
			String.valueOf(httpRequestBase.getURI()));

		httpRequestBase.setURI(URI.create(requestURL));

		try {
			oAuthConsumer.sign(httpRequestBase);
		}
		catch (OAuthException oae) {
			throw new JSONWebServiceTransportException.SigningFailure(
				"Unable to sign HTTP request", oae);
		}
	}

	private static final String _URL_OSB_LCS_REST_TEST = "/o/osb-lcs-rest/";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSPortalClientImpl.class);

	private static final AtomicLong _lcsAccessTokenNextValidityCheckMillis =
		new AtomicLong(0);

	private String _accessSecret;
	private String _accessToken;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

}