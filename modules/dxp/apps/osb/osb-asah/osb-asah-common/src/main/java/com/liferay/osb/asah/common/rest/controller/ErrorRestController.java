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

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leslie Wong
 */
@RestController
public class ErrorRestController extends AbstractErrorController {

	public ErrorRestController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping(produces = "application/json", value = "/error")
	public String handleError(HttpServletRequest httpServletRequest) {
		HttpStatus httpStatus = getStatus(httpServletRequest);

		return JSONUtil.put(
			"error", httpStatus.getReasonPhrase()
		).put(
			"message",
			"Encountered error with status code " + httpStatus.value()
		).put(
			"status", httpStatus.value()
		).toString();
	}

}