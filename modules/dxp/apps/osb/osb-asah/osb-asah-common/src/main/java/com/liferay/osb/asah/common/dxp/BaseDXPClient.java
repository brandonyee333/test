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

package com.liferay.osb.asah.common.dxp;

import com.liferay.osb.asah.common.oauth2.DXPOAuth2Client;
import com.liferay.osb.asah.common.security.Encryptor;
import com.liferay.osb.asah.common.spring.http.Http;

import java.util.Map;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 * @author André Miranda
 */
public abstract class BaseDXPClient {

	public JSONObject deleteJSONObject(
		JSONObject dataSourceJSONObject, String path) {

		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dataSourceJSONObject.getJSONObject("credentials"),
			dataSourceJSONObject.getString("url"), path, HttpMethod.DELETE,
			null);

		if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
			return getBodyAsJSONObject(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	public JSONObject getJSONObject(
		JSONObject dataSourceJSONObject, String path) {

		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dataSourceJSONObject.getJSONObject("credentials"),
			dataSourceJSONObject.getString("url"), path, HttpMethod.GET, null);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return getBodyAsJSONObject(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	public JSONObject postJSONObject(
		JSONObject dataSourceJSONObject, String path, Object body) {

		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dataSourceJSONObject.getJSONObject("credentials"),
			dataSourceJSONObject.getString("url"), path, HttpMethod.POST, body);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return getBodyAsJSONObject(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	protected JSONObject getBodyAsJSONObject(String body) {
		if (body == null) {
			return new JSONObject();
		}

		return new JSONObject(body);
	}

	protected String getPath(
		String methodName, Map<String, Object> parameters) {

		StringBuilder sb = new StringBuilder();

		sb.append(URL_JSONWS);
		sb.append(methodName);

		if ((parameters == null) || parameters.isEmpty()) {
			return sb.toString();
		}

		sb.append(".");
		sb.append(parameters.size());

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			Object value = entry.getValue();

			if (StringUtils.isEmpty(value)) {
				continue;
			}

			sb.append("/");
			sb.append(entry.getKey());
			sb.append("/");
			sb.append(value);
		}

		return sb.toString();
	}

	protected static final String URL_JSONWS = "/api/jsonws";

	protected static final String URL_JSONWS_INVOKE = "/api/jsonws/invoke";

	private ResponseEntity<String> _exchangeResponseEntity(
		JSONObject credentialsJSONObject, String url, String path,
		HttpMethod httpMethod, Object body) {

		String type = credentialsJSONObject.getString("type");

		if (type.equals("Basic Authentication")) {
			return _http.exchangeResponseEntity(
				url, path, httpMethod, body,
				new BasicAuthorizationInterceptor(
					credentialsJSONObject.getString("login"),
					credentialsJSONObject.getString("password")));
		}
		else if (type.equals("OAuth 1 Authentication")) {
			return _http.exchangeResponseEntity(
				url, path, httpMethod, body,
				credentialsJSONObject.getString("oAuthAccessSecret"),
				credentialsJSONObject.getString("oAuthAccessToken"),
				credentialsJSONObject.getString("oAuthConsumerKey"),
				credentialsJSONObject.getString("oAuthConsumerSecret"));
		}
		else if (type.equals("OAuth 2 Authentication")) {
			try {
				return _http.exchangeResponseEntity(
					url, path, httpMethod, body,
					_dxpOAuth2Client.getAccessToken(
						url, credentialsJSONObject.getString("oAuthClientId"),
						credentialsJSONObject.getString("oAuthClientSecret"),
						credentialsJSONObject.getString("oAuthRefreshToken")));
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
						url, credentialsJSONObject.getString("privateKey"))));

			return _http.exchangeResponseEntity(
				url, path, httpMethod, body, httpHeaders);
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