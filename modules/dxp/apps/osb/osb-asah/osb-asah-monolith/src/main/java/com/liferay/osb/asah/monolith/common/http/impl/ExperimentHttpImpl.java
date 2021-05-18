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

import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Override
	public JSONObject getExperimentMetricsJSONObject(String id) {
		return _objectMapper.convertValue(
			_experimentsRestController.getCalculateExperimentMetric(id),
			JSONObject.class);
	}

	@Autowired
	private ExperimentsRestController _experimentsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

}