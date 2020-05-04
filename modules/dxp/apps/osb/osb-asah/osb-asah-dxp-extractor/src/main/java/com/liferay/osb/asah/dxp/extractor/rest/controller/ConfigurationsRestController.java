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

package com.liferay.osb.asah.dxp.extractor.rest.controller;

import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@MonolithExclude
@RequestMapping("/configurations")
@RestController
public class ConfigurationsRestController {

	@DeleteMapping
	public void deleteConfiguration(@RequestBody String json) {
		_configurationManager.deleteRuntimeConfiguration(json);
	}

	@GetMapping
	public String getConfigurations() {
		JSONArray jsonArray = new JSONArray(
			_configurationManager.getConfigurations());

		return jsonArray.toString();
	}

	@GetMapping("/state")
	public String getState(@RequestBody String json) {
		return _configurationManager.getState(json);
	}

	@PostMapping
	public void postConfiguration(@RequestBody String json) {
		_configurationManager.addRuntimeConfiguration(json);
	}

	@PutMapping
	public void putConfiguration(@RequestBody String json) throws Exception {
		_configurationManager.updateRuntimeConfiguration(json);
	}

	@PostMapping("/refresh")
	public String refresh(@RequestBody String json) {
		return _configurationManager.refresh(json);
	}

	@PostMapping("/validate")
	public boolean validate(@RequestBody String json) {
		return _configurationManager.validate(json);
	}

	@Autowired
	private ConfigurationManager _configurationManager;

}