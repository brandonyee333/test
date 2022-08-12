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

import com.liferay.osb.asah.common.repository.FieldRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author David Bhasme
 * @author Shinn Lok
 */
@RequestMapping("/field-names")
@RestController
public class FieldNamesRestController extends BaseRestController {

	@GetMapping
	public String getFieldNames(
		@RequestParam(required = false) String label,
		@RequestParam(required = false) String ownerType,
		@RequestParam(required = false) String[] values) {

		// TODO Implement operation

		return null;
	}

	@Autowired
	private FieldRepository _fieldRepository;

}