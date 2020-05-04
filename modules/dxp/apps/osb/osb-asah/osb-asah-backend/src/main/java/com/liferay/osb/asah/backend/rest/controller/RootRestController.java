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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.common.servlet.util.ServletRequestUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping("/")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.RootRestController"
)
public class RootRestController extends BaseRestController {

	@GetMapping("/")
	public String get(HttpServletRequest httpServletRequest) throws Exception {
		String content = ResourceUtil.readResourceToString(
			"endpoints.json", this);

		return content.replaceAll(
			_PLACEHOLDER_URL,
			ServletRequestUtil.getOriginalURL(httpServletRequest));
	}

	private static final String _PLACEHOLDER_URL = Pattern.quote("{url}");

}