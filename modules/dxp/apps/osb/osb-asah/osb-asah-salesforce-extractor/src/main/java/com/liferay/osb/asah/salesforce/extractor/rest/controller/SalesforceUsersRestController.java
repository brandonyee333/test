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

package com.liferay.osb.asah.salesforce.extractor.rest.controller;

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.salesforce.extractor.client.SalesforcePartnerClientInvoker;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorConfigurationManagerImpl;

import com.sforce.soap.partner.GetUserInfoResult;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@RequestMapping("/salesforce-users")
@RestController
public class SalesforceUsersRestController extends BaseRestController {

	@GetMapping
	public String get(@RequestBody String json) throws Exception {
		JSONObject dataSourceJSONObject = new JSONObject(json);

		GetUserInfoResult getUserInfoResult =
			_salesforcePartnerClientInvoker.getUserInfoResult(
				_salesforceExtractorConfigurationManagerImpl.getConfiguration(
					dataSourceJSONObject.getString("id")));

		return String.valueOf(
			JSONUtil.put(
				"emailAddress", getUserInfoResult.getUserEmail()
			).put(
				"name", getUserInfoResult.getUserFullName()
			));
	}

	@GetMapping("/fields")
	public String getFields(
		@RequestParam String dataSourceId,
		@RequestParam(defaultValue = "20") int end,
		@RequestParam(defaultValue = "0") int start) {

		return getFields("individuals", dataSourceId, end, start);
	}

	@Autowired
	private SalesforceExtractorConfigurationManagerImpl
		_salesforceExtractorConfigurationManagerImpl;

	@Autowired
	private SalesforcePartnerClientInvoker _salesforcePartnerClientInvoker;

}