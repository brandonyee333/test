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

package com.liferay.osb.asah.common.oauth2;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Rachael Koestartyo
 */
@Component
public class DXPOAuth2Client extends BaseOAuth2Client {

	public String getAccessToken(
		String url, String clientId, String clientSecret, String refreshToken) {

		if (_oAuth2Clients.containsKey(clientId)) {
			OAuth2ClientConfiguration oAuth2ClientConfiguration =
				_oAuth2Clients.get(clientId);

			if (!oAuth2ClientConfiguration.isExpired()) {
				if (_log.isInfoEnabled()) {
					_log.info("Returning existing access token");
				}

				return oAuth2ClientConfiguration.getAccessToken();
			}

			if (_log.isInfoEnabled()) {
				_log.info("Removing expired access token");
			}

			_oAuth2Clients.remove(clientId);
		}

		long time = System.currentTimeMillis();

		ResponseEntity<String> responseEntity = post(
			clientId, clientSecret, _DXP_OAUTH2_TOKEN_PATH, refreshToken, url);

		if (!Objects.equals(responseEntity.getStatusCode(), HttpStatus.OK)) {
			throw new HttpClientErrorException(responseEntity.getStatusCode());
		}

		JSONObject responseEntityJSONObject = new JSONObject(
			responseEntity.getBody());

		String accessToken = responseEntityJSONObject.getString("access_token");
		int expiresIn = responseEntityJSONObject.getInt("expires_in");

		_oAuth2Clients.put(
			clientId,
			new OAuth2ClientConfiguration(
				accessToken, time + (expiresIn * 1000)));

		if (_log.isInfoEnabled()) {
			_log.info("Refreshed and saved access token");
		}

		return accessToken;
	}

	public void removeAccessToken(String clientId) {
		if (clientId == null) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Removing access token for client ID " + clientId);
		}

		_oAuth2Clients.remove(clientId);
	}

	private static final String _DXP_OAUTH2_TOKEN_PATH = "/o/oauth2/token";

	private static final Log _log = LogFactory.getLog(DXPOAuth2Client.class);

	private final Map<String, OAuth2ClientConfiguration> _oAuth2Clients =
		new ConcurrentHashMap<>();

	private static class OAuth2ClientConfiguration {

		public OAuth2ClientConfiguration(
			String accessToken, long expirationTime) {

			_accessToken = accessToken;
			_expirationTime = expirationTime;
		}

		public String getAccessToken() {
			return _accessToken;
		}

		public boolean isExpired() {
			if (System.currentTimeMillis() > _expirationTime) {
				return true;
			}

			return false;
		}

		private final String _accessToken;
		private final long _expirationTime;

	}

}