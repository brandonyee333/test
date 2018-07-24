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

package com.liferay.osb.customer.zendesk.connector.util;

import com.liferay.osb.customer.zendesk.connector.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.portal.kernel.exception.PortalException;
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

/**
 * @author Kyle Bischof
 */
public class ZendeskHttpUtil {

	public static JSONObject delete(String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setDelete(true);
		options.setLocation(_toURI(endpoint));

		return _send(dataJSONObject.toString(), options);
	}

	public static JSONObject get(String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));

		return _send(dataJSONObject.toString(), options);
	}

	public static JSONObject post(String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));
		options.setPost(true);

		return _send(dataJSONObject.toString(), options);
	}

	public static JSONObject put(String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));
		options.setPut(true);

		return _send(dataJSONObject.toString(), options);
	}

	private static String _getCredentials() {
		String zendeskCredentials =
			ZendeskConnectorConfigurationValues.ZENDESK_EMAIL_ADDRESS +
				StringPool.SLASH + "token" + StringPool.COLON +
					ZendeskConnectorConfigurationValues.ZENDESK_API_TOKEN;

		return "Basic " + Base64.encode(zendeskCredentials.getBytes());
	}

	private static JSONObject _send(String body, Http.Options options)
		throws PortalException {

		options.addHeader("Authorization", _CREDENTIALS);

		String contentType = "application" + StringPool.SLASH + "json";

		options.addHeader("Content-Type", contentType);

		options.setBody(body, contentType, StringPool.UTF8);

		String response = StringPool.BLANK;

		try {
			byte[] bytes = HttpUtil.URLtoByteArray(options);

			if (bytes != null) {
				response = new String(bytes);
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		if (Validator.isNotNull(response)) {
			return JSONFactoryUtil.createJSONObject(response);
		}
		else {
			return JSONFactoryUtil.createJSONObject();
		}
	}

	private static String _toURI(String endpoint) {
		StringBundler sb = new StringBundler(4);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(ZendeskConnectorConfigurationValues.ZENDESK_DOMAIN_NAME);
		sb.append("/api/v2/");
		sb.append(endpoint);

		return sb.toString();
	}

	private static final String _CREDENTIALS = _getCredentials();

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskHttpUtil.class);

}