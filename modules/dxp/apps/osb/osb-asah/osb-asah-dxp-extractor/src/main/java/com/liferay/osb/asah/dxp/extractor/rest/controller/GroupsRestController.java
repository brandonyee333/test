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
import com.liferay.osb.asah.dxp.extractor.dog.GroupDog;
import com.liferay.osb.asah.dxp.extractor.dog.UserDog;

import org.apache.http.HttpStatus;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Rachael Koestartyo
 */
@RequestMapping("/groups")
@RestController
public class GroupsRestController {

	@GetMapping(params = "parentGroupId")
	public String getGroups(
		@RequestParam String dataSourceId,
		@RequestParam(defaultValue = "20") int end,
		@RequestParam(required = false) String name,
		@RequestParam long parentGroupId, @RequestParam boolean site,
		@RequestParam(defaultValue = "0") int start) {

		DXPExtractorConfiguration dxpExtractorConfiguration =
			_dxpExtractorConfigurationManagerImpl.getConfiguration(
				dataSourceId);

		long companyId = _userDog.getCurrentUserCompanyId(
			dxpExtractorConfiguration);

		return JSONUtil.put(
			"_embedded",
			JSONUtil.put(
				"groups",
				_groupDog.getGroupsJSONArray(
					dxpExtractorConfiguration, companyId, parentGroupId, name,
					site, start, end))
		).put(
			"page",
			JSONUtil.put(
				"end", end
			).put(
				"start", start
			).put(
				"totalElements",
				_getGroupsCount(
					dxpExtractorConfiguration, companyId, parentGroupId, name,
					site)
			)
		).toString();
	}

	@GetMapping(params = "!parentGroupId")
	public String getGroups(
		@RequestParam String dataSourceId, @RequestBody String json) {

		DXPExtractorConfiguration dxpExtractorConfiguration =
			_dxpExtractorConfigurationManagerImpl.getConfiguration(
				dataSourceId);

		JSONArray groupsJSONArray = _groupDog.getGroupsJSONArray(
			dxpExtractorConfiguration,
			JSONUtil.toLongArray(new JSONArray(json)));

		return JSONUtil.put(
			"_embedded", JSONUtil.put("groups", groupsJSONArray)
		).put(
			"page",
			JSONUtil.put(
				"end", groupsJSONArray.length()
			).put(
				"start", 0
			).put(
				"totalElements", groupsJSONArray.length()
			)
		).toString();
	}

	private int _getGroupsCount(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		long parentGroupId, String name, boolean site) {

		// See LPS-87210

		try {
			return _groupDog.getGroupsCount(
				dxpExtractorConfiguration, companyId, parentGroupId, name,
				site);
		}
		catch (HttpClientErrorException hcee) {
			if (hcee.getRawStatusCode() == HttpStatus.SC_FORBIDDEN) {
				JSONArray groupsJSONArray = _groupDog.getGroupsJSONArray(
					dxpExtractorConfiguration, companyId, parentGroupId, name,
					site, -1, -1);

				return groupsJSONArray.length();
			}

			throw hcee;
		}
	}

	@Autowired
	private DXPExtractorConfigurationManagerImpl
		_dxpExtractorConfigurationManagerImpl;

	@Autowired
	private GroupDog _groupDog;

	@Autowired
	private UserDog _userDog;

}