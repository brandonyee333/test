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

package com.liferay.osb.asah.common.http.impl;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import java.io.IOException;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * @author Inácio Nery
 */
@Component
@MonolithExclude
public class QueueHttpImpl implements QueueHttp {

	@Override
	public String getMessages(String queueName) {
		return getMessages(queueName, 50);
	}

	@Override
	public String getMessages(String queueName, long total) {
		RestTemplate restTemplate = _getRestTemplate();

		StringBuilder sb = new StringBuilder();

		sb.append(ServiceConstants.URL_QUEUE);
		sb.append("/messages/");
		sb.append(queueName);
		sb.append("?total=");
		sb.append(total);

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(
			sb.toString(), String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		}

		_createQueue(queueName);

		return getMessages(queueName, total);
	}

	@Override
	public int getMessagesCount(String queueName) {
		RestTemplate restTemplate = _getRestTemplate();

		ResponseEntity<String> responseEntity = restTemplate.getForEntity(
			ServiceConstants.URL_QUEUE + "/queues/" + queueName, String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			JSONObject queueJSONObject = new JSONObject(
				responseEntity.getBody());

			return queueJSONObject.getInt("total");
		}

		_createQueue(queueName);

		return getMessagesCount(queueName);
	}

	@Override
	public void initializeQueue() {
		List<String> queueNames = _getQueueNames();

		if (!queueNames.contains(QUEUE_NAME_ACTIVE_INDIVIDUAL_IDS)) {
			_createQueue(QUEUE_NAME_ACTIVE_INDIVIDUAL_IDS);
		}

		if (!queueNames.contains(QUEUE_NAME_DXP_ENTITIES)) {
			_createQueue(QUEUE_NAME_DXP_ENTITIES);
		}

		if (!queueNames.contains(QUEUE_NAME_IDENTITY)) {
			_createQueue(QUEUE_NAME_IDENTITY);
		}
	}

	@Override
	public void pushMessage(String message, String queueName) {
		RestTemplate restTemplate = _getRestTemplate();

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(
			ServiceConstants.URL_QUEUE + "/messages/" + queueName,
			new HashMap<String, Object>() {
				{
					put("delayBeforeRead", 0);
					put("message", message);
				}
			},
			String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return;
		}

		_createQueue(queueName);

		pushMessage(message, queueName);
	}

	private void _createQueue(String queueName) {
		RestTemplate restTemplate = new RestTemplate();

		String responseJSON = restTemplate.postForObject(
			ServiceConstants.URL_QUEUE + "/queues",
			new HashMap<String, Object>() {
				{
					put("delayAfterRead", 30);
					put("delayBeforeRead", 0);
					put("maxSize", -1);
					put("name", queueName);
				}
			},
			String.class);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Response for creating queue " + queueName + ": " +
					responseJSON);
		}
	}

	private List<String> _getQueueNames() {
		List<String> queueNames = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();

		JSONObject resultJSONObject = new JSONObject(
			restTemplate.getForObject(
				ServiceConstants.URL_QUEUE + "/queues", String.class));

		JSONArray queueNamesJSONArray = resultJSONObject.getJSONArray("queues");

		Iterator<Object> iterator = queueNamesJSONArray.iterator();

		while (iterator.hasNext()) {
			String queueName = String.valueOf(iterator.next());

			queueNames.add(queueName);
		}

		return queueNames;
	}

	private RestTemplate _getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.setErrorHandler(new ResponseErrorHandler());

		return restTemplate;
	}

	private static final Log _log = LogFactory.getLog(QueueHttpImpl.class);

	private static class ResponseErrorHandler
		extends DefaultResponseErrorHandler {

		@Override
		public void handleError(ClientHttpResponse clientHttpResponse)
			throws IOException {

			HttpStatus httpStatus = clientHttpResponse.getStatusCode();

			if (!httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
				super.handleError(clientHttpResponse);

				return;
			}

			JSONObject responseJSONObject = new JSONObject(
				IOUtils.toString(
					clientHttpResponse.getBody(), Charset.defaultCharset()));

			JSONArray errorsJSONArray = responseJSONObject.optJSONArray(
				"errors");

			if (errorsJSONArray == null) {
				super.handleError(clientHttpResponse);

				return;
			}

			for (int i = 0; i < errorsJSONArray.length(); i++) {
				JSONObject errorJSONObject = errorsJSONArray.getJSONObject(i);

				if (Objects.equals(
						errorJSONObject.getString("reason"), "queueNotFound")) {

					if (_log.isInfoEnabled()) {
						_log.info("Queue is missing");
					}

					return;
				}
			}

			super.handleError(clientHttpResponse);
		}

	}

}