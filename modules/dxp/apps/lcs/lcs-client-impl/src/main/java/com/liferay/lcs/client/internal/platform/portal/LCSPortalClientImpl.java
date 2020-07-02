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
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.internal.platform.http.RESTClient;
import com.liferay.lcs.client.internal.platform.http.RESTClientException;
import com.liferay.lcs.client.internal.platform.http.RESTClientFactory;
import com.liferay.lcs.client.internal.platform.http.RESTClientInvocationException;
import com.liferay.lcs.client.internal.platform.http.RESTClientSerializeException;
import com.liferay.lcs.client.internal.platform.http.RESTClientTransportException;
import com.liferay.lcs.client.platform.exception.LCSClientAuthenticationException;
import com.liferay.lcs.client.platform.exception.LCSClientInternalException;
import com.liferay.lcs.client.platform.exception.LCSException;
import com.liferay.lcs.client.platform.exception.LCSPlatformException;
import com.liferay.lcs.security.KeyStoreFactory;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSPortalClient.class)
public class LCSPortalClientImpl implements LCSPortalClient {

	@Override
	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws LCSException {

		try {
			return _restClient.doGetToList(clazz, url, parametersArray);
		}
		catch (RESTClientException restClientException) {
			if (restClientException.getStatus() ==
					HttpServletResponse.SC_NOT_FOUND) {

				return Collections.emptyList();
			}

			throw _toLCSException(restClientException);
		}
	}

	@Override
	public <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws LCSException {

		try {
			return _restClient.doGetToObject(clazz, url, parametersArray);
		}
		catch (RESTClientException restClientException) {
			if (restClientException.getStatus() ==
					HttpServletResponse.SC_NOT_FOUND) {

				if (_log.isTraceEnabled()) {
					_log.trace(
						"Response contains no data", restClientException);
				}

				return null;
			}

			throw _toLCSException(restClientException);
		}
	}

	public synchronized boolean isAuthorized(
			String oauthAccessSecret, String oauthAccessToken)
		throws LCSException {

		if (System.currentTimeMillis() <
				_lcsAccessTokenNextValidityCheckMillis.get()) {

			return true;
		}

		_restClient.setOAuthAccessToken(oauthAccessToken);
		_restClient.setOAuthAccessSecret(oauthAccessSecret);

		try {
			testOAuthRequest();
		}
		catch (LCSClientAuthenticationException lcscae) {
			_log.error(
				"There was an error in communication with LCS: " +
					lcscae.getMessage());

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
	public void testOAuthRequest() throws LCSException {
		String json = null;

		try {
			json = _restClient.doGet(_URL_OSB_LCS_REST_TEST);
		}
		catch (RESTClientException restClientException) {
			throw _toLCSException(restClientException);
		}

		if (Validator.isNull(json)) {
			throw new LCSPlatformException(
				"Unable to perform test OAuth request");
		}

		if (json.contains("exception\":\"")) {
			int exceptionMessageStart = json.indexOf("exception\":\"") + 12;

			int exceptionMessageEnd = json.indexOf("\"", exceptionMessageStart);

			throw new LCSPlatformException(
				json.substring(exceptionMessageStart, exceptionMessageEnd));
		}
	}

	@Activate
	protected void activate() throws Exception {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		Map<String, Object> properties = new HashMap<>();

		properties.put(
			"hostName", lcsConfiguration.lcsPlatformPortalHostName());
		properties.put(
			"hostPort",
			String.valueOf(lcsConfiguration.lcsPlatformPortalHostPort()));
		properties.put(
			"keyStore",
			KeyStoreFactory.getInstance(
				lcsConfiguration.lcsPlatformPortalKeyStorePath(),
				lcsConfiguration.lcsPlatformPortalKeyStoreType()));
		properties.put(
			"oAuthConsumerKey",
			lcsConfiguration.lcsPlatformPortalOauthConsumerKey());
		properties.put(
			"oAuthConsumerSecret",
			lcsConfiguration.lcsPlatformPortalOauthConsumerSecret());
		properties.put(
			"protocol", lcsConfiguration.lcsPlatformPortalProtocol());
		properties.put("proxyAuthType", lcsConfiguration.proxyAuthType());
		properties.put("proxyDomain", lcsConfiguration.proxyDomain());
		properties.put("proxyHostName", lcsConfiguration.proxyHostName());
		properties.put(
			"proxyHostPort", String.valueOf(lcsConfiguration.proxyHostPort()));
		properties.put("proxyLogin", lcsConfiguration.proxyHostLogin());
		properties.put("proxyPassword", lcsConfiguration.proxyHostPassword());
		properties.put("proxyWorkstation", lcsConfiguration.proxyWorkstation());

		_restClient = _restClientFactory.getInstance(properties, true);

		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_restClient != null) {
			_restClient.destroy();
		}

		_lcsAccessTokenNextValidityCheckMillis.set(0);
	}

	private LCSException _toLCSException(
			RESTClientException restClientException)
		throws LCSException {

		if (restClientException instanceof RESTClientInvocationException) {
			throw new LCSPlatformException(
				"Unable to execute remote request", restClientException);
		}
		else if (restClientException instanceof RESTClientSerializeException) {
			throw new LCSClientInternalException(
				"Error communicating with LCS. A message in an unexpected " +
					"format caused a serialization error.",
				restClientException);
		}
		else if (restClientException instanceof RESTClientTransportException) {
			if (restClientException instanceof
					RESTClientTransportException.AuthenticationFailure) {

				StringBundler sb = new StringBundler(4);

				sb.append("Unable to communicate with LCS. The user");
				sb.append("credentials in the environment token were ");
				sb.append("rejected. Please regenerate, download, and ");
				sb.append("install a new token.");

				throw new LCSClientAuthenticationException(
					sb.toString(), restClientException);
			}

			throw new LCSPlatformException(
				"Unable to communicate with LCS", restClientException);
		}

		return new LCSException("LCS Exception", restClientException);
	}

	private static final String _URL_OSB_LCS_REST_TEST = "/o/osb-lcs-rest/";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSPortalClientImpl.class);

	private static final AtomicLong _lcsAccessTokenNextValidityCheckMillis =
		new AtomicLong(0);

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	private RESTClient _restClient;

	@Reference
	private RESTClientFactory _restClientFactory;

}