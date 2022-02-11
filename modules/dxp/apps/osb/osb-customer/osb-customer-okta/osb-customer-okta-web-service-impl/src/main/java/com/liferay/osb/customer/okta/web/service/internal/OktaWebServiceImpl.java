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

package com.liferay.osb.customer.okta.web.service.internal;

import com.liferay.osb.customer.okta.web.service.OktaWebService;
import com.liferay.osb.customer.okta.web.service.internal.configuration.OktaConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	configurationPid = "com.liferay.osb.customer.okta.web.service.internal.configuration.OktaConfiguration",
	immediate = true, service = OktaWebService.class
)
public class OktaWebServiceImpl implements OktaWebService {

	public JSONObject getUser(String emailAddress) throws Exception {
		JSONObject jsonObject = _sendToJSONObject(
			_URL_API_REST_USERS + emailAddress);

		if ((jsonObject == null) || jsonObject.has("errorCode")) {
			return null;
		}

		return jsonObject;
	}

	public JSONArray getUserGroups(String oktaUserId) throws Exception {
		return _sendToJSONArray(_URL_API_REST_USERS + oktaUserId + "/groups");
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_oktaConfiguration = ConfigurableUtil.createConfigurable(
			OktaConfiguration.class, properties);
	}

	private JSONArray _sendToJSONArray(String endpoint) throws Exception {
		Http.Options options = new Http.Options();

		options.addHeader(
			HttpHeaders.AUTHORIZATION, "SSWS " + _oktaConfiguration.apiToken());
		options.addHeader(
			HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);

		StringBundler sb = new StringBundler(3);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(_oktaConfiguration.host());
		sb.append(endpoint);

		options.setLocation(sb.toString());

		String response = StringPool.BLANK;

		byte[] bytes = _http.URLtoByteArray(options);

		if (bytes != null) {
			response = new String(bytes);
		}

		if (Validator.isNotNull(response)) {
			return _jsonFactory.createJSONArray(response);
		}

		return null;
	}

	private JSONObject _sendToJSONObject(String endpoint) throws Exception {
		Http.Options options = new Http.Options();

		options.addHeader(
			HttpHeaders.AUTHORIZATION, "SSWS " + _oktaConfiguration.apiToken());
		options.addHeader(
			HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);

		StringBundler sb = new StringBundler(3);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(_oktaConfiguration.host());
		sb.append(endpoint);

		options.setLocation(sb.toString());

		String response = StringPool.BLANK;

		byte[] bytes = _http.URLtoByteArray(options);

		if (bytes != null) {
			response = new String(bytes);
		}

		if (Validator.isNotNull(response)) {
			return _jsonFactory.createJSONObject(response);
		}

		return null;
	}

	private static final String _URL_API_REST_USERS = "/api/v1/users/";

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

	private volatile OktaConfiguration _oktaConfiguration;

}