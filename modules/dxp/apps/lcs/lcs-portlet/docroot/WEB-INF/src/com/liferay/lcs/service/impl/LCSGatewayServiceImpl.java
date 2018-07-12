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

package com.liferay.lcs.service.impl;

import com.liferay.lcs.exception.CompressionException;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.util.CompressionUtil;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSGatewayServiceImpl implements LCSGatewayService {

	@Override
	public void deleteMessages(String key)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		_jsonWebServiceClient.doGet(
			_URL_LCS_GATEWAY_DELETE_MESSAGES, "key", key);
	}

	@Override
	public List<Message> getMessages(String key)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("key", key);

		Map<String, String> headers = new HashMap<>();

		headers.put(
			"BUILD_NUMBER", String.valueOf(ReleaseInfo.getBuildNumber()));
		headers.put("HASH_CODE", String.valueOf(key.hashCode()));
		headers.put(
			"LCS_PORTLET_BUILD_NUMBER",
			String.valueOf(LCSUtil.getLCSPortletBuildNumber()));
		headers.put("PROTOCOL_VERSION", Message.PROTOCOL_VERSION_CURRENT);

		if (_log.isTraceEnabled()) {
			_log.trace("Getting messages from gateway");
		}

		List<Message> messages = _jsonWebServiceClient.doGetToList(
			Message.class, _URL_LCS_GATEWAY_GET_MESSAGES, parameters, headers);

		if (_log.isTraceEnabled()) {
			_log.trace("Received messages: " + messages);
		}

		return messages;
	}

	@Override
	public void sendMessage(Message message)
		throws CompressionException,
			   JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

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

		Map<String, String> headers = new HashMap<>();

		String key = message.getKey();

		headers.put(
			"BUILD_NUMBER", String.valueOf(ReleaseInfo.getBuildNumber()));
		headers.put("HASH_CODE", String.valueOf(key.hashCode()));
		headers.put("KEY", key);
		headers.put(
			"LCS_PORTLET_BUILD_NUMBER",
			String.valueOf(LCSUtil.getLCSPortletBuildNumber()));
		headers.put("MESSAGE_TYPE_CODE", _getMessageNameHashCode(message));
		headers.put("PROTOCOL_VERSION", Message.PROTOCOL_VERSION_CURRENT);

		_jsonWebServiceClient.doPost(
			_URL_LCS_GATEWAY_SEND_MESSAGE, parameters, headers);
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	@Override
	public boolean testLCSGatewayAvailability() {
		try {
			_jsonWebServiceClient.doGet(_URL_LCS_GATEWAY_HEALTH);
		}
		catch (JSONWebServiceTransportException jsonwste) {
			return false;
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			return false;
		}

		return true;
	}

	private String _getMessageNameHashCode(Message message) {
		Class<? extends Message> messageClass = message.getClass();

		String name = messageClass.getName();

		return String.valueOf(name.hashCode());
	}

	private static final String _URL_LCS_GATEWAY = "/api/lcsgateway";

	private static final String _URL_LCS_GATEWAY_DELETE_MESSAGES =
		LCSGatewayServiceImpl._URL_LCS_GATEWAY + "/delete-messages";

	private static final String _URL_LCS_GATEWAY_GET_MESSAGES =
		LCSGatewayServiceImpl._URL_LCS_GATEWAY + "/v12/get-messages";

	private static final String _URL_LCS_GATEWAY_HEALTH = "/management/health";

	private static final String _URL_LCS_GATEWAY_SEND_MESSAGE =
		LCSGatewayServiceImpl._URL_LCS_GATEWAY + "/send-message";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSGatewayServiceImpl.class);

	private JSONWebServiceClient _jsonWebServiceClient;

}