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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	@PostMapping
	public void post(@RequestBody String json) {
		JSONObject jsonObject = new JSONObject(json);

		String channelId = _getChannelId(jsonObject);

		if (StringUtils.isBlank(channelId)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Discarding identity message because channel ID is " +
						"invalid: " + json);
			}

			return;
		}

		_messageBus.sendMessage(
			Channel.IDENTITY_MESSAGE,
			JSONUtil.put(
				"analyticsData", _getAnalyticsDataJSONObject(jsonObject)
			).put(
				"channelId", channelId
			).put(
				"createDate", DateUtil.newDateString()
			).put(
				"dataSourceId", jsonObject.getString("dataSourceId")
			).put(
				"individualId", _getIndividualId(jsonObject)
			).put(
				"projectId", ProjectIdThreadLocal.getProjectId()
			).put(
				"userId", jsonObject.getString("userId")
			).toString());
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

	private String _getChannelId(JSONObject jsonObject) {
		String channelId = jsonObject.optString("channelId");

		if (StringUtils.isBlank(channelId) ||
			!NumberUtils.isCreatable(channelId)) {

			return String.valueOf(
				_dataSourceDog.getDefaultChannelId(
					Long.parseLong(jsonObject.getString("dataSourceId"))));
		}

		return channelId;
	}

	private String _getIndividualId(JSONObject jsonObject) {
		if (jsonObject.has("emailAddressHashed")) {
			return jsonObject.getString("emailAddressHashed");
		}

		JSONObject identityJSONObject = jsonObject.getJSONObject("identity");

		return DigestUtils.sha256Hex(
			StringUtils.lowerCase(identityJSONObject.getString("email")));
	}

	private static final String[] _ANALYTICS_DATA_IDENTITY_FIELD_NAMES = {
		"browserPluginDetails", "canvasFingerPrint", "cookiesEnabled",
		"language", "platform", "protocolVersion", "screenSizeAndColorDepth",
		"systemFonts", "timezone", "touchSupport", "userAgent",
		"webGLFingerPrint"
	};

	private static final Log _log = LogFactory.getLog(
		IdentityRestController.class);

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private MessageBus _messageBus;

}