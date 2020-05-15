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

import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorUserDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.dxp.extractor.client.ExtractorDXPClient;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;

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
@Component("com.liferay.osb.asah.dxp.extractor.dog.UserDog")
public class UserDog {

	public int getCompanyUsersCount(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId) {

		// TODO Test

		return _extractorDXPClient.getInt(
			dxpExtractorConfiguration,
			"/api/jsonws/user/get-company-users-count/company-id/" + companyId);
	}

	public JSONArray getCompanyUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		int start, int end) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$user = /user/get-company-users",
			JSONUtil.put(
				"$contact = /contact/get-contact",
				JSONUtil.put("@contactId", "$user.contactId")
			).put(
				"companyId", companyId
			).put(
				"end", end
			).put(
				"start", start
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		JSONArray usersJSONArray = _processGenderField(
			_extractorDXPClient.getJSONArray(
				dxpExtractorConfiguration, "/api/jsonws/invoke",
				bodyJSONObject));

		if (_log.isInfoEnabled()) {
			_log.info("Return " + usersJSONArray.length() + " users");
		}

		return usersJSONArray;
	}

	public long getCurrentUserCompanyId(
		DXPExtractorConfiguration dxpExtractorConfiguration) {

		JSONObject currentUserJSONObject = getCurrentUserJSONObject(
			dxpExtractorConfiguration);

		return currentUserJSONObject.getLong("companyId");
	}

	public JSONObject getCurrentUserJSONObject(
		DXPExtractorConfiguration dxpExtractorConfiguration) {

		return _dxpExtractorUserDog.processGenderField(
			_extractorDXPClient.getJSONObject(
				dxpExtractorConfiguration,
				"/api/jsonws/user/get-current-user"));
	}

	public JSONObject getCurrentUserJSONObject(String json) {
		JSONObject dataSourceJSONObject = new JSONObject(json);

		return _dxpExtractorUserDog.processGenderField(
			_extractorDXPClient.getJSONObject(
				dataSourceJSONObject, "/api/jsonws/user/get-current-user"));
	}

	public JSONArray getGtCompanyUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long gtUserId,
		long companyId, int size) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$user = /user/get-gt-company-users",
			JSONUtil.put(
				"$contact = /contact/get-contact",
				JSONUtil.put("@contactId", "$user.contactId")
			).put(
				"companyId", companyId
			).put(
				"gtUserId", gtUserId
			).put(
				"size", size
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		JSONArray usersJSONArray = _processGenderField(
			_extractorDXPClient.getJSONArray(
				dxpExtractorConfiguration, "/api/jsonws/invoke",
				bodyJSONObject));

		if (_log.isInfoEnabled()) {
			_log.info("Return " + usersJSONArray.length() + " users");
		}

		return usersJSONArray;
	}

	public JSONArray getGtOrganizationUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long gtUserId,
		long organizationId, int size) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$user = /user/get-gt-organization-users",
			JSONUtil.put(
				"$contact = /contact/get-contact",
				JSONUtil.put("@contactId", "$user.contactId")
			).put(
				"gtUserId", gtUserId
			).put(
				"organizationId", organizationId
			).put(
				"size", size
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		JSONArray usersJSONArray = _processGenderField(
			_extractorDXPClient.getJSONArray(
				dxpExtractorConfiguration, "/api/jsonws/invoke",
				bodyJSONObject));

		if (_log.isInfoEnabled()) {
			_log.info("Return " + usersJSONArray.length() + " users");
		}

		return usersJSONArray;
	}

	public JSONArray getGtUserGroupUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long gtUserId,
		long userGroupId, int size) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$user = /user/get-gt-user-group-users",
			JSONUtil.put(
				"$contact = /contact/get-contact",
				JSONUtil.put("@contactId", "$user.contactId")
			).put(
				"gtUserId", gtUserId
			).put(
				"size", size
			).put(
				"userGroupId", userGroupId
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		JSONArray usersJSONArray = _processGenderField(
			_extractorDXPClient.getJSONArray(
				dxpExtractorConfiguration, "/api/jsonws/invoke",
				bodyJSONObject));

		if (_log.isInfoEnabled()) {
			_log.info("Return " + usersJSONArray.length() + " users");
		}

		return usersJSONArray;
	}

	public int getOrganizationsAndUserGroupsUsersCount(
		DXPExtractorConfiguration dxpExtractorConfiguration,
		long[] organizationIds, long[] userGroupIds) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"/user/get-organizations-and-user-groups-users-count",
			JSONUtil.put(
				"organizationIds", organizationIds
			).put(
				"userGroupIds", userGroupIds
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		return _extractorDXPClient.getInt(
			dxpExtractorConfiguration, "/api/jsonws/invoke", bodyJSONObject);
	}

	public JSONArray getOrganizationUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration,
		long organizationId, int start, int end) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$user = /user/get-organization-users.5",
			JSONUtil.put(
				"$contact = /contact/get-contact",
				JSONUtil.put("@contactId", "$user.contactId")
			).put(
				"end", end
			).put(
				"organizationId", organizationId
			).put(
				"start", start
			).put(
				"status", -1
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		JSONArray usersJSONArray = _processGenderField(
			_extractorDXPClient.getJSONArray(
				dxpExtractorConfiguration, "/api/jsonws/invoke",
				bodyJSONObject));

		if (_log.isInfoEnabled()) {
			_log.info("Return " + usersJSONArray.length() + " users");
		}

		return usersJSONArray;
	}

	public JSONArray getUserGroupUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long userGroupId,
		int start, int end) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$user = /user/get-user-group-users",
			JSONUtil.put(
				"$contact = /contact/get-contact",
				JSONUtil.put("@contactId", "$user.contactId")
			).put(
				"end", end
			).put(
				"start", start
			).put(
				"userGroupId", userGroupId
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		JSONArray usersJSONArray = _processGenderField(
			_extractorDXPClient.getJSONArray(
				dxpExtractorConfiguration, "/api/jsonws/invoke",
				bodyJSONObject));

		if (_log.isInfoEnabled()) {
			_log.info("Return " + usersJSONArray.length() + " users");
		}

		return usersJSONArray;
	}

	public JSONObject getUserJSONObject(
		DXPExtractorConfiguration dxpExtractorConfiguration, long userId) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$user = /user/get-user-by-id",
			JSONUtil.put(
				"$contact = /contact/get-contact",
				JSONUtil.put("@contactId", "$user.contactId")
			).put(
				"userId", userId
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		return _dxpExtractorUserDog.processGenderField(
			_extractorDXPClient.getJSONObject(
				dxpExtractorConfiguration, "/api/jsonws/invoke",
				bodyJSONObject));
	}

	private JSONArray _processGenderField(JSONArray usersJSONArray) {
		for (int i = 0; i < usersJSONArray.length(); i++) {
			_dxpExtractorUserDog.processGenderField(
				usersJSONArray.getJSONObject(i));
		}

		return usersJSONArray;
	}

	private static final Log _log = LogFactory.getLog(UserDog.class);

	@Autowired
	private DXPExtractorUserDog _dxpExtractorUserDog;

	@Autowired
	private ExtractorDXPClient _extractorDXPClient;

}