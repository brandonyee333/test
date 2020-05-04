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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@RequestMapping("/context")
@RestController
public abstract class BaseContextRestController {

	@GetMapping
	public String get() {
		return JSONUtil.put(
			"environment",
			JSONUtil.put(
				"EXTERNAL_URL", getExternalURL()
			).put(
				"LABEL_BUILD_DATE", System.getenv("LABEL_BUILD_DATE")
			).put(
				"LABEL_VCS_REF", System.getenv("LABEL_VCS_REF")
			)
		).toString();
	}

	protected String getExternalURL() {
		return null;
	}

}