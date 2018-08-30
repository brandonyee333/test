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

package com.liferay.lcs.jsonwebserviceclient;

import com.liferay.lcs.oauth.OAuthUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.petra.json.web.service.client.internal.JSONWebServiceClientImpl;

import java.net.URI;

import java.util.HashMap;
import java.util.Map;

import com.liferay.portal.kernel.util.Validator;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthException;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * @author Igor Beslic
 */
public class OAuthJSONWebServiceClientImpl extends JSONWebServiceClientImpl {

	@Override
	public void resetHttpClient() {
	}

	public void setAccessSecret(String accessSecret) {
		_accessSecret = accessSecret;
	}

	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;
	}

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
	protected String execute(HttpRequestBase httpRequestBase)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		if ((_accessToken == null) && (_accessSecret == null)) {
			throw new JSONWebServiceTransportException.AuthenticationFailure(
				"OAuth credentials are not set");
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
			throw new JSONWebServiceTransportException.CommunicationFailure(
				"Unable to sign HTTP request", oae);
		}

		return super.execute(httpRequestBase);
	}

	private static final String _URL_OSB_LCS_REST_TEST = "/o/osb-lcs-rest/";

	private String _accessSecret;
	private String _accessToken;

}