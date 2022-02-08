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

package com.liferay.osb.asah.publisher.rest.controller;

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import io.prometheus.client.Histogram;
import io.prometheus.client.SimpleTimer;

import java.util.Collections;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rachael Koestartyo
 */
@CrossOrigin
@RequestMapping("/dxp-entities")
@RestController
public class DXPEntitiesRestController {

	@PostMapping
	public ResponseEntity<?> post(
		@RequestHeader(required = false, value = HeaderConstants.DATA_SOURCE_ID)
			String dataSourceId,
		@RequestBody String json) {

		SimpleTimer simpleTimer = new SimpleTimer();

		try {
			JSONArray jsonArray = new JSONArray(json);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String action = jsonObject.getString("action");
				JSONObject objectJSONObject = jsonObject.getJSONObject(
					"objectJSONObject");
				String type = jsonObject.getString("type");

				if (type.equals(DXPEntity.Type.CLASS_NAME_CONTACT)) {
					Date date = DateUtil.newDate();

					JSONObject contactJSONObject = JSONUtil.put(
						"contact", objectJSONObject
					).put(
						"emailAddress",
						objectJSONObject.getString("emailAddress")
					).put(
						"modifiedDate",
						objectJSONObject.optLong("modifiedDate", date.getTime())
					).put(
						"userId", objectJSONObject.getInt("classPK")
					);

					if (action.equalsIgnoreCase("add")) {
						action = "update";

						contactJSONObject.put(
							"createDate",
							objectJSONObject.optLong(
								"createDate", date.getTime()));
					}
					else if (objectJSONObject.has("createDate") &&
							 action.equalsIgnoreCase("update")) {

						contactJSONObject.put(
							"createDate",
							objectJSONObject.getLong("createDate"));
					}

					objectJSONObject = contactJSONObject;

					_processGenderField(objectJSONObject);

					type = DXPEntity.Type.CLASS_NAME_USER;
				}
				else if (type.equals(DXPEntity.Type.CLASS_NAME_USER)) {
					jsonObject.putOnce("expando", new JSONArray());
				}

				if (dataSourceId == null) {
					objectJSONObject.put(
						"osbAsahDataSourceId",
						jsonObject.getString("dataSourceId"));
				}
				else {
					objectJSONObject.put("osbAsahDataSourceId", dataSourceId);
				}

				_messageBus.sendMessage(
					Channel.DXP_ENTITIES_MESSAGE,
					JSONUtil.put(
						"context",
						JSONUtil.put(
							"action", action
						).put(
							"type", type
						)
					).put(
						"object", objectJSONObject
					).put(
						"projectId", ProjectIdThreadLocal.getProjectId()
					).toString());
			}

			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
		}
		finally {
			_eventRequestsHistogram.observe(simpleTimer.elapsedSeconds());
		}
	}

	private JSONObject _processGenderField(JSONObject userJSONObject) {
		if (userJSONObject == null) {
			return null;
		}

		if (userJSONObject.has("male")) {
			if (userJSONObject.getBoolean("male")) {
				userJSONObject.put("gender", "male");
			}
			else {
				userJSONObject.put("gender", "female");
			}
		}

		JSONObject contactJSONObject = userJSONObject.optJSONObject("contact");

		if (contactJSONObject == null) {
			return userJSONObject;
		}

		if (contactJSONObject.has("male")) {
			if (contactJSONObject.getBoolean("male")) {
				contactJSONObject.put("gender", "male");
			}
			else {
				contactJSONObject.put("gender", "female");
			}
		}

		return userJSONObject;
	}

	private static final Histogram _eventRequestsHistogram =
		PrometheusUtil.histogram(
			"publisher_dxp_entity_request_seconds",
			"The number of seconds taken to process the DXP entity requests");

	@Autowired
	private MessageBus _messageBus;

}