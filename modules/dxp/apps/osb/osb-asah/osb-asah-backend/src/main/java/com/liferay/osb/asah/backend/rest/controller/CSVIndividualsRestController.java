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

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoCSVIndividualDog;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author David Bhasme
 */
@RequestMapping("/csv-individuals")
@RestController
public class CSVIndividualsRestController extends BaseRestController {

	@PostMapping
	public void postCSVIndividuals(@RequestBody String json) {
		_faroInfoCSVIndividualDog.addCSVIndividuals(new JSONArray(json));
	}

	@Autowired
	private FaroInfoCSVIndividualDog _faroInfoCSVIndividualDog;

}