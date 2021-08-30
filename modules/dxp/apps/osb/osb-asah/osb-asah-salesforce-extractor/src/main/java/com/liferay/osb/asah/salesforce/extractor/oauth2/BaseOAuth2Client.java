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

package com.liferay.osb.asah.salesforce.extractor.oauth2;

import com.liferay.osb.asah.common.spring.http.client.OSBAsahResponseErrorHandler;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Shinn Lok
 */
public abstract class BaseOAuth2Client {

	protected ResponseEntity<String> post(
		String clientId, String clientSecret, String path, String refreshToken,
		String url) {

		ResponseEntity<String> responseEntity = null;

		RestTemplate restTemplate = new RestTemplate() {
			{
				setErrorHandler(new OSBAsahResponseErrorHandler());
			}
		};

		try {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"%s: Obtaining refreshed access token for client ID %s",
						ProjectIdThreadLocal.getProjectId(), clientId));
			}

			responseEntity = restTemplate.postForEntity(
				url + path,
				new HttpEntity<>(
					new LinkedMultiValueMap<String, String>() {
						{
							add("client_id", clientId);
							add("client_secret", clientSecret);
							add("grant_type", "refresh_token");
							add("refresh_token", refreshToken);
						}
					},
					new HttpHeaders() {
						{
							put(
								HttpHeaders.USER_AGENT,
								Collections.singletonList(
									"LiferayAnalyticsCloud"));
							setContentType(
								MediaType.APPLICATION_FORM_URLENCODED);
						}
					}),
				String.class);
		}
		catch (RestClientException restClientException) {
			_log.error(restClientException, restClientException);

			if (restClientException instanceof HttpClientErrorException) {
				HttpClientErrorException httpClientErrorException =
					(HttpClientErrorException)restClientException;

				responseEntity = new ResponseEntity<>(
					httpClientErrorException.getStatusCode());
			}
		}

		if (responseEntity == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"%s: Unable to obtain refreshed access token because " +
							"OAuth URL is not found",
						ProjectIdThreadLocal.getProjectId()));
			}

			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else if (responseEntity.getStatusCode() != HttpStatus.OK) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"%s: Unable to obtain refreshed access token due to " +
							"invalid client ID, client secret, and/or " +
								"refresh token, or insufficient permissions",
						ProjectIdThreadLocal.getProjectId()));
			}

			responseEntity = new ResponseEntity<>(
				responseEntity.getStatusCode());
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"%s: Refreshed access token",
					ProjectIdThreadLocal.getProjectId()));
		}

		return responseEntity;
	}

	private static final Log _log = LogFactory.getLog(BaseOAuth2Client.class);

}