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

import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.security.Encryptor;
import com.liferay.osb.asah.common.spring.http.Http;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 * @author André Miranda
 */
public abstract class BaseDXPClient {

	public JSONObject deleteJSONObject(DataSource dataSource, String path) {
		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dataSource, path, HttpMethod.DELETE, null);

		if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
			return getBodyAsJSONObject(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	public JSONObject postJSONObject(
		DataSource dataSource, String path, Object body) {

		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dataSource, path, HttpMethod.POST, body);

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

	private ResponseEntity<String> _exchangeResponseEntity(
		DataSource dataSource, String path, HttpMethod httpMethod,
		Object body) {

		String credentialType = dataSource.getCredentialType();

		if (credentialType.equals("Basic Authentication")) {
			return _http.exchangeResponseEntity(
				dataSource.getURL(), path, httpMethod, body,
				new BasicAuthorizationInterceptor(
					dataSource.getLogin(), dataSource.getPassword()));
		}

		if (credentialType.equals("Token Authentication")) {
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
						dataSource.getURL(), dataSource.getPrivateKey())));

			return _http.exchangeResponseEntity(
				dataSource.getURL(), path, httpMethod, body, httpHeaders);
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@Autowired
	private Encryptor _encryptor;

	@Autowired
	private Http _http;

}