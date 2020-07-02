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

package com.liferay.lcs.client.internal.platform.gateway;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.exception.CompressionException;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.http.RESTClient;
import com.liferay.lcs.client.internal.platform.http.RESTClientException;
import com.liferay.lcs.client.internal.platform.http.RESTClientFactory;
import com.liferay.lcs.client.internal.platform.http.RESTClientSerializeException;
import com.liferay.lcs.client.internal.platform.http.RESTClientTransportException;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.security.KeyStoreFactory;
import com.liferay.lcs.util.CompressionUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSGatewayClient.class)
public class LCSGatewayClientImpl implements LCSGatewayClient {

	public LCSGatewayClientImpl() {
		_objectMapper.configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		_objectMapper.enableDefaultTypingAsProperty(
			ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, "class");
	}

	public LCSGatewayClientImpl(LCSEventManager lcsEventManager) {
		_lcsEventManager = lcsEventManager;

		_subscribeToLCSEvents();
	}

	@Override
	public synchronized void deleteMessages(String key)
		throws LCSGatewayException {

		try {
			_restClient.doGet(_URL_LCS_GATEWAY_DELETE_MESSAGES, "key", key);
		}
		catch (RESTClientException restClientException) {
			_processJSONWebServiceException(restClientException);
		}
	}

	public long getLastHandshakeSuccess() {
		return _lastHandshakeSuccess;
	}

	public long getLastMessageReceived() {
		return _lastMessageReceived;
	}

	public long getLastMessageSent() {
		return _lastMessageSent;
	}

	@Override
	public List<Message> getMessages(
			boolean checkGatewayAvailability, String key)
		throws LCSGatewayException {

		List<NameValuePair> parameters = new ArrayList<>();

		parameters.add(new LCSNameValuePair("key", key));

		List<NameValuePair> headers = new ArrayList<>(
			_baseHeaderNameValuePairs);

		if (_log.isTraceEnabled()) {
			_log.trace("Getting messages from gateway");
		}

		return _getMessages(checkGatewayAvailability, parameters, headers);
	}

	public List<Message> getMessages(String key) throws LCSGatewayException {
		return getMessages(true, key);
	}

	@Override
	public synchronized boolean isAvailable() {
		return _available;
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
		if (lcsEvent == LCSEvent.HANDSHAKE_FAILED) {
			synchronized (this) {
				if (!_available) {
					return;
				}

				_available = false;
			}

			_lcsEventManager.publish(LCSEvent.LCS_GATEWAY_UNAVAILABLE);
		}
		else if (lcsEvent == LCSEvent.HANDSHAKE_SUCCESS) {
			synchronized (this) {
				if (_available) {
					return;
				}

				_available = true;
			}

			_lastHandshakeSuccess = System.currentTimeMillis();
		}
		else if (lcsEvent == LCSEvent.SIGN_OFF_SUCCESS) {
			synchronized (this) {
				if (!_available) {
					return;
				}

				_available = false;
			}

			_lcsEventManager.publish(LCSEvent.LCS_GATEWAY_UNAVAILABLE);
		}
	}

	@Override
	public void sendMessage(boolean checkGatewayAvailability, Message message)
		throws CompressionException, LCSGatewayException {

		String json = message.toJSON();

		try {
			json = CompressionUtil.compress(json);
		}
		catch (IOException ioe) {
			throw new CompressionException(
				"Unable to compress message " + json, ioe);
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Sending " + message);
		}
		else if (_log.isDebugEnabled()) {
			Class<?> clazz = message.getClass();

			_log.debug("Sending " + clazz.getName());
		}

		List<NameValuePair> parameters = new ArrayList<>();

		parameters.add(new LCSNameValuePair("json", json));

		String key = message.getKey();

		List<NameValuePair> headers = new ArrayList<>(
			_baseHeaderNameValuePairs);

		headers.add(
			new LCSNameValuePair("HASH_CODE", String.valueOf(key.hashCode())));
		headers.add(new LCSNameValuePair("KEY", key));
		headers.add(
			new LCSNameValuePair(
				"MESSAGE_TYPE_CODE", _getMessageNameHashCode(message)));

		_sendMessage(checkGatewayAvailability, parameters, headers);
	}

	@Override
	public void sendMessage(Message message)
		throws CompressionException, LCSGatewayException {

		sendMessage(true, message);
	}

