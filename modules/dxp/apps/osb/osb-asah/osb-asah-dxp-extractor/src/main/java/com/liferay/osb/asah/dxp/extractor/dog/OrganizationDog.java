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

import com.liferay.osb.asah.common.json.JSONArrayPaginator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.dxp.extractor.client.ExtractorDXPClient;
import com.liferay.osb.asah.dxp.extractor.configuration.DXPExtractorConfiguration;

import java.util.HashMap;
import java.util.Set;

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
public class OrganizationDog {

	public void getDescendantOrganizationIds(
			DXPExtractorConfiguration dxpExtractorConfiguration,
			Set<Long> organizationIds, long companyId,
			long parentOrganizationId)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info(
				"Get organizations for company " + companyId +
					" and parent organization " + parentOrganizationId);
		}

		new JSONArrayPaginator() {

			@Override
			protected JSONArray paginate(int start, int end) throws Exception {
				JSONArray organizationsJSONArray = getOrganizationsJSONArray(
					dxpExtractorConfiguration, companyId, parentOrganizationId,
					start, end);

				for (int i = 0; i < organizationsJSONArray.length(); i++) {
					JSONObject organizationJSONObject =
						organizationsJSONArray.getJSONObject(i);

					long organizationId = organizationJSONObject.getLong(
						"organizationId");

					organizationIds.add(organizationId);

					getDescendantOrganizationIds(
						dxpExtractorConfiguration, organizationIds, companyId,
						organizationId);
				}

				return organizationsJSONArray;
			}

		};
	}

	public JSONArray getGtOrganizationsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration,
		long gtOrganizationId, long companyId, long parentOrganizationId,
		int size) {

		StringBuilder sb = new StringBuilder();

		sb.append("/api/jsonws/organization/get-gt-organizations");
		sb.append("/gt-organization-id/");
		sb.append(gtOrganizationId);
		sb.append("/company-id/");
		sb.append(companyId);
		sb.append("/parent-organization-id/");
		sb.append(parentOrganizationId);
		sb.append("/size/");
		sb.append(size);

		JSONArray organizationsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, sb.toString());

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + organizationsJSONArray.length() + " organizations");
		}

		return organizationsJSONArray;
	}

	public JSONObject getOrganizationJSONObject(
		DXPExtractorConfiguration dxpExtractorConfiguration,
		long organizationId) {

		return _extractorDXPClient.getJSONObject(
			dxpExtractorConfiguration,
			"/api/jsonws/organization/get-organization/organization-id/" +
				organizationId);
	}

	public int getOrganizationsCount(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		long parentOrganizationId, String name) {

		return _extractorDXPClient.getInt(
			dxpExtractorConfiguration, "/organization/get-organizations-count",
			new HashMap<String, Object>() {
				{
					put("company-id", companyId);

					// See LPS-87209

					compute("name", (key, value) -> name);

					put("parent-organization-id", parentOrganizationId);
				}
			});
	}

	public JSONArray getOrganizationsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		long parentOrganizationId, int start, int end) {

		JSONArray organizationsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration,
			"/api/jsonws/organization/get-organizations/company-id/" +
				companyId + "/parent-organization-id/" + parentOrganizationId +
					"/start/" + start + "/end/" + end);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + organizationsJSONArray.length() + " organizations");
		}

		return organizationsJSONArray;
	}

	public JSONArray getOrganizationsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration,
		long[] organizationIds) {

		JSONArray bodyJSONArray = new JSONArray();

		for (long organizationId : organizationIds) {
			JSONObject jsonObject = JSONUtil.put(
				"/organization/get-organization",
				JSONUtil.put(
					"$usersCount = /user/get-organization-users-count",
					JSONUtil.put(
						"organizationId", organizationId
					).put(
						"status", -1
					)
				).put(
					"organizationId", organizationId
				));

			bodyJSONArray.put(jsonObject);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONArray);
		}

		JSONArray organizationsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, "/api/jsonws/invoke", bodyJSONArray);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + organizationsJSONArray.length() + " organizations");
		}

		return organizationsJSONArray;
	}

	public JSONArray getOrganizationsOrganizationsCountJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long companyId,
		long parentOrganizationId, String name, int start, int end) {

		JSONObject bodyJSONObject = JSONUtil.put(
			"$organization = /organization/get-organizations",
			JSONUtil.put(
				"$organizationsCount = /organization/get-organizations-count",
				JSONUtil.put(
					"@companyId", "$organization.companyId"
				).put(
					"@parentOrganizationId", "$organization.organizationId"
				)
			).put(
				"$usersCount = /user/get-organization-users-count",
				JSONUtil.put(
					"@organizationId", "$organization.organizationId"
				).put(
					"status", -1
				)
			).put(
				"companyId", companyId
			).put(
				"end", end
			).putOpt(

				// See LPS-87209

				"name", name

			).put(
				"parentOrganizationId", parentOrganizationId
			).put(
				"start", start
			));

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONObject);
		}

		JSONArray organizationsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, "/api/jsonws/invoke", bodyJSONObject);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + organizationsJSONArray.length() + " organizations");
		}

		return organizationsJSONArray;
	}

	public JSONArray getOrganizationsUsersJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long gtUserId,
		int size) {

		JSONArray bodyJSONArray = new JSONArray();

		for (long organizationId :
				dxpExtractorConfiguration.getSyncOrganizationIds()) {

			try {
				JSONObject organizationJSONObject = getOrganizationJSONObject(
					dxpExtractorConfiguration, organizationId);

				if (organizationJSONObject == null) {
					continue;
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Skipping organization " + organizationId +
							" because it does not exist");
				}

				continue;
			}

			JSONObject jsonObject = JSONUtil.put(
				"/user/get-gt-organization-users",
				JSONUtil.put(
					"gtUserId", gtUserId
				).put(
					"organizationId", organizationId
				).put(
					"size", size
				));

			bodyJSONArray.put(jsonObject);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Body: " + bodyJSONArray);
		}

		return _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration, "/api/jsonws/invoke", bodyJSONArray);
	}

	public JSONArray getUserOrganizationsJSONArray(
		DXPExtractorConfiguration dxpExtractorConfiguration, long userId) {

		JSONArray organizationsJSONArray = _extractorDXPClient.getJSONArray(
			dxpExtractorConfiguration,
			"/api/jsonws/organization/get-user-organizations/userId/" + userId);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Return " + organizationsJSONArray.length() + " organizations");
		}

		return organizationsJSONArray;
	}

	private static final Log _log = LogFactory.getLog(OrganizationDog.class);

	@Autowired
	private ExtractorDXPClient _extractorDXPClient;

}