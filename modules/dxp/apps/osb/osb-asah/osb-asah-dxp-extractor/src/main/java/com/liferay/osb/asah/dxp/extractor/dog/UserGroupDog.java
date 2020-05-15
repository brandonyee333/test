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

package com.liferay.osb.asah.dxp.extractor.dog;

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.dxp.extractor.client.ExtractorDXPClient;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@Component
public class UserGroupDog {

	public JSONArray getGtUserGroupsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long gtUserGroupId,
		long companyId, long parentUserGroupId, int size) {

		JSONArray userGroupsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration,
			"/api/jsonws/usergroup/get-gt-user-groups/gt-user-group-id/" +
				gtUserGroupId + "/company-id/" + companyId +
					"/parent-user-group-id/" + parentUserGroupId + "/size/" +
						size);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + userGroupsJSONArray.length() + " user groups");
		}

		return userGroupsJSONArray;
	}

	public JSONObject getUserGroupJSONObject(
		DXPExtractorConfiguration dxpExtractorConfiguration, long userGroupId) {

		return _extractorDXPClient.getJSONObject(
			dxpExtractorConfiguration,
			"/api/jsonws/usergroup/get-user-group/user-group-id/" +
				userGroupId);
	}

	public int getUserGroupsCount(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		String name) {

		return _extractorDXPClient.getInt(
			dxpExtractorConfiguration, "/usergroup/get-user-groups-count",
			new HashMap<String, Object>() {
				{
					put("company-id", companyId);
					put("name", name);
				}
			});
	}

	public JSONArray getUserGroupsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		String name, int start, int end) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$userGroup = /usergroup/get-user-groups",
			JSONUtil.put(
				"$usersCount = /user" +
					"/get-organizations-and-user-groups-users-count.2",
				JSONUtil.put("@userGroupIds", "$userGroup.userGroupId")
			).put(
				"companyId", companyId
			).put(
				"end", end
			).put(
				"name", name
			).put(
				"start", start
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		JSONArray userGroupsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, "/api/jsonws/invoke", bodyJSONObject);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + userGroupsJSONArray.length() + " user groups");
		}

		return userGroupsJSONArray;
	}

	public JSONArray getUserGroupsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration,
		long[] userGroupIds) {

		JSONArray bodyJSONArray = new JSONArray();

		for (long userGroupId : userGroupIds) {
			JSONObject jsonObject = JSONUtil.put(
				"/usergroup/get-user-group",
				JSONUtil.put(
					"$usersCount = /user" +
						"/get-organizations-and-user-groups-users-count.2",
					JSONUtil.put("userGroupIds", userGroupId)
				).put(
					"userGroupId", userGroupId
				));

			bodyJSONArray.put(jsonObject);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONArray);
		}

		JSONArray userGroupsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, "/api/jsonws/invoke", bodyJSONArray);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + userGroupsJSONArray.length() + " user groups");
		}

		return userGroupsJSONArray;
	}

	public JSONArray getUserGroupsUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long gtUserId,
		int size) {

		JSONArray bodyJSONArray = new JSONArray();

		for (long userGroupId :
				dxpExtractorConfiguration.getSyncUserGroupIds()) {

			try {
				JSONObject userGroupJSONObject = getUserGroupJSONObject(
					dxpExtractorConfiguration, userGroupId);

				if (userGroupJSONObject == null) {
					continue;
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Skipping user group " + userGroupId +
							" because it does not exist");
				}

				continue;
			}

			JSONObject jsonObject = JSONUtil.put(
				"/user/get-gt-user-group-users",
				JSONUtil.put(
					"gtUserId", gtUserId
				).put(
					"size", size
				).put(
					"userGroupId", userGroupId
				));

			bodyJSONArray.put(jsonObject);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONArray);
		}

		return _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, "/api/jsonws/invoke", bodyJSONArray);
	}

	public JSONArray getUserUserGroupsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long userId) {

		JSONArray userGroupsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration,
			"/api/jsonws/usergroup/get-user-user-groups/userId/" + userId);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + userGroupsJSONArray.length() + " user groups");
		}

		return userGroupsJSONArray;
	}

	private static final Log _log = LogFactory.getLog(UserGroupDog.class);

	@Autowired
	private ExtractorDXPClient _extractorDXPClient;

}