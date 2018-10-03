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

package com.liferay.osb.customer.zendesk.connector.internal.service;

import com.liferay.osb.customer.zendesk.connector.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.internal.http.ZendeskHttpDelete;
import com.liferay.osb.customer.zendesk.connector.internal.http.ZendeskHttpGet;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true)
public class ZendeskBaseWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements ZendeskBaseWebService {

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		setMaxAttempts(3);

		super.afterPropertiesSet();
	}

	public JSONObject delete(String url, Map<String, String> parameters)
		throws PortalException {

		String response = null;

		try {
			response = doDelete(url, parameters, _authHeader);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			if (response != null) {
				_log.error("Error parsing response: " + response);
			}

			throw new PortalException(e);
		}
	}

	public JSONObject delete(String endpoint, String json)
		throws PortalException {

		String response = null;

		try {
			ZendeskHttpDelete httpDelete = new ZendeskHttpDelete(endpoint);

			addHeaders(httpDelete, _headers);

			StringEntity stringEntity = getStringEntity(endpoint, json);

			httpDelete.setEntity(stringEntity);

			response = execute(httpDelete);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			if (response != null) {
				_log.error("Error parsing response: " + response);
			}

			throw new PortalException(e);
		}
	}

	public JSONObject get(String url, Map<String, String> parameters)
		throws PortalException {

		String response = null;

		try {
			response = doGet(url, parameters, _authHeader);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			if (response != null) {
				_log.error("Error parsing response: " + response);
			}

			throw new PortalException(e);
		}
	}

	public JSONObject get(String endpoint, String json) throws PortalException {
		ZendeskHttpGet httpGet = new ZendeskHttpGet(endpoint);

		String response = null;

		try {
			addHeaders(httpGet, _headers);

			StringEntity stringEntity = getStringEntity(endpoint, json);

			httpGet.setEntity(stringEntity);

			response = execute(httpGet);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (jsonwsie.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				throw new NoSuchModelException(jsonwsie);
			}

			throw new PortalException(jsonwsie);
		}
		catch (Exception e) {
			if (response != null) {
				_log.error("Error parsing response: " + response);
			}

			throw new PortalException(e);
		}
	}

	public JSONObject post(
			String endpoint, Map<String, String> params, String fileName,
			byte[] bytes)
		throws PortalException {

		String response = null;

		try {
			HttpPost httpPost = new HttpPost(
				Http.HTTPS_WITH_SLASH +
					ZendeskConnectorConfigurationValues.ZENDESK_DOMAIN_NAME +
						endpoint);

			httpPost.addHeader("Authorization", _CREDENTIALS);

			MultipartEntity multipartEntity = new MultipartEntity();

			for (Map.Entry<String, String> entry : params.entrySet()) {
				multipartEntity.addPart(
					entry.getKey(), new StringBody(entry.getValue()));
			}

			ByteArrayBody byteArrayBody = new ByteArrayBody(
				bytes, MimeTypesUtil.getContentType(fileName), fileName);

			multipartEntity.addPart("file", byteArrayBody);

			httpPost.setEntity(multipartEntity);

			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse httpResponse = httpClient.execute(httpPost);

			response = EntityUtils.toString(httpResponse.getEntity());

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			if (response != null) {
				_log.error("Error parsing response: " + response);
			}

			throw new PortalException(e);
		}
	}

	public JSONObject post(String endpoint, String json)
		throws PortalException {

		String response = null;

		try {
			HttpPost httpPost = new HttpPost(endpoint);

			addHeaders(httpPost, _headers);

			StringEntity stringEntity = getStringEntity(endpoint, json);

			httpPost.setEntity(stringEntity);

			response = execute(httpPost);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			if (response != null) {
				_log.error("Error parsing response: " + response);
			}

			throw new PortalException(e);
		}
	}

	public JSONObject put(String endpoint, String json) throws PortalException {
		String response = null;

		try {
			HttpPut httpPut = new HttpPut(endpoint);

			addHeaders(httpPut, _headers);

			StringEntity stringEntity = getStringEntity(endpoint, json);

			httpPut.setEntity(stringEntity);

			response = execute(httpPut);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			if (response != null) {
				_log.error("Error parsing response: " + response);
			}

			throw new PortalException(e);
		}
	}

	public JSONObject send(ZendeskRequest zendeskRequest)
		throws PortalException {

		String method = zendeskRequest.getMethod();
		JSONObject body = zendeskRequest.getBody();

		if (method.equals("post")) {
			return post(zendeskRequest.getEndpoint(), body.toString());
		}
		else if (method.equals("put")) {
			return put(zendeskRequest.getEndpoint(), body.toString());
		}
		else if (method.equals("delete")) {
			if (zendeskRequest.hasParameters()) {
				return delete(
					zendeskRequest.getEndpoint(),
					zendeskRequest.getParameters());
			}
			else {
				return delete(zendeskRequest.getEndpoint(), body.toString());
			}
		}
		else {
			throw new PortalException("Invalid Zendesk Request");
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

	private static final Map<String, String> _authHeader =
		new HashMap<String, String>() {
			{
				put("Authorization", _CREDENTIALS);
			}
		};
	private static final Map<String, String> _headers =
		new HashMap<String, String>() {
			{
				put("Authorization", _CREDENTIALS);
				put("Content-Type", "application/json");
			}
		};

}