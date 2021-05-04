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

import com.liferay.osb.asah.common.dog.CSVIndividualDog;
import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.List;

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
	public void postCSVIndividuals(@RequestBody String json) throws Exception {
		List<CSVIndividual> csvIndividuals = JSONUtil.toList(
			new JSONArray(json),
			jsonObject -> {
				CSVIndividual csvIndividual = new CSVIndividual();

				csvIndividual.setDataSourceId(
					Long.valueOf(jsonObject.getString("dataSourceId")));
				csvIndividual.setDataSourceIndividualPK(
					jsonObject.getString("dataSourceIndividualPK"));
				csvIndividual.setFieldsJSONObject(
					jsonObject.optJSONObject("fields"));

				return csvIndividual;
			});

		_csvIndividualDog.addCSVIndividuals(csvIndividuals);
	}

	@Autowired
	private CSVIndividualDog _csvIndividualDog;

}