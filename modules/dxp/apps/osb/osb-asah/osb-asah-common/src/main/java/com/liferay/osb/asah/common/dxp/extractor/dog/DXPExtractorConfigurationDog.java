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

package com.liferay.osb.asah.common.dxp.extractor.dog;

import com.liferay.osb.asah.common.configuration.dog.BaseConfigurationDog;
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.http.PortalPreferencesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Objects;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 * @author Brian Wing Shun Chan
 */
@Component
public class DXPExtractorConfigurationDog extends BaseConfigurationDog {

	@Override
	public void setUpDataSource(JSONObject dataSourceJSONObject) {
		if (Objects.equals(
				dataSourceJSONObject.getString("state"), "CREDENTIALS_VALID") &&
			!_configureAnalyticsClient(dataSourceJSONObject)) {

			dataSourceJSONObject.put(
				"state", "ANALYTICS_CLIENT_CONFIGURATION_FAILURE");
		}
	}

	@Override
	protected JSONObject buildConfigurationsJSONObject(
		JSONObject dataSourceJSONObject, JSONObject providerJSONObject) {

		JSONObject configurationsJSONObject =
			super.buildConfigurationsJSONObject(
				dataSourceJSONObject, providerJSONObject);

		return configurationsJSONObject.put(
			"analyticsConfiguration",
			providerJSONObject.optJSONObject("analyticsConfiguration")
		).put(
			"contactsConfiguration",
			providerJSONObject.optJSONObject("contactsConfiguration")
		);
	}

	@Override
	protected JSONObject buildOAuthOwnerJSONObject(
		JSONObject dataSourceJSONObject) {

		JSONObject currentUserJSONObject = _getCurrentUserJSONObject(
			dataSourceJSONObject);

		if (currentUserJSONObject == null) {
			return null;
		}

		return JSONUtil.put(
			"emailAddress", currentUserJSONObject.getString("emailAddress")
		).put(
			"name",
			currentUserJSONObject.getString("firstName") + " " +
				currentUserJSONObject.getString("lastName")
		);
	}

	@Override
	protected String getProviderType() {
		return "LIFERAY";
	}

	@Override
	protected String getURL() {
		return ServiceConstants.URL_DXP_EXTRACTOR;
	}

	@Override
	protected void updateConfiguration(
		JSONObject dataSourceJSONObject, String state) {

		if (Objects.equals(state, "DUMMY_CREDENTIALS")) {
			_nanitesHttp.refreshAnalytics();

			_faroInfoOSBAsahTaskDog.addOSBAsahTask("ActivitiesNanite", null);
			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"IndividualActivityFieldsNanite", null);
			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"IndividualSegmentActivityFieldsNanite", null);

			return;
		}

		super.updateConfiguration(dataSourceJSONObject, state);
	}

	private boolean _configureAnalyticsClient(JSONObject dataSourceJSONObject) {
		dataSourceJSONObject.put(
			"faroBackendSecuritySignature", String.valueOf(UUID.randomUUID()));

		ResponseEntity<String> responseEntity =
			_portalPreferencesHttp.updatePortalPreferences(
				dataSourceJSONObject);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return true;
		}

		_log.error("Unable to configure analytics client");

		return false;
	}

	private JSONObject _getCurrentUserJSONObject(
		JSONObject dataSourceJSONObject) {

		ResponseEntity<String> responseEntity = _dataSourceHttp.getDXPOwner(
			dataSourceJSONObject.toString());

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return new JSONObject(responseEntity.getBody());
		}

		return null;
	}

	private static final Log _log = LogFactory.getLog(
		DXPExtractorConfigurationDog.class);

	@Autowired
	private DataSourceHttp _dataSourceHttp;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private NanitesHttp _nanitesHttp;

	@Autowired
	private PortalPreferencesHttp _portalPreferencesHttp;

}