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

package com.liferay.osb.asah.dxp.extractor.rest.controller;

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;
import com.liferay.osb.asah.dxp.extractor.configuration.impl.DXPExtractorConfigurationManagerImpl;
import com.liferay.osb.asah.dxp.extractor.dog.UserDog;
import com.liferay.osb.asah.dxp.extractor.dog.UserGroupDog;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@RequestMapping("/user-groups")
@RestController
public class UserGroupsRestController {

	@GetMapping(params = "name")
	public String getUserGroups(
		@RequestParam String dataSourceId,
		@RequestParam(defaultValue = "20") int end, @RequestParam String name,
		@RequestParam(defaultValue = "0") int start) {

		DXPExtractorConfiguration dxpExtractorConfiguration =
			_dxpExtractorConfigurationManagerImpl.getConfiguration(
				dataSourceId);

		long companyId = _userDog.getCurrentUserCompanyId(
			dxpExtractorConfiguration);

		return JSONUtil.put(
			"_embedded",
			JSONUtil.put(
				"userGroups",
				_userGroupDog.getUserGroupsJSONArray(
					dxpExtractorConfiguration, companyId, name, start, end))
		).put(
			"page",
			JSONUtil.put(
				"end", end
			).put(
				"start", start
			).put(
				"totalElements",
				_userGroupDog.getUserGroupsCount(
					dxpExtractorConfiguration, companyId, name)
			)
		).toString();
	}

	@GetMapping(params = "!name")
	public String getUserGroups(
		@RequestParam String dataSourceId, @RequestBody String json) {

		DXPExtractorConfiguration dxpExtractorConfiguration =
			_dxpExtractorConfigurationManagerImpl.getConfiguration(
				dataSourceId);

		JSONArray userGroupsJSONArray = _userGroupDog.getUserGroupsJSONArray(
			dxpExtractorConfiguration,
			JSONUtil.toLongArray(new JSONArray(json)));

		return JSONUtil.put(
			"_embedded", JSONUtil.put("userGroups", userGroupsJSONArray)
		).put(
			"page",
			JSONUtil.put(
				"end", userGroupsJSONArray.length()
			).put(
				"start", 0
			).put(
				"totalElements", userGroupsJSONArray.length()
			)
		).toString();
	}

	@Autowired
	private DXPExtractorConfigurationManagerImpl
		_dxpExtractorConfigurationManagerImpl;

	@Autowired
	private UserDog _userDog;

	@Autowired
	private UserGroupDog _userGroupDog;

}