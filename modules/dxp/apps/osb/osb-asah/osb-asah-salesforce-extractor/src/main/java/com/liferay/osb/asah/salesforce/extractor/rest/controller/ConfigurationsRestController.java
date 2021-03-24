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

package com.liferay.osb.asah.salesforce.extractor.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rachael Koestartyo
 */
@MonolithExclude
@RequestMapping("/configurations")
@RestController
public class ConfigurationsRestController {

	@DeleteMapping
	public void deleteConfiguration(@RequestBody String json) {
		JSONObject jsonObject = new JSONObject(json);

		_configurationManager.deleteConfiguration(
			jsonObject.getString("dataSourceId"));
	}

	@GetMapping("/state")
	public String getState(@RequestBody DataSource dataSource) {
		return _configurationManager.getState(dataSource);
	}

	@PostMapping
	public void postConfiguration(@RequestBody DataSource dataSource) {
		_configurationManager.addConfiguration(dataSource);
	}

	@PutMapping
	public void putConfiguration(@RequestBody DataSource dataSource) {
		_configurationManager.updateConfiguration(dataSource);
	}

	@PostMapping("/refresh")
	public String refresh(@RequestBody DataSource dataSource) {
		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			_configurationManager.refresh(dataSource), JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	@Autowired
	private ConfigurationManager _configurationManager;

	@Autowired
	private ObjectMapper _objectMapper;

}