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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.BQCSVUserDog;
import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
		Date date = DateUtil.newDate();

		List<BQCSVUser> bqCSVUsers = JSONUtil.toList(
			new JSONArray(json),
			jsonObject -> {
				BQCSVUser bqCSVUser = new BQCSVUser();

				bqCSVUser.setDataSourceId(
					Long.valueOf(jsonObject.getString("dataSourceId")));
				bqCSVUser.setDataSourceUserPK(
					jsonObject.getString("dataSourceIndividualPK"));
				bqCSVUser.setFields(
					_toFields(jsonObject.optJSONObject("fields")));
				bqCSVUser.setModifiedDate(date);

				return bqCSVUser;
			});

		_bqCSVUserDog.addBQCSVUsers(bqCSVUsers);
	}

	private List<BQCSVUser.Field> _toFields(JSONObject fieldsJSONObject) {
		if (fieldsJSONObject == null) {
			return Collections.emptyList();
		}

		List<BQCSVUser.Field> fields = new ArrayList<>();

		for (String key : fieldsJSONObject.keySet()) {
			fields.add(
				new BQCSVUser.Field(key, fieldsJSONObject.getString(key)));
		}

		return fields;
	}

	@Autowired
	private BQCSVUserDog _bqCSVUserDog;

}