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

import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorUserDog;
import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;

import io.prometheus.client.Histogram;
import io.prometheus.client.SimpleTimer;

import java.util.Collections;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	public void init() {
		_queueHttp.initializeQueue();
	}

	@PostMapping
	public ResponseEntity<?> post(
		@RequestHeader(required = false, value = "OSB-Asah-Data-Source-ID")
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

				if (type.equals(DXPEntityType.CLASS_NAME_CONTACT)) {
					if (action.equalsIgnoreCase("add")) {
						action = "update";
					}

					objectJSONObject = JSONUtil.put(
						"contact", objectJSONObject
					).put(
						"createDate", objectJSONObject.getLong("createDate")
					).put(
						"emailAddress",
						objectJSONObject.getString("emailAddress")
					).put(
						"modifiedDate", objectJSONObject.getLong("modifiedDate")
					).put(
						"userId",
						String.valueOf(objectJSONObject.getLong("classPK"))
					);

					_dxpExtractorUserDog.processGenderField(objectJSONObject);

					type = DXPEntityType.CLASS_NAME_USER;
				}

				if (dataSourceId == null) {
					objectJSONObject.put(
						"osbAsahDataSourceId",
						jsonObject.getString("dataSourceId"));
				}
				else {
					objectJSONObject.put("osbAsahDataSourceId", dataSourceId);
				}

				JSONObject messageJSONObject = JSONUtil.put(
					"context",
					JSONUtil.put(
						"action", action
					).put(
						"type", type
					)
				).put(
					"object", objectJSONObject
				);

				_queueHttp.pushMessage(
					messageJSONObject.toString(),
					QueueHttp.QUEUE_NAME_DXP_ENTITIES);
			}

			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
		}
		finally {
			_eventRequestsHistogram.observe(simpleTimer.elapsedSeconds());
		}
	}

	private static final Histogram _eventRequestsHistogram =
		PrometheusUtil.histogram(
			"publisher_dxp_entity_request_seconds",
			"The number of seconds taken to process the DXP entity requests");

	@Autowired
	private DXPExtractorUserDog _dxpExtractorUserDog;

	@Autowired
	private QueueHttp _queueHttp;

}