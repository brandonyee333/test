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

package com.liferay.osb.asah.dxp.extractor.test.rest.controller;

import com.liferay.osb.asah.common.json.JSONUtil;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@Profile("DXPExtractorConfigurationDogTest")
@RequestMapping("/api/jsonws")
@RestController
public class DXPExtractorConfigurationDogTestRestController {

	@GetMapping("/portal/get-build-number")
	public String getBuildNumber() {
		return "7010";
	}

	@GetMapping("/user/get-current-user")
	public String getCurrentUser() {
		return JSONUtil.put(
			"companyId", "456"
		).toString();
	}

	@PostMapping("/invoke")
	public String invoke(@RequestBody String json) {
		return "{}";
	}

}