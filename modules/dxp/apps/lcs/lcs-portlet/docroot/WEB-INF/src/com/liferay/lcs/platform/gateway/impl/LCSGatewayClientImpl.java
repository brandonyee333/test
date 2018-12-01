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

package com.liferay.lcs.platform.gateway.impl;

import com.liferay.lcs.exception.CompressionException;
import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.internal.event.LCSEventListener;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.platform.gateway.LCSGatewayException;
import com.liferay.lcs.util.CompressionUtil;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSGatewayClientImpl implements LCSGatewayClient {

	@Override
	public void deleteMessages(String key) throws LCSGatewayException {
		try {
			_jsonWebServiceClient.doGet(
				_URL_LCS_GATEWAY_DELETE_MESSAGES, "key", key);
		}
		catch (JSONWebServiceException jsonwse) {
			_processJSONWebServiceException(jsonwse);
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
	public List<Message> getMessages(String key) throws LCSGatewayException {
		Map<String, String> parameters = new HashMap<>();

		parameters.put("key", key);

		Map<String, String> headers = _getBaseHeaders();

		if (_log.isTraceEnabled()) {
			_log.trace("Getting messages from gateway");
		}

		try {
			List<Message> messages = _jsonWebServiceClient.doGetToList(
				Message.class, _URL_LCS_GATEWAY_GET_MESSAGES, parameters,
				headers);

			if (_log.isTraceEnabled()) {
				_log.trace("Received messages: " + messages);
			}

			_lastMessageReceived = System.currentTimeMillis();

			return messages;
		}
		catch (JSONWebServiceException jsonwse) {
			_processJSONWebServiceException(jsonwse);
		}

		return Collections.emptyList();
	}

	@Override
	public boolean isAvailable() {
		synchronized (this) {
			return _available;
		}
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

			_lcsEventListeners(LCSEvent.LCS_GATEWAY_UNAVAILABLE);

			return;
		}
		else if (lcsEvent == LCSEvent.HANDSHAKE_SUCCESS) {
			synchronized (this) {
				if (_available) {
					return;
				}

				_available = true;
			}

			_lastHandshakeSuccess = System.currentTimeMillis();

			_lcsEventListeners(LCSEvent.LCS_GATEWAY_AVAILABLE);

			return;
		}
		else if (lcsEvent == LCSEvent.SIGNOFF_SUCCESS) {
			synchronized (this) {
				if (!_available) {
					return;
				}

				_available = false;
			}

			_lcsEventListeners(LCSEvent.LCS_GATEWAY_UNAVAILABLE);

			return;
		}
	}

	@Override
	public void registerLCSEventListener(LCSEventListener lcsEventListener) {
		_lcsEventListeners.add(lcsEventListener);
	}

	@Override
	public void sendMessage(Message message)
		throws CompressionException,
			   LCSGatewayException {

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

		Map<String, String> parameters = new HashMap<>();

		parameters.put("json", json);

		String key = message.getKey();

		Map<String, String> headers = _getBaseHeaders();

		headers.put("HASH_CODE", String.valueOf(key.hashCode()));
		headers.put("KEY", key);
		headers.put("MESSAGE_TYPE_CODE", _getMessageNameHashCode(message));

		try {
			_jsonWebServiceClient.doPost(
				_URL_LCS_GATEWAY_SEND_MESSAGE, parameters, headers);

			_lastMessageSent = System.currentTimeMillis();
		}
		catch (JSONWebServiceException jsonwse) {
			_processJSONWebServiceException(jsonwse);
		}
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	private Map<String, String> _getBaseHeaders() {
		if (!_baseHeaders.isEmpty()) {
			return _baseHeaders;
		}

		_baseHeaders.put(
			"LCS_PORTLET_BUILD_NUMBER",
			String.valueOf(LCSUtil.getLCSPortletBuildNumber()));
		_baseHeaders.put("PROTOCOL_VERSION", Message.PROTOCOL_VERSION_CURRENT);

		return _baseHeaders;
	}

	private String _getMessageNameHashCode(Message message) {
		Class<? extends Message> messageClass = message.getClass();

		String name = messageClass.getName();

		return String.valueOf(name.hashCode());
	}

	private void _lcsEventListeners(LCSEvent lcsEvent) {
		for (LCSEventListener lcsEventListener : _lcsEventListeners) {
			lcsEventListener.onLCSEvent(lcsEvent);
		}
	}

	private void _processJSONWebServiceException(
			JSONWebServiceException jsonwse)
		throws LCSGatewayException {

		String message = "Error communicating with LCS";

		if (jsonwse instanceof JSONWebServiceTransportException) {
			if (jsonwse instanceof
					JSONWebServiceTransportException.AuthenticationFailure) {

				message =
					message +
						". The LCS client is not authorized to access the " +
							"LCS gateway.";
			}

			synchronized (this) {
				if (_available) {
					_available = false;

					_lcsEventListeners(LCSEvent.LCS_GATEWAY_UNAVAILABLE);
				}
			}
		}

		throw new LCSGatewayException(message, jsonwse);
	}

	private static final String _URL_LCS_GATEWAY = "/api/lcsgateway";

	private static final String _URL_LCS_GATEWAY_DELETE_MESSAGES =
		LCSGatewayClientImpl._URL_LCS_GATEWAY + "/delete-messages";

	private static final String _URL_LCS_GATEWAY_GET_MESSAGES =
		LCSGatewayClientImpl._URL_LCS_GATEWAY + "/v12/get-messages";

	private static final String _URL_LCS_GATEWAY_SEND_MESSAGE =
		LCSGatewayClientImpl._URL_LCS_GATEWAY + "/send-message";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSGatewayClientImpl.class);

	private volatile boolean _available;
	private final Map<String, String> _baseHeaders = new HashMap<>();
	private JSONWebServiceClient _jsonWebServiceClient;
	private long _lastHandshakeSuccess;
	private long _lastMessageReceived;
	private long _lastMessageSent;
	private final List<LCSEventListener> _lcsEventListeners = new ArrayList<>();

}