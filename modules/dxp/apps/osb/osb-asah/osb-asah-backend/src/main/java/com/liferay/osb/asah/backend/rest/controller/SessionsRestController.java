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

import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.common.dog.UserSessionDog;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.apache.commons.lang3.StringUtils;

import org.jooq.tools.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rachael Koestartyo
 */
@RequestMapping("/sessions")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.SessionsRestController"
)
public class SessionsRestController extends BaseRestController {

	@GetMapping("/values")
	public PageDTO<String> getSessionValues(
			@RequestParam String fieldName,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(required = false) String value)
		throws Exception {

		Page<String> userSessionsFieldValuesPage =
			_userSessionDog.searchBQSessionsFieldValues(
				StringUtils.substringAfter(fieldName, "/"), filterString, page,
				size, value);

		return new PageDTO(
			"_embedded",
			JSONUtil.put(
				"session-values",
				new JSONArray(userSessionsFieldValuesPage.getContent())),
			userSessionsFieldValuesPage.getNumber(),
			userSessionsFieldValuesPage.getSize(),
			userSessionsFieldValuesPage.getTotalElements(),
			userSessionsFieldValuesPage.getTotalPages());
	}

	@Autowired
	private UserSessionDog _userSessionDog;

}