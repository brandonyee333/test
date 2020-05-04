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

package com.liferay.osb.asah.common.salesforce.extractor.dog;

import com.liferay.osb.asah.common.configuration.dog.BaseConfigurationDog;
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.http.DataSourceHttp;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SalesforceExtractorConfigurationDog extends BaseConfigurationDog {

	@Override
	public void setUpDataSource(JSONObject dataSourceJSONObject) {
	}

	@Override
	protected JSONObject buildConfigurationsJSONObject(
		JSONObject dataSourceJSONObject, JSONObject providerJSONObject) {

		JSONObject configurationsJSONObject =
			super.buildConfigurationsJSONObject(
				dataSourceJSONObject, providerJSONObject);

		return configurationsJSONObject.put(
			"accountsConfiguration",
			providerJSONObject.optJSONObject("accountsConfiguration")
		).put(
			"contactsConfiguration",
			providerJSONObject.optJSONObject("contactsConfiguration")
		);
	}

	@Override
	protected JSONObject buildOAuthOwnerJSONObject(
		JSONObject dataSourceJSONObject) {

		ResponseEntity<String> responseEntity =
			_dataSourceHttp.getSalesforceOwner(dataSourceJSONObject.toString());

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return new JSONObject(responseEntity.getBody());
		}

		return null;
	}

	@Override
	protected String getProviderType() {
		return "SALESFORCE";
	}

	@Override
	protected String getURL() {
		return ServiceConstants.URL_SALESFORCE_EXTRACTOR;
	}

	@Autowired
	private DataSourceHttp _dataSourceHttp;

}