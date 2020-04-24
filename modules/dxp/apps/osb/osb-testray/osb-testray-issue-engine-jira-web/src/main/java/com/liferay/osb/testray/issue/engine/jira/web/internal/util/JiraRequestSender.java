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

package com.liferay.osb.testray.issue.engine.jira.web.internal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * @author Ethan Bustad
 */
@Component(immediate = true, service = JiraRequestSender.class)
public class JiraRequestSender {

	public synchronized boolean addJiraRequest(
		String url, String payloadJSON, String userName, Token accessToken,
		Verb httpMethod) {

		JiraRequest jiraRequest = new JiraRequest(
			url, payloadJSON, userName, accessToken, httpMethod);

		if (_jiraRequestQueue.contains(jiraRequest)) {
			return false;
		}

		return _jiraRequestQueue.add(jiraRequest);
	}

	public String getJiraResponse(
			String url, String payloadJSON, Token accessToken, Verb httpMethod)
		throws IOException {

		JiraRequest jiraRequest = new JiraRequest(
			url, payloadJSON, null, accessToken, httpMethod);

		Response response = sendJiraRequest(jiraRequest);

		if (!response.isSuccessful()) {
			throw new IOException(
				"Request to JIRA failed with status " + response.getCode());
		}

		return response.getBody();
	}

	public void nonblockingSendJiraRequests() throws Exception {
		Thread thread = new Thread(
			new Runnable() {

				public void run() {
					if (!_lock.tryLock()) {
						return;
					}

					try {
						sendJiraRequests(_RETRY_INTERVAL_DEFAULT);
					}
					catch (Exception e) {
						_log.error(e, e);
					}
					finally {
						_lock.unlock();
					}
				}

			});

		thread.start();
	}

	public Response sendJiraRequest(JiraRequest jiraRequest) {
		ServiceBuilder oAuthServiceBuilder = new ServiceBuilder();

		oAuthServiceBuilder = oAuthServiceBuilder.provider(
			new JiraApiHelper(_http));
		oAuthServiceBuilder = oAuthServiceBuilder.apiKey(
			TestrayIssueEngineJiraWebValues.JIRA_OAUTH_CONSUMER_KEY);
		oAuthServiceBuilder = oAuthServiceBuilder.apiSecret(
			TestrayIssueEngineJiraWebValues.JIRA_OAUTH_SHARED_SECRET);
		oAuthServiceBuilder = oAuthServiceBuilder.signatureType(
			SignatureType.Header);

		OAuthService oAuthService = oAuthServiceBuilder.build();

		OAuthRequest oAuthRequest = new OAuthRequest(
			jiraRequest.getHttpMethod(), jiraRequest.getURL());

		oAuthRequest.addHeader("Content-Type", "application/json");

		if (Validator.isNotNull(jiraRequest.getPayloadJSON())) {
			oAuthRequest.addPayload(jiraRequest.getPayloadJSON());
		}

		oAuthService.signRequest(jiraRequest.getAccessToken(), oAuthRequest);

		return oAuthRequest.send();
	}

	protected void sendJiraRequests(long retryInterval) throws Exception {
		JiraRequest jiraRequest = _jiraRequestQueue.peek();

		if (jiraRequest == null) {
			return;
		}

		Response response = sendJiraRequest(jiraRequest);

		if (ArrayUtil.contains(_RETRY_STATUS_CODES, response.getCode())) {
			Thread.sleep(retryInterval);

			if (retryInterval <= _RETRY_INTERVAL_MAX) {
				retryInterval *= 2;
			}
		}
		else {
			_jiraRequestQueue.remove();

			if (!response.isSuccessful()) {
				_log.error(
					"API call to " + jiraRequest.getURL() + " by " +
						jiraRequest.getUserName() +
							" failed with status code " + response.getCode());
			}

			retryInterval = _RETRY_INTERVAL_DEFAULT;
		}

		sendJiraRequests(retryInterval);
	}

	private static final long _RETRY_INTERVAL_DEFAULT = 1000;

	private static final long _RETRY_INTERVAL_MAX = 600000;

	private static final int[] _RETRY_STATUS_CODES = {
		408, 409, 423, 429, 503, 504
	};

	private static final Log _log = LogFactoryUtil.getLog(
		JiraRequestSender.class);

	private static final ConcurrentLinkedQueue<JiraRequest> _jiraRequestQueue =
		new ConcurrentLinkedQueue<>();
	private static final Lock _lock = new ReentrantLock();

	@Reference
	private Http _http;

	private static class JiraRequest {

		public JiraRequest(
			String url, String payloadJSON, String userName, Token accessToken,
			Verb httpMethod) {

			_url = url;
			_payloadJSON = payloadJSON;
			_userName = userName;
			_accessToken = accessToken;
			_httpMethod = httpMethod;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof JiraRequest)) {
				return false;
			}

			JiraRequest jiraRequest = (JiraRequest)obj;

			if (_accessToken.equals(jiraRequest.getAccessToken()) &&
				_httpMethod.equals(jiraRequest.getHttpMethod()) &&
				_payloadJSON.equals(jiraRequest.getPayloadJSON()) &&
				_url.equals(jiraRequest.getURL())) {

				return true;
			}

			return false;
		}

		public Token getAccessToken() {
			return _accessToken;
		}

		public Verb getHttpMethod() {
			return _httpMethod;
		}

		public String getPayloadJSON() {
			return _payloadJSON;
		}

		public String getURL() {
			return _url;
		}

		public String getUserName() {
			return _userName;
		}

		@Override
		public int hashCode() {
			HashCode hashCode = HashCodeFactoryUtil.getHashCode();

			hashCode.append(_accessToken);
			hashCode.append(_httpMethod);
			hashCode.append(_payloadJSON);
			hashCode.append(_url);

			return hashCode.toHashCode();
		}

		private final Token _accessToken;
		private final Verb _httpMethod;
		private final String _payloadJSON;
		private final String _url;
		private final String _userName;

	}

}