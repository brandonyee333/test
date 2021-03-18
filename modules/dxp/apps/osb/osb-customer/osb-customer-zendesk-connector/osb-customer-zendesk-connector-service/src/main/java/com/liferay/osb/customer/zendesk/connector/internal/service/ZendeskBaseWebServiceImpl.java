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

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.customer.zendesk.connector.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.internal.http.ZendeskHttpDelete;
import com.liferay.osb.customer.zendesk.connector.internal.http.ZendeskHttpGet;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
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
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ZendeskBaseWebService.class)
public class ZendeskBaseWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements ZendeskBaseWebService {

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		setMaxAttempts(3);

		super.afterPropertiesSet();
	}

	public JSONObject delete(String url, Map<String, String> parameters)
		throws PortalException {

		return _delete(url, parameters, 0);
	}

	public JSONObject delete(String endpoint, String json)
		throws PortalException {

		return _delete(endpoint, json, 0);
	}

	public JSONObject get(String url, Map<String, String> parameters)
		throws PortalException {

		return _get(url, parameters, 0);
	}

	public JSONObject get(String endpoint, String json) throws PortalException {
		return _get(endpoint, json, 0);
	}

	public JSONObject post(
			String endpoint, Map<String, String> parameters, String fileName,
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

			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				multipartEntity.addPart(
					entry.getKey(), new StringBody(entry.getValue()));
			}

			ByteArrayBody byteArrayBody = new ByteArrayBody(
				bytes, MimeTypesUtil.getContentType(fileName), fileName);

			multipartEntity.addPart("file", byteArrayBody);

			httpPost.setEntity(multipartEntity);

			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse httpResponse = httpClient.execute(httpPost);

			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() == 429) {
				try {
					Thread.sleep(
						ZendeskConnectorConfigurationValues.
							ZENDESK_API_LIMIT_RETRY_WAIT_TIME);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return post(endpoint, parameters, fileName, bytes);
			}

			response = EntityUtils.toString(httpResponse.getEntity());

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception exception) {
			throw processedException(
				exception, endpoint, parameters.toString(), response);
		}
	}

	public JSONObject post(String endpoint, String json)
		throws PortalException {

		return _post(endpoint, json, 0);
	}

	public JSONObject put(String endpoint, String json) throws PortalException {
		return put(endpoint, json, _headers);
	}

	public JSONObject put(
			String endpoint, String json, Map<String, String> headers)
		throws PortalException {

		return _put(endpoint, json, headers, 0);
	}

	public JSONObject put(
			String endUserEmailAddress, String endpoint, String json)
		throws PortalException {

		Map<String, String> headers = new HashMap<String, String>() {
			{
				put("Authorization", _getCredentials(endUserEmailAddress));
				put("Content-Type", "application/json");
			}
		};

		return put(endpoint, json, headers);
	}

	public JSONObject send(ZendeskRequest zendeskRequest)
		throws PortalException {

		String method = zendeskRequest.getMethod();
		JSONObject body = zendeskRequest.getBody();

		if (method.equals("delete")) {
			if (zendeskRequest.hasParameters()) {
				return delete(
					zendeskRequest.getEndpoint(),
					zendeskRequest.getParameters());
			}
			else if (body != null) {
				return delete(zendeskRequest.getEndpoint(), body.toString());
			}
			else {
				return delete(
					zendeskRequest.getEndpoint(),
					new HashMap<String, String>());
			}
		}
		else if (method.equals("get")) {
			return get(
				zendeskRequest.getEndpoint(), zendeskRequest.getParameters());
		}
		else if (method.equals("post")) {
			return post(zendeskRequest.getEndpoint(), body.toString());
		}
		else if (method.equals("put")) {
			return put(zendeskRequest.getEndpoint(), body.toString());
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

	protected PortalException processedException(
			Exception e, String url, String data, String response)
		throws PortalException {

		if (e instanceof
				JSONWebServiceTransportException.CommunicationFailure) {

			JSONWebServiceTransportException.CommunicationFailure cf =
				(JSONWebServiceTransportException.CommunicationFailure)e;

			if ((cf.getStatus() == 422) && url.contains("/identities/")) {
				sendEmail(e, url, data);
			}
		}

		_log.error("Request failed for " + url);
		_log.error("Data: " + data);

		if (response != null) {
			_log.error("Error parsing response: " + response);
		}

		return new PortalException(e);
	}

	protected void sendEmail(Exception e, String url, String data) {
		StringBundler sb = new StringBundler(6);

		sb.append("<strong>URL</strong><br />");
		sb.append(url);
		sb.append("<br /><strong>Parameters: </strong><br />");
		sb.append(data);
		sb.append("<br /><br /><strong>Stack Trace:</strong><br />");

		String stackTrace = StackTraceUtil.getStackTrace(e);

		sb.append(StringUtil.replace(stackTrace, CharPool.NEW_LINE, "<br />"));

		try {
			InternetAddress from = new InternetAddress("noreply@liferay.com");
			InternetAddress to = new InternetAddress(
				ZendeskConnectorConfigurationValues.
					ZENDESK_API_ERROR_EMAIL_ADDRESS);

			String mailSubject = "Auto Generated Zendesk API Error Message";

			MailMessage mailMessage = new MailMessage(
				from, to, mailSubject, sb.toString(), true);

			_mailService.sendEmail(mailMessage);
		}
		catch (AddressException addressException) {
			_log.error(addressException, addressException);
		}
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase) {
	}

	private static String _getCredentials(String emailAddress) {
		String zendeskCredentials = StringBundler.concat(
			emailAddress, "/token:",
			ZendeskConnectorConfigurationValues.ZENDESK_API_TOKEN);

		return "Basic " + Base64.encode(zendeskCredentials.getBytes());
	}

	private void _apiLimitRetryWait() {
		try {
			Thread.sleep(
				ZendeskConnectorConfigurationValues.
					ZENDESK_API_LIMIT_RETRY_WAIT_TIME);
		}
		catch (InterruptedException interruptedException) {
			_log.error(interruptedException, interruptedException);
		}
	}

	private void _apiRetryWait() {
		try {
			Thread.sleep(
				ZendeskConnectorConfigurationValues.
					ZENDESK_API_RETRY_WAIT_TIME);
		}
		catch (InterruptedException interruptedException) {
			_log.error(interruptedException, interruptedException);
		}
	}

	private JSONObject _delete(
			String url, Map<String, String> parameters, int retryAttempts)
		throws PortalException {

		String response = null;

		try {
			response = doDelete(url, parameters, _authHeader);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception exception) {
			if (_isAPILimitError(exception)) {
				_apiLimitRetryWait();

				return _delete(url, parameters, ++retryAttempts);
			}

			if (_isRetry(exception, retryAttempts)) {
				_apiRetryWait();

				return _delete(url, parameters, ++retryAttempts);
			}

			throw processedException(
				exception, url, parameters.toString(), response);
		}
	}

	private JSONObject _delete(String endpoint, String json, int retryAttempts)
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
		catch (Exception exception) {
			if (_isAPILimitError(exception)) {
				_apiLimitRetryWait();

				return _delete(endpoint, json, ++retryAttempts);
			}

			if (_isRetry(exception, retryAttempts)) {
				_apiRetryWait();

				return _delete(endpoint, json, ++retryAttempts);
			}

			throw processedException(exception, endpoint, json, response);
		}
	}

	private JSONObject _get(
			String url, Map<String, String> parameters, int retryAttempts)
		throws PortalException {

		String response = null;

		try {
			response = doGet(url, parameters, _authHeader);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception exception) {
			if (_isAPILimitError(exception)) {
				_apiLimitRetryWait();

				return _get(url, parameters, ++retryAttempts);
			}

			if (_isRetry(exception, retryAttempts)) {
				_apiRetryWait();

				return _get(url, parameters, ++retryAttempts);
			}

			throw processedException(
				exception, url, parameters.toString(), response);
		}
	}

	private JSONObject _get(String endpoint, String json, int retryAttempts)
		throws PortalException {

		ZendeskHttpGet httpGet = new ZendeskHttpGet(endpoint);

		String response = null;

		try {
			addHeaders(httpGet, _headers);

			StringEntity stringEntity = getStringEntity(endpoint, json);

			httpGet.setEntity(stringEntity);

			response = execute(httpGet);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception exception) {
			if (_isAPILimitError(exception)) {
				_apiLimitRetryWait();

				return _get(endpoint, json, ++retryAttempts);
			}

			if (_isRetry(exception, retryAttempts)) {
				_apiRetryWait();

				return _get(endpoint, json, ++retryAttempts);
			}

			if (exception instanceof JSONWebServiceInvocationException) {
				JSONWebServiceInvocationException
					jsonWebServiceInvocationException =
						(JSONWebServiceInvocationException)exception;

				if (jsonWebServiceInvocationException.getStatus() ==
						HttpServletResponse.SC_NOT_FOUND) {

					throw new NoSuchModelException(
						jsonWebServiceInvocationException);
				}
			}

			throw processedException(exception, endpoint, json, response);
		}
	}

	private boolean _isAPILimitError(Exception exception) {
		if (exception instanceof JSONWebServiceTransportException) {
			JSONWebServiceTransportException jsonWebServiceTransportException =
				(JSONWebServiceTransportException)exception;

			if (jsonWebServiceTransportException.getStatus() == 429) {
				return true;
			}
		}

		return false;
	}

	private boolean _isRetry(Exception exception, int retryAttempts) {
		if (!(exception instanceof JSONWebServiceInvocationException)) {
			return false;
		}

		if (retryAttempts <
				ZendeskConnectorConfigurationValues.
					ZENDESK_API_RETRY_ATTEMPTS) {

			return true;
		}

		return false;
	}

	private JSONObject _post(String endpoint, String json, int retryAttempts)
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
		catch (Exception exception) {
			if (_isAPILimitError(exception)) {
				_apiLimitRetryWait();

				return _post(endpoint, json, ++retryAttempts);
			}

			if (_isRetry(exception, retryAttempts)) {
				_apiRetryWait();

				return _post(endpoint, json, ++retryAttempts);
			}

			throw processedException(exception, endpoint, json, response);
		}
	}

	private JSONObject _put(
			String endpoint, String json, Map<String, String> headers,
			int retryAttempts)
		throws PortalException {

		String response = null;

		try {
			HttpPut httpPut = new HttpPut(endpoint);

			addHeaders(httpPut, headers);

			StringEntity stringEntity = getStringEntity(endpoint, json);

			httpPut.setEntity(stringEntity);

			response = execute(httpPut);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception exception) {
			if (_isAPILimitError(exception)) {
				_apiLimitRetryWait();

				return _put(endpoint, json, headers, ++retryAttempts);
			}

			if (_isRetry(exception, retryAttempts)) {
				_apiRetryWait();

				return _put(endpoint, json, headers, ++retryAttempts);
			}

			throw processedException(exception, endpoint, json, response);
		}
	}

	private static final String _CREDENTIALS = _getCredentials(
		ZendeskConnectorConfigurationValues.ZENDESK_EMAIL_ADDRESS);

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

	@Reference
	private MailService _mailService;

}