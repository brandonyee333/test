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

	@Override
	public JSONObject getIndividualsJSONObject(String after) {
		return _objectMapper.convertValue(
			_reportRestController.getIndividualResultBag(after),
			JSONObject.class);
	}

	@Override
	public JSONObject getSegmentsJSONObject(String after) {
		return _objectMapper.convertValue(
			_reportRestController.getSegmentDTOPageDTO(after),
			JSONObject.class);
	}

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private ReportRestController _reportRestController;

}