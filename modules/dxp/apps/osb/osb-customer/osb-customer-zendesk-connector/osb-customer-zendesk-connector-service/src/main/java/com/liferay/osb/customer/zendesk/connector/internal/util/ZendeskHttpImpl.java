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

package com.liferay.osb.customer.zendesk.connector.internal.util;

import com.liferay.osb.customer.zendesk.connector.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskHttp;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true)
public class ZendeskHttpImpl implements ZendeskHttp {

	@Override
	public JSONObject delete(String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setDelete(true);
		options.setLocation(_toURI(endpoint));

		return _send(dataJSONObject.toString(), options);
	}

	@Override
	public JSONObject get(String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));

		return _send(dataJSONObject.toString(), options);
	}

	@Override
	public JSONObject post(String endpoint, JSONObject dataJSONObject)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI(endpoint));
		options.setPost(true);

		return _send(dataJSONObject.toString(), options);
	}

	public JSONObject put(String endpoint, JSONObject dataJSONObject)
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

	private JSONObject _send(String body, Http.Options options)
		throws PortalException {

		options.addHeader("Authorization", _CREDENTIALS);
		options.addHeader("Content-Type", ContentTypes.APPLICATION_JSON);
		options.setBody(body, ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		String response = StringPool.BLANK;

		try {
			byte[] bytes = _http.URLtoByteArray(options);

			if (bytes != null) {
				response = new String(bytes);
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		if (Validator.isNotNull(response)) {
			return _jsonFactory.createJSONObject(response);
		}
		else {
			return _jsonFactory.createJSONObject();
		}
	}

	private String _toURI(String endpoint) {
		StringBundler sb = new StringBundler(4);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(ZendeskConnectorConfigurationValues.ZENDESK_DOMAIN_NAME);
		sb.append("/api/v2/");
		sb.append(endpoint);

		return sb.toString();
	}

	private static final String _CREDENTIALS = _getCredentials();

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

}