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

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leslie Wong
 */
@Profile("prod")
@RestController
public class ErrorRestController implements ErrorController {

	@Override
	public String getErrorPath() {
		return null;
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest httpServletRequest) {
		Object status = httpServletRequest.getAttribute(
			RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			return "Encountered error with status code " + status.toString();
		}

		return "Encountered error";
	}

}