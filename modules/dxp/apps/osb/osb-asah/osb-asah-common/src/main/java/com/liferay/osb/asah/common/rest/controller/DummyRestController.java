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

package com.liferay.osb.asah.common.rest.controller;

import com.liferay.osb.asah.common.json.JSONUtil;

import org.json.JSONObject;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@Profile("test")
@RequestMapping(produces = "application/json", value = "/dummy")
@RestController
public class DummyRestController {

	@DeleteMapping
	public String delete() {
		return String.valueOf(JSONUtil.put("dummy", 4));
	}

	@GetMapping
	public String get() {
		return String.valueOf(JSONUtil.put("dummy", 1));
	}

	@PatchMapping
	public String patch(@RequestBody String json) {
		return String.valueOf(new JSONObject(json));
	}

	@PostMapping
	public String post(@RequestParam int value) {
		return String.valueOf(JSONUtil.put("dummy", value));
	}

	@PutMapping
	public String put() {
		return String.valueOf(JSONUtil.put("dummy", 3));
	}

}