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

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ZendeskOrganizationWebService.class)
public class ZendeskOrganizationWebServiceImpl
	implements ZendeskOrganizationWebService {

	public ZendeskOrganization getZendeskOrganization(long accountEntryId)
		throws PortalException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("external_id", String.valueOf(accountEntryId));

		JSONObject responseJSONObject = _zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "organizations/search.json",
			parameters);

		JSONArray organizationsJSONArray = responseJSONObject.getJSONArray(
			"organizations");

		if (organizationsJSONArray.length() <= 0) {
			return null;
		}

		return _translate(organizationsJSONArray.getJSONObject(0));
	}

	private ZendeskOrganization _translate(JSONObject jsonObject) {
		ZendeskOrganization zendeskOrganization = new ZendeskOrganization();

		zendeskOrganization.setZendeskOrganizationId(jsonObject.getLong("id"));
		zendeskOrganization.setName(jsonObject.getString("name"));

		return zendeskOrganization;
	}

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}