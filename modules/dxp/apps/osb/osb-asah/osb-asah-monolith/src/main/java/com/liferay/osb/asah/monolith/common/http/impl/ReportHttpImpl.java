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

package com.liferay.osb.asah.monolith.common.http.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.backend.rest.controller.ReportRestController;
import com.liferay.osb.asah.common.http.ReportHttp;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@Primary
public class ReportHttpImpl implements ReportHttp {

	@Override
	public JSONObject getAccountsJSONObject(String after) {
		return _objectMapper.convertValue(
			_reportRestController.getAccountResultBag(after), JSONObject.class);
	}

	public JSONObject getIndividualsJSONObject(String after) {
		return _objectMapper.convertValue(
			_reportRestController.getIndividualResultBag(after),
			JSONObject.class);
	}

	@Override
	public JSONObject getSegmentsJSONObject(String after) {
		return _objectMapper.convertValue(
			_reportRestController.getSegmentResultBag(after), JSONObject.class);
	}

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

	@Autowired
	private ReportRestController _reportRestController;

}