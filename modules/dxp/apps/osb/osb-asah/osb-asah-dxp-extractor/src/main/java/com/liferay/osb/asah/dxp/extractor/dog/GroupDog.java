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
 * @author Rachael Koestartyo
 */
@Component
public class GroupDog {

	public JSONObject getGroupJSONObject(
		DXPExtractorConfiguration dxpExtractorConfiguration, long groupId) {

		return _extractorDXPClient.getJSONObject(
			dxpExtractorConfiguration,
			"/api/jsonws/group/get-group/group-id/" + groupId);
	}

	public int getGroupsCount(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		long parentGroupId, String name, boolean site) {

		return _extractorDXPClient.getInt(
			dxpExtractorConfiguration, "/group/get-groups-count",
			new HashMap<String, Object>() {
				{
					put("company-id", companyId);

					// See LPS-87209

					compute("name", (key, value) -> name);

					put("parent-group-id", parentGroupId);
					put("site", site);
				}
			});
	}

	public JSONArray getGroupsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		long parentGroupId, String name, boolean site, int start, int end) {

		JSONArray groupsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, "/group/get-groups",
			new HashMap<String, Object>() {
				{
					put("company-id", companyId);
					put("end", end);

					// See LPS-87209

					compute("name", (key, value) -> name);

					put("parent-group-id", parentGroupId);
					put("site", site);
					put("start", start);
				}
			});

		if (_log.isInfoEnabled()) {
			_log.info("Return " + groupsJSONArray.length() + " groups");
		}

		return groupsJSONArray;
	}

	public JSONArray getGroupsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long[] groupIds) {

		JSONArray bodyJSONArray = new JSONArray();

		for (long groupId : groupIds) {
			JSONObject jsonObject = JSONUtil.put(
				"/group/get-group", JSONUtil.put("groupId", groupId));

			bodyJSONArray.put(jsonObject);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONArray);
		}

		JSONArray groupsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, "/api/jsonws/invoke", bodyJSONArray);

		if (_log.isInfoEnabled()) {
			_log.info("Return " + groupsJSONArray.length() + " groups");
		}

		return groupsJSONArray;
	}

	public JSONArray getGtGroupsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long gtGroupId,
		long companyId, long parentGroupId, boolean site, int size) {

		JSONArray groupsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration,
			"/api/jsonws/group/get-gt-groups/gt-group-id/" + gtGroupId +
				"/company-id/" + companyId + "/parent-group-id/" +
					parentGroupId + "/site/" + site + "/size/" + size);

		if (_log.isInfoEnabled()) {
			_log.info("Return " + groupsJSONArray.length() + " groups");
		}

		return groupsJSONArray;
	}

	private static final Log _log = LogFactory.getLog(GroupDog.class);

	@Autowired
	private ExtractorDXPClient _extractorDXPClient;

}