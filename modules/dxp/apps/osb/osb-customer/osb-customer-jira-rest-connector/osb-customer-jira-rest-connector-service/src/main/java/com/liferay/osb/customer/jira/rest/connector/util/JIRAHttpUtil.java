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

package com.liferay.osb.customer.jira.rest.connector.util;

import com.liferay.osb.customer.jira.rest.connector.configuration.JIRARESTConnectorConfigurationValues;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRAResponseIOException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Map;

/**
 * @author Noah Sherrill
 */
public class JIRAHttpUtil {

	public static JSONObject deleteToJSONObject(
			String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setDelete(true);
		options.setLocation(_toURI(endpoint));

		return _sendToJSONObject(dataJSONObject.toString(), options);
	}

	public static String get(String endpoint) throws PortalException {
		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));

		return _send(StringPool.BLANK, options);
	}

	public static JSONArray getToJSONArray(String endpoint)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));

		return _sendToJSONArray(StringPool.BLANK, options);
	}

	public static JSONObject getToJSONObject(
			String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));

		return _sendToJSONObject(dataJSONObject.toString(), options);
	}

	public static JSONObject getToJSONObject(
			String endpoint, Map<String, String> parameters)
		throws PortalException {

		Http.Options options = new Http.Options();

		String url = _toURI(endpoint);

		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			url = HttpUtil.addParameter(url, entry.getKey(), entry.getValue());
		}

		options.setLocation(url);

		return _sendToJSONObject(StringPool.BLANK, options);
	}

	public static JSONObject postToJSONObject(
			String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));
		options.setPost(true);

		return _sendToJSONObject(dataJSONObject.toString(), options);
	}

	public static JSONObject putToJSONObject(
			String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));
		options.setPut(true);

		return _sendToJSONObject(dataJSONObject.toString(), options);
	}

	private static String _getCredentials() {
		String jiraUserNameAndJiraPassword =
			JIRARESTConnectorConfigurationValues.JIRA_USER_NAME +
				StringPool.COLON +
					JIRARESTConnectorConfigurationValues.JIRA_USER_PASSWORD;

		return "Basic " + Base64.encode(jiraUserNameAndJiraPassword.getBytes());
	}

	private static String _send(String body, Http.Options options)
		throws PortalException {

		options.addHeader("Authorization", _CREDENTIALS);

		String contentType = "application" + StringPool.SLASH + "json";

		options.addHeader("Content-Type", contentType);

		options.addHeader("X-Atlassian-Token", "nocheck");
		options.setBody(body, contentType, StringPool.UTF8);

		try {
			String response = StringPool.BLANK;

			// Use HttpUtil#URLtoByteArray method to avoid a
			// NullPointerException

			byte[] bytes = HttpUtil.URLtoByteArray(options);

			if (bytes != null) {
				response = new String(bytes);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Response from JIRA: " + response);
			}

			return response;
		}
		catch (IOException ioe) {
			throw new JIRAResponseIOException(ioe);
		}
	}

	private static JSONArray _sendToJSONArray(String body, Http.Options options)
		throws PortalException {

		String response = _send(body, options);

		if (Validator.isNotNull(response)) {
			return JSONFactoryUtil.createJSONArray(response);
		}
		else {
			return JSONFactoryUtil.createJSONArray();
		}
	}

	private static JSONObject _sendToJSONObject(
			String body, Http.Options options)
		throws PortalException {

		String response = _send(body, options);

		if (Validator.isNotNull(response)) {
			return JSONFactoryUtil.createJSONObject(response);
		}
		else {
			return JSONFactoryUtil.createJSONObject();
		}
	}

	private static String _toURI(String endpoint) {
		StringBundler sb = new StringBundler(3);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(JIRARESTConnectorConfigurationValues.JIRA_DOMAIN_NAME);
		sb.append(endpoint);

		return sb.toString();
	}

	private static final String _CREDENTIALS = _getCredentials();

	private static final Log _log = LogFactoryUtil.getLog(JIRAHttpUtil.class);

}