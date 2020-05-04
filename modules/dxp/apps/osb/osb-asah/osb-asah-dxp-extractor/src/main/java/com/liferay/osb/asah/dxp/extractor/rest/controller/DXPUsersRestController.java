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
import com.liferay.osb.asah.dxp.extractor.dog.OrganizationDog;
import com.liferay.osb.asah.dxp.extractor.dog.UserDog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@RequestMapping("/dxp-users")
@RestController
public class DXPUsersRestController {

	@GetMapping
	public String get(@RequestBody String json) {
		JSONObject jsonObject = _userDog.getCurrentUserJSONObject(json);

		if (jsonObject == null) {
			return null;
		}

		return jsonObject.toString();
	}

	@GetMapping("/fields")
	public String getFields(
		@RequestParam String dataSourceId,
		@RequestParam(defaultValue = "20") int end,
		@RequestParam(defaultValue = "0") int start) {

		JSONArray userFieldsJSONArray = new JSONArray();

		DXPExtractorConfiguration dxpExtractorConfiguration =
			_dxpExtractorConfigurationManagerImpl.getConfiguration(
				dataSourceId);

		JSONArray usersJSONArray = _getUsersJSONArray(
			dxpExtractorConfiguration, start, end);

		Map<String, Set<String>> fieldValuesMap = _getFieldValuesMap(
			usersJSONArray);

		for (Map.Entry<String, Set<String>> entry : fieldValuesMap.entrySet()) {
			userFieldsJSONArray.put(
				JSONUtil.put(
					"name", entry.getKey()
				).put(
					"values", entry.getValue()
				));
		}

		return userFieldsJSONArray.toString();
	}

	@GetMapping("/total")
	public String getTotal(
			@RequestParam String dataSourceId,
			@RequestBody(required = false) String json)
		throws Exception {

		DXPExtractorConfiguration dxpExtractorConfiguration =
			_dxpExtractorConfigurationManagerImpl.getConfiguration(
				dataSourceId);

		long companyId = _userDog.getCurrentUserCompanyId(
			dxpExtractorConfiguration);

		if (StringUtils.isEmpty(json)) {
			if (dxpExtractorConfiguration.isSyncAllUsers()) {
				return String.valueOf(
					_userDog.getCompanyUsersCount(
						dxpExtractorConfiguration, companyId));
			}

			Set<Long> syncOrganizationIds =
				dxpExtractorConfiguration.getSyncOrganizationIds();
			Set<Long> syncUserGroupIds =
				dxpExtractorConfiguration.getSyncUserGroupIds();

			return String.valueOf(
				_userDog.getOrganizationsAndUserGroupsUsersCount(
					dxpExtractorConfiguration,
					ArrayUtils.toPrimitive(
						syncOrganizationIds.toArray(new Long[0])),
					ArrayUtils.toPrimitive(
						syncUserGroupIds.toArray(new Long[0]))));
		}

		JSONObject contactsConfigurationJSONObject = new JSONObject(json);

		if (contactsConfigurationJSONObject.getBoolean("enableAllContacts")) {
			return String.valueOf(
				_userDog.getCompanyUsersCount(
					dxpExtractorConfiguration, companyId));
		}

		Set<Long> organizationIds = new HashSet<>();

		JSONArray organizationsJSONArray =
			contactsConfigurationJSONObject.getJSONArray("organizations");

		for (int i = 0; i < organizationsJSONArray.length(); i++) {
			JSONObject organizationJSONObject =
				organizationsJSONArray.getJSONObject(i);

			long organizationId = organizationJSONObject.getLong("id");

			organizationIds.add(organizationId);

			if (organizationJSONObject.optBoolean("enableAllChildren", false)) {
				_organizationDog.getDescendantOrganizationIds(
					dxpExtractorConfiguration, organizationIds, companyId,
					organizationId);
			}
		}

		JSONArray userGroupsJSONArray =
			contactsConfigurationJSONObject.getJSONArray("userGroups");

		return String.valueOf(
			_userDog.getOrganizationsAndUserGroupsUsersCount(
				dxpExtractorConfiguration,
				ArrayUtils.toPrimitive(organizationIds.toArray(new Long[0])),
				JSONUtil.toLongArray(userGroupsJSONArray, "id")));
	}

	private Map<String, Set<String>> _getFieldValuesMap(
		JSONArray usersJSONArray) {

		Map<String, Set<String>> fieldValuesMap = new HashMap<>();

		for (int i = 0; i < usersJSONArray.length(); i++) {
			JSONObject userJSONObject = usersJSONArray.getJSONObject(i);

			JSONObject contactJSONObject = userJSONObject.getJSONObject(
				"contact");

			Iterator<String> keys = contactJSONObject.keys();

			while (keys.hasNext()) {
				Set<String> values = null;

				String key = keys.next();

				if (fieldValuesMap.containsKey(key)) {
					values = fieldValuesMap.get(key);
				}
				else {
					values = new HashSet<>();

					fieldValuesMap.put(key, values);
				}

				String value = String.valueOf(contactJSONObject.get(key));

				if (!StringUtils.isBlank(value)) {
					values.add(value);
				}
			}
		}

		return fieldValuesMap;
	}

	private JSONArray _getUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, int start,
		int end) {

		if (dxpExtractorConfiguration.isSyncAllUsers()) {
			long companyId = _userDog.getCurrentUserCompanyId(
				dxpExtractorConfiguration);

			return _userDog.getCompanyUsersJSONArray(
				dxpExtractorConfiguration, companyId, start, end);
		}

		JSONArray usersJSONArray = new JSONArray();

		for (long organizationId :
				dxpExtractorConfiguration.getSyncOrganizationIds()) {

			if (usersJSONArray.length() >= (end - start)) {
				break;
			}

			JSONArray organizationUsersJSONArray =
				_userDog.getOrganizationUsersJSONArray(
					dxpExtractorConfiguration, organizationId, start, end);

			for (int i = 0; i < organizationUsersJSONArray.length(); i++) {
				usersJSONArray.put(organizationUsersJSONArray.getJSONObject(i));
			}
		}

		for (long userGroupId :
				dxpExtractorConfiguration.getSyncUserGroupIds()) {

			if (usersJSONArray.length() >= (end - start)) {
				break;
			}

			JSONArray userGroupUsersJSONArray =
				_userDog.getUserGroupUsersJSONArray(
					dxpExtractorConfiguration, userGroupId, start, end);

			for (int i = 0; i < userGroupUsersJSONArray.length(); i++) {
				usersJSONArray.put(userGroupUsersJSONArray.getJSONObject(i));
			}
		}

		return usersJSONArray;
	}

	@Autowired
	private DXPExtractorConfigurationManagerImpl
		_dxpExtractorConfigurationManagerImpl;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private UserDog _userDog;

}