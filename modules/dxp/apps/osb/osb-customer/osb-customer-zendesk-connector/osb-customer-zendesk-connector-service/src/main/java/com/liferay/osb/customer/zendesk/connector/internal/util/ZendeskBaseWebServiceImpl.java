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
import com.liferay.osb.customer.zendesk.connector.internal.http.ZendeskHttpDelete;
import com.liferay.osb.customer.zendesk.connector.internal.http.ZendeskHttpGet;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true)
public class ZendeskBaseWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements ZendeskBaseWebService {

	public JSONObject delete(String endpoint, String json) throws Exception {
		ZendeskHttpDelete httpDelete = new ZendeskHttpDelete(endpoint);

		addHeaders(httpDelete, _headers);

		StringEntity stringEntity = getStringEntity(endpoint, json);

		httpDelete.setEntity(stringEntity);

		String response = execute(httpDelete);

		try {
			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			_log.error("Error parsing response: " + response);

			throw e;
		}
	}

	public JSONObject get(String endpoint, String json) throws Exception {
		ZendeskHttpGet httpGet = new ZendeskHttpGet(endpoint);

		addHeaders(httpGet, _headers);

		StringEntity stringEntity = getStringEntity(endpoint, json);

		httpGet.setEntity(stringEntity);

		String response = execute(httpGet);

		try {
			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			_log.error("Error parsing response: " + response);

			throw e;
		}
	}

	public JSONObject post(
			String endpoint, Map<String, String> params, File file)
		throws Exception {

		HttpPost httpPost = new HttpPost(endpoint);

		addHeaders(httpPost, _headers);

		MultipartEntity multipartEntity = new MultipartEntity();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			multipartEntity.addPart(
				entry.getKey(), new StringBody(entry.getValue()));
		}

		multipartEntity.addPart("file", new FileBody(file));

		httpPost.setEntity(multipartEntity);

		String response = execute(httpPost);

		try {
			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			_log.error("Error parsing response: " + response);

			throw e;
		}
	}

	public JSONObject post(String endpoint, String json) throws Exception {
		HttpPost httpPost = new HttpPost(endpoint);

		addHeaders(httpPost, _headers);

		StringEntity stringEntity = getStringEntity(endpoint, json);

		httpPost.setEntity(stringEntity);

		String response = execute(httpPost);

		try {
			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			_log.error("Error parsing response: " + response);

			throw e;
		}
	}

	public JSONObject put(String endpoint, String json) throws Exception {
		HttpPut httpPut = new HttpPut(endpoint);

		addHeaders(httpPut, _headers);

		StringEntity stringEntity = getStringEntity(endpoint, json);

		httpPut.setEntity(stringEntity);

		String response = execute(httpPut);

		try {
			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			_log.error("Error parsing response: " + response);

			throw e;
		}
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		setHostName(ZendeskConnectorConfigurationValues.ZENDESK_DOMAIN_NAME);
		setHostPort(Http.HTTPS_PORT);
		setProtocol(Http.HTTPS);

		return super.execute(httpRequestBase);
	}

	protected StringEntity getStringEntity(String endpoint, String json) {
		StringEntity stringEntity = new StringEntity(
			json, StandardCharsets.UTF_8);

		stringEntity.setContentType("application/json");

		return stringEntity;
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase) {
	}

	private static String _getCredentials() {
		String zendeskCredentials =
			ZendeskConnectorConfigurationValues.ZENDESK_EMAIL_ADDRESS +
				StringPool.SLASH + "token" + StringPool.COLON +
					ZendeskConnectorConfigurationValues.ZENDESK_API_TOKEN;

		return "Basic " + Base64.encode(zendeskCredentials.getBytes());
	}

	private static final String _CREDENTIALS = _getCredentials();

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskBaseWebServiceImpl.class);

	private static final Map<String, String> _headers = new HashMap<>();

	static {
		_headers.put("Authorization", _CREDENTIALS);
		_headers.put("Content-Type", "application/json");
	}

}