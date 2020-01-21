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

package com.liferay.osb.customer.web.internal.remote;

import com.liferay.osb.customer.service.DXPCloudBaseWebService;
import com.liferay.osb.customer.web.internal.configuration.CustomerWebConfigurationValues;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.nio.reactor.IOReactorException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = DXPCloudBaseWebService.class)
public class DXPCloudBaseWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements DXPCloudBaseWebService {

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		setMaxAttempts(3);

		super.afterPropertiesSet();
	}

	@Override
	public JSONObject deleteSubscriber(String subscriberId)
		throws PortalException {

		try {
			String url =
				_URL_API_REST_DXP_CLOUD_SUBSCRIBERS + StringPool.SLASH +
					subscriberId;

			String response = doDelete(
				url, Collections.<String, String>emptyMap(), _headers);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	public JSONArray getSubscribers() throws PortalException {
		try {
			String response = doGet(
				_URL_API_REST_DXP_CLOUD_SUBSCRIBERS,
				Collections.<String, String>emptyMap(), _headers);

			return JSONFactoryUtil.createJSONArray(response);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	public JSONObject postSubscriber(
			String emailAddress, boolean skipConfirmationNotification)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject subscriberJSONObject = JSONFactoryUtil.createJSONObject();

		subscriberJSONObject.put("email", emailAddress);
		subscriberJSONObject.put(
			"skip_confirmation_notification", skipConfirmationNotification);

		jsonObject.put("subscriber", subscriberJSONObject);

		try {
			String response = doPostAsJSON(
				_URL_API_REST_DXP_CLOUD_SUBSCRIBERS, jsonObject.toString(),
				_headers);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		setHostName(
			CustomerWebConfigurationValues.
				REMOTE_REST_SERVICE_API_DXP_CLOUD_HOST);

		setHostPort(
			CustomerWebConfigurationValues.
				REMOTE_REST_SERVICE_API_DXP_CLOUD_PORT);

		setProtocol(
			CustomerWebConfigurationValues.
				REMOTE_REST_SERVICE_API_DXP_CLOUD_PROTOCOL);

		return super.execute(httpRequestBase);
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase) {
	}

	private static final String _URL_API_REST_DXP_CLOUD =
		"/v1/pages/" +
			CustomerWebConfigurationValues.
				REMOTE_REST_SERVICE_API_DXP_CLOUD_PAGE_ID;

	private static final String _URL_API_REST_DXP_CLOUD_SUBSCRIBERS =
		_URL_API_REST_DXP_CLOUD + "/subscribers";

	private static final Map<String, String> _headers =
		new HashMap<String, String>() {
			{
				put(
					"Authorization",
					"OAuth " +
						CustomerWebConfigurationValues.
							REMOTE_REST_SERVICE_API_DXP_CLOUD_TOKEN);
			}
		};

}