	@Activate
	protected void activate() throws Exception {
		_initJSONWebServiceClient(
			_lcsConfigurationProvider.getLCSConfiguration());

		_subscribeToLCSEvents();

		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_restClient != null) {
			_restClient.destroy();
		}
	}

	private <V, T> List<V> _convertToList(Class<T> clazz, String json)
		throws RESTClientSerializeException {

		if (json == null) {
			return Collections.emptyList();
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		try {
			TypeFactory typeFactory = _objectMapper.getTypeFactory();

			List<V> list = new ArrayList<>();

			JavaType javaType = typeFactory.constructCollectionType(
				list.getClass(), clazz);

			currentThread.setContextClassLoader(Message.class.getClassLoader());

			return _objectMapper.readValue(json, javaType);
		}
		catch (IOException ioe) {
			throw new RESTClientSerializeException(ioe);
		}
		finally {
			currentThread.setContextClassLoader(classLoader);
		}
	}

	private String _getMessageNameHashCode(Message message) {
		Class<? extends Message> messageClass = message.getClass();

		String name = messageClass.getName();

		return String.valueOf(name.hashCode());
	}

	private synchronized List<Message> _getMessages(
			boolean checkGatewayAvailability,
			List<? extends NameValuePair> parameters,
			List<? extends NameValuePair> headers)
		throws LCSGatewayException {

		if (checkGatewayAvailability && !_available) {
			if (_log.isDebugEnabled()) {
				_log.debug("Aborting because LCS gateway is not available");
			}

			return Collections.emptyList();
		}

		try {
			String json = _restClient.doGet(
				_URL_LCS_GATEWAY_GET_MESSAGES, parameters, headers);

			List<Message> messages = _convertToList(Message.class, json);

			if (_log.isTraceEnabled()) {
				_log.trace("Received messages: " + messages);
			}

			_lastMessageReceived = System.currentTimeMillis();

			return messages;
		}
		catch (RESTClientException restClientException) {
			_processJSONWebServiceException(restClientException);
		}

		return Collections.emptyList();
	}

	private void _initJSONWebServiceClient(LCSConfiguration lcsConfiguration)
		throws Exception {

		Map<String, Object> properties = new HashMap<>();

		properties.put(
			"headers",
			"OSB_LCS_API_Token=" +
				lcsConfiguration.osbLCSGatewayWebSecureApiToken());
		properties.put(
			"hostName", lcsConfiguration.platformLcsGatewayHostName());
		properties.put(
			"hostPort",
			String.valueOf(lcsConfiguration.platformLcsGatewayHostPort()));
		properties.put(
			"keyStore",
			KeyStoreFactory.getInstance(
				lcsConfiguration.platformLcsGatewayKeyStorePath(),
				lcsConfiguration.platformLcsGatewayKeyStoreType()));
		properties.put(
			"protocol", lcsConfiguration.platformLcsGatewayWebProtocol());
		properties.put("proxyAuthType", lcsConfiguration.proxyAuthType());
		properties.put("proxyDomain", lcsConfiguration.proxyDomain());
		properties.put("proxyHostName", lcsConfiguration.proxyHostName());
		properties.put(
			"proxyHostPort", String.valueOf(lcsConfiguration.proxyHostPort()));
		properties.put("proxyLogin", lcsConfiguration.proxyHostLogin());
		properties.put("proxyPassword", lcsConfiguration.proxyHostPassword());
		properties.put("proxyWorkstation", lcsConfiguration.proxyWorkstation());

		_restClient = _restClientFactory.getInstance(properties, false);
	}

	private void _processJSONWebServiceException(
			RESTClientException restClientException)
		throws LCSGatewayException {

		String message = "Error communicating with LCS";

		if (restClientException instanceof RESTClientTransportException) {
			if (restClientException instanceof
					RESTClientTransportException.AuthenticationFailure) {

				message =
					message +
						". The LCS client is not authorized to access the " +
							"LCS gateway.";
			}

			synchronized (this) {
				if (_available) {
					_available = false;

					_lcsEventManager.publish(LCSEvent.LCS_GATEWAY_UNAVAILABLE);
				}
			}
		}

		throw new LCSGatewayException(message, restClientException);
	}

	private synchronized void _sendMessage(
			boolean checkGatewayAvailability,
			List<? extends NameValuePair> parameters,
			List<? extends NameValuePair> headers)
		throws LCSGatewayException {

		if (checkGatewayAvailability && !_available) {
			if (_log.isDebugEnabled()) {
				_log.debug("Aborting. LCS gateway is not available.");
			}

			return;
		}

		try {
			_restClient.doPost(
				_URL_LCS_GATEWAY_SEND_MESSAGE, parameters, headers);

			_lastMessageSent = System.currentTimeMillis();
		}
		catch (RESTClientException restClientException) {
			_processJSONWebServiceException(restClientException);
		}
	}

	private void _subscribeToLCSEvents() {
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_FAILED, this);
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_SUCCESS, this);
		_lcsEventManager.subscribe(LCSEvent.SIGN_OFF_SUCCESS, this);
	}

	private static final String _URL_LCS_GATEWAY = "/api/lcsgateway";

	private static final String _URL_LCS_GATEWAY_DELETE_MESSAGES =
		_URL_LCS_GATEWAY + "/delete-messages";

	private static final String _URL_LCS_GATEWAY_GET_MESSAGES =
		_URL_LCS_GATEWAY + "/v12/get-messages";

	private static final String _URL_LCS_GATEWAY_SEND_MESSAGE =
		_URL_LCS_GATEWAY + "/send-message";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSGatewayClientImpl.class);

	private static final List<NameValuePair> _baseHeaderNameValuePairs =
		new ArrayList<NameValuePair>() {
			{
				add(
					new LCSNameValuePair(
						"LCS_PORTLET_BUILD_NUMBER",
						String.valueOf(
							LCSClientConstants.LCS_CLIENT_BUILD_NUMBER)));

				add(
					new LCSNameValuePair(
						"PROTOCOL_VERSION", Message.PROTOCOL_VERSION_CURRENT));
			}
		};

	private volatile boolean _available;
	private long _lastHandshakeSuccess;
	private long _lastMessageReceived;
	private long _lastMessageSent;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSEventManager _lcsEventManager;

	private ObjectMapper _objectMapper = new ObjectMapper();
	private RESTClient _restClient;

	@Reference
	private RESTClientFactory _restClientFactory;

}