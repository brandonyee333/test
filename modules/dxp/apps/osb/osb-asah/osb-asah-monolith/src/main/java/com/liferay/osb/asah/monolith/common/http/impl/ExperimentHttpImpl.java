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

import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.ExperimentsRestController;
import com.liferay.osb.asah.common.http.ExperimentHttp;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@Primary
public class ExperimentHttpImpl implements ExperimentHttp {

	public JSONObject getExperimentMetricsJSONObject(String id) {
		return _objectMapper.convertValue(
			_experimentsRestController.getCalculateExperimentMetrics(id),
			JSONObject.class);
	}

	@Autowired
	private ExperimentsRestController _experimentsRestController;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}