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

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.dxp.extractor.client.ExtractorDXPClient;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@Component
public class PortalDog {

	public JSONObject removeCompanyPreferences(String json) {
		JSONObject dataSourceJSONObject = new JSONObject(json);

		JSONObject propertiesJSONObject = JSONUtil.put(
			"liferayAnalyticsDataSourceId", ""
		).put(
			"liferayAnalyticsEnableAllGroupIds", ""
		).put(
			"liferayAnalyticsEndpointURL", ""
		).put(
			"liferayAnalyticsFaroBackendSecuritySignature", ""
		).put(
			"liferayAnalyticsFaroBackendURL", ""
		).put(
			"liferayAnalyticsGroupIds", ""
		).put(
			"liferayAnalyticsURL", ""
		);

		JSONObject bodyJSONObject = JSONUtil.put(
			"/company/update-preferences",
			JSONUtil.put(
				"companyId", _getCompanyId(dataSourceJSONObject)
			).put(
				"properties", propertiesJSONObject
			).put(
				"unicodeProperties", propertiesJSONObject
			));

		return _extractorDXPClient.postJSONObject(
			dataSourceJSONObject, "/api/jsonws/invoke", bodyJSONObject);
	}

	public JSONObject updateCompanyPreferences(String json) {
		JSONObject dataSourceJSONObject = new JSONObject(json);

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		JSONObject analyticsConfigurationJSONObject =
			providerJSONObject.optJSONObject("analyticsConfiguration");

		JSONObject propertiesJSONObject = JSONUtil.put(
			"liferayAnalyticsDataSourceId", dataSourceJSONObject.getString("id")
		).put(
			"liferayAnalyticsFaroBackendSecuritySignature",
			dataSourceJSONObject.getString("faroBackendSecuritySignature")
		).put(
			"liferayAnalyticsFaroBackendURL", ServiceConstants.URL_BACKEND
		).put(
			"liferayAnalyticsURL",
			dataSourceJSONObject.optString("workspaceURL")
		);

		_nanitesHttp.refreshAnalytics();

		if (StringUtils.isNotEmpty(
				_getLiferayAnalyticsGroupIds(
					analyticsConfigurationJSONObject)) ||
			_getLiferayAnalyticsEnableAllSites(
				analyticsConfigurationJSONObject)) {

			propertiesJSONObject.put(
				"liferayAnalyticsEnableAllGroupIds",
				String.valueOf(
					_getLiferayAnalyticsEnableAllSites(
						analyticsConfigurationJSONObject))
			).put(
				"liferayAnalyticsEndpointURL", ServiceConstants.URL_PUBLISHER
			).put(
				"liferayAnalyticsGroupIds",
				_getLiferayAnalyticsGroupIds(analyticsConfigurationJSONObject)
			);

			_faroInfoOSBAsahTaskDog.addOSBAsahTask("ActivitiesNanite", null);
			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"IndividualActivityFieldsNanite", null);
			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"IndividualSegmentActivityFieldsNanite", null);
		}
		else {
			propertiesJSONObject.put(
				"liferayAnalyticsEnableAllGroupIds", ""
			).put(
				"liferayAnalyticsEndpointURL", ""
			).put(
				"liferayAnalyticsGroupIds", ""
			);
		}

		JSONObject bodyJSONObject = JSONUtil.put(
			"/company/update-preferences",
			JSONUtil.put(
				"companyId", _getCompanyId(dataSourceJSONObject)
			).put(
				"properties", propertiesJSONObject
			).put(
				"unicodeProperties", propertiesJSONObject
			));

		return _extractorDXPClient.postJSONObject(
			dataSourceJSONObject, "/api/jsonws/invoke", bodyJSONObject);
	}

	private long _getCompanyId(JSONObject dataSourceJSONObject) {
		JSONObject currentUserJSONObject = _userDog.getCurrentUserJSONObject(
			dataSourceJSONObject.toString());

		return currentUserJSONObject.getLong("companyId");
	}

	private boolean _getLiferayAnalyticsEnableAllSites(
		JSONObject analyticsConfigurationJSONObject) {

		if (analyticsConfigurationJSONObject == null) {
			return false;
		}

		return analyticsConfigurationJSONObject.optBoolean("enableAllSites");
	}

	private String _getLiferayAnalyticsGroupIds(
		JSONObject analyticsConfigurationJSONObject) {

		if (analyticsConfigurationJSONObject == null) {
			return "";
		}

		JSONArray sitesJSONArray =
			analyticsConfigurationJSONObject.optJSONArray("sites");

		if (sitesJSONArray == null) {
			return "";
		}

		return String.join(",", JSONUtil.toStringList(sitesJSONArray, "id"));
	}

	@Autowired
	private ExtractorDXPClient _extractorDXPClient;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private NanitesHttp _nanitesHttp;

	@Autowired
	private UserDog _userDog;

}