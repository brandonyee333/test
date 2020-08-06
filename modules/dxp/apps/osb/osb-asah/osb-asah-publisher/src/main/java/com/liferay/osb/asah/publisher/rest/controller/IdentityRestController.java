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

import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;

import io.prometheus.client.Histogram;
import io.prometheus.client.SimpleTimer;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eddie Olson
 */
@CrossOrigin
@RequestMapping("/identity")
@RestController
public class IdentityRestController {

	@PostConstruct
	public void init() {
		_queueHttp.initializeQueue();
	}

	@PostMapping
	public void post(@RequestBody String json) {
		SimpleTimer simpleTimer = new SimpleTimer();

		try {
			JSONObject jsonObject = new JSONObject(json);

			JSONObject identityJSONObject = jsonObject.getJSONObject(
				"identity");

			_queueHttp.pushMessage(
				JSONUtil.put(
					"analyticsData", _getAnalyticsDataJSONObject(jsonObject)
				).put(
					"channelId", jsonObject.optString("channelId")
				).put(
					"dataSourceId", jsonObject.getString("dataSourceId")
				).put(
					"emailAddressHashed",
					DigestUtils.sha256Hex(
						StringUtils.lowerCase(
							identityJSONObject.getString("email")))
				).put(
					"userId", jsonObject.getString("userId")
				).toString(),
				QueueHttp.QUEUE_NAME_IDENTITY);
		}
		finally {
			_identityRequestsHistogram.observe(simpleTimer.elapsedSeconds());
		}
	}

	private JSONObject _getAnalyticsDataJSONObject(JSONObject jsonObject) {
		JSONObject analyticsDataJSONObject = new JSONObject();

		for (String analyticsDataFieldName :
				_ANALYTICS_DATA_IDENTITY_FIELD_NAMES) {

			analyticsDataJSONObject.put(
				analyticsDataFieldName, jsonObject.opt(analyticsDataFieldName));
		}

		return analyticsDataJSONObject;
	}

	private static final String[] _ANALYTICS_DATA_IDENTITY_FIELD_NAMES = {
		"browserPluginDetails", "canvasFingerPrint", "cookiesEnabled",
		"language", "platform", "protocolVersion", "screenSizeAndColorDepth",
		"systemFonts", "timezone", "touchSupport", "userAgent",
		"webGLFingerPrint"
	};

	private static final Histogram _identityRequestsHistogram =
		PrometheusUtil.histogram(
			"publisher_identity_request_seconds",
			"The number of seconds taken to process the identity requests");

	@Autowired
	private QueueHttp _queueHttp;

}