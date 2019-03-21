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
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSPortalClientImpl.class)
public class LCSPortalClientImpl implements LCSPortalClient {

	@Activate
	public void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		Dictionary<String, String> properties = new Hashtable<>();

		properties.put(
			"hostName", lcsConfiguration.platformLcsGatewayHostName());
		properties.put(
			"hostPort", lcsConfiguration.platformLcsGatewayHostPort());
		properties.put(
			"protocol", lcsConfiguration.platformLcsGatewayWebProtocol());

		properties.put(
			"oAuthConsumerKey",
			lcsConfiguration.lcsPlatformPortalOauthConsumerKey());
		properties.put(
			"oAuthConsumerSecret",
			lcsConfiguration.lcsPlatformPortalOauthConsumerSecret());

		properties.put("proxyHostName", lcsConfiguration.proxyHostName());
		properties.put(
			"proxyHostPort", String.valueOf(lcsConfiguration.proxyHostPort()));
		properties.put("proxyLogin", lcsConfiguration.proxyHostLogin());
		properties.put("proxyPassword", lcsConfiguration.proxyHostPassword());

		ComponentInstance componentInstance =
			_jsonWebServiceClientComponentFactory.newInstance(properties);

		_jsonWebServiceClient =
			(JSONWebServiceClient)componentInstance.getInstance();
	}

	@Override
	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		return _jsonWebServiceClient.doGetToList(clazz, url, parametersArray);
	}

	@Override
	public <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		return _jsonWebServiceClient.doGetToObject(clazz, url, parametersArray);
	}

	public synchronized boolean isAuthorized(
			String oauthAccessSecret, String oauthAccessToken)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		if (System.currentTimeMillis() <
				_lcsAccessTokenNextValidityCheckMillis.get()) {

			return true;
		}

		_jsonWebServiceClient.setOAuthAccessToken(oauthAccessToken);
		_jsonWebServiceClient.setOAuthAccessSecret(oauthAccessSecret);

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

		String json = _jsonWebServiceClient.doGet(_URL_OSB_LCS_REST_TEST);

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

	private static final String _URL_OSB_LCS_REST_TEST = "/o/osb-lcs-rest/";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSPortalClientImpl.class);

	private static final AtomicLong _lcsAccessTokenNextValidityCheckMillis =
		new AtomicLong(0);

	private JSONWebServiceClient _jsonWebServiceClient;

	@Reference(target = "(component.factory=OAuthJSONWebServiceClient)")
	private ComponentFactory _jsonWebServiceClientComponentFactory;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

}