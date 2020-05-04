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

package com.liferay.osb.asah.dxp.extractor.client;

import com.liferay.osb.asah.common.dxp.BaseDXPClient;
import com.liferay.osb.asah.common.oauth2.DXPOAuth2Client;
import com.liferay.osb.asah.common.security.Encryptor;
import com.liferay.osb.asah.common.spring.http.Http;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@Component
public class ExtractorDXPClient extends BaseDXPClient {

	public int getInt(
		DXPExtractorConfiguration dxpExtractorConfiguration, String path) {

		return getInt(dxpExtractorConfiguration, path, (Object)null);
	}

	public int getInt(
		DXPExtractorConfiguration dxpExtractorConfiguration, String methodName,
		Map<String, Object> parameters) {

		return getInt(
			dxpExtractorConfiguration, getPath(methodName, parameters),
			(Object)null);
	}

	public int getInt(
		DXPExtractorConfiguration dxpExtractorConfiguration, String path,
		Object body) {

		HttpMethod httpMethod = null;

		if (path.equals(URL_JSONWS_INVOKE)) {
			httpMethod = HttpMethod.POST;
		}
		else {
			httpMethod = HttpMethod.GET;
		}

		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dxpExtractorConfiguration, path, httpMethod, body);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return Integer.parseInt(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	public JSONArray getJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, String path) {

		return getJSONArray(dxpExtractorConfiguration, path, (Object)null);
	}

	public JSONArray getJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, String methodName,
		Map<String, Object> parameters) {

		return getJSONArray(
			dxpExtractorConfiguration, getPath(methodName, parameters),
			(Object)null);
	}

	public JSONArray getJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, String path,
		Object body) {

		HttpMethod httpMethod = null;

		if (path.equals(URL_JSONWS_INVOKE)) {
			httpMethod = HttpMethod.POST;
		}
		else {
			httpMethod = HttpMethod.GET;
		}

		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dxpExtractorConfiguration, path, httpMethod, body);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			if (StringUtils.isEmpty(responseEntity.getBody())) {
				return new JSONArray();
			}

			return new JSONArray(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	public JSONObject getJSONObject(
		DXPExtractorConfiguration dxpExtractorConfiguration, String path) {

		return getJSONObject(dxpExtractorConfiguration, path, null);
	}

	public JSONObject getJSONObject(
		DXPExtractorConfiguration dxpExtractorConfiguration, String path,
		Object body) {

		HttpMethod httpMethod = null;

		if (path.equals(URL_JSONWS_INVOKE)) {
			httpMethod = HttpMethod.POST;
		}
		else {
			httpMethod = HttpMethod.GET;
		}

		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dxpExtractorConfiguration, path, httpMethod, body);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			if (StringUtils.isEmpty(responseEntity.getBody())) {
				return new JSONObject();
			}

			return new JSONObject(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	private ResponseEntity<String> _exchangeResponseEntity(
		DXPExtractorConfiguration dxpExtractorConfiguration, String path,
		HttpMethod httpMethod, Object body) {

		String type = dxpExtractorConfiguration.getDXPAuthenticationType();

		if (type.equals("Basic Authentication")) {
			return _http.exchangeResponseEntity(
				dxpExtractorConfiguration.getDXPURL(), path, httpMethod, body,
				new BasicAuthorizationInterceptor(
					dxpExtractorConfiguration.getDXPLogin(),
					dxpExtractorConfiguration.getDXPPassword()));
		}
		else if (type.equals("OAuth 1 Authentication")) {
			return _http.exchangeResponseEntity(
				dxpExtractorConfiguration.getDXPURL(), path, httpMethod, body,
				dxpExtractorConfiguration.getDXPOAuth1AccessSecret(),
				dxpExtractorConfiguration.getDXPOAuth1AccessToken(),
				dxpExtractorConfiguration.getDXPOAuth1ConsumerKey(),
				dxpExtractorConfiguration.getDXPOAuth1ConsumerSecret());
		}
		else if (type.equals("OAuth 2 Authentication")) {
			try {
				return _http.exchangeResponseEntity(
					dxpExtractorConfiguration.getDXPURL(), path, httpMethod,
					body,
					_dxpOAuth2Client.getAccessToken(
						dxpExtractorConfiguration.getDXPURL(),
						dxpExtractorConfiguration.getDXPOAuth2ClientId(),
						dxpExtractorConfiguration.getDXPOAuth2ClientSecret(),
						dxpExtractorConfiguration.getDXPOAuth2RefreshToken()));
			}
			catch (HttpClientErrorException hcee) {
				return new ResponseEntity<>(hcee.getStatusCode());
			}
		}
		else if (type.equals("Token Authentication")) {
			HttpHeaders httpHeaders = new HttpHeaders() {
				{
					set(
						"Liferay-Analytics-Cloud-Security-Timestamp",
						String.valueOf(System.currentTimeMillis()));
				}
			};

			httpHeaders.set(
				"Liferay-Analytics-Cloud-Security-Signature",
				_encryptor.getSignature(
					httpHeaders, path,
					_encryptor.decrypt(
						dxpExtractorConfiguration.getDXPURL(),
						dxpExtractorConfiguration.getDXPPrivateKey())));

			return _http.exchangeResponseEntity(
				dxpExtractorConfiguration.getDXPURL(), path, httpMethod, body,
				httpHeaders);
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@Autowired
	private DXPOAuth2Client _dxpOAuth2Client;

	@Autowired
	private Encryptor _encryptor;

	@Autowired
	private Http _http;

